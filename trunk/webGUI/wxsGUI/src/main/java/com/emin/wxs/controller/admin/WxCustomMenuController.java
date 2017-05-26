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
import com.emin.wxs.domain.WxMenu;
import com.emin.wxs.facade.WxCustomMenuFacade;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Controller
@RequestMapping("/wxmenu")
public class WxCustomMenuController extends WxsBaseController{
	private Logger logger = LoggerFactory.getLogger(WxCustomMenuController.class);
	@Autowired
	@Qualifier("wxCustomMenuFacade")
	private WxCustomMenuFacade wxCustomMenuFacade;
	
	/**
	 * 保存自定义菜单信息
	 * url : wxmenu/saveOrUpdate.do
	 * @param menu
	 * 实体信息：WxMenu
	 */
	@RequestMapping(value="/saveOrUpdate.do",method={RequestMethod.POST,RequestMethod.PUT})
	@ResponseBody
	public JSONObject saveOrUpdateMenu(WxMenu menu){
		JSONObject json = new JSONObject();
		boolean success = false;
		String message = "";
		try {
			wxCustomMenuFacade.saveOrUpdate(menu);
			success = true;
		} catch (EminException e) {
			logger.error(e.getLocalizedMessage(),e);
			message = e.getLocalizedMessage();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			message = "保存菜单失败";
		}
		json.accumulate("success", success);
		json.accumulate("message", message);
		return json;
	}
	/**
	 * 加载自定义菜单树
	 * url : wxmenu/tree.do
	 * @param woaId:公众号ID，pid:父级菜单 顶级菜单穿0或者不传
	 * 实体信息：WxMenu
	 */
	@RequestMapping(value="/tree.do",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public JSONArray loadMenuTree(Long woaId,Long pid){
		JSONArray menus = wxCustomMenuFacade.loadWxMenu(woaId,pid);
		return menus;
	}
	
	/**
	 * 调用微信接口生成微信公众号自定义菜单
	 * url : wxmenu/tree.do
	 * @param woaId:公众号ID	 
	 */
	@RequestMapping(value="/generate.do",method={RequestMethod.POST,RequestMethod.PUT})
	@ResponseBody
	public JSONObject auditMenu(Long woaId){
		JSONObject json = new JSONObject();	
		String message = "";
		boolean success = false;
		try {
			success = wxCustomMenuFacade.auditMenu(woaId);
			if(success==false){
				message = "生成菜单失败";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		json.accumulate("success", success);
		json.accumulate("message", message);
		return json;
	}
	
	/**
	 * 删除公众号自定义菜单
	 * url : wxmenu/delete.do
	 * @param id:菜单ID
	 */
	@RequestMapping(value="/delete.do",method={RequestMethod.POST,RequestMethod.DELETE})
	@ResponseBody
	public void deleteMenu(Long id){
		
		JSONObject json = new JSONObject();
		boolean success = false;
		String message = "";
		try {
			
			wxCustomMenuFacade.deleteMenu(id);
			success = true;
		} catch (EminException e) {
			logger.error(e.getLocalizedMessage(),e);
			message = e.getLocalizedMessage();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			message = "删除菜单失败";
		}
		json.accumulate("success", success);
		json.accumulate("message", message.toString());
		printJson(json);
	}

	/**
	 * 获取事件按钮
	 * url : wxmenu/clickMenus.do
	 * @param woaId:公众号ID
	 */
	@RequestMapping("/clickMenus.do")
	@ResponseBody
	public JSONArray loadClickMenu(Long woaId){
		return wxCustomMenuFacade.loadClickMenu(woaId);
	}
}
