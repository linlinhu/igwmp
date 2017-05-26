package com.emin.wxs.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.emin.base.controller.BaseController;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.SortKey;
import com.emin.wxs.util.PageData;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public abstract class WxsBaseController extends BaseController{
	
	private HttpServletRequest req;
	private HttpServletResponse res;
	
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request,HttpServletResponse response) {
		this.req = request;
		this.res = response;
		super.setReqAndRes(request, response);
	}
	
	@Override
	public HttpServletRequest getRequest() {
		
		return this.req;
	}

	@Override
	public HttpServletResponse getResponse() {
		// TODO Auto-generated method stub
		return this.res;
	}

	@Override
	public void printFtl(String code,Map<String,Object> data){
		HttpServletResponse response = res;
		HttpServletRequest request = req;
		//String contextPath = request.getContextPath();
		String path = request.getSession().getServletContext().getRealPath("/")+ "/modules";
		Configuration cfg = new Configuration();
		
		
		try {
			cfg.setDirectoryForTemplateLoading(new File(path));
			String encoding = "UTF-8";
			cfg.setDefaultEncoding(encoding);
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
			cfg.setNumberFormat("#");
			Template t = cfg.getTemplate(code.toLowerCase()+".js");
			response.setHeader("Pragma","No-cache");
			response.setHeader("Cache-Control","no-cache"); 
			response.setDateHeader("Expires", 0);
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-type","text/html");
			//Person person = PltThreadLocalUtil.getPerson();
			data.put("base",request.getScheme()+"://"+request.getServerName()
					+":"+request.getServerPort()+request.getContextPath());
			//data.put("p",person);
			t.process(data, response.getWriter());
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void printJson(JSONObject json) {
		OutputStream pw = null;
		try {
			// 璁剧疆缂栫爜
			res.setCharacterEncoding("UTF-8");
			res.setHeader("Pragma", "No-cache");
			res.setHeader("Access-Control-Allow-Origin", "*");
			res.setHeader("Cache-Control", "no-cache");
			res.setHeader("Cache-Control", "no-store");
			res.setContentType("text/html;charset=UTF-8");
			pw = res.getOutputStream();
			System.out.println("printJson==================" + json.toString());
			pw.write(json.toString().getBytes("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				try {
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	@Override
	public void printJson(JSONArray json) {
		OutputStream pw = null;
		try {
			req.setCharacterEncoding("UTF-8");
			res.setCharacterEncoding("UTF-8");
			res.setHeader("Pragma", "No-cache");
			res.setHeader("Cache-Control", "no-cache");
			res.setHeader("Cache-Control", "no-store");
			res.setContentType("text/html;charset=UTF-8");
			pw = res.getOutputStream();
			pw.write(json.toString().getBytes("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				try {
					pw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public PageRequest getPageRequestData() {
		
		String ascend = (String) getParameterValue("dir");
		String sortPropertys = (String) getParameterValue("sort");
        int rowsPerPage = Integer.parseInt(getParameterValue("limit")!=null?(getParameterValue("limit").toString()):"20");
        //每页的开始记录  第一页为1  第二页为rowsPerPage +1   
        int offset = Integer.parseInt(getParameterValue("start")!=null?(getParameterValue("start").toString()):"0");
       
      

		boolean ascending = true;
		SortKey orderBy = null;
		SortKey[] sortKeys = null;
		
		
		
		if(sortPropertys != null && !sortPropertys.equals("") && ascend != null){
			
			
				if("desc".equalsIgnoreCase(ascend)){
					ascending = false;
				}else{
					ascending = true;
				}
				orderBy = ascending?SortKey.asc(sortPropertys):SortKey.desc(sortPropertys);
				sortKeys = new SortKey[]{orderBy};
			
		}
		
		int limit = rowsPerPage;
		PageRequest pageRequest = new PageRequest();
		pageRequest.setOffset(offset);
		pageRequest.setLimit(limit);
		
		
		pageRequest.setOrderBy(sortKeys);
		
		return pageRequest;
	}
	
	public String getBasePath(){
		return getRequest().getScheme()+"://"+getRequest().getServerName()
		+":"+getRequest().getServerPort()+getRequest().getContextPath();
	}
	
	/**
	 * 得到PageData
	 */
	public PageData getPageData() {
		return new PageData(this.getRequest());
	}

	public String getIpAddress() { 
	    String ip = getRequest().getHeader("x-forwarded-for"); 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = getRequest().getHeader("Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = getRequest().getHeader("WL-Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = getRequest().getHeader("HTTP_CLIENT_IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = getRequest().getHeader("HTTP_X_FORWARDED_FOR"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = getRequest().getRemoteAddr(); 
	    } 
	    return ip; 
	  }
}
