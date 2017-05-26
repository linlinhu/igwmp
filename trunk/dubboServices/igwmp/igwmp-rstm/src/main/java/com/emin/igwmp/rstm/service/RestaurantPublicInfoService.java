package com.emin.igwmp.rstm.service;


import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.base.service.UndeleteableService;
import com.emin.igwmp.rstm.domain.RestaurantPublicInfo;

/**
 * 饭店信息业务类
 * @author zhaoqt
 *
 */
public interface RestaurantPublicInfoService extends UndeleteableService<RestaurantPublicInfo>{
   

    /**
     * 根据关键字分页查询饭店列表
     * @param pageRequest
     * @param match
     * @return
     */
	public PagedResult<RestaurantPublicInfo> findPagedRestaurantPublicInfoByMatch(PageRequest pageRequest , String... match);
	
	/**
     * 根据条件分页查询饭店列表
     * @param pageRequest
     * @param conditions
     * @return
     */
	public PagedResult<RestaurantPublicInfo> findPagedRestaurantPublicInfoByCondition(PageRequest pageRequest , List<Condition> conditions);
	
}
