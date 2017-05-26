package com.emin.wxs.facade.pcs.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.igwmp.pcs.domain.DeductRule;
import com.emin.igwmp.pcs.domain.ProductPrice;
import com.emin.igwmp.pcs.facade.accepter.DeductRuleAccepter;
import com.emin.igwmp.pcs.facade.accepter.ProductPriceAccepter;
import com.emin.wxs.facade.pcs.WxsToDeductRuleCaller;
import com.emin.wxs.vo.pcs.Convert;
import com.emin.wxs.vo.pcs.DeductRuleVO;
import com.emin.wxs.vo.pcs.ProductPriceVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/28.
 */
@Component("wxsToDeductRuleCaller")
public class WxsToDeductRuleCallerImpl implements WxsToDeductRuleCaller {

    @Reference(version="0.0.1")
    private DeductRuleAccepter deductRuleAccepter;


    @Override
    public void saveOrUpdateDeductRule(DeductRuleVO deductRuleVO) {
        deductRuleAccepter.saveOrUpdateDeductRule(Convert.ConvertDeductRule(deductRuleVO));
    }

    @Override
    public PagedResult<DeductRuleVO> loadPagedDeductRuleByCondition(PageRequest req, List<Condition> conditions) {
        PagedResult<DeductRule> products = deductRuleAccepter.loadPagedDeductRuleByCondition(req,null );
        List<DeductRuleVO> vos = new ArrayList<>();
        for(DeductRule product : products.getResultList()){
            vos.add(Convert.DeductRuleToVO(product));
        }
        PagedResult<DeductRuleVO> result = new PagedResult<>(vos, products.getNextOffset(), products.getTotalCount());
        return result;
    }

    @Override
    public void deleteDeductRule(Long id) {
        deductRuleAccepter.deleteDeductRule(id);
    }
}
