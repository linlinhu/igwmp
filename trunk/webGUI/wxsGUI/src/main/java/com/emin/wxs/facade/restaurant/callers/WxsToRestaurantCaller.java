package com.emin.wxs.facade.restaurant.callers;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.igwmp.rstm.domain.RestaurantPublicInfo;


/**
 * 调用饭店相关信息
 * @author zhaoqt
 *
 */
public interface WxsToRestaurantCaller {

	/**
     * 根据条件分页查询饭店列表
     * @param pageRequest
     * @param conditions
     * @return
     */
	public PagedResult<RestaurantPublicInfo> queryRestaurantPublicInfoByCondition(PageRequest pageRequest , List<Condition> conditions);

	/**
	 * 保存饭店信息
	 * @param id
	 */
	Long saveRestaurantInfo(RestaurantPublicInfo id);
	
	 /**
     * 根据ID删除饭店基本信息表
     *
     * @param id
     */
    void removeRestaurantInfo(Long id);
    
    RestaurantPublicInfo queryRestaurantPublicInfoById(Long id);
    
    
    
}
