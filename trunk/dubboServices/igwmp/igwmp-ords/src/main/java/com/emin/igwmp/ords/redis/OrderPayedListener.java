package com.emin.igwmp.ords.redis;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emin.igwmp.ords.domain.GivenStatus;
import com.emin.igwmp.ords.domain.Order;
import com.emin.igwmp.ords.domain.OrderItem;
import com.emin.igwmp.ords.domain.OrderStatus;
import com.emin.igwmp.ords.service.OrderService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONObject;

@Component("orderPayedListener")
public class OrderPayedListener {
	
	private Logger logger = LoggerFactory.getLogger(OrderPayedListener.class);

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private RedisDao redisDao;
	
	/**
	 * 订单支付成功后将redis中的Order写入数据库 并从redis中移除
	 * @param message
	 */
	@SuppressWarnings("unchecked")
	public void handleMessage(Serializable message) {
		logger.info("订单支付成功!进入监听器处理");
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String,Object> log = null;
		try {
			log = objectMapper.readValue(message.toString(), Map.class);
			String paymentType = log.get("paymentType").toString();
			String orderNumber = log.get("orderNumber").toString();
			logger.info("相关订单号:"+log.get("orderNumber").toString());
			logger.info("支付方式:"+log.get("paymentType").toString());
			String orderJSON = redisDao.get("Order", "ORDER_"+log.get("orderNumber").toString());
			
			if(StringUtils.isBlank(orderJSON)){
				return;
			}
			Order order= null;
			
			order = objectMapper.readValue(orderJSON, Order.class);
			order.setOrderStatus(OrderStatus.TOBETAKE);
			order.setGivenStatus(GivenStatus.NORMAL);
			order.setCreateTime(System.currentTimeMillis());
			order.setLastModifyTime(System.currentTimeMillis());
			order.setPayedMoney(Double.valueOf(log.get("payedAmount").toString())/100);
			order.setId(null);
			for(OrderItem item : order.getItems()){
				item.setId(null);
				item.setOrderId(null);
			}
			if(paymentType.equals("wx_pub_qr")){
				//扫码支付，回填下单方信息
				logger.info("扫码支付人信息:"+log.get("fans").toString());
				JSONObject json = JSONObject.fromObject(log.get("fans").toString());				
				order.setVendeeInfo(json);
				order.setVendeeId(json.getLong("id"));
			}
			orderService.save(order);
			logger.info("保存到数据库");
			redisDao.delete("Order", "ORDER_"+orderNumber);
			logger.info("从Redis移除支付成功的订单："+orderNumber);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
    }  

}
