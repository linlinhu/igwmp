package com.emin.igwmp.rstm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.dao.PreFilter;
import com.emin.base.dao.PreFilters;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.base.service.Condition.ConditionOperator;
import com.emin.base.service.Conditions;
import com.emin.base.service.UndeleteableServiceImpl;
import com.emin.igwmp.rstm.domain.RestaurantAdminInfo;
import com.emin.igwmp.rstm.exception.ExceptionCode;
import com.emin.igwmp.rstm.service.RestaurantAdminService;

@Service("restaurantAdminService")
public class RestaurantAdminServiceImpl extends RstmBaseServiceImpl<RestaurantAdminInfo> implements RestaurantAdminService {
	
	Logger log = LoggerFactory.getLogger(RestaurantAdminServiceImpl.class);
	
	@Override
	public void saveOrUpdate(RestaurantAdminInfo restaurantAdminInfo) {
		//this.beforeSaveOrUpdate(restaurantAdminInfo);
		super.saveOrUpdate(restaurantAdminInfo);
//
//		log.error("异常日志");//在异常处理中输出   异常（除主动抛出异常）必须写  
//		
//		log.warn("关键日志");//关键步骤  如支付 订单保存 等
//		
//		log.info("记录过程");//普通过程  如普通的增删改查 在确认没有异常的时候可以不写
//
//		log.debug("测试日志");//测试的时候使用  
		 
	}
	
	private void beforeSaveOrUpdate(RestaurantAdminInfo restaurantAdminInfo) {
		if (restaurantAdminInfo == null) {
			throw new EminException(ExceptionCode.A_PARAMTERS_INVALID);
		}
		PreFilter idFilter = null;
		if (restaurantAdminInfo.getId() != null && restaurantAdminInfo.getId() != 0) {
			idFilter = PreFilters.eq(restaurantAdminInfo.PROP_ID, restaurantAdminInfo.getId());
        }
		if(idFilter == null){
			return;
		}
		RestaurantAdminInfo rstInfo = this.findUniqueByPreFilter(
				getStatusFilter(), idFilter);
		if (rstInfo != null) {
			throw new EminException(ExceptionCode.A_ID_EXSISTS, rstInfo.getId());
		}

	}

	
	@Override
	public PagedResult<RestaurantAdminInfo> loadPagedRestaurantAdminInfoByCondition(
			PageRequest pageRequest, List<Condition> conditions) {
		return this.getPage(pageRequest,transferConditionsToPreFilter(conditions), getStatusFilter());
	}

}
