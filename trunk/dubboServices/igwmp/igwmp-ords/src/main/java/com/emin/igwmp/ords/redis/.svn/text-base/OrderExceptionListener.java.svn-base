package com.emin.igwmp.ords.redis;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emin.igwmp.ords.domain.Order;
import com.emin.igwmp.ords.domain.OrderStatus;
import com.emin.igwmp.ords.service.OrderService;

import net.sf.json.JSONObject;

@Component("orderExceptionListener")
public class OrderExceptionListener {

	@Autowired
	private RedisDao redisDao;
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 订单支付取消或者失败 将order中redis中移除
	 * @param message
	 */
	public void handleMessage(Serializable message) {  
       JSONObject order = JSONObject.fromObject(message.toString());
       String exception = order.getString("exception");
       String orderNumber = order.getString("orderNumber");
       if(exception.equals("paymentTimeout")){
    	   //支付超时 直接删除redis中的订单
    	   redisDao.delete("Order", "ORDER_"+orderNumber);
       }else if(exception.equals("waitingTimeout")){
    	   //等待上报超时直接锁定订单不能进行任何操作
    	   Order o = orderService.findOrderByOrderNumber(orderNumber);
    	   if(o!=null && o.getId()!=null && o.getId()!=0){
    		   o.setOrderStatus(OrderStatus.LOCKED);
    		   orderService.update(o);
    	   }
       }
       
    }  

}
