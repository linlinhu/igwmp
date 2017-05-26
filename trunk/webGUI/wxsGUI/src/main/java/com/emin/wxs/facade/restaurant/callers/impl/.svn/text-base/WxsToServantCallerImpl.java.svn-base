package com.emin.wxs.facade.restaurant.callers.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import net.sf.json.JSONArray;
import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.base.service.Condition.ConditionOperator;
import com.emin.base.service.Condition.ConditionType;
import com.emin.igwmp.ords.domain.Order;
import com.emin.igwmp.ords.domain.OrderItem;
import com.emin.igwmp.ords.facade.accepters.OrderAccepter;
import com.emin.igwmp.rp.facade.accepters.ServantReportAccepter;
import com.emin.igwmp.rstm.domain.RestaurantPublicInfo;
import com.emin.igwmp.rstm.domain.RestaurantServantInfo;
import com.emin.igwmp.rstm.domain.ServantExchangeIntegralRecord;
import com.emin.igwmp.rstm.domain.ServantVenoutRecord;
import com.emin.igwmp.rstm.facade.accepters.RedisAccepter;
import com.emin.igwmp.rstm.facade.accepters.RestaurantAccepter;
import com.emin.igwmp.rstm.facade.accepters.ServantAccepter;
import com.emin.igwmp.rstm.facade.accepters.ServantExchangeIntegralRecordAccepter;
import com.emin.igwmp.rstm.facade.accepters.ServantVenoutRecordAccepter;
import com.emin.igwmp.skm.facade.accepters.UserNoticeAccepter;
import com.emin.igwmp.tws.domain.TakeWineRecord;
import com.emin.igwmp.tws.facade.accepters.TakeWineRecordAccepter;
import com.emin.wxs.controller.restaurant.Const;
import com.emin.wxs.controller.restaurant.ServantVendoutRecordController;
import com.emin.wxs.domain.Fans;
import com.emin.wxs.domain.FansItem;
import com.emin.wxs.facade.restaurant.callers.WxsToServantCaller;
import com.emin.wxs.service.FansService;

@Service("wxsToServantCaller")
public class WxsToServantCallerImpl implements WxsToServantCaller {

	@Reference(version="0.0.1")
	ServantExchangeIntegralRecordAccepter servantExchangeIntegralRecordAccepter;
	
	@Reference(version="0.0.1")
	ServantVenoutRecordAccepter servantVenoutRecordAccepter;
	
	@Reference(version="0.0.1")
	ServantAccepter servantAccepter;
	
	@Reference(version="0.0.1")
	ServantReportAccepter servantReportAccepter;
	
	@Reference(version="0.0.1")
	RestaurantAccepter restaurantAccepter;
	
	@Reference(version="0.0.1")
	RedisAccepter redisAccepter;
	
	@Reference(version="0.0.1")
	private OrderAccepter orderAccepter;
	
	@Reference(version="0.0.1")
	private FansService fansService;
	 
	@Reference(version="0.0.1")
	TakeWineRecordAccepter takeWineRecordAccepter;
	
	@Reference(version="0.0.1")
	UserNoticeAccepter userNoticeAccepter; 
	
	@Override
	public PagedResult<RestaurantServantInfo> queryServantInfoByCondition(
			PageRequest pageRequest, List<Condition> conditions) {
		
		return servantAccepter.queryServantInfoByCondition(pageRequest, conditions);
	}

	@Override
	public void saveServantInfo(RestaurantServantInfo restaurantServantInfo) {
		servantAccepter.saveServantInfo(restaurantServantInfo);
		
	}

	@Override
	public void removeServantInfo(Long id) {
		servantAccepter.removeServantInfo(id);
		
	}

	@Override
	public RestaurantServantInfo queryRestaurantServantInfoById(Long id) {
		
		return servantAccepter.queryServantInfoById(id);
	}

	@Override
	public PagedResult<ServantVenoutRecord> queryServantVenoutRecordByCondition(
			PageRequest pageRequest, List<Condition> conditions) {

		return servantVenoutRecordAccepter.queryServantVenoutRecordByCondition(pageRequest, conditions);
	}

	@Override
	public void saveServantVenoutRecord(ServantVenoutRecord servantVenoutRecord) {
		servantVenoutRecordAccepter.saveServantVenoutRecord(servantVenoutRecord);	
	}

