package com.emin.wxs.facade.restaurant.callers;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.igwmp.rstm.domain.CooperationPlanInfo;
import com.emin.igwmp.rstm.domain.RestaurantAdminInfo;
import com.emin.igwmp.rstm.domain.RestaurantPrivateInfo;
import com.emin.igwmp.rstm.domain.RestaurantPublicInfo;


/**
 * 调用合同相关信息
 * @author zhaoqt
 *
 */
public interface WxsToContractCaller {
	
	/**
     * 根据条件分页查询服务员列表
     * @param pageRequest
     * @param conditions
     * @return
     */
	public PagedResult<RestaurantPrivateInfo> queryContractByCondition(PageRequest pageRequest , List<Condition> conditions);

	/**
	 * 保存合同信息
	 * @param id
	 */
	void saveContract(RestaurantPrivateInfo id);
	
	 /**
     * 根据ID删除合同信息表
     *
     * @param id
     */
    void removeContract(Long id);
    
    RestaurantPrivateInfo queryContractById(Long id);
    
    /**
     * 根据签署获取一定规则的合同编码
     * @param isSigned
     * @return
     */
    String getRgstNumBySignedFlag(boolean isSigned);
    
    /**
     * 根据条件分页查询CooperationPlan列表
     * @param pageRequest
     * @param conditions
     * @return
     */
	public PagedResult<CooperationPlanInfo> queryCooperationPlanInfoByCondition(PageRequest pageRequest , List<Condition> conditions);

	/**
	 * 保存CooperationPlan信息
	 * @param id
	 */
	void saveCooperationPlanInfo(CooperationPlanInfo id);
	
	 /**
     * 根据ID删除CooperationPlan信息表
     *
     * @param id
     */
    void removeCooperationPlanInfo(Long id);

    CooperationPlanInfo queryCooperationPlanById(Long id);
}
