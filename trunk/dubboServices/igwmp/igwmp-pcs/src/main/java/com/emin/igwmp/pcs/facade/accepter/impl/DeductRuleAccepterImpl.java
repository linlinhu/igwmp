package com.emin.igwmp.pcs.facade.accepter.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.igwmp.pcs.domain.DeductRule;
import com.emin.igwmp.pcs.exception.ExceptionCode;
import com.emin.igwmp.pcs.facade.accepter.DeductRuleAccepter;
import com.emin.igwmp.pcs.service.DeductRuleService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/3/27.
 */
@Service(version="0.0.1")
@Component("deductRuleAccepter")
public class DeductRuleAccepterImpl implements DeductRuleAccepter{

    @Resource(name = "deductRuleService")
    DeductRuleService deductRuleService;

    @Override
    public PagedResult<DeductRule> loadPagedDeductRuleByCondition(PageRequest pageRequest, List<Condition> conditions) {
        if(deductRuleService == null){
            throw new EminException(ExceptionCode.C_PARAMTERS_INVALID,"DeductRule Fail");
        }
        return deductRuleService.loadPagedDeductRuleByCondition(pageRequest,conditions);
    }

    @Override
    public PagedResult<DeductRule> loadPagedDeductRuleByMatch(PageRequest pageRequest, String... match) {
        if(deductRuleService == null){
            throw new EminException(ExceptionCode.C_PARAMTERS_INVALID,"DeductRule Fail");
        }
        return deductRuleService.loadPagedDeductRuleByMatch(pageRequest,match);
    }

    @Override
    public void saveOrUpdateDeductRule(DeductRule deductRule) throws EminException {
        if(deductRuleService == null){
            throw new EminException(ExceptionCode.C_PARAMTERS_INVALID,"DeductRule  Save Fail");
        }
        deductRuleService.saveOrUpdataDeductRule(deductRule);
    }

    @Override
    public void deleteDeductRule(Long priceId) throws EminException {
        if(deductRuleService == null){
            throw new EminException(ExceptionCode.C_PARAMTERS_INVALID,"DeductRule  Save Fail");
        }
        deductRuleService.removeDeductRule(priceId);
    }
}
