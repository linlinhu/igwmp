package com.emin.pms.facade.accepters.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.emin.base.exception.EminException;
import com.emin.pms.domain.PayerType;
import com.emin.pms.domain.PaymentChannel;
import com.emin.pms.domain.PaymentLog;
import com.emin.pms.domain.PaymentStrategy;
import com.emin.pms.facade.accepters.PaymentAccepter;
import com.emin.pms.service.PaymentService;

@Component("paymentAccepter")
@Service(version="0.0.1")
public class PaymentAccepterImpl implements PaymentAccepter{

	@Autowired
	private PaymentService paymentService;
	
	
	@Override
	public String createPayment(String orderNumber, String channelCode, Integer amount, PayerType payerType, Long payerId,
			Map<String,Object> otherParam) throws EminException,Exception{
		return paymentService.createPayment(orderNumber, channelCode, amount, payerType, payerId, PaymentStrategy.FULL, otherParam);
	}
	
	@Override
	public List<PaymentChannel> loadChannels(){
		return paymentService.loadChannels();
	}
	@Override
	public PaymentLog findPaymentLogByNumber(String number){
		return paymentService.findPaymentLogByPaymentNumber(number);
	}
	

}
