package com.emin.igwmp.pcs.service;

import com.emin.igwmp.pcs.domain.DeductRule;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.base.service.UndeleteableService;
import com.emin.igwmp.pcs.domain.ProductPrice;

import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 */
public interface DeductRuleService extends UndeleteableService<DeductRule>  {
    /**
     * 分页查询运营规则列表
     * @param pageRequest
     * @param conditions
     * */
    public PagedResult<DeductRule> loadPagedDeductRuleByCondition(PageRequest pageRequest, List<Condition> conditions);


    /**
     * 根据关键字分页查询产品价格<br>
     * 关键字可用项:产品名称，分类名称，产品编号，标签名称
     * @param pageRequest
     * @param match
     * @return {@link PagedResult}
     * @see ProductPrice
     */
    public PagedResult<DeductRule> loadPagedDeductRuleByMatch(PageRequest pageRequest , String... match);

    /**
     * 添加或修改运营规则
     * */
    public void saveOrUpdataDeductRule(DeductRule deductRule);


    /**
     * 删除运营规则
     * */
    public void removeDeductRule(Long id);
}
