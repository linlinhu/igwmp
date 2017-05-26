package com.emin.pms.exception;

public class PmsExceptionCode {

	/**
	 * 参数非法
	 */
	public static final String PARAMTER_INVALID = "PMS.0.0.1";
	/**
	 * 支付通道不可用
	 */
	public static final String CHANNEL_INVALID = "PMS.0.0.2";
	/**
	 * 微信公众号支付必须包含openId
	 */
	public static final String WXPUB_MUST_BE_HAS_OPENID = "PMS.0.0.3";
	
	/**
	 * 支付过期时间太久
	 */
	public static final String PAY_EXPIRE_TOME_TOO_LONG = "PMS.0.0.4";
	
	/**
	 * 微信公众号扫码支付必须产品标识(机柜扫码支付的时候产品标识用做机柜标识)
	 */
	public static final String WXPUB_QR_MUST_BE_HAS_PRODUCTID = "PMS.0.0.5";
}
