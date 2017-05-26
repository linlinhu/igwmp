package com.emin.igwmp.pcs.service.impl;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.dao.PreFilter;
import com.emin.base.dao.PreFilters;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.base.service.Conditions;
import com.emin.base.service.UndeleteableServiceImpl;
import com.emin.igwmp.pcs.domain.DeductRule;
import com.emin.igwmp.pcs.domain.ProductPrice;
import com.emin.igwmp.pcs.exception.ExceptionCode;
import com.emin.igwmp.pcs.service.DeductRuleService;
import com.emin.igwmp.pcs.service.ProductPriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 */
@Service("deductRuleService")
public class DeductRuleServiceImpl extends UndeleteableServiceImpl<DeductRule> implements DeductRuleService {
    private Logger logger = LoggerFactory.getLogger(this.clazz);
    @Override
    public PagedResult<DeductRule> loadPagedDeductRuleByCondition(PageRequest pageRequest, List<Condition> conditions) {
        if (pageRequest == null) {
            logger.info("分页查询机运营规则信息参数异常 ");
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
    public PagedResult<DeductRule> loadPagedDeductRuleByMatch(PageRequest pageRequest, String... match) {
        PreFilter nameFilter = null;
        PreFilter productrFilter = null;
        PreFilter areaFilter = null;
        if (match != null && match.length > 0) {
            for (String m : match) {
                nameFilter = PreFilters.and(nameFilter, PreFilters.like(DeductRule.PROP_NAME, "%" + m + "%"));
                productrFilter = PreFilters.and(productrFilter, PreFilters.like(DeductRule.PROP_PRDUCT_ID, "%" + m + "%"));
                areaFilter = PreFilters.and(areaFilter, PreFilters.like(DeductRule.PROP_AREA_ID, "%" + m + "%"));
            }
        }
        PreFilter finalFilter = PreFilters.or(nameFilter, productrFilter,  areaFilter);

        return this.getPage(pageRequest, finalFilter, getStatusFilter());
    }

    @Override
    public void saveOrUpdataDeductRule(DeductRule deductRule) {
        if (deductRule == null) {
            logger.info("添加或更新运营规则表信息参数异常");
            throw new EminException(ExceptionCode.T_PARAMTERS_INVALID);
        }
        this.saveOrUpdate(deductRule);
    }


    @Override
    public void removeDeductRule(Long id) {
        if (id == null) {
            logger.info("删除运营规则表 错误" );
            throw new EminException(ExceptionCode.T_PARAMTERS_INVALID);
        }

        this.deleteById(id);
    }
}
