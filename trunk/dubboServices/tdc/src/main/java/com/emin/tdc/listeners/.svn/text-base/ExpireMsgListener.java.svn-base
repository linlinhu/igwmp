package com.emin.tdc.listeners;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emin.base.log.BussLog;
import com.emin.tdc.facade.callers.TradingCenterToOrderCaller;
import com.emin.tdc.facade.callers.TradingCenterToPMSCaller;
import com.emin.tdc.redis.RedisDao;

import net.sf.json.JSONObject;
@Component("expireListener")
public class ExpireMsgListener{

	@Autowired
	private TradingCenterToPMSCaller tradingCenterToPMSCaller;
	@Autowired
	private TradingCenterToOrderCaller tradingCenterToOrderCaller;
	@Autowired
	private RedisDao redisDao;
	
	@BussLog(description="Redis Key过期监听")
	public void handleMessage(Serializable message) { 
		String key = message.toString();
		String[] objKey = key.split("_");
		if(objKey.length<2){
			return;
		}
		String number = objKey[1];
		if(objKey[0].equalsIgnoreCase("PAYMENT")){
			
			String orderNumber = tradingCenterToPMSCaller.getOrderNumerByPaymentNumber(number);
			//告知订单服务此订单交易异常
			JSONObject order = new JSONObject();
			order.put("orderNumber", orderNumber);
			order.put("exception","paymentTimeout");
			redisDao.sendMessage("orderexception", order.toString());
			//告知支付服务此支付过期
			redisDao.sendMessage("payedexpire", number);
		}else if(objKey[0].equalsIgnoreCase("GIVING")){
			//赠送订单过期
			tradingCenterToOrderCaller.cancelGiveOrder(number);
			
		}else if(objKey[0].equalsIgnoreCase("TAKEWINE")){
			//取酒码过期
		}else if(objKey[0].equalsIgnoreCase("WAITINGORDER")){
			//订单上报超时
			System.out.println("订单上报超时:"+number);
			JSONObject order = new JSONObject();
			order.put("orderNumber", number);
			order.put("exception","waitingTimout");
			redisDao.sendMessage("orderexception", order.toString());
		}
    }  
}
