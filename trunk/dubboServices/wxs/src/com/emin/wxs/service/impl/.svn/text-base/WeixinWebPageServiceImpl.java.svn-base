package com.emin.wxs.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.dao.PreFilter;
import com.emin.base.dao.PreFilters;
import com.emin.base.exception.BaseExCode;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.base.service.Conditions;
import com.emin.base.service.UndeleteableServiceImpl;
import com.emin.wxs.domain.WeixinWebPage;
import com.emin.wxs.service.WeixinWebPageService;
@Service("weixinWebPageService")
public class WeixinWebPageServiceImpl extends UndeleteableServiceImpl<WeixinWebPage> implements WeixinWebPageService{
	@Override
	public void saveOrUpdate(WeixinWebPage webPage){
		this.beforeSaveOrUpdate(webPage);
		super.saveOrUpdate(webPage);
	}
	private void beforeSaveOrUpdate(WeixinWebPage webPage){
		if(webPage.getTitle()==null
				||webPage.getCategoryId()==null
				||webPage.getInfo()==null
				){
			throw new EminException(BaseExCode.BIZ_ILLEGAL_PARAMETER);
		}
		webPage.setPublish(false);
		webPage.setStatus(WeixinWebPage.STATUS_VALID);
	}
	@Override
	public PagedResult<WeixinWebPage> loadWebPageByConditions(PageRequest pageRequest,List<Condition> conditions){
		List<PreFilter> filters = new ArrayList<PreFilter>();
		if(conditions!=null && conditions.size()>0){
			for (Condition condition : conditions) {
				filters.add(Conditions.convertToPropertyFilter(condition));
			}
		}
		filters.add(PreFilters.eq(WeixinWebPage.PROP_STATUS, WeixinWebPage.STATUS_VALID));
		PreFilter[] finalFilter = new PreFilter[filters.size()];
		finalFilter = filters.toArray(finalFilter);
		return this.getPage(pageRequest, finalFilter);
	}
	public void webPagePublish(Long webPageId){
		WeixinWebPage page = this.findById(webPageId);
		page.setPublish(true);
		super.update(page);
	}
}
