/**
 * 
 */
package com.emin.platform.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

/**
 * @author Jim.Lee
 *
 */
public class IndexFilter implements Filter{
	private Logger log4j = Logger.getLogger(IndexFilter.class);
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) sRequest;
		HttpServletResponse response = (HttpServletResponse) sResponse;
		String value = CookieUtil.getValue(request, "loginInfo");
		JSONObject loginInfo = JSONObject.fromObject(value);
		
		//判断是否为合法用户登录
		if (value!=null&&loginInfo.get("id")!=null) {
			log4j.info("主页过滤器：合法用户登录---");
			Long companyId = loginInfo.get("companyId")==null?null:loginInfo.getLong("companyId");
			int personType = loginInfo.get("type") == null ? null : loginInfo.getInt("type");
			
			//将信息存储到Session之中
			HttpSession session = request.getSession();
			session.setAttribute("companyId", companyId);
			session.setAttribute("type", personType);
			chain.doFilter(request,response);
		}else{
			response.sendRedirect("/meris/login.jsp");
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}
	
}
