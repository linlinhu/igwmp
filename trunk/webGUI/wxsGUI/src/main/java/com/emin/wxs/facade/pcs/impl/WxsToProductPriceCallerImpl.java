package com.emin.wxs.facade.pcs.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.igwmp.pcs.domain.ProductPrice;
import com.emin.igwmp.pcs.facade.accepter.ProductPriceAccepter;
import com.emin.wxs.facade.pcs.WxsToProductPriceCaller;
import com.emin.wxs.vo.pcs.Convert;
import com.emin.wxs.vo.pcs.ProductPriceVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/25.
 */
@Component("wxsToProductPriceCaller")
public class WxsToProductPriceCallerImpl implements WxsToProductPriceCaller {

    @Reference(version="0.0.1")
    private ProductPriceAccepter productPriceAccepter;

    @Override
    public void saveOrUpdateProductPrice(ProductPriceVO productPriceVO) {
        productPriceAccepter.saveOrUpdateProductPrice(Convert.ConvertProductPrice(productPriceVO));
    }

    @Override
    public PagedResult<ProductPriceVO> loadPagedProductPriceByCondition(PageRequest req, List<Condition> conditions) {
        PagedResult<ProductPrice> products = productPriceAccepter.loadPagedProductPriceByCondition(req,conditions );
        List<ProductPriceVO> vos = new ArrayList<>();
        for(ProductPrice product : products.getResultList()){
            vos.add(Convert.ProductPriceToVO(product));
        }
        PagedResult<ProductPriceVO> result = new PagedResult<>(vos, products.getNextOffset(), products.getTotalCount());
        return result;
    }

    @Override
    public void deleteProductPrice(Long id) {
        productPriceAccepter.deleteProductPrice(id);
    }
}
