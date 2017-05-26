package com.emin.tdc.facade.callers;

public interface TradingCenterToOrderCaller {

	/**
	 * 
	* @Title: cancelGiveOrder
	* @Description: 调用订单服务 赠送订单超时
	* @param orderNumber void
	 */
	void cancelGiveOrder(String orderNumber);

}
