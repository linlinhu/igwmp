package com.emin.platform.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

public class HttpUtil {
	private static Logger logger = Logger.getLogger(HttpUtil.class);
	public static JSONObject httpPost(String host,Integer port,String url,JSONObject params,String loginInfo){
		HttpClient client = new HttpClient();  
		
		//设置Cookie信息
	    client.getHostConfiguration().setHost( host , port, "http" ); 
	    PostMethod post = new PostMethod( "/"+url );
	    try {
			post.setRequestHeader("Cookie", "loginInfo="+ URLEncoder.encode(loginInfo, "utf-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
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
