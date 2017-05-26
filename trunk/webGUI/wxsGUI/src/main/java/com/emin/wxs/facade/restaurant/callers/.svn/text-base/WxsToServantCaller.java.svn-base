package com.emin.wxs.facade.restaurant.callers;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.igwmp.rstm.domain.RestaurantServantInfo;
import com.emin.igwmp.rstm.domain.ServantExchangeIntegralRecord;
import com.emin.igwmp.rstm.domain.ServantVenoutRecord;
import com.emin.wxs.domain.FansItem;


/**
 * 调用服务员相关信息
 * @author zhaoqt
 *
 */
public interface WxsToServantCaller {

//    /**
//     * 根据关键字分页查询饭店列表
//     * @param pageRequest
//     * @param match
//     * @return
//     */
//	public PagedResult<RestaurantPublicInfo> queryRestaurantPublicInfoByMatch(PageRequest pageRequest , String... match);
	
	/**
     * 根据条件分页服务员出酒记录列表
     * @param pageRequest
     * @param conditions
     * @return
     */
	public PagedResult<ServantVenoutRecord> queryServantVenoutRecordByCondition(PageRequest pageRequest , List<Condition> conditions);

	/**
	 * 保存服务员出酒记录信息
	 * @param id
	 */
	void saveServantVenoutRecord(ServantVenoutRecord id);
	
	 /**
     * 根据ID删除出酒记录信息
     *
     * @param id
     */
    void removeServantVenoutRecord(Long id);

    ServantVenoutRecord queryServantVenoutRecordById(Long id);
    
	/**
     * 根据条件分页查询服务员兑换积分列表
     * @param pageRequest
     * @param conditions
     * @return
     */
	public PagedResult<ServantExchangeIntegralRecord> queryServantExchangeIntegralRecordByCondition(PageRequest pageRequest , List<Condition> conditions);

	/**
	 * 保存服务员兑换积分信息
	 * @param id
	 */
	void saveServantExchangeIntegralRecord(ServantExchangeIntegralRecord id);
	
	 /**
     * 根据ID删除服务员兑换积分信息表
     *
     * @param id
     */
    void removeServantExchangeIntegralRecord(Long id);

    ServantExchangeIntegralRecord queryServantExchangeIntegralRecordById(Long id);
    
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

    RestaurantServantInfo queryRestaurantServantInfoById(Long id);
    
    /**
     * 验证验证码正确性
     * @param phoneNum
     * @param isRegerstered 是否注册，否则为登录 
     * @return
     */
    String validateServantVerifyCode(String phoneNum ,boolean isRegerstered);
    
   /**
    * 将验证码放到redis中
    * @param phoneNum
    * @param verifyCode
    * @param isRegerstered 是否注册，否则为登录 
    */
    void putServantVerifyCode(String phoneNum,String verifyCode,boolean isRegerstered);

    /**
     * 服务员和饭店绑定
     * @param phoneNum
     * @param name
     * @param restaurantId
     */
    void bindServantAndRestaurant(RestaurantServantInfo restaurantServantInfo,String phoneNum,String name,Long restaurantId,String openId);
    
    /**
     * 根据条件查询饭店列表
     * @param conditions
     * @return
     */
    List<Map<String,Object>> queryRestaurantsByCondition(List<Condition> conditions);
    
    /**
     * 查询单个服务员信息
     * @param condition
     * @return
     */
    RestaurantServantInfo queryUniqueServant(List<Condition> condition);
    
    /**
     * 根据条件查询服务员信息列表
     * @param condition
     * @return
     */
    List<RestaurantServantInfo> queryServantList(List<Condition> condition);
    
    /**
     * 服务员扫码绑定取酒码对应的订单
     * @param openId
     * @param takeCode
     * @return
     */
    Long createServantVenoutRecordsByOpenIdAndTakeCode(String openId,String takeCode);
    
    /**
     * 出酒报告后给服务员相关订单回写状态
     * @param orderNum
     * @param status
     * @param actualMl
     * @return
     */
    Long modifyServantVendoutRecords(String orderNum,int status,int actualMl);
    
    
    /**
	 * 服务员取酒排行榜
	 * @return [{name:'服务员名称',url:'图片路径',restaurantName:'饭店名称',amount:50,integral:500}]
	 * @throws EminException
	 */
    JSONArray findTakeWineRanking();
    
    
    /**
	 * 服务员取酒列表
	 * @param fansId 粉丝id
	 * @param beginDate 查询开始时间
	 * @param endDate 结束时间
	 * @return [{time:1490950052276,wineName:'习酒',quantity:20,amount:120000,integral:500}]
	 * @throws EminException
	 */
    JSONArray findTakeWineList(Long fansId,Long beginDate,Long endDate);

	FansItem loadFansItemByOpenId(String openId);
	

	void machineScanLogin(String cellphone, String ipcCode);
}
