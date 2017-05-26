package com.emin.wxs.facade.trading.callers.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.base.exception.EminException;
import com.emin.pms.domain.PayerType;
import com.emin.pms.domain.PaymentChannel;
import com.emin.pms.facade.accepters.PaymentAccepter;
import com.emin.tdc.facade.accepters.TradingCenterAccepter;
import com.emin.wxs.domain.Fans;
import com.emin.wxs.domain.FansItem;
import com.emin.wxs.facade.trading.callers.WxsToPaymentCaller;
import com.emin.wxs.service.FansService;
import com.emin.wxs.service.WeixinToolService;
import com.emin.wxs.vo.trading.PaymentChannelVO;

import net.sf.json.JSONObject;

/**
 * 
* @ClassName: WxsToPaymentCallerImpl
* @Description: 支付服务关联业务调用实现类
* @author Jim.lee
* @date 2017年4月11日 上午11:38:00
*
 */
@Component("wxsToPaymentCaller")
public class WxsToPaymentCallerImpl implements WxsToPaymentCaller{

	private Logger logger = LoggerFactory.getLogger(WxsToPaymentCallerImpl.class);
	
	@Reference(version="0.0.1")
	private PaymentAccepter paymentAccepter;
	
	@Reference(version="0.0.1")
	private WeixinToolService weixinToolService;
	
	@Reference(version="0.0.1")
	private FansService fansService;
	
	@Reference(version="0.0.1")
	private TradingCenterAccepter tradingCenterAccepter;
	@Override
	public String createPayment(String channelCode,String orderNumber,String openId,Integer amount,String ip) throws EminException, Exception{
		Map<String,Object> otherParam = new HashMap<>();
		otherParam.put("clientIP", ip);
		otherParam.put("subject", "测试");
		otherParam.put("body", "测试");
		otherParam.put("openId", openId);
		FansItem fansItem = fansService.loadByOpenId(openId);
		Fans fans = fansService.findById(fansItem.getId());
		otherParam.put("payerInfo", JSONObject.fromObject(fans));
		return paymentAccepter.createPayment(orderNumber, channelCode, amount, PayerType.WEIXINFANS, fansItem.getFansId(), otherParam);
	}
	@Override
	public List<PaymentChannelVO> loadChannels(){
		List<PaymentChannel> channels = paymentAccepter.loadChannels();
		return PaymentChannelVO.paymentChannelToVO(channels);
	}
	@Override
	public void paymentSuccess(String paymentNumber,Integer payedAmount) throws EminException, Exception{
		tradingCenterAccepter.paymentSuccess(paymentNumber, payedAmount);
		
	}
	@Override
	public void paymentSuccess(String paymentNumber,Integer payedAmount,String machineTag,String openId) throws EminException, Exception{
		FansItem fansItem = fansService.loadByOpenId(openId);
		Fans fans = fansService.findById(fansItem.getId());
		logger.info("扫码支付人信息:"+fans);
		try {
			tradingCenterAccepter.paymentSuccess(paymentNumber,payedAmount,machineTag,JSONObject.fromObject(fans));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
