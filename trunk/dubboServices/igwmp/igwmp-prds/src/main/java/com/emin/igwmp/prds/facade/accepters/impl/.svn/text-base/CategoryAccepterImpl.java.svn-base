package com.emin.igwmp.prds.facade.accepters.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.igwmp.prds.domain.Category;
import com.emin.igwmp.prds.facade.accepters.CategoryAccepter;
import com.emin.igwmp.prds.service.CategoryService;

import net.sf.json.JSONArray;
@Component("categoryAccepter")
@Service(version="0.0.1")
public class CategoryAccepterImpl implements CategoryAccepter {
	@Autowired
	@Qualifier("categoryService")
	private CategoryService categoryService;
	
	@Override
	public PagedResult<Category> loadPagedImagesByCondition(PageRequest pageRequest, List<Condition> conditions)
			throws EminException {
		return categoryService.loadPagedImagesByCondition(pageRequest,conditions);
	}

	@Override
	public List<Category> findCategory(List<Condition> conditions) throws EminException {
		return categoryService.findCategory(conditions);
	}

	@Override
	public Category getCategory(Long id) throws EminException {	 
		return categoryService.findById(id);
	}

	@Override
	public void saveOrUpdate(Category category) throws EminException {
		if(null==category.getId()||category.getId()<=0){
			category.setCreateTime(System.currentTimeMillis());
			category.setParentId(0L); 
			category.setId(null);
		}
		category.setLastModifyTime(System.currentTimeMillis());
		category.setStatus(Category.STATUS_VALID);
		categoryService.saveOrUpdate(category);
	}
 
	@Override
	public List<Category> loadCategoryTreeAsync(Long parentId) {		 
		return categoryService.loadCategoryTreeAsync(parentId);
	}

	@Override
	public JSONArray loadCategoryTreeSync(Long id) {		 
		return categoryService.loadCategoryTreeSync(id);
	}

	@Override
	public void deleteCategory(Long id) throws EminException {
		categoryService.deleteById(id);		
	}

	@Override
	public void deleteCategory(Category category) throws EminException {
		categoryService.delete(category);		
	}

}
