package com.emin.wxs.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.emin.base.util.CookieUtil;
import com.emin.base.util.DesUtil;

import net.sf.json.JSONObject;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter{
	private Logger logger = Logger.getLogger(AuthorizationInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception {

		String url = request.getRequestURI();
		logger.info("请求路径====="+url);
		String methodName = "";
		boolean isAjax = false;
		String requestType = request.getHeader("X-Requested-With");
		isAjax = requestType!=null;
		methodName = url.substring(url.lastIndexOf("/")+1);
		logger.info("(1)执行方法："+methodName);
		List<String> acceptMethods = new ArrayList<String>();
		acceptMethods.add("login.do");
		acceptMethods.add("paymentHooks.do");
		acceptMethods.add("upload.do");
		if(!acceptMethods.contains(methodName)){
			if(isAjax){
				String value = CookieUtil.getValue(request, "loginInfo");
				if(StringUtils.isEmpty(value)){
					response.getWriter().print("sessionTimeout");
					return false;
				}else{
					String infovalue = DesUtil.decrypt(value);
					JSONObject loginInfo = JSONObject.fromObject(infovalue);
					WxsThreadLocalUtil.setPersonId(loginInfo.getLong("id"));
					return true;
				}
				
				
			}
			else{
				String value = CookieUtil.getValue(request, "loginInfo");
				if(StringUtils.isEmpty(value)){
					response.sendRedirect(request.getContextPath()+"/login.html");
					return false;
				}else{
					String infovalue = DesUtil.decrypt(value);
					JSONObject loginInfo = JSONObject.fromObject(infovalue);
					WxsThreadLocalUtil.setPersonId(loginInfo.getLong("id"));
					return true;
				}
				
			}
			
		}
		
	
		
		return true;
	}
	
}
