package com.emin.tdc.facade.accepters.impl;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.emin.base.exception.EminException;
import com.emin.igwmp.ords.domain.Order;
import com.emin.igwmp.ords.domain.OrderItem;
import com.emin.igwmp.ords.facade.accepters.OrderAccepter;
import com.emin.igwmp.skm.facade.accepters.OrderNoticeAccepter;
import com.emin.igwmp.tws.domain.TakeWineRecord;
import com.emin.igwmp.tws.domain.TakeWineStatus;
import com.emin.igwmp.tws.facade.accepters.TakeWineRecordAccepter;
import com.emin.tdc.domain.TakeWineAction;
import com.emin.tdc.exception.TdcExceptionCode;
import com.emin.tdc.facade.accepters.TradingCenterAccepter;
import com.emin.tdc.redis.RedisDao;
import com.emin.tdc.util.EminPropertyPlaceholderConfigurer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import net.sf.json.JSONObject;
@Component("tradingCenterAccepter")
@Service(version="0.0.1")
@EnableScheduling
public class TradingCenterAccepterImpl implements TradingCenterAccepter {

	private Logger logger = LoggerFactory.getLogger(TradingCenterAccepterImpl.class);
	
	@Autowired
	private RedisDao redisDao;
	@Reference(version="0.0.1")
	private OrderNoticeAccepter orderNoticeAccepter;
	@Reference(version="0.0.1")
	private TakeWineRecordAccepter takeWineRecordAccepter;
	@Reference(version="0.0.1")
	private OrderAccepter orderAccepter;
	
	@Override
	public void paymentSuccess(String paymentNumber,Integer payedAmount) throws EminException, Exception{
		if(StringUtils.isBlank(paymentNumber)){
			throw new EminException(TdcExceptionCode.PARAMTER_INVALID);
		}
		String orderNumber = redisDao.get("Payment", "PAYMENT_"+paymentNumber.replace("_", "-"));
		if(StringUtils.isBlank("paymentJson")){
			throw new EminException(TdcExceptionCode.UNKNOW_PAYMENTNUMBER);
		}
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode node = objectMapper.createObjectNode();
		node.put("orderNumber", orderNumber);
		node.put("paymentNumber", paymentNumber);
		node.put("paymentType", "wx_pub");
		node.put("payedAmount",payedAmount);
		String message = objectMapper.writeValueAsString(node);
		//通知支付服务 此支付单完成
		redisDao.sendMessage("paymentsuccess",message);
		//通知订单服务 此订单支付完成
		redisDao.sendMessage("orderpayed", message);
			
	}
	
	@Override
	public void paymentSuccess(String paymentNumber,Integer payedAmount,String machineTag,JSONObject fans) throws EminException, Exception{
		logger.info("支付完成的交易号:"+paymentNumber);
		String orderNumber = redisDao.get("Payment", "PAYMENT_"+paymentNumber.replace("_", "-"));
		if(StringUtils.isBlank(orderNumber)){
			throw new EminException(TdcExceptionCode.UNKNOW_PAYMENTNUMBER);
		}
		logger.info("支付完成的订单号:"+orderNumber);
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode node = objectMapper.createObjectNode();
		node.put("orderNumber", orderNumber);
		node.put("paymentNumber", paymentNumber);
		node.put("paymentType", "wx_pub_qr");
		node.put("payedAmount",payedAmount);
		node.put("fans",fans.toString());
		String message = objectMapper.writeValueAsString(node);
		//通知支付服务 此支付单完成
		redisDao.sendMessage("paymentsuccess",message);
		//通知订单服务 此订单支付完成
		redisDao.sendMessage("orderpayed", message);
		Order order = orderAccepter.findOrderByNumber(orderNumber);
		Integer shouldTakeMl = 0;
		for(OrderItem item:order.getItems()){
			shouldTakeMl+=item.getCount();
		}
		//生成取酒码
		TakeWineRecord twr = takeWineRecordAccepter.createOrGetTakeWineRecord(orderNumber, shouldTakeMl);
		//调用推送服务
		logger.info("扫码支付完成推送订单到对应机柜");
		
		try {
			takeWineRecordAccepter.lockTakeCode(twr.getTakeCode());
			com.alibaba.fastjson.JSONObject json = orderNoticeAccepter.OrderDeliver(machineTag,orderNumber);
			boolean success = json.getBoolean("success");
			Integer code = json.getInteger("code");
		    logger.info("推送返回结果:"+success+",状态:"+code);
		    if(success){
		    	//推送成功加入超时验证等待
		    	addWaitingOrder(machineTag,orderNumber);
		    }
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			redisDao.put(machineTag,"UnsendOrder",orderNumber);
			logger.info("由于推送失败,机柜:"+machineTag+"的订单("+orderNumber+") 被存入缓存队列等待下次推送");
		}		
		
	}
	
