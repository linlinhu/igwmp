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
 * 调用合同相关信息
 * @author zhaoqt
 *
 */
public interface ServantAccepter {

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
	public PagedResult<RestaurantServantInfo> queryServantInfoByCondition(PageRequest pageRequest , List<Condition> conditions);

	/**
	 * 保存服务员信息
	 * @param id
	 */
	void saveServantInfo(RestaurantServantInfo id);
	
	 /**
     * 根据ID删除服务员信息表
     *
     * @param id
     */
    void removeServantInfo(Long id);
    
    RestaurantServantInfo queryServantInfoById(Long id);
    
    /**
     * 根据条件查询服务员列表
     * 
     * @param conditions
     */
    List<RestaurantServantInfo> queryRestaurantServantForListByCondition(List<Condition> conditions);
    
    /**
     * 根据条件查询服务员单个对象
     * 
     * @param conditions
     */
    RestaurantServantInfo queryRestaurantServantObjByCondition(List<Condition> conditions);
}
