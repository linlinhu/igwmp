package com.emin.tdc.facade.accepters;


import com.emin.base.exception.EminException;
import com.emin.tdc.domain.TakeWineAction;

import net.sf.json.JSONObject;


public interface TradingCenterAccepter {

	/**
	 * 
	* @Title: paymentAction
	* @Description: 支付完成后将支付结果通知交易中心
	* @param paymentNumber
    * @param payedAmount 
	* @param paymentAction
	* @throws EminException
	* @throws Exception void
	 */
	void paymentSuccess(String paymentNumber, Integer payedAmount) throws EminException, Exception;

	/**
	* 
	* @Title: paymentAction
	* @Description: 支付完成后将支付结果通知交易中心(根据机柜标识会做取酒推送)
	* @param paymentNumber
	* @param payedAmount
	* @param machineTag
	* @param paymentAction
	* @throws EminException
	* @throws Exception void
	 */
	void paymentSuccess(String paymentNumber, Integer payedAmount, String machineTag,JSONObject fans)
			throws EminException, Exception;

	/**
	 * 
	* @Title: takeWineComplete
	* @Description: 订单取酒完成回调
	* @param orderNumber 订单号
	* @param actualTakeMl 实际出酒量 ml
	* @param takeInfo 取酒信息 可以包含地址等信息的JSON字符串
	* @param takeWineAction 完成状态
	 */
	void takeWineComplete(String orderNumber, Integer actualTakeMl, String takeInfo, TakeWineAction takeWineAction);

}
