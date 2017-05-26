package com.emin.wxs.facade.restaurant.callers.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.igwmp.rstm.domain.RestaurantPublicInfo;
import com.emin.igwmp.rstm.facade.accepters.RestaurantAccepter;
import com.emin.wxs.facade.restaurant.callers.WxsToRestaurantCaller;

@Service("wxsToRestaurantCaller")
public class WxsToRestaurantCallerImpl implements WxsToRestaurantCaller{

	@Reference(version="0.0.1")
	RestaurantAccepter restaurantAccepter;
	
	@Override
	public PagedResult<RestaurantPublicInfo> queryRestaurantPublicInfoByCondition(
			PageRequest pageRequest, List<Condition> conditions) {
		PagedResult<RestaurantPublicInfo>  pageResult = restaurantAccepter.queryRestaurantPublicInfoByCondition(pageRequest, conditions);
		return pageResult;
	}

	@Override
	public Long saveRestaurantInfo(RestaurantPublicInfo restaurantPublicInfo) {		
	   
		 return  restaurantAccepter.saveRestaurantInfo(restaurantPublicInfo);	  
	}

	@Override
	public void removeRestaurantInfo(Long id) {
		restaurantAccepter.removeRestaurantInfo(id);
		return;
	}

	@Override
	public RestaurantPublicInfo queryRestaurantPublicInfoById(Long id) {
		
		return restaurantAccepter.queryRestaurantPublicInfoById(id);
	}

}