	@Override
	public void removeServantVenoutRecord(Long id) {
		servantVenoutRecordAccepter.removeServantVenoutRecord(id);		
	}

	@Override
	public ServantVenoutRecord queryServantVenoutRecordById(Long id) {
		
		return servantVenoutRecordAccepter.queryServantVenoutRecordById(id);
	}

	@Override
	public PagedResult<ServantExchangeIntegralRecord> queryServantExchangeIntegralRecordByCondition(
			PageRequest pageRequest, List<Condition> conditions) {
		
		return servantExchangeIntegralRecordAccepter.queryServantExchangeIntegral(pageRequest, conditions);
	}

	@Override
	public void saveServantExchangeIntegralRecord(ServantExchangeIntegralRecord entity) {
		servantExchangeIntegralRecordAccepter.saveServantExchangeIntegral(entity);
	}

	@Override
	public void removeServantExchangeIntegralRecord(Long id) {
		servantExchangeIntegralRecordAccepter.removeServantExchangeIntegral(id);
		
	}

	@Override
	public ServantExchangeIntegralRecord queryServantExchangeIntegralRecordById(
			Long id) {
		
		return servantExchangeIntegralRecordAccepter.queryServantExchangeIntegralById(id);
	}

	@Override
	public String validateServantVerifyCode(String phoneNum,boolean isRegerstered) {
		if(isRegerstered){
			return redisAccepter.get("ServantRegister", phoneNum);
		}else{
			return redisAccepter.get("ServantLogin", phoneNum);
		} 
	}

	@Override
    public void putServantVerifyCode(String phoneNum,String verifyCode,boolean isRegerstered){
    	
    	if(isRegerstered){
    		redisAccepter.put("ServantRegister", phoneNum, verifyCode);
		}else{
			redisAccepter.put("ServantLogin", phoneNum, verifyCode);
		} 
    	return;
    }

	@Override
	public void bindServantAndRestaurant(RestaurantServantInfo restaurantServantInfo,String phoneNum, String name,
			Long restaurantId,String openId) {
		RestaurantPublicInfo  restaurantPublicInfo = restaurantAccepter.queryRestaurantPublicInfoById(restaurantId);
		if(restaurantServantInfo == null){
			restaurantServantInfo = new RestaurantServantInfo();
			restaurantServantInfo.setCreateTime(System.currentTimeMillis());
			restaurantServantInfo.setStatus(1);
		}
		restaurantServantInfo.setLastModifyTime(System.currentTimeMillis());
		restaurantServantInfo.setCellphone(phoneNum);
		restaurantServantInfo.setName(name);
		restaurantServantInfo.setAuditingStatus(Const.SERVANT_AUDITING_STATUS_UNAUDITING);
		Set<RestaurantPublicInfo> restaurants = new HashSet<RestaurantPublicInfo>();
		restaurants.add(restaurantPublicInfo);
		restaurantServantInfo.setRestaurants(restaurants);
		
		//关联fansId
		FansItem fansItem = fansService.loadByOpenId(openId);
		if(fansItem!=null){
			Fans fans = fansService.findById(fansItem.getFansId());
			if(fans!=null){
				restaurantServantInfo.setFansId(fans.getId());
				restaurantServantInfo.setFansInfo(JSONObject.fromObject(fans));
			}
		}		
		servantAccepter.saveServantInfo(restaurantServantInfo);
	}

