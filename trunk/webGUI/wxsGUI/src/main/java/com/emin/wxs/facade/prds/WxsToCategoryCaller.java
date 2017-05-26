package com.emin.wxs.facade.prds;

import java.util.List; 
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.igwmp.prds.domain.Category;

/**
 *  分类
 */
public interface WxsToCategoryCaller {
	
	public void saveOrUpdateCategory(Category category);
	/**
	 * 获取分类列表
	 * @param req 
	 * @param conditions
	 * @return
	 */
	 
	public PagedResult<Category> loadPagedCategorysByCondition(PageRequest req,List<Condition> conditions);
	
	void deleteCategory(Long id);
}



