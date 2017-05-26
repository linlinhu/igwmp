package com.emin.wxs.facade.pcs;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.wxs.vo.ProductVO;
import com.emin.wxs.vo.pcs.ProductPriceVO;

import java.util.List;

/**
 * Created by Administrator on 2017/3/25.
 */
public interface WxsToProductPriceCaller {
    public void saveOrUpdateProductPrice(ProductPriceVO productPriceVO);

    public PagedResult<ProductPriceVO> loadPagedProductPriceByCondition(PageRequest req, List<Condition> conditions);

    void deleteProductPrice(Long id);
}
