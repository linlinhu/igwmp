package com.emin.wxs.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.base.service.Condition.ConditionOperator;
import com.emin.base.service.Condition.ConditionType;
import com.emin.wxs.domain.WeixinWebPage;
import com.emin.wxs.domain.WeixinWebPageCategory;
import com.emin.wxs.facade.WxWebPageFacade;
import com.emin.wxs.service.WeixinWebPageCategoryService;
import com.emin.wxs.service.WeixinWebPageService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Component("wxWebPageFacade")
public class WxWebPageFacadeImpl implements WxWebPageFacade{

	@Reference(version="0.0.1")
	private WeixinWebPageService weixinWebPageService;
	@Reference(version="0.0.1")
	private WeixinWebPageCategoryService weixinWebPageCategoryService;
	
	@Override
	public void saveCategory(WeixinWebPageCategory category){
		weixinWebPageCategoryService.saveOrUpdate(category);
	}

	@Override
	public JSONArray loadCategoryTree(Long woaId,Long pid){
		return JSONArray.fromObject(weixinWebPageCategoryService.findByPid(pid,woaId));
	}
	@Override
	public void deleteCategory(Long id){
		weixinWebPageCategoryService.deleteCategory(id);
	}
	@Override
	public void saveWebPage(WeixinWebPage webPage){
		weixinWebPageService.saveOrUpdate(webPage);
	}
	@Override
	public JSONObject loadWebPage(PageRequest pageRequest,Long categoryId){
		List<Condition> conditions = new ArrayList<Condition>();
		conditions.add(new Condition(WeixinWebPage.PROP_CATEGORYID, ConditionOperator.EQ, ConditionType.OTHER, categoryId));
		PagedResult<WeixinWebPage> page = weixinWebPageService.loadWebPageByConditions(pageRequest,conditions );
		JSONObject json = new JSONObject();
		json.put("total", page.getTotalCount());
		json.put("rows", page.getResultList());
		return json;
	}
	@Override
	public void deleteWebPages(Long[] ids){
		weixinWebPageService.deleteByIds(ids);
	}
	@Override
	public WeixinWebPage getWebPageById(Long id){
		return weixinWebPageService.findById(id);
	}
}
