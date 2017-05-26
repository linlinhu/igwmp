package com.emin.wxs.facade.trading.callers;

import java.util.List;

import com.emin.base.exception.EminException;
import com.emin.wxs.vo.trading.PaymentChannelVO;

/**
* 
* @ClassName: WxsToPaymentCaller
* @Description: 支付服务关联业务调用接口
* @author Jim.lee
* @date 2017年4月11日 上午11:36:50
*
*/
public interface WxsToPaymentCaller {

	/**
	* @Title: 发起支付
	* @Description: 调用支付服务发起支付 返回charge对象JSON字符串
	* @param  channelId 支付通道ID
	* @param  orderNumber 订单号
	* @param  openId 微信粉丝OPENID
	* @param  amount 支付金额 单位（分）
	* @param  ip 支付发起方的IP
	* @return String charge对象JSON字符串 H5 ping++api直接使用即可
	**/
	String createPayment(String channelCode, String orderNumber, String openId, Integer amount, String ip) throws EminException, Exception;

	/**
	 * 
	* @Title: loadChannels
	* @Description: 获取所有可用的支付通道
	* @return List<PaymentChannelVO>
	 */
	List<PaymentChannelVO> loadChannels();

	/**
	 * 
	* @Title: paymentSuccess
	* @Description: 支付成功
	* @param paymentNumber
	* @throws EminException
	* @throws Exception void
	 */
	void paymentSuccess(String paymentNumber,Integer payedAmount) throws EminException, Exception;

	/**
	 * 
	* @Title: paymentSuccess
	* @Description: 支付成功(扫码)
	* @param paymentNumber
	* @throws EminException
	* @throws Exception void
	 */
	void paymentSuccess(String paymentNumber, Integer payedAmount, String machineTag, String openId)
			throws EminException, Exception;


}
