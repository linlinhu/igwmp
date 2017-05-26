package com.emin.igwmp.rstm.facade.accepters;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.igwmp.rstm.domain.CooperationPlanInfo;


/**
 * 调用服务员相关信息
 * @author zhaoqt
 *
 */
public interface CooperationPlanAccepter {
	
	/**
     * 根据条件分页查询服务员列表
     * @param pageRequest
     * @param conditions
     * @return
     */
	public PagedResult<CooperationPlanInfo> queryCooperationPlanByCondition(PageRequest pageRequest , List<Condition> conditions);

	/**
	 * 保存合同信息
	 * @param id
	 */
	void saveCooperationPlan(CooperationPlanInfo id);
	
	 /**
     * 根据ID删除合同信息表
     *
     * @param id
     */
    void removeCooperationPlan(Long id);

    CooperationPlanInfo queryCooperationPlanById(Long id);
}
