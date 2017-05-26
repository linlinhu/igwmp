/**
 * 
 */
package com.emin.igwmp.prds.service;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.base.service.UndeleteableService;
import com.emin.igwmp.prds.domain.Product; 

/**产品管理接口
 * @author jim
 *
 */
public interface ProductService extends UndeleteableService<Product>{

	/**根据条件分页查询产品
	 * @param pageRequest
	 * @param conditions
	 * @return {@link PagedResult}
	 * @see Product
	 */
	public PagedResult<Product> loadPagedProductsByCondition(PageRequest pageRequest, List<Condition> conditions);

	/**
	 * 根据关键字分页查询产品<br>
	 * 关键字可用项:产品名称，分类名称，产品编号，标签名称
	 * @param pageRequest 
	 * @param match
	 * @return {@link PagedResult}
	 * @see Product
	 */
	public PagedResult<Product> loadPagedProductsByMatch(PageRequest pageRequest , String... match);

	/**根据产品二维码查看产品信息 属性：Product -> productCode
	 * @param code
	 * @return
	 */
	Product findByProductCode(String code);
	
	/**
	 * 获取产品列表 
	 * @param lastDateTime 最后一次更新时间 Long类型
	 * @return 产品列表
	 */
	List<Product> getProductList(Long lastDateTime);

	public List<Product> findProducts(List<Condition> conditions);

}
