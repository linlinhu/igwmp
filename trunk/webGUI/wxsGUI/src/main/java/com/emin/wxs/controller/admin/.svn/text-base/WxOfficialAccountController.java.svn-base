package com.emin.wxs.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emin.base.exception.EminException;
import com.emin.wxs.controller.WxsBaseController;
import com.emin.wxs.domain.WxOfficialAccount;
import com.emin.wxs.facade.WxOfficialAccountFacade;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Controller
@RequestMapping("/woa")
public class WxOfficialAccountController extends WxsBaseController{
	private Logger logger = LoggerFactory.getLogger(WxOfficialAccountController.class);
	@Autowired
	@Qualifier("wxOfficialAccountFacade")
	private WxOfficialAccountFacade wxOfficialAccountFacade;
	
	
	/**
	 * 获取有效的微信公众号列表
	 * url : woa/validList.do
	 * method : GET
	 * 公众号POJO信息 ：{@link WxOfficialAccount}
	 * 
	 */
	@RequestMapping(value="/validList.do",method={RequestMethod.GET})
	@ResponseBody
	public JSONArray loadValidAccounts(){
		return wxOfficialAccountFacade.loadValidWxOfficialAccounts();
	}
	
	
	/**
	 * 保存公众号基本信息
	 * url : woa/saveOrUpdate.do
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/saveOrUpdate.do",method={RequestMethod.POST,RequestMethod.PUT})
	@ResponseBody
	public JSONObject saveOrUpdateWOA(WxOfficialAccount account){
		JSONObject result = new JSONObject();
		StringBuilder message = new StringBuilder();
		boolean success = false;
		try {
			wxOfficialAccountFacade.saveOrUpdate(account);
			success = true;
		} catch (EminException e) {
			logger.warn(e.getLocalizedMessage(), e);
			message.append(e.getLocalizedMessage());
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
			message.append("服务器繁忙！请稍后再试");
		}
		result.put("success", success);
		result.put("message", message);
		return result;
	}
	@RequestMapping(value="/delete.do",method={RequestMethod.DELETE,RequestMethod.POST})
	@ResponseBody
	public JSONObject deleteWOA(Long id){
		JSONObject result = new JSONObject();
		StringBuilder message = new StringBuilder();
		boolean success = false;
		try {
			wxOfficialAccountFacade.deleteWOA(id);
			success = true;
		} catch (EminException e) {
			logger.warn(e.getLocalizedMessage(), e);
			message.append(e.getLocalizedMessage());
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
			message.append("服务器繁忙！请稍后再试");
		}
		result.put("success", success);
		result.put("message", message.toString());
		return result;
	}
}
