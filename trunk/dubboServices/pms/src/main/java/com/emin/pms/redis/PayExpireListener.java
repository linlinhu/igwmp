package com.emin.pms.redis;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.emin.pms.domain.PayerType;
import com.emin.pms.domain.PaymentLog;
import com.emin.pms.domain.PaymentStatus;
import com.emin.pms.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONObject;

@Component("payExpireListener")
public class PayExpireListener {

	private Logger logger = LoggerFactory.getLogger(PayExpireListener.class);
	
	@Autowired
	private PaymentService paymentService;
	
	
	public void handleMessage(Serializable message) {  
		logger.info("支付过期");
		
		try {
			//更新支付记录
			
			String paymentNumber = message.toString();
			logger.info("支付号:"+paymentNumber);
			PaymentLog log = paymentService.findPaymentLogByPaymentNumber(paymentNumber);			
			log.setPaymentStatus(PaymentStatus.EXPIRED);
			paymentService.update(log);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
    }  

}
