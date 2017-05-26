package com.emin.tdc.listeners;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emin.tdc.redis.RedisDao;

@Component("orderListener")
public class OrderListener {

	@Autowired
	private RedisDao redisDao;
	
	public void handleMessage(Serializable message) {  
        //什么都不做,只输出  
       /* if(message == null){  
            System.out.println("null");  
        } else if(message.getClass().isArray()){  
            System.out.println(Arrays.toString((Object[]) message));  
        } else if(message instanceof List<?>) {  
            System.out.println(message);  
        } else if(message instanceof Map<? , ?>) {  
            System.out.println(message);  
        }else if(message instanceof Order){
        	Order o = (Order)message;
        	System.out.println(o.getNumber()+"==="+o.getRemark());
        }
        else {  
            System.out.println(ToStringBuilder.reflectionToString(message));  
            System.out.println(message);  
        }*/ 
		System.out.println(ToStringBuilder.reflectionToString(message));
		System.out.println("订单:"+message);
		String order = redisDao.get("Order", message.toString());
		System.out.println(order);
    }  

}
