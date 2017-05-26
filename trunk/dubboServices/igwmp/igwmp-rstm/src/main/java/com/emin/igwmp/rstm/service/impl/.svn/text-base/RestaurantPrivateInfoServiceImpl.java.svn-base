package com.emin.igwmp.rstm.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.igwmp.rstm.domain.RestaurantPrivateInfo;
import com.emin.igwmp.rstm.service.RestaurantPrivateInfoService;

@Service("restaurantPrivateInfoService")
public class RestaurantPrivateInfoServiceImpl extends RstmBaseServiceImpl<RestaurantPrivateInfo> implements RestaurantPrivateInfoService {

	@Override
	public PagedResult<RestaurantPrivateInfo> loadRestaurantPrivateInfoByCondition(
			PageRequest pageRequest, List<Condition> conditions) {
		return this.getPage(pageRequest,transferConditionsToPreFilter(conditions), getStatusFilter());
	}
    
}
