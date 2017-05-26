package com.emin.wxs.facade.prds.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult; 
import com.emin.base.service.Condition; 
import com.emin.igwmp.prds.domain.Category;
import com.emin.igwmp.prds.facade.accepters.CategoryAccepter;
import com.emin.wxs.facade.prds.WxsToCategoryCaller;

import org.springframework.stereotype.Component; 
import java.util.List;
 
  
/** 
 * 商品
 * @author Administrator
 *
 */
@Component("wxsToCategoryCaller")
public class WxsToCategoryCallerImpl implements WxsToCategoryCaller {
	
	@Reference(version="0.0.1")
	private CategoryAccepter categoryAccepter;

	@Override
	public void saveOrUpdateCategory(Category category) {
		categoryAccepter.saveOrUpdate(category);
		
	}

	@Override
	public PagedResult<Category> loadPagedCategorysByCondition(PageRequest req, List<Condition> conditions) {
		PagedResult<Category> categorys = categoryAccepter.loadPagedImagesByCondition(req, conditions);
		return categorys;
	}

	@Override
	public void deleteCategory(Long id) {
		categoryAccepter.deleteCategory(id);
		
	} 
	
}
