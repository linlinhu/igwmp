package com.emin.igwmp.rstm.service;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.base.service.UndeleteableService;
import com.emin.igwmp.rstm.domain.CooperationPlanInfo;
import com.emin.igwmp.rstm.domain.RestaurantAdminInfo;
import com.emin.igwmp.rstm.domain.RestaurantPublicInfo;

/**
 * 合作方案管理类
 * @author zhaoqt
 *
 */
public interface CooperationPlanService extends UndeleteableService<CooperationPlanInfo>{
	
	/**
     * 分页加载合作方案列表
     * @param pageRequest
     * @param match
     * @return
     */
	public PagedResult<CooperationPlanInfo> loadPagedCooperationPlanByByCondition(PageRequest pageRequest , List<Condition> conditions);

}
