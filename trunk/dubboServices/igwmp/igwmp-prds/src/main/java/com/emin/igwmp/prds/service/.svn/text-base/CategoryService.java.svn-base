/**
 * 
 */
package com.emin.igwmp.prds.service;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.base.service.UndeleteableService;
import com.emin.igwmp.prds.domain.Category;

import net.sf.json.JSONArray; 

/**产品分类接口
 * @author jim
 *
 */
public interface CategoryService extends UndeleteableService<Category>{

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

	public PagedResult<Category> loadPagedImagesByCondition(PageRequest pageRequest, List<Condition> conditions);

	public List<Category> findCategory(List<Condition> conditions);

 
}
