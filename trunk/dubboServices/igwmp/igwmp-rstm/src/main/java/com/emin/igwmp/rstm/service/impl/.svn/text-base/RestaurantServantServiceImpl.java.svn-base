/**
 * 
 */
package com.emin.igwmp.rstm.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.igwmp.rstm.domain.RestaurantServantInfo;
import com.emin.igwmp.rstm.service.RestaurantServantService;

/**
 * @author lenovo
 *
 */
@Service("restaurantServantService")
public class RestaurantServantServiceImpl extends
		RstmBaseServiceImpl<RestaurantServantInfo> implements
		RestaurantServantService {

	@Override
	public PagedResult<RestaurantServantInfo> loadPagedRestaurantServantInfoByCondition(
			PageRequest pageRequest, List<Condition> conditions) {

		return this.getPage(pageRequest,transferConditionsToPreFilter(conditions), getStatusFilter());
	}

}
