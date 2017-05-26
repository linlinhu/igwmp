package com.emin.wxs.pay.service;

import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.emin.wxs.pay.common.Signature;
import com.emin.wxs.pay.common.XMLParser;
import com.emin.wxs.pay.protocol.UnifiedOrderReqData;
import com.emin.wxs.util.HttpTool;

import net.sf.json.JSONObject;

/**
 * Created by hupeng on 2015/7/28.
 */
public class WxPayApi {
	//微信统一下单地址
	public static final String URL_PAY = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	private static Logger logger = Logger.getLogger(WxPayApi.class);

	public static Map<String, Object> UnifiedOrder(UnifiedOrderReqData reqData)
			throws IOException, SAXException, ParserConfigurationException {
		// String res = HttpService.doPost(Configure.UNIFIED_ORDER_API,
		// reqData);
		String res = HttpTool.httpsPost(URL_PAY, XMLParser.toXML(reqData));
		String sign = Signature.getSignFromResponseString(res);
		logger.debug("UnifiedOrder get response:" + res);
		Map<String, Object> result = XMLParser.getMapFromXML(res);

		if (result.containsKey("sign")) {
			String respSign = (String) result.get("sign");

			if (!sign.equals(respSign)) {
				throw new RuntimeException("响应的签名错误");
			}
		} else {
			throw new RuntimeException("响应没有签名");
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public static String getOpenid(String appid, String appSecret, String code) throws Exception {
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&secret=" + appSecret
				+ "&code=" + code + "&grant_type=authorization_code";
		String res = HttpTool.httpsGet(requestUrl);
		logger.debug(res);
		Map<String, Object> resultMap = JSONObject.fromObject(res);

		if (resultMap.get("openid") == null) {
			return null;
		}

		return resultMap.get("openid").toString();
	}

	public static void main(String[] args) throws Exception {
		UnifiedOrderReqData reqData = new UnifiedOrderReqData.UnifiedOrderReqDataBuilder("appid", "mch_id", "body",
				"out_trade_no", 1, "spbill_create_ip", "notify_url", "JSAPI").setOpenid("openid").build();
		System.out.println(UnifiedOrder(reqData));

	}
}
