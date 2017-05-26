/**
 * 
 */
package com.emin.igwmp.prds.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.dao.PreFilter;
import com.emin.base.dao.PreFilters;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.base.service.Conditions;
import com.emin.base.service.UndeleteableServiceImpl;
import com.emin.igwmp.prds.domain.Category;
import com.emin.igwmp.prds.exception.ExceptionCode;
import com.emin.igwmp.prds.service.CategoryService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject; 

/** 
 *
 */
@Service("categoryService")
public class CategoryServiceImpl extends UndeleteableServiceImpl<Category> implements CategoryService{
	
	@Override
	public void saveOrUpdate(Category category){
		this.beforeSaveOrUpdate(category);
		category.setLastModifyTime(System.currentTimeMillis());
		if(null==category.getId()||category.getId()<=0){
			category.setCreateTime(System.currentTimeMillis());
			category.setStatus(Category.STATUS_VALID);
		}
		super.saveOrUpdate(category);
	}
	private void beforeSaveOrUpdate(Category category){
		if(category==null || category.getName()==null ){
			throw new EminException(ExceptionCode.C_PARAMTERS_INVALID);
		}
		if(category.getParentId()==null && category.getId()!=null){
			category.setParentId(0l);
		}
	}
	@Override
	public List<Category> loadCategoryTreeAsync(Long parentId ){
		if(parentId==null){
			parentId=0l;
		} 
		PreFilter parentFilter = PreFilters.eq(Category.PROP_PARENT_ID, parentId); 
		return this.findByPreFilter(parentFilter,getStatusFilter());
	}
	@Override
	public JSONArray loadCategoryTreeSync(Long id ){
		JSONArray categories = new JSONArray();
		Category current = null;
		if(id==null){
			id=0l;
		}else{
			//若ID不为空 则查询本分类
			current = findById(id);			
		}
		JSONArray childrenArr = new JSONArray();
		List<Category> children = this.loadCategoryTreeAsync(id);
		for (Category category : children) {  	
			JSONObject c =   JSONObject.fromObject(category); 
			c.put("children", loadCategoryTreeAsync(category.getId()));
			childrenArr.add(c);			
		}
		if(current!=null){
			//如果ID不为null 将本分类作为第一节点
			JSONObject c =  JSONObject.fromObject(current);   
			c.put("children", childrenArr);
			categories.add(c);
		}else{
			categories = childrenArr;
		}
		return categories;
	}
	@Override
	public PagedResult<Category> loadPagedImagesByCondition(PageRequest pageRequest, List<Condition> conditions) {

		List<PreFilter> filterList = new ArrayList<PreFilter>();
		filterList.add(getStatusFilter());
		if (conditions != null) {
			for (Condition condition : conditions) {

				PreFilter filter = Conditions.convertToPropertyFilter(condition);
				filterList.add(filter);
			}
		}
		PreFilter[] filters = new PreFilter[filterList.size()];
		filters = filterList.toArray(filters);
		return this.getPage(pageRequest, filters);
	}
	@Override
	public List<Category> findCategory(List<Condition> conditions) {
		List<PreFilter> filterList = new ArrayList<PreFilter>();
		filterList.add(getStatusFilter());
		if (conditions != null) {
			for (Condition condition : conditions) {

				PreFilter filter = Conditions.convertToPropertyFilter(condition);
				filterList.add(filter);
			}
		}
		PreFilter[] filters = new PreFilter[filterList.size()];
		filters = filterList.toArray(filters);
		return this.findByPreFilter(filters);
	}
 
}