	@Override
	public List<Map<String, Object>> queryRestaurantsByCondition(
			List<Condition> conditions) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<RestaurantPublicInfo> restaurants = restaurantAccepter.queryRestaurantPublicInfoForListByCondition(conditions);
		if(restaurants!=null){
			for(RestaurantPublicInfo rst:restaurants){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id",rst.getId());
				map.put("name", rst.getName());
				list.add(map);
			}
		}
		return list;
	}

	@Override
	public RestaurantServantInfo queryUniqueServant(List<Condition> conditions) {
		
		return servantAccepter.queryRestaurantServantObjByCondition(conditions);
	}

	@Override
	public List<RestaurantServantInfo> queryServantList(
			List<Condition> condition) {
		
		return servantAccepter.queryRestaurantServantForListByCondition(condition);
	}
	

	
	@Override
	public Long createServantVenoutRecordsByOpenIdAndTakeCode(String openId,String takeCode) {
		//查询fans信息		
		FansItem fansItem = fansService.loadByOpenId(openId);
		Fans fans = fansService.findById(fansItem.getFansId());
		if(fans!=null){
	     Long id = fans.getId();
		 //查询服务员信息
		 List<Condition> conditions = new ArrayList<Condition>();
         conditions.add(new Condition("fansId", ConditionOperator.EQ, ConditionType.CHARACTER, id));
		 RestaurantServantInfo servantInfo = servantAccepter.queryRestaurantServantObjByCondition(conditions);
		 ServantVenoutRecord servantVendoutRecord = new ServantVenoutRecord();
		 if(servantInfo!=null){			
		     servantVendoutRecord.setCreateTime(System.currentTimeMillis());
		     servantVendoutRecord.setLastModifyTime(System.currentTimeMillis());
		     servantVendoutRecord.setStatus(1);
			 servantVendoutRecord.setRestaurantServantInfo(servantInfo);
			 servantVendoutRecord.setServantId(servantInfo.getId());
			 servantVendoutRecord.setServantName(servantInfo.getName());
			 //查询取酒记录
			 TakeWineRecord  takeWineRecord= takeWineRecordAccepter.queryByTakeCode(takeCode);
		    if(takeWineRecord!=null){
		    	String orderNum = takeWineRecord.getOrderNumber();
		    	Long  codeExpireTime = takeWineRecord.getCodeExpireTime();		    	
		    	String recordNum = takeWineRecord.getRecordNumber();//暂时不存
		    	
		    	Order order =  orderAccepter.findOrderByNumber(orderNum);		    	
		    	servantVendoutRecord.setOrderNo(orderNum);		  
		    	servantVendoutRecord.setVendeeId(order.getVendeeId());
		    	servantVendoutRecord.setVendeeInfo(order.getVendeeInfo());
		    	servantVendoutRecord.setCodeExpireTime(codeExpireTime);
		    	servantVendoutRecord.setTakeCode(takeCode);
		    	Set<OrderItem> items = order.getItems();
		    	servantVendoutRecord.setTotalMoney(order.getTotalMoney());
		    	if(items!=null&&items.size()>0){
		    		for(OrderItem item:items){
		    			servantVendoutRecord.setProductId(item.getProductId());
				    	servantVendoutRecord.setProductInfo(item.getProductInfo());
		    		}		    	
		    	}
		    	servantVendoutRecord.setVendoutStatus(Const.VENOUT_STATUS_READY);
		    	servantVendoutRecord.setVendoutCapacity(takeWineRecord.getShouldTakeMl());
		    }
			return servantVenoutRecordAccepter.saveServantVenoutRecord(servantVendoutRecord);
		 }
	   }
	   return -1L;
   }

	@Override
	public Long modifyServantVendoutRecords(String orderNum, int status,
			int actualMl) {
		 List<Condition> conditions = new ArrayList<Condition>();
         conditions.add(new Condition("orderNo", ConditionOperator.EQ, ConditionType.CHARACTER, orderNum));
         ServantVenoutRecord servantVenoutRecord =  servantVenoutRecordAccepter.queryServantVenoutRecordObjByCondition(conditions);
        if(servantVenoutRecord!=null){
        	 servantVenoutRecord.setStatus(status);
             servantVenoutRecord.setVendoutCapacity(actualMl);
             servantVenoutRecordAccepter.saveServantVenoutRecord(servantVenoutRecord);
             return servantVenoutRecord.getId();             
        }        
		return -1L;
	}

	@Override
	public JSONArray findTakeWineRanking() {
		JSONArray array = servantReportAccepter.findTakeWineRanking();
		return array;
	}

	@Override
	public JSONArray findTakeWineList(Long fansId, Long beginDate, Long endDate) {
		return servantReportAccepter.findTakeWineList(fansId,beginDate,endDate);
	}   
	
	@Override
	public FansItem loadFansItemByOpenId(String openId) {
		return fansService.loadByOpenId(openId);
	}

	@Override
	public void machineScanLogin(String cellphone,String ipcCode) {
		try {
			userNoticeAccepter.ProxyPorter(cellphone, ipcCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
