package com.emin.wxs.facade.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.wxs.domain.WxMenu;
import com.emin.wxs.facade.WxCustomMenuFacade;
import com.emin.wxs.service.WxMenuService;

import net.sf.json.JSONArray;
@Component("wxCustomMenuFacade")
public class WxCustomMenuFacadeImpl implements WxCustomMenuFacade{

	private Logger logger = LoggerFactory.getLogger(WxCustomMenuFacadeImpl.class);
	
	@Reference(version="0.0.1")
	private WxMenuService wxMenuService;
	
	@Override
	public JSONArray loadWxMenu(Long woaId,Long pid){
		List<WxMenu> menus = wxMenuService.loadMenus(woaId, pid);
		return JSONArray.fromObject(menus);
	}
	@Override
	public void saveOrUpdate(WxMenu menu){
		wxMenuService.saveOrUpdateMenu(menu);
	}
	@Override
	public boolean auditMenu(Long woaId){
		boolean success = true;
		try {
			success = wxMenuService.auditMenu(woaId);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
			success = false;
		}
		return success;
	}
	@Override
	public void deleteMenu(Long id){
		wxMenuService.deleteById(id);
	}
	
	@Override
	public JSONArray loadClickMenu(Long woaId){
		return JSONArray.fromObject(wxMenuService.loadClickMenu(woaId));
	}
}
