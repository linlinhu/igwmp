package com.emin.wxs.facade.restaurant.callers.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.igwmp.rstm.domain.CooperationPlanInfo;
import com.emin.igwmp.rstm.domain.RestaurantPrivateInfo;
import com.emin.igwmp.rstm.facade.accepters.ContractAccepter;
import com.emin.igwmp.rstm.facade.accepters.CooperationPlanAccepter;
import com.emin.igwmp.rstm.facade.accepters.RestaurantAccepter;
import com.emin.wxs.facade.restaurant.callers.WxsToContractCaller;

@Service("wxsToContractCaller")
public class WxsToContractCallerImpl implements WxsToContractCaller{

	@Reference(version="0.0.1")
	ContractAccepter contractAccepter;
	
	@Reference(version="0.0.1")
	CooperationPlanAccepter cooperationPlanAccepter;
	
	@Override
	public PagedResult<RestaurantPrivateInfo> queryContractByCondition(
			PageRequest pageRequest, List<Condition> conditions) {
		
		return contractAccepter.queryContractByCondition(pageRequest, conditions);
	}

	@Override
	public void saveContract(RestaurantPrivateInfo restPriInfo) {
		contractAccepter.saveContract(restPriInfo);		
	}

	@Override
	public void removeContract(Long id) {
		contractAccepter.removeContract(id);		
	}
	
	@Override
	public RestaurantPrivateInfo queryContractById(Long id) {
	
		return contractAccepter.queryContractById(id);
	}

	@Override
	public PagedResult<CooperationPlanInfo> queryCooperationPlanInfoByCondition(
			PageRequest pageRequest, List<Condition> conditions) {
		return cooperationPlanAccepter.queryCooperationPlanByCondition(pageRequest, conditions);
	}

	@Override
	public void saveCooperationPlanInfo(CooperationPlanInfo cooperationPlanInfo) {
		cooperationPlanAccepter.saveCooperationPlan(cooperationPlanInfo);
		
	}

	@Override
	public void removeCooperationPlanInfo(Long id) {
		cooperationPlanAccepter.removeCooperationPlan(id);
		
	}

	@Override
	public CooperationPlanInfo queryCooperationPlanById(Long id) {
		return cooperationPlanAccepter.queryCooperationPlanById(id);
	}

	@Override
	public String getRgstNumBySignedFlag(boolean isSigned) {
		
		return contractAccepter.getRgstNumBySignedFlag(isSigned);
	}
}
