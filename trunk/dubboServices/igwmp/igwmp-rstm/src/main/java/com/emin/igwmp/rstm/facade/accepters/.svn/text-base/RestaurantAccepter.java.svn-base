package com.emin.igwmp.rstm.facade.accepters;

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
public interface RestaurantAccepter {

//    /**
//     * 根据关键字分页查询饭店列表
//     * @param pageRequest
//     * @param match
//     * @return
//     */
//	public PagedResult<RestaurantPublicInfo> queryRestaurantPublicInfoByMatch(PageRequest pageRequest , String... match);
	
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
    
    /**
     * 根据ID查询饭店基本信息表
     * 
     * @param id
     */
    RestaurantPublicInfo queryRestaurantPublicInfoById(Long id);
    
    /**
     * 根据条件查询饭店列表
     * 
     * @param conditions
     */
    List<RestaurantPublicInfo> queryRestaurantPublicInfoForListByCondition(List<Condition> conditions);
    
    /**
     * 根据条件查询饭店单个对象
     * 
     * @param conditions
     */
    RestaurantPublicInfo queryRestaurantPublicInfoObjByCondition(List<Condition> conditions);
}
