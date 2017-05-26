package com.emin.pms.service;

import java.util.List;
import java.util.Map;

import com.emin.base.service.CRUDService;
import com.emin.pms.domain.PayerType;
import com.emin.pms.domain.PaymentChannel;
import com.emin.pms.domain.PaymentLog;
import com.emin.pms.domain.PaymentStrategy;

public interface PaymentService extends CRUDService<PaymentLog>{

	String createPayment(String orderNumber, String channelCode,Integer amount, PayerType payerType, Long payerId,
			PaymentStrategy paymentStrategy,Map<String,Object> otherParam);

	List<PaymentChannel> loadChannels();

	List<PaymentLog> findPaymentLogByOrderNumber(String orderNumber);

	PaymentLog findPaymentLogByPaymentNumber(String paymentNumber);



}