	private void addWaitingOrder(String machineTag,String order){
		logger.info("机柜:"+machineTag+"的订单:"+order+"开始进入超时等待，过时不上报将锁定");
		redisDao.put("WAITINGORDER", "WAITINGORDER_"+order, machineTag);
		Long time = Long.valueOf(EminPropertyPlaceholderConfigurer.getContextProperty("tdc.waitingOrderTime"));
		TimeUnit timeUnit = TimeUnit.valueOf(EminPropertyPlaceholderConfigurer.getContextProperty("tdc.waitingOrderTimeUnit"));	
		redisDao.exipre("WAITINGORDER_"+order,time,timeUnit);
		logger.info("机柜:"+machineTag+"的订单:"+order+"开始进入上报等待，"+timeUnit.toMinutes(time)+"分钟内不上报将锁定");
	}
	
	@Scheduled(cron = "0/5 * * * * ?")
	public void reSendOrder(){
		
		Set<Object> keys = redisDao.query("UnsendOrder");
		if(keys.size()>0){
			logger.info("缓存队列中存在推送失败的订单");			
		}
		for (Object key : keys) {
			String machineTag = key.toString();
			String orderNumber = redisDao.get(machineTag,"UnsendOrder");
			logger.info("查找到需要推送的订单信息 机柜码："+machineTag+" 订单号:"+orderNumber);
			logger.info("开始推送...");
			try {
				com.alibaba.fastjson.JSONObject json = orderNoticeAccepter.OrderDeliver(machineTag,orderNumber);
				boolean success = json.getBoolean("success");
				Integer code = json.getInteger("code");
			    logger.info("推送返回结果:"+success+",状态吗:"+code);
			    if(success){
			    	redisDao.delete(machineTag,"UnsendOrder");
			    	logger.info("二次推送订单成功 机柜:"+machineTag+"的订单("+orderNumber+") 从缓存队列中移除");
			    	addWaitingOrder(machineTag,orderNumber);
			    }
			} catch (Exception e) {
				logger.info("推送服务调用失败，等待下次推送..");
				logger.error(e.getMessage(),e);		
			}		
		}
	}
	@Override
	public void takeWineComplete(String orderNumber,Integer actualTakeMl,String takeInfo,TakeWineAction takeWineAction){
		this.beforeTakeWineComplete(orderNumber, actualTakeMl, takeInfo, takeWineAction);
		JSONObject takeWineMsg = new JSONObject();
		takeWineMsg.put("orderNumber", orderNumber);
		takeWineMsg.put("actualTakeMl", actualTakeMl);
		takeWineMsg.put("takeInfo", takeInfo);
		takeWineMsg.put("status", takeWineAction.equals(TakeWineAction.SUCCESS)?TakeWineStatus.SUCCESS:TakeWineStatus.EXCEPTION);
		redisDao.sendMessage("takeresult", takeWineMsg.toString());
	}
	
	private void beforeTakeWineComplete(String orderNumber,Integer actualTakeMl,String takeInfo,TakeWineAction takeWineAction){
		if(StringUtils.isBlank(orderNumber)
				||actualTakeMl==null
				||actualTakeMl.intValue()==0
				||StringUtils.isBlank(takeInfo)
				||takeWineAction==null){
			throw new EminException(TdcExceptionCode.PARAMTER_INVALID);
		}
	}
}
