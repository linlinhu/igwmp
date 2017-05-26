package com.emin.igwmp.pcs;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.igwmp.pcs.base.BaseJunit4Test;
import com.emin.igwmp.pcs.base.ItemDataTest;
import com.emin.igwmp.pcs.domain.ProductPrice;
import com.emin.igwmp.pcs.service.ProductPriceService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/3/21.
 */
public class ProductPriceTest extends BaseJunit4Test {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     *
     */
    @Resource(name = "productPriceService")
    ProductPriceService productPriceService;
    //测试项1  添加数据库
    @Test
    @Rollback(false)
    public void AddTest(){
        ProductPrice item = ItemDataTest.getProductPrice();
        productPriceService.saveOrUpdataProductPrice(item);
    }

    @Test
    @Rollback(false)
    public void findPagedProductPriceoByMatch(){
        PageRequest pageRequest = new PageRequest(0,5);
        PagedResult<ProductPrice> pr = productPriceService.loadPagedProductPricesByCondition(pageRequest, null);
        if(pr != null){
            List<ProductPrice> pricePages =  pr.getResultList();
            if(pricePages != null && !pricePages.isEmpty()){
                for (ProductPrice price : pricePages) {
                    ItemDataTest.DisplayEntity(this.getClass(),price);
                }
            }
        }
    }

    @Test
    @Rollback(false)
    public void deleteTest(){
        Long id = 5l;
        productPriceService.removeProductPrice(id);
    }

    //
}
