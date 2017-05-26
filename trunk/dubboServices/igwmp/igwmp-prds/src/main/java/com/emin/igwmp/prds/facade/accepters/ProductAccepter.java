package com.emin.igwmp.prds.facade.accepters;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.igwmp.prds.domain.Images;
import com.emin.igwmp.prds.domain.Product;
import com.emin.igwmp.prds.domain.ProductDetail;

public interface ProductAccepter {

	
	public Product getProductById(Long productId)throws EminException;
	
	public Product saveProduct(String product) throws EminException;
	
	
	public Product saveOrUpdateProduct(Product product) throws EminException;
	
	public void deleteProduct(Long id) throws EminException;
	 
	
	public List<Product> findProducts(List<Condition> conditions)throws EminException;
	
	/**根据条件分页查询产品
	 * @param pageRequest
	 * @param conditions
	 * @return {@link PagedResult}
	 * @see Product
	 */
	public PagedResult<Product> loadPagedProductsByCondition(PageRequest pageRequest, List<Condition> conditions)throws EminException;

	/**
	 * 根据关键字分页查询产品<br>
	 * 关键字可用项:产品名称，分类名称，产品编号，标签名称
	 * @param pageRequest 
	 * @param match
	 * @return {@link PagedResult}
	 * @see Product
	 */
	public PagedResult<Product> loadPagedProductsByMatch(PageRequest pageRequest , String... match)throws EminException;

 
	
	public ProductDetail saveOrUpdateProductDetail(ProductDetail productDetail) throws EminException;
	
	public void deleteProductDetail(Long productId) throws EminException;

	public List<ProductDetail> findProductDetail(Long[] productIds) throws EminException;
	
	public List<ProductDetail> findProductDetails() throws EminException;
	
	public ProductDetail getProductDetail(Long productId)throws EminException;
	 
	
	
	public void saveOrUpdateImages(List<Images> images,Long productId,Integer type) throws EminException;
	
	public Images saveOrUpdateImage(Images img)throws EminException;
	
	public void deleteImages(Long productId) throws EminException;
	
	public List<Images> findImages(Long productId,Integer type) throws EminException;
	
 
}
