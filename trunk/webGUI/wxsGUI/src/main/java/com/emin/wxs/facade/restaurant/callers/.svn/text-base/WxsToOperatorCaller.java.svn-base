package com.emin.wxs.facade.restaurant.callers;

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
public interface WxsToOperatorCaller {

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
     * 根据ID删除服务员信息表
     *
     * @param id
     */
    void removeOperatorInfo(Long id);
     
    RestaurantAdminInfo queryOperatorById(Long id);
    
    /**
     * 验证验证码正确性
     * @param phoneNum
     * @param isRegerstered 是否注册，否则为登录 
     * @return
     */
    String validateOperatorVerifyCode(String phoneNum);
    
   /**
    * 将验证码放到redis中
    * @param phoneNum
    * @param verifyCode
    * @param isRegerstered 是否注册，否则为登录 
    */
    void putOperatorVerifyCode(String phoneNum,String verifyCode);

    /**
     * 查询单个运营者信息
     * @param condition
     * @return
     */
    RestaurantAdminInfo queryUniqueOperator(List<Condition> condition);
    
    /**
     * 保存服务员审核结果
     * @param condition
     * @return
     */
    void saveServantAuditResult(Long servantId,int auditingStatus);
}
