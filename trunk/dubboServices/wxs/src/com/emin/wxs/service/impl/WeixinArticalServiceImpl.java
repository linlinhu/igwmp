package com.emin.wxs.service.impl;

import org.springframework.stereotype.Service;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.dao.PreFilter;
import com.emin.base.dao.PreFilters;
import com.emin.base.exception.BaseExCode;
import com.emin.base.exception.EminException;
import com.emin.base.service.UndeleteableServiceImpl;
import com.emin.wxs.domain.WxArticle;
import com.emin.wxs.service.WeixinArticalService;
@Service("weixinArticalService")
public class WeixinArticalServiceImpl extends UndeleteableServiceImpl<WxArticle> implements WeixinArticalService{
	@Override
	public void saveArtical(WxArticle article){
		this.beforeSaveArtical(article);
		super.saveOrUpdate(article);
	}
	private void beforeSaveArtical(WxArticle article){
		if(article.getPicUrl()==null
				||article.getSize()==null
				||article.getTitle()==null
				||article.getUrl()==null){
			throw new EminException(BaseExCode.BIZ_ILLEGAL_PARAMETER);
		}
		if(article.getId()!=null && article.getId().longValue()==0l){
			article.setId(null);
		}
	}
	@Override
	public PagedResult<WxArticle> loadArticals(PageRequest request,Long woaId,String... match){
		PreFilter matchFilter = null;
		PreFilter woaFilter = PreFilters.eq(WxArticle.PROP_WOAID, woaId);
		if(match!=null && match.length>0){
			PreFilter[] filters = new PreFilter[match.length*2];
			for (int i=0;i<match.length;i++) {
				filters[i] = PreFilters.like(WxArticle.PROP_TITLE, "%"+match[i]+"%");
				filters[match.length+i] = PreFilters.like(WxArticle.PROP_DESCRIPTION, "%"+match[i]+"%");
			}
			matchFilter = PreFilters.or(filters);
		}
		return this.getPage(request, woaFilter,matchFilter,getStatusFilter());
	}
}
