package com.emin.tdc.facade.callers.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.pms.domain.PaymentLog;
import com.emin.pms.facade.accepters.PaymentAccepter;
import com.emin.tdc.facade.callers.TradingCenterToPMSCaller;
@Component("tradingCenterToPMSCaller")
public class TradingCenterToPMSCallerImpl implements TradingCenterToPMSCaller{

	@Reference(version="0.0.1")
	private PaymentAccepter paymentAccepter;
	
	@Override
	public String getOrderNumerByPaymentNumber(String paymentNumber){
		PaymentLog log = paymentAccepter.findPaymentLogByNumber(paymentNumber);
		return log.getOrderNumber();
	}
}
