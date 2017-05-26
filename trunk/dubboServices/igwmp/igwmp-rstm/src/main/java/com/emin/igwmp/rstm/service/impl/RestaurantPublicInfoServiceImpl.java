package com.emin.igwmp.rstm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.dao.PreFilter;
import com.emin.base.dao.PreFilters;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.igwmp.rstm.domain.RestaurantPublicInfo;
import com.emin.igwmp.rstm.exception.ExceptionCode;
import com.emin.igwmp.rstm.service.RestaurantPublicInfoService;

@Service("restaurantPublicInfoService")
public class RestaurantPublicInfoServiceImpl  extends RstmBaseServiceImpl<RestaurantPublicInfo>
		implements RestaurantPublicInfoService {
	Logger log = LoggerFactory.getLogger(RestaurantPublicInfoServiceImpl.class);
	
	@Override	
	public void saveOrUpdate(RestaurantPublicInfo restaurantPublicInfo) {
		//this.beforeSaveOrUpdate(restaurantPublicInfo);
		 super.saveOrUpdate(restaurantPublicInfo);
		 Long rstId = restaurantPublicInfo.getId();
		 log.info(rstId+"");
		 log.info("oldRstInfoObj = " + JSONObject.toJSONString(restaurantPublicInfo));
		 if(rstId!=null){
			 RestaurantPublicInfo rstInfo = findById(rstId);
			 String newRstInfoObj = JSONObject.toJSONString(rstInfo);
			 log.info("newRstInfoObj = " + newRstInfoObj);
		 }	
		 return;
    }

	private void beforeSaveOrUpdate(RestaurantPublicInfo restaurantPublicInfo) {
		if (restaurantPublicInfo == null) {
			throw new EminException(ExceptionCode.R_PARAMTERS_INVALID);
		}
		PreFilter idFilter = null;
		if (restaurantPublicInfo.getId() != null && restaurantPublicInfo.getId() != 0) {
			
			idFilter = PreFilters.eq(restaurantPublicInfo.PROP_ID, restaurantPublicInfo.getId());
        }
		if(idFilter == null){
			return;
		}
		
		RestaurantPublicInfo rstInfo = this.findUniqueByPreFilter(
				getStatusFilter(), idFilter);
		if (rstInfo != null) {
			throw new EminException(ExceptionCode.R_ID_EXSISTS, rstInfo.getId());
		}

	}
	
	
	@Override
	public PagedResult<RestaurantPublicInfo> findPagedRestaurantPublicInfoByMatch(
			PageRequest pageRequest,String... match) {
		PreFilter nameFilter = null;
		PreFilter corperNameFilter = null;
		if (match != null && match.length > 0) {
			for (String m : match) {
				nameFilter = PreFilters.or(nameFilter, PreFilters.like(RestaurantPublicInfo.PROP_NAME, "%" + m + "%"));
				corperNameFilter = PreFilters.or(corperNameFilter, PreFilters.like(RestaurantPublicInfo.PROP_PRIVATE_INFO_CPRT_NAME, "%" + m + "%"));
			}
		}
		PreFilter finalFilter = PreFilters.or(nameFilter, corperNameFilter);
	
		return this.getPage(pageRequest, finalFilter, getStatusFilter());
	}

	@Override
	public PagedResult<RestaurantPublicInfo> findPagedRestaurantPublicInfoByCondition(
			PageRequest pageRequest, List<Condition> conditions) {
		return this.getPage(pageRequest,transferConditionsToPreFilter(conditions), getStatusFilter());
	}
}
