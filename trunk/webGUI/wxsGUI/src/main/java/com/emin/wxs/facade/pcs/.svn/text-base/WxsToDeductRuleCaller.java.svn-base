package com.emin.wxs.facade.pcs;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.wxs.vo.pcs.DeductRuleVO;

import java.util.List;

/**
 * Created by Administrator on 2017/3/28.
 */
public interface WxsToDeductRuleCaller {

    public void saveOrUpdateDeductRule(DeductRuleVO deductRuleVO);

    public PagedResult<DeductRuleVO> loadPagedDeductRuleByCondition(PageRequest req, List<Condition> conditions);

    void deleteDeductRule(Long id);
}
