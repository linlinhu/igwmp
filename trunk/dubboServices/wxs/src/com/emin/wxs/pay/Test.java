package com.emin.wxs.pay;
import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.emin.wxs.pay.common.Configure;
import com.emin.wxs.pay.protocol.UnifiedOrderReqData;
import com.emin.wxs.pay.service.WxPayApi;

public class Test {
	public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
		Configure.setKey("Qmin2Xsw3Edc4Vfr5Tgb6Nhy7Ujm8iKp");
		Configure.setAppID("wx5f5700a7767fdae0");
		Configure.setMchID("1231482402");
		UnifiedOrderReqData reqData = new UnifiedOrderReqData.UnifiedOrderReqDataBuilder(Configure.getAppid(),Configure.getMchid(), "test_body", "100", 1, "8.8.8.8", "http://www.baidu.com", "JSAPI").setOpenid("ohJiZtwS17eZ3T0E3srqmGZWzDC0").build();
		Map<String, Object> resultMap = WxPayApi.UnifiedOrder(reqData);

		for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}
}
