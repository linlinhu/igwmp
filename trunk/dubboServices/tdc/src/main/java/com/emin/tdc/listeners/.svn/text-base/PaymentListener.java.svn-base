package com.emin.tdc.listeners;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emin.tdc.redis.RedisDao;

@Component("paymentListener")
public class PaymentListener {

	@Autowired
	private RedisDao redisDao;
	
	public void handleMessage(Serializable message) {  
       
		System.out.println("支付:"+message);
		String payment = redisDao.get("Payment", message.toString());
		System.out.println(payment);
    }  

}
