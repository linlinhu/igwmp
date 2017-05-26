/**
 * 
 */
package com.emin.igwmp.prds.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.dao.PreFilter;
import com.emin.base.dao.PreFilters;
import com.emin.base.exception.EminException; 
import com.emin.base.service.Condition;
import com.emin.base.service.Conditions;
import com.emin.base.service.UndeleteableServiceImpl;
import com.emin.base.util.DesUtil;
import com.emin.igwmp.prds.domain.Product;
import com.emin.igwmp.prds.exception.ExceptionCode;
import com.emin.igwmp.prds.service.ProductService;

/** 
 *
 */
@Service("productService")
public class ProductServiceImpl extends UndeleteableServiceImpl<Product> implements ProductService {

	Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Override
	public void saveOrUpdate(Product product) {
		this.beforeSaveOrUpdate(product);
		super.saveOrUpdate(product);

//		log.error("异常日志");//在异常处理中输出   异常（除主动抛出异常）必须写  
//																
//		log.warn("关键日志");//关键步骤  如支付 订单保存 等
//																	
//		log.info("记录过程");//普通过程  如普通的增删改查 在确认没有异常的时候可以不写
//																		
//		log.debug("测试日志");//测试的时候使用  
		 
	}

	private void beforeSaveOrUpdate(Product product) {
		if (product == null || product.getDegree() == null		 
		|| product.getCategoryId() == null || product.getName() == null
		|| product.getNumber() == null   ) {
			throw new EminException(ExceptionCode.P_PARAMTERS_INVALID);
		}
		PreFilter idFilter = null;
		if (product.getId() != null && product.getId() != 0) {
			// 如果产品有ID则为修改,则判断处当前产品外编号是否重复 （若不抛开会一直提示重复）故加上ID条件
			idFilter = PreFilters.notEq(Product.PROP_ID, product.getId());
		}
		Product numberExists = this.findUniqueByPreFilter(PreFilters.eq(Product.PROP_NUMBER, product.getNumber()),
				getStatusFilter(), idFilter);
		if (numberExists != null) {
			throw new EminException(ExceptionCode.P_NUMBER_EXISTS, product.getNumber());
		}

	}

	@Override
	public PagedResult<Product> loadPagedProductsByMatch(PageRequest pageRequest, String... match) {
		PreFilter nameFilter = null;
		PreFilter numberFilter = null; 
		PreFilter categoryFilter = null;
		if (match != null && match.length > 0) {
			for (String m : match) {
				nameFilter = PreFilters.and(nameFilter, PreFilters.like(Product.PROP_NAME, "%" + m + "%"));
				numberFilter = PreFilters.and(numberFilter, PreFilters.like(Product.PROP_NUMBER, "%" + m + "%"));
				categoryFilter = PreFilters.and(categoryFilter, PreFilters.like(Product.PROP_CATEGORY_NAME, "%" + m + "%"));
			}
		} 
		PreFilter finalFilter = PreFilters.or(nameFilter, numberFilter,  categoryFilter);

		return this.getPage(pageRequest, finalFilter, getStatusFilter());
	}

	@Override
	public PagedResult<Product> loadPagedProductsByCondition(PageRequest pageRequest, List<Condition> conditions) {
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
	public Product findByProductCode(String code) {
		if (StringUtils.isBlank(code)) {
			throw new EminException(ExceptionCode.P_PARAMTERS_INVALID);
		}
		String rawCode = DesUtil.decrypt(code);
		return this.findUniqueByPreFilter(PreFilters.eq(Product.PROP_NUMBER, rawCode));
	}

	@Override
	public List<Product> getProductList(Long lastDateTime) {
		PreFilter dateFilter = PreFilters.gt("lastModifyTime", lastDateTime);
		PreFilter statusFilter = PreFilters.eq(Product.PROP_STATUS, Product.STATUS_VALID);
		return this.findByPreFilter(dateFilter, statusFilter);
	}

	@Override 
	public List<Product> findProducts(List<Condition> conditions) { 
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
		return this.findByPreFilter( filters);
	}
}
