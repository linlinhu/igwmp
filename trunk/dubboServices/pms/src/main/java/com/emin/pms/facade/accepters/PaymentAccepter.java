package com.emin.pms.facade.accepters;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.emin.base.exception.EminException;
import com.emin.pms.domain.PayerType;
import com.emin.pms.domain.PaymentChannel;
import com.emin.pms.domain.PaymentLog;

public interface PaymentAccepter {

	/**
	 * 调用Ping++ Api 创建支付并保存记录 
	 * @param orderNumber 订单号
	 * @param channelCode 支付通道码  wx_pub:微信公众号支付 ; wx_pub_qr:微信公众号扫码支付
	 * @param amount 支付金额
	 * @param payerType 支付方类型
	 * @param payerId 支付方ID
	 * @param otherParam 其他参数
	 * 	otherParam{
	 * 		clientIP：调用支付的客户端IP,（必填）
	 * 		subject:标题 (32字节），
	 * 		body：描述（128个unicode字符）,
	 * 		expire:过期时间, 例如 expire=10 expireTimeUnit=TimeUnit.SECOND 则过期时间为10秒后 （可选）
	 * 		expireTimeUnit：过期时间单位 {@link TimeUnit} （可选）
	 * 		openId：公众号粉丝ID （微信公众号支付必填）
	 * 	    productId:公众号扫码支付必填（机柜扫码支付时传机柜标识）
	 *  }
	 * @return string charger字符串 参考ping++ api文档
	 */
	String createPayment(String orderNumber, String channelCode, Integer amount, PayerType payerType, Long payerId,
			Map<String, Object> otherParam) throws EminException,Exception;

	/**
	 * 获取支付通道列表
	 * @return
	 */
	List<PaymentChannel> loadChannels();

	/**
	 * 根据交易号查询交易记录
	 * @param number
	 * @return
	 */
	PaymentLog findPaymentLogByNumber(String number);

}
