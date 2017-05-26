package com.emin.wxs.controller.woa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emin.base.exception.EminException;
import com.emin.wxs.controller.WxsBaseController;
/**
 * 
* @ClassName: PaymentController
* @Description: 支付相关的web接口controller
* @author Jim.lee
* @date 2017年4月11日 下午12:46:19
*
 */
import com.emin.wxs.facade.trading.callers.WxsToPaymentCaller;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("/pay")
public class PaymentController extends WxsBaseController{

	private Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired
	private WxsToPaymentCaller wxsToPaymentCaller;
	
	/**
	 * 
	* @Title: createPayment
	* @Description: 创建支付
	* @param channelId
	* @param orderNumber
	* @param openId
	* @param amount void
	 */
	@RequestMapping(value="/create.do",method=RequestMethod.POST)
	private void createPayment(String channelCode,String orderNumber,String openId,Integer amount){
		
		try {
			String chargeInfo = wxsToPaymentCaller.createPayment(channelCode, orderNumber, openId, amount, getIpAddress());
			JSONObject json = JSONObject.fromObject(chargeInfo);
			printJson(json);
		} catch (EminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/paymentHooks.do")
	public void paymentHook(@RequestBody JSONObject event){
		try{
			handleHookEvent(event);
			getResponse().setStatus(HttpStatus.OK.value());
		}catch(Exception e){
			e.printStackTrace();
			getResponse().setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		
		
	}
	private void handleHookEvent(JSONObject event) throws EminException, Exception{
		String type = event.getString("type");
		if(type.equalsIgnoreCase("charge.succeeded")){
			logger.info("支付成功挂钩事件");
			//获取支付对象信息
			
			JSONObject chargeData = event.getJSONObject("data").getJSONObject("object");
			//支付渠道
			logger.info("chargeData:"+chargeData);
			String channel = chargeData.getString("channel");
			String paymentNumber = chargeData.getString("id");
			Integer payedAmount = chargeData.getInt("amount");
			if(channel.equals("wx_pub")){
				//微信公众号支付，根据交易号直接处理
				wxsToPaymentCaller.paymentSuccess(paymentNumber,payedAmount);
				
			}else if(channel.equals("wx_pub_qr")){
				logger.info("paymentNumber:"+paymentNumber);
				//微信公众号扫码支付 获取订单号 及 extra处理
				JSONObject extra = chargeData.getJSONObject("extra");
				//extra中包含了product_id(机柜标识) openId 支付人
				String machineTag = extra.getString("product_id");
				logger.info("machineTag:"+machineTag);
				String openId = extra.getString("open_id");
				logger.info("openId:"+openId);
				
				wxsToPaymentCaller.paymentSuccess(paymentNumber, payedAmount,machineTag,openId);
				
			}
			
		}else{
			throw new RuntimeException("unsupport event type");
		}
	}
	
}
