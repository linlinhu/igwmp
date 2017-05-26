package com.emin.wxs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.emin.base.dao.PreFilters;
import com.emin.base.exception.BaseExCode;
import com.emin.base.exception.EminException;
import com.emin.base.service.UndeleteableServiceImpl;
import com.emin.wxs.domain.WeixinWebPage;
import com.emin.wxs.domain.WeixinWebPageCategory;
import com.emin.wxs.service.WeixinWebPageCategoryService;
import com.emin.wxs.service.WeixinWebPageService;
@Service("weixinWebPageCategoryService")
public class WeixinWebPageCategoryServiceImpl extends UndeleteableServiceImpl<WeixinWebPageCategory> implements WeixinWebPageCategoryService{
	@Autowired
	@Qualifier("weixinWebPageService")
	private WeixinWebPageService webPageService;
	@Override
	public void saveOrUpdate(WeixinWebPageCategory webPageCategory){
		this.beforeSaveOrUpdate(webPageCategory);
		super.saveOrUpdate(webPageCategory);
	}
	private void beforeSaveOrUpdate(WeixinWebPageCategory webPageCategory){
		if(webPageCategory.getName()==null
				||webPageCategory.getPid()==null
				){
			throw new EminException(BaseExCode.BIZ_ILLEGAL_PARAMETER);
		}
		List<WeixinWebPageCategory> categories = this.findByPreFilter(PreFilters.eq(WeixinWebPageCategory.PROP_NAME, webPageCategory.getName()),PreFilters.eq(WeixinWebPageCategory.PROP_STATUS, WeixinWebPageCategory.STATUS_VALID));
		if(categories!=null && categories.size()>0 ){
			throw new EminException(BaseExCode.BIZ_NAME_EXISTS, webPageCategory.getName());
		}
		webPageCategory.setStatus(WeixinWebPageCategory.STATUS_VALID);
	}
	@Override
	public List<WeixinWebPageCategory> findByPid(Long pid,Long woaId){
		List<WeixinWebPageCategory> categories = this.findByPreFilter(PreFilters.eq(WeixinWebPageCategory.PROP_WOAID, woaId),PreFilters.eq(WeixinWebPageCategory.PROP_PID,pid),PreFilters.eq(WeixinWebPageCategory.PROP_STATUS, WeixinWebPageCategory.STATUS_VALID));
		return categories;
	}
	@Override
	public void deleteCategory(Long categoryId){
		List<WeixinWebPageCategory> categories = this.findByPreFilter(PreFilters.eq(WeixinWebPageCategory.PROP_PID,categoryId),PreFilters.eq(WeixinWebPageCategory.PROP_STATUS, WeixinWebPageCategory.STATUS_VALID));
		if(categories!=null && categories.size()>0){
			throw new EminException("");
		}
		Integer count = this.checkCategoryHasWebPage(categoryId);
		if(count>0){
		
			throw new EminException("");
			
		}
		super.deleteById(categoryId);
	}
	private Integer checkCategoryHasWebPage(Long categoryId){
		Integer count = webPageService.getCountByPreFilter(PreFilters.eq(WeixinWebPage.PROP_CATEGORYID, categoryId));
		if(count>0){
			return count;
		}
		List<WeixinWebPageCategory> categories = this.findByPreFilter(PreFilters.eq(WeixinWebPageCategory.PROP_PID, categoryId),getStatusFilter());
		for (WeixinWebPageCategory webPageCategory : categories) {
			count = checkCategoryHasWebPage(webPageCategory.getId());
		}
		
		return count;
	}
}
