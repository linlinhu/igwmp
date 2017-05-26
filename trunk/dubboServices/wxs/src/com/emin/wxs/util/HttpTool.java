package com.emin.wxs.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;

public class HttpTool {
	private static final Logger logger = Logger.getLogger(HttpTool.class);
	
	public static String httpsPost(String httpsUrl, String str){
		try {
			HttpsURLConnection urlCon = (HttpsURLConnection) (new URL(httpsUrl)).openConnection();
			urlCon.setDoInput(true);
			urlCon.setDoOutput(true);
			urlCon.setRequestMethod("POST");
			
			urlCon.setRequestProperty("Content-Length", String.valueOf(str.getBytes("UTF-8").length));
			urlCon.setUseCaches(false);
			urlCon.getOutputStream().write(str.getBytes("UTF-8"));
			urlCon.getOutputStream().flush();
			urlCon.getOutputStream().close();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream(),"UTF-8"));
			String line;
			StringBuffer sb = new StringBuffer();
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}
	
	
	/**
	 * @param httpUrl 连接网址
	 * @param param POST内容
	 * */
	public static String httpPost(String httpUrl, String param){
		try {
			HttpURLConnection urlCon = (HttpURLConnection) (new URL(httpUrl)).openConnection();
			urlCon.setDoInput(true);
			urlCon.setDoOutput(true);
			urlCon.setRequestMethod("POST");
			urlCon.setRequestProperty("Content-Length", String.valueOf(param.getBytes("UTF-8").length));
			urlCon.setUseCaches(false);
			urlCon.getOutputStream().write(param.getBytes("UTF-8"));
			urlCon.getOutputStream().flush();
			urlCon.getOutputStream().close();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream(),"UTF-8"));
			String line;
			StringBuffer sb = new StringBuffer();
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}
	/**
	 * @param httpUrl 连接网址
	 * @param param POST内容
	 * */
	public static String httpGet(String httpUrl){
		try {
			HttpURLConnection urlCon = (HttpURLConnection) (new URL(httpUrl)).openConnection();
			urlCon.setDoInput(true);
			urlCon.setDoOutput(true);
			urlCon.setRequestMethod("GET");
			urlCon.setUseCaches(false);
			BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream(),"UTF-8"));
			String line;
			StringBuffer sb = new StringBuffer();
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}
	
	
	/**
	 * @param httpUrl 连接网址
	 * @param param POST内容
	 * */
	public static String httpsGet(String httpUrl){
		try {
			HttpURLConnection urlCon = (HttpURLConnection) (new URL(httpUrl)).openConnection();
			urlCon.setDoInput(true);
			urlCon.setDoOutput(true);
			urlCon.setRequestMethod("GET");
			urlCon.setUseCaches(false);
			BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream(),"UTF-8"));
			String line;
			StringBuffer sb = new StringBuffer();
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} catch (Exception e) {
			logger.error(e);
			return e.getMessage();
		}
	}
	
}
