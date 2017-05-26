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

@Component("payedListener")
public class PayedListener {

	private Logger logger = LoggerFactory.getLogger(PayedListener.class);
	
	@Autowired
	private RedisDao redisDao;
	
	@Autowired
	private PaymentService paymentService;
	
	@SuppressWarnings("unchecked")
	public void handleMessage(Serializable message) {  
		logger.info("支付成功监听器");
		ObjectMapper objectMapper = new ObjectMapper();
		 Map<String,Object> paymentInfo = null;
		try {
			//更新支付记录
			paymentInfo = objectMapper.readValue(message.toString(), Map.class);
			String paymentNumber = paymentInfo.get("paymentNumber").toString().replace("_", "-");
			logger.info("支付号:"+paymentNumber);
			Integer payedAmount = Integer.valueOf(paymentInfo.get("payedAmount").toString());
			logger.info("支付号金额:"+Double.valueOf(payedAmount.toString())/100+"元");
			JSONObject fans =JSONObject.fromObject(paymentInfo.get("fans").toString());
			logger.info("支付人信息:"+fans);
			PaymentLog log = paymentService.findPaymentLogByPaymentNumber(paymentNumber);
			log.setPayerType(PayerType.WEIXINFANS);
			log.setPayerId(fans.getLong("id"));
			log.setPayerInfo(paymentInfo.get("fans").toString());
			log.setAmount(payedAmount);
			log.setPaymentStatus(PaymentStatus.PAYED);
			paymentService.update(log);
			redisDao.delete("Payment", "PAYMENT_"+paymentNumber);
		}catch(Exception e){
			logger.error(e.getMessage(),e);
		}
    }  

}
