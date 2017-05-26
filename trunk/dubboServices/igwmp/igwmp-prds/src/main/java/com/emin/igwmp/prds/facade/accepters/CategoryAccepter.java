package com.emin.igwmp.prds.facade.accepters;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.igwmp.prds.domain.Category;

import net.sf.json.JSONArray; 

public interface CategoryAccepter {
	
	public PagedResult<Category> loadPagedImagesByCondition(PageRequest pageRequest, List<Condition> conditions)throws EminException;

	public List<Category> findCategory(List<Condition> conditions)throws EminException;
	
	public Category getCategory(Long id)throws EminException;
	
	public void saveOrUpdate(Category category)throws EminException;
	
	public void deleteCategory(Long id)throws EminException;
	
	public void deleteCategory(Category category) throws EminException;
	
	/**异步加载分类树
	 * @param parentId 父节点ID 加载第一级节点传NULL或0
	 * @param companyId
	 * @return 分类集合 List
	 */
	public List<Category> loadCategoryTreeAsync(Long parentId);

	/**同步加载分类树
	 * @param id 根据传入的分类ID 加载此分类 并递归所有子分类
	 * @param companyId
	 * @return JSONArray 
	 * @see Category
	 */
	public JSONArray loadCategoryTreeSync(Long id ); 
 
}
