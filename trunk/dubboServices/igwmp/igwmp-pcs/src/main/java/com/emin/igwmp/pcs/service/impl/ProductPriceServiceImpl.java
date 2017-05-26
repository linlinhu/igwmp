package com.emin.igwmp.pcs.service.impl;

import com.emin.base.dao.PreFilter;
import com.emin.base.dao.PreFilters;
import com.emin.base.exception.EminException;
import com.emin.base.service.Conditions;
import com.emin.base.service.UndeleteableServiceImpl;
import com.emin.igwmp.pcs.domain.ProductPrice;
import com.emin.igwmp.pcs.exception.ExceptionCode;
import com.emin.igwmp.pcs.service.ProductPriceService;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.igwmp.pcs.utils.EntityCopyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 */
@Service("productPriceService")
public class ProductPriceServiceImpl extends UndeleteableServiceImpl<ProductPrice> implements ProductPriceService {
    private Logger logger = LoggerFactory.getLogger(this.clazz);
    @Override
    public PagedResult<ProductPrice> loadPagedProductPricesByCondition(PageRequest pageRequest, List<Condition> conditions) {
        if (pageRequest == null) {
            logger.info("分页查询产品价格参数异常 ");
            throw new EminException(ExceptionCode.T_PARAMTERS_INVALID);
        }

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
    public PagedResult<ProductPrice> loadPagedProductPricesByMatch(PageRequest pageRequest, String... match) {
        PreFilter nameFilter = null;
        PreFilter productrFilter = null;
        PreFilter areaFilter = null;
        if (match != null && match.length > 0) {
            for (String m : match) {
                nameFilter = PreFilters.and(nameFilter, PreFilters.like(ProductPrice.PROP_NAME, "%" + m + "%"));
                productrFilter = PreFilters.and(productrFilter, PreFilters.like(ProductPrice.PROP_PRDUCT_ID, "%" + m + "%"));
                areaFilter = PreFilters.and(areaFilter, PreFilters.like(ProductPrice.PROP_AREA_ID, "%" + m + "%"));
            }
        }
        PreFilter finalFilter = PreFilters.or(nameFilter, productrFilter,  areaFilter);

        return this.getPage(pageRequest, finalFilter, getStatusFilter());
    }

    @Override
    public void saveOrUpdataProductPrice(ProductPrice productPrice)  {
//        if (productPrice == null) {
//            logger.info("添加或更新产品价格信息参数异常");
//            throw new EminException(ExceptionCode.T_PARAMTERS_INVALID);
//        }
//        ProductPrice savePrice = null;
//        if(productPrice.getId() != null){
//            ProductPrice price = this.findById(productPrice.getId());
//            if(price != null){
//                try {
////                    EntityCopyUtils.SimilarCopy(productPrice,price);
//                    savePrice = price;
//                } catch (EminException e) {
//                    e.printStackTrace();
//                }
//            }
//        }else{
//            savePrice = productPrice;
//        }
        if(productPrice == null){
            throw  new EminException(ExceptionCode.PRDUCT_INFO_ERROR,"保存为空");
        }
        this.saveOrUpdate(productPrice);
    }


    @Override
    public void removeProductPrice(Long id)  {
        if (id == null) {
            logger.info("删除产品价格信息 错误" );
            throw new EminException(ExceptionCode.T_PARAMTERS_INVALID);
        }

        this.deleteById(id);
    }
}
