package com.emin.igwmp.rstm.facade.accepters;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.igwmp.rstm.domain.RestaurantAdminInfo;
import com.emin.igwmp.rstm.domain.RestaurantPrivateInfo;
import com.emin.igwmp.rstm.domain.RestaurantPublicInfo;
import com.emin.igwmp.rstm.domain.RestaurantServantInfo;


/**
 * 调用运营者相关信息
 * @author zhaoqt
 *
 */
public interface OperatorAccepter {

//    /**
//     * 根据关键字分页查询饭店列表
//     * @param pageRequest
//     * @param match
//     * @return
//     */
//	public PagedResult<RestaurantPublicInfo> queryRestaurantPublicInfoByMatch(PageRequest pageRequest , String... match);
	
	/**
     * 根据条件分页查询服务员列表
     * @param pageRequest
     * @param conditions
     * @return
     */
	public PagedResult<RestaurantAdminInfo> queryOperatorByCondition(PageRequest pageRequest , List<Condition> conditions);

	/**
	 * 保存运营者信息
	 * @param id
	 */
	void saveOperatorInfo(RestaurantAdminInfo id);
	
	 /**
     * 根据ID删除运营者信息表
     *
     * @param id
     */
    void removeOperatorInfo(Long id);
    
    /**
     * 根据ID查询运营者基本信息表
     * 
     * @param id
     */
    RestaurantAdminInfo queryOperatorById(Long id);
    
    /**
     * 根据条件查询运营者列表
     * 
     * @param conditions
     */
    List<RestaurantAdminInfo> queryRestaurantAdminForListByCondition(List<Condition> conditions);
    
    /**
     * 根据条件查询运营者单个对象
     * 
     * @param conditions
     */
    RestaurantAdminInfo queryRestaurantAdminObjByCondition(List<Condition> conditions);

}
