package com.emin.platform.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.emin.platform.domain.Person;

import net.sf.json.JSONObject;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter{
	private Logger logger = Logger.getLogger(AuthorizationInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		String url = request.getRequestURI();
		logger.info("请求路径====="+url);
		String methodName = "";
		boolean isAjax = false;
		String requestType = request.getHeader("X-Requested-With");
		isAjax = requestType!=null;
		methodName = url.substring(url.lastIndexOf("/")+1);
		logger.info("(1)执行方法："+methodName);
		List<String> acceptMethods = new ArrayList<String>();
		acceptMethods.add("login.htm");
		acceptMethods.add("regist.htm");
		acceptMethods.add("active.htm");
		acceptMethods.add("validateOrgName.htm");
		acceptMethods.add("validateOrgCode.htm");
		acceptMethods.add("validateEmail.htm");
		acceptMethods.add("codeInfo.htm");
		acceptMethods.add("codeImg.htm");
		acceptMethods.add("getAllTree.htm");
		acceptMethods.add("search.htm");
		if(!acceptMethods.contains(methodName)){
			if(isAjax){
				String value = CookieUtil.getValue(request, "loginInfo");
				if(StringUtils.isEmpty(value)){
					response.getWriter().print("sessionTimeout");
					return false;
				}else{
					String infovalue = DesUtil.decrypt(value);
					JSONObject loginInfo = JSONObject.fromObject(infovalue);
					Person person = (Person) JSONObject.toBean(loginInfo, Person.class);		
					PltThreadLocalUtil.setPerson(person);
					return true;
				}
				
				
			}
			else{
				String value = CookieUtil.getValue(request, "loginInfo");
				if(StringUtils.isEmpty(value)){
					response.sendRedirect("../login.html");
					return false;
				}else{
					String infovalue = DesUtil.decrypt(value);
					JSONObject loginInfo = JSONObject.fromObject(infovalue);
					Person person = (Person) JSONObject.toBean(loginInfo, Person.class);		
					PltThreadLocalUtil.setPerson(person);
					return true;
				}
				
			}
			
		}
		
	
		
		return true;
	}
	
}
