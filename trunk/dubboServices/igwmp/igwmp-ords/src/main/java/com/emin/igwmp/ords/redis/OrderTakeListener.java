package com.emin.igwmp.ords.redis;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emin.igwmp.ords.domain.Order;
import com.emin.igwmp.ords.domain.OrderStatus;
import com.emin.igwmp.ords.service.OrderService;
import com.emin.igwmp.ords.util.EminPropertyPlaceholderConfigurer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component("orderTakeListener")
public class OrderTakeListener {

	private Logger logger = LoggerFactory.getLogger(OrderTakeListener.class);
	
	@Autowired
	private OrderService orderService;
	/**
	 * 接收取酒完成的通知 进行状态更新
	 * @param message
	 * @throws Exception 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public void handleMessage(Serializable message) throws Exception {  
		logger.info("订单取酒完成:"+message.toString());
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode msg = mapper.readValue(message.toString(), ObjectNode.class);
		String orderNumber = msg.get("orderNumber").textValue();
		Order order = orderService.findOrderByOrderNumber(orderNumber);
		boolean success = msg.get("success").booleanValue();
		order.setFininshTime(System.currentTimeMillis());
		if(success){
			order.setOrderStatus(OrderStatus.TOOK);
		}else{
			
			//取酒异常
			int actual = msg.get("actualTakeMl").intValue();
			int should = msg.get("shouldTakeMl").intValue();
			String ml = EminPropertyPlaceholderConfigurer.getContextProperty("order.minML");
			int minML = Integer.parseInt(ml);
			if(should-actual<minML){
				//小于出酒最小量 不补单
				order.setOrderStatus(OrderStatus.FAKE_EXCEPTION);
			}else{
				//补单
				orderService.makeUpForExceptionOrder(orderNumber, should-actual);
				order.setOrderStatus(OrderStatus.EXCEPTION);
			}
			
			
		}
		orderService.update(order);
    }  

}
