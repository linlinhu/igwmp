package com.emin.igwmp.pcs;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.igwmp.pcs.base.BaseJunit4Test;
import com.emin.igwmp.pcs.base.ItemDataTest;
import com.emin.igwmp.pcs.domain.DeductRule;
import com.emin.igwmp.pcs.service.DeductRuleService;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/3/27.
 */
public class DeductRuleTest extends BaseJunit4Test {
    /**
     *
     */
    @Resource(name = "deductRuleService")
    DeductRuleService deductRuleService;
    //测试项1  添加数据库
    @Test
    @Rollback(false)
    public void AddDuductRuleTest(){
        DeductRule item = ItemDataTest.getDeductRule();
        deductRuleService.saveOrUpdataDeductRule(item);
    }

    @Test
    @Rollback(false)
    public void findPagedDuductRuleByMatch(){
        PageRequest pageRequest = new PageRequest(0,5);
        PagedResult<DeductRule> pr = deductRuleService.loadPagedDeductRuleByCondition(pageRequest, null);
        if(pr != null){
            List<DeductRule> pricePages =  pr.getResultList();
            if(pricePages != null && !pricePages.isEmpty()){
                for (DeductRule price : pricePages) {
                    ItemDataTest.DisplayEntity(this.getClass(),price);
                }
            }
        }
    }

    @Test
    @Rollback(false)
    public void deleteDuductRuleTest(){

    }
}
