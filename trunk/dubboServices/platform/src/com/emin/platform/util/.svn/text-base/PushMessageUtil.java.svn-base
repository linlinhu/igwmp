package com.emin.platform.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

import com.emin.base.util.EncryptUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PushMessageUtil {
	private static Logger logger = Logger.getLogger(PushMessageUtil.class);
	public static void pushMessage(List<String> channelIds,String title,String content,Map<String,String> otherParams){
		List<String> androidList = new ArrayList<String>();
		List<String> iosList = new ArrayList<String>();
		for(int i=0;i<channelIds.size();i++){
			String channelId = channelIds.get(i);
			String[] idAndOS = channelId.split("_");
			if(idAndOS[1].equalsIgnoreCase("ios")){
				iosList.add(idAndOS[0]);
			}else{
				androidList.add(idAndOS[0]);
			}
		}
		if(androidList.size()>0){
			pushMessageToAndroid(androidList,title,content,otherParams);
		}
	}
	private static void pushMessageToAndroid(List<String> channelIds,String title,String content,Map<String,String> otherParams){
		String apiKey = "m5mXCYak5RG0GgtFyHYuU4zF";
		String secretKey = "YYfkKbWIIhQ4CIB2upweQg3xSG10klTo";
		Long timestamp = System.currentTimeMillis()/1000;
		Long expires = timestamp+120;
		int deviceType = 3;
		JSONObject params = new JSONObject();
		params.put("apikey", apiKey);
		params.put("timestamp", timestamp);
		params.put("expires", expires);
		params.put("device_type", deviceType);
		params.put("channel_ids", JSONArray.fromObject(channelIds).toString());
		params.put("msg_type", 1);
		params.put("msg_expires", 86400);
		JSONObject pushMsg = new JSONObject();
		pushMsg.put("title",title);
		pushMsg.put("description", content);
		pushMsg.put("custom_content", otherParams);		
		params.put("msg", pushMsg.toString());
		ArrayList<String> list = new ArrayList<String>();
		for(Object key :params.keySet()){
			list.add(key.toString()+"="+params.get(key).toString());
		}
		Collections.sort(list);
		String[] arr = new String[list.size()];
		arr = list.toArray(arr);
		
		String sign = "POSThttp://api.tuisong.baidu.com/rest/3.0/push/batch_device";
		for (int i = 0; i < arr.length; i++) {
			sign+=arr[i];
		}
		try {
			sign+=secretKey;
			System.out.println(sign);
			
			sign = URLEncoder.encode(sign, "UTF-8");
			sign = EncryptUtils.encodeMD5(sign);
			System.out.println(sign);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		params.put("sign", sign);
		
		System.out.println(params.toString());
		JSONObject result = requestAPI(params);
		logger.info("============android push result:"+result.toString());
	}
	private static JSONObject requestAPI(JSONObject params){
		HttpClient client = new HttpClient();  
		String url = "/rest/3.0/push/batch_device";
		//设置Cookie信息
	    client.getHostConfiguration().setHost( "api.tuisong.baidu.com" , 80, "http" ); 
	    PostMethod post = new PostMethod( url );
	    post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
	    post.addRequestHeader("User-Agent", " BCCS_SDK/3.0 (Darwin; Darwin Kernel Version 14.0.0: Tue, 13 Nov 2015 16:00:00 GMT; root:xnu-2782.1.97~2/RELEASE_X86_64; x86_64) PHP/5.6.3 (Baidu Push Server SDK V3.0.0 and so on..) cli/Unknown ZEND/2.6.0");
	    //post.addRequestHeader("Cookie", "loginInfo="+loginInfo);
	    JSONObject json = new JSONObject();
	    if(params!=null){
	    	List<NameValuePair> pairs = new ArrayList<NameValuePair>();
	 	    for(Object key : params.keySet()){
	 	    	NameValuePair pair = new NameValuePair(key.toString(), params.get(key).toString());
	 	    	pairs.add(pair);
	 	    }
	 	    NameValuePair[] pairArr = new NameValuePair[pairs.size()];
	 	    pairArr = pairs.toArray(pairArr);
	 	    post.setRequestBody(pairArr);
	 	   // System.out.println(post.getParams().);
	 	    //post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");  
	    }
	    try {
			int status = client.executeMethod(post);
			HttpCodeDesc desc = HttpCodeDesc.get(status);
			
			if(desc!=null){
				json.put("status", -1);
				json.put("desc", desc.getDesc());
				return json;
			}
			
			InputStream inputStream = post.getResponseBodyAsStream();   
	        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));   
	        StringBuffer stringBuffer = new StringBuffer();   
	        String str= "";   
	        while((str = br.readLine()) != null){   
	            stringBuffer .append(str);   
	        }   
			json.put("status", 0);
			json.put("result", stringBuffer.toString());
			return json;
		} catch (HttpException e) {
			json.put("status", -1);
			json.put("desc", "接口访问异常");
			logger.error(e.getMessage());
		} catch (IOException e) {
			json.put("status", -1);
			json.put("desc", "接口访问异常");
			logger.error(e.getMessage());
		}
		return json;
	}
}
