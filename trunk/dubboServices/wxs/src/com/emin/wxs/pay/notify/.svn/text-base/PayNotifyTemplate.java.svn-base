package com.emin.wxs.pay.notify;

import org.apache.log4j.Logger;

import com.emin.wxs.pay.common.XMLParser;

public class PayNotifyTemplate {
	private static Logger logger = Logger.getLogger(PayNotifyTemplate.class);
	private PayNotifyData payNotifyData;

	public PayNotifyTemplate(String notifyXml) {
		// boolean isValid =
		// Signature.checkIsSignValidFromResponseString(notifyXml);
		// if (isValid) {
		payNotifyData = XMLParser.getObjectFromXML(notifyXml, PayNotifyData.class);
		// }

	}

	public String execute(PaySuccessCallBack successCallBack) {
		ResponseData responseData = new ResponseData();

		logger.debug("receive data from wechat:" + payNotifyData);
		if ("SUCCESS".equals(payNotifyData.getReturn_code()) && "SUCCESS".equals(payNotifyData.getResult_code())) {

			if (successCallBack != null) {
				try {
					successCallBack.onSuccess(payNotifyData);
					responseData.setReturn_code("SUCCESS");
					responseData.setReturn_msg("OK");
				} catch (Exception e) {
					responseData.setReturn_code("FAIL");
					responseData.setReturn_msg(e.getMessage());
				}

			}
		}

		return XMLParser.toXML(responseData);
	}
}
