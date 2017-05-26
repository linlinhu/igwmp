package com.emin.igwmp.pcs.facade.accepter;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.igwmp.pcs.domain.DeductRule;
import com.emin.igwmp.pcs.domain.ProductPrice;

import java.util.List;

/**
 * Created by Administrator on 2017/3/27.
 */
public interface DeductRuleAccepter {
    /**根据条件分页查询产品价格
     * @param pageRequest
     * @param conditions
     * @return {@link PagedResult}
     * @see ProductPrice
     */
    public PagedResult<DeductRule> loadPagedDeductRuleByCondition(PageRequest pageRequest, List<Condition> conditions);

    /**
     * 根据关键字分页查询产品价格<br>
     * 关键字可用项:产品名称，分类名称，产品编号
     * @param pageRequest
     * @param match
     * @return {@link PagedResult}
     * @see ProductPrice
     */
    public PagedResult<DeductRule> loadPagedDeductRuleByMatch(PageRequest pageRequest , String... match);


    public void saveOrUpdateDeductRule(DeductRule deductRule) throws EminException;

    public void deleteDeductRule(Long priceId) throws EminException;
}
