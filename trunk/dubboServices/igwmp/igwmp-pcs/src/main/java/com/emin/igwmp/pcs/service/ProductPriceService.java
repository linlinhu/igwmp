package com.emin.igwmp.pcs.service;

import com.emin.igwmp.pcs.domain.ProductPrice;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.base.service.UndeleteableService;
import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 */
public interface ProductPriceService extends UndeleteableService<ProductPrice>  {

    /**
     * 分页查询产品价格列表
     * @param pageRequest
     * @param conditions
     * */
    public PagedResult<ProductPrice> loadPagedProductPricesByCondition(PageRequest pageRequest, List<Condition> conditions);

    /**
     * 根据关键字分页查询产品价格<br>
     * 关键字可用项:产品名称，分类名称，产品编号，标签名称
     * @param pageRequest
     * @param match
     * @return {@link PagedResult}
     * @see ProductPrice
     */
    public PagedResult<ProductPrice> loadPagedProductPricesByMatch(PageRequest pageRequest , String... match);

    /**
     * 添加产品价格
     * */
    public void saveOrUpdataProductPrice(ProductPrice productPrice);


    /**
     * 删除产品价格
     * */
    public void removeProductPrice(Long id);

}
