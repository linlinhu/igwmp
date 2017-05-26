package com.emin.wxs.facade.impl;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.domain.UndeleteableEntity;
import com.emin.wxs.domain.Fans;
import com.emin.wxs.domain.FansItem;
import com.emin.wxs.domain.WxOfficialAccount;
import com.emin.wxs.facade.WxOfficialAccountFacade;
import com.emin.wxs.service.FansService;
import com.emin.wxs.service.WeixinToolService;
import com.emin.wxs.service.WxOfficialAccountService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Component("wxOfficialAccountFacade")
public class WxOfficialAccountFacadeImpl implements WxOfficialAccountFacade{
	
	private static final Map<String, String> us = new HashMap<String, String>();
	private static final Map<String, String> acMap = new HashMap<String, String>();
	
	@Reference(version="0.0.1")
	private WxOfficialAccountService wxOfficialAccountService;
	@Reference(version="0.0.1")
	private WeixinToolService weixinToolService;
	@Reference(version="0.0.1")
	private FansService fansService;
	
	@Override
	public JSONArray loadValidWxOfficialAccounts(){
		List<WxOfficialAccount> accounts = wxOfficialAccountService.findAllByStatus(UndeleteableEntity.STATUS_VALID);
		JSONArray resultList = JSONArray.fromObject(accounts);
		return resultList;
	}
	@Override
	public JSONObject loadValidWxOfficialAccountsByPage(PageRequest pageRequest){
		JSONObject result = new JSONObject();
		PagedResult<WxOfficialAccount> accounts = wxOfficialAccountService.getPageByStatus(pageRequest, UndeleteableEntity.STATUS_VALID);	
		result.put("rows", accounts.getResultList());
		result.put("total", accounts.getTotalCount());
		return result;
	}
	@Override
	public void saveOrUpdate(WxOfficialAccount wxOfficialAccount){
		wxOfficialAccountService.saveOrUpdate(wxOfficialAccount);
	}
	@Override
	public void deleteWOA(Long woaId){
		wxOfficialAccountService.disable(woaId);
	}
	@Override
	public WxOfficialAccount findById(Long id){
		return wxOfficialAccountService.findById(id);
	}
	@Override
	public Map<String,Object> getWxAboutConf(HttpServletRequest request) throws Exception{
		Map<String, Object> aboutConf = new HashMap<>();
		Long woaId = Long.valueOf(request.getParameter("state"));
		JSONObject jsApiConf = weixinToolService.loadJSAPIConf(woaId, URLEncoder.encode(
				request.getRequestURL().toString() + "?" + request.getQueryString(),
				"utf-8"));
		aboutConf.put("jsAPIConf", jsApiConf.toString());
		String code = (String) request.getSession().getAttribute("code");
		if(code==null){
			code = request.getParameter("code");
		}
		
		String openid ="";
		JSONObject user = new JSONObject();
		String access_token = "";
		if (us.containsKey(code.toString())) {
			// renderText(us.get(code.toString()));
			user = JSONObject.fromObject(us.get(code.toString()));
			access_token = weixinToolService.freshSnsTokenOpenid(woaId,acMap.get(code.toString()));
			access_token = JSONObject.fromObject(access_token).getString("access_token");
			openid = user.getString("openid");
			request.getSession().setAttribute("code", code);
		} else {
			String rtn = weixinToolService.loadSnsTokenOpenid(woaId,code);
			JSONObject jo = JSONObject.fromObject(rtn);
			if (jo.containsKey("errcode") && jo.getInt("errcode") > 0) {
				// renderText("e:" + jo.getString("errmsg"));
			} else {
				access_token = jo.getString("access_token");
				openid = jo.getString("openid");
				// 取得用户基本信息
				String userinfo = weixinToolService.loadSnsUesrInfo(woaId,access_token, openid);
				us.put(code.toString(), userinfo);
				acMap.put(code.toString(), jo.getString("refresh_token"));
				user = JSONObject.fromObject(userinfo);
				request.getSession().setAttribute("code", code);

				// renderText(userinfo);
			}
		}
		String jsApiTicket = weixinToolService.loadJSAPITicket(woaId);
		FansItem fansItem = fansService.loadByOpenId(user.getString("openid"));
		Fans fans = fansService.findById(fansItem.getFansId());
		aboutConf.put("fans", fans);
		aboutConf.put("openId", fansItem.getOpenid());
		aboutConf.put("jsApiTicket", jsApiTicket);
		return aboutConf;
	}
}
