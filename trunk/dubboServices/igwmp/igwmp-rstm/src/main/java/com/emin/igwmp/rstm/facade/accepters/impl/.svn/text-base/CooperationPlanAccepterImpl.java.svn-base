package com.emin.igwmp.rstm.facade.accepters.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.igwmp.rstm.domain.CooperationPlanInfo;
import com.emin.igwmp.rstm.facade.accepters.CooperationPlanAccepter;
import com.emin.igwmp.rstm.service.CooperationPlanService;

@Service(version="0.0.1")
@Component("cooperationPlanAccepter")
public class CooperationPlanAccepterImpl implements
		CooperationPlanAccepter {
    @Resource
	CooperationPlanService cooperationPlanService;
     
	@Override
	public PagedResult<CooperationPlanInfo> queryCooperationPlanByCondition(
			PageRequest pageRequest, List<Condition> conditions) {
		
		return cooperationPlanService.loadPagedCooperationPlanByByCondition(pageRequest, conditions);
	}

	@Override
	public void saveCooperationPlan(CooperationPlanInfo cooperationPlanInfo) {
		
		cooperationPlanService.saveOrUpdate(cooperationPlanInfo);
	}

	@Override
	public void removeCooperationPlan(Long id) {
		
		cooperationPlanService.deleteById(id);
	}

	@Override
	public CooperationPlanInfo queryCooperationPlanById(Long id) {
		
		return cooperationPlanService.findById(id);
	}

}
