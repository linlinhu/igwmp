package com.emin.igwmp.pcs.facade.accepter.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.dao.PreFilter;
import com.emin.base.dao.PreFilters;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.igwmp.pcs.domain.ProductPrice;
import com.emin.igwmp.pcs.exception.ExceptionCode;
import com.emin.igwmp.pcs.facade.accepter.ProductPriceAccepter;
import com.emin.igwmp.pcs.service.ProductPriceService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/3/23.
 */
@Service(version = "0.0.1")
@Component("productPriceAccepter")
public class ProductPriceAccepterImpl implements ProductPriceAccepter {

	@Resource(name = "productPriceService")
	ProductPriceService productPriceService;

	@Override
	public PagedResult<ProductPrice> loadPagedProductPriceByCondition(PageRequest pageRequest,
			List<Condition> conditions) {
		if (productPriceService == null) {
			throw new EminException(ExceptionCode.C_PARAMTERS_INVALID, "ProductService Fail");
		}
		return productPriceService.loadPagedProductPricesByCondition(pageRequest, conditions);
	}

	@Override
	public PagedResult<ProductPrice> loadPagedProductPriceByMatch(PageRequest pageRequest, String... match) {
		if (productPriceService == null) {
			throw new EminException(ExceptionCode.C_PARAMTERS_INVALID, "ProductService Fail");
		}
		return productPriceService.loadPagedProductPricesByMatch(pageRequest, match);
	}

	@Override
	public void saveOrUpdateProductPrice(ProductPrice productPrice) throws EminException {
		if (productPriceService == null) {
			throw new EminException(ExceptionCode.C_PARAMTERS_INVALID, "ProductService  Save Fail");
		}
		productPriceService.saveOrUpdataProductPrice(productPrice);
	}

	@Override
	public void deleteProductPrice(Long priceId) throws EminException {
		if (productPriceService == null) {
			throw new EminException(ExceptionCode.C_PARAMTERS_INVALID, "ProductService  Save Fail");
		}
		productPriceService.removeProductPrice(priceId);
	}

	@Override
	public ProductPrice getProductPrice(Long productId, Long areaId) throws EminException {
		PreFilter preFilter = null;
		PreFilter statusFilter = productPriceService.getStatusFilter();
		PreFilter productFilter = PreFilters.eq(ProductPrice.PROP_PRDUCT_ID, productId);
		if (areaId > 0) {
			preFilter = PreFilters.eq(ProductPrice.PROP_AREA_ID, areaId);
		}
		List<ProductPrice> list = productPriceService.findByPreFilter(productFilter, preFilter, statusFilter);
		if (null == list || list.size() <= 0){
			return null;
		}
		return list.get(0);
	}
}
