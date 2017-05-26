package com.emin.igwmp.rstm.facade.accepters.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.igwmp.rstm.domain.RestaurantPublicInfo;
import com.emin.igwmp.rstm.domain.RestaurantServantInfo;
import com.emin.igwmp.rstm.facade.accepters.ServantAccepter;
import com.emin.igwmp.rstm.service.RestaurantServantService;
import com.emin.igwmp.rstm.service.RstmBaseService;


/**
 * 调用合同相关信息
 * @author zhaoqt
 *
 */
@Service(version="0.0.1")
@Component("servantAccepter")
public class ServantAccepterImpl implements ServantAccepter{
    
	@Resource
    RestaurantServantService restaurantServantService;
	@Resource
	RstmBaseService<RestaurantPublicInfo> rstmBaseService;
	
	@Override
	public RestaurantServantInfo queryServantInfoById(Long id) {
		
		return restaurantServantService.findById(id);
	}

	@Override
	public PagedResult<RestaurantServantInfo> queryServantInfoByCondition(
			PageRequest pageRequest, List<Condition> conditions) {
		
		return restaurantServantService.loadPagedRestaurantServantInfoByCondition(pageRequest, conditions);
	}

	@Override
	public void saveServantInfo(RestaurantServantInfo id) {
		restaurantServantService.saveOrUpdate(id); 
	}

	@Override
	public void removeServantInfo(Long id) {
		
		restaurantServantService.deleteById(id);
	}

	@Override
	public List<RestaurantServantInfo> queryRestaurantServantForListByCondition(
			List<Condition> conditions) {
		
		return restaurantServantService.findByPreFilter(rstmBaseService.transferConditionsToPreFilter(conditions));
	}

	@Override
	public RestaurantServantInfo queryRestaurantServantObjByCondition(
			List<Condition> conditions) {
	
		return restaurantServantService.findUniqueByPreFilter(rstmBaseService.transferConditionsToPreFilter(conditions));
	}	
}
