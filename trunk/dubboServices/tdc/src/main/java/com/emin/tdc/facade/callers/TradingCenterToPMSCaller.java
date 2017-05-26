package com.emin.tdc.facade.callers;

public interface TradingCenterToPMSCaller {

	
	/**
	 * 根据交易号获取订单号
	 * @param paymentNumber
	 * @return
	 */
	String getOrderNumerByPaymentNumber(String paymentNumber);

}
