package com.emin.wxs.facade.restaurant.callers.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.igwmp.rstm.domain.RestaurantAdminInfo;
import com.emin.igwmp.rstm.domain.RestaurantServantInfo;
import com.emin.igwmp.rstm.facade.accepters.OperatorAccepter;
import com.emin.igwmp.rstm.facade.accepters.RedisAccepter;
import com.emin.igwmp.rstm.facade.accepters.ServantAccepter;
import com.emin.wxs.facade.restaurant.callers.WxsToOperatorCaller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("wxsToOperatorCaller")
public class WxsToOperatorCallerImpl implements WxsToOperatorCaller {
     
	@Reference(version="0.0.1")
	private OperatorAccepter operatorAccepter;
	@Reference(version="0.0.1")
	RedisAccepter redisAccepter;
	@Reference(version="0.0.1")
	ServantAccepter servantAccepter;
	
	
	@Override
	public PagedResult<RestaurantAdminInfo> queryOperatorByCondition(
			PageRequest pageRequest, List<Condition> conditions) {
		
		return operatorAccepter.queryOperatorByCondition(pageRequest, conditions);
	}

	@Override
	public void saveOperatorInfo(RestaurantAdminInfo restaurantAdminInfo) {
		
		operatorAccepter.saveOperatorInfo(restaurantAdminInfo);
	}

	@Override
	public void removeOperatorInfo(Long id) {
		
		operatorAccepter.removeOperatorInfo(id);
	}

	@Override
	public RestaurantAdminInfo queryOperatorById(Long id) {
		
		return operatorAccepter.queryOperatorById(id);
	}

	@Override
	public String validateOperatorVerifyCode(String phoneNum) {
		return redisAccepter.get("OperatorLogin", phoneNum);
	}

	@Override
	public void putOperatorVerifyCode(String phoneNum, String verifyCode) {
			redisAccepter.put("OperatorLogin", phoneNum, verifyCode);	
	}

	@Override
	public RestaurantAdminInfo queryUniqueOperator(List<Condition> condition) {
		return operatorAccepter.queryRestaurantAdminObjByCondition(condition);
	}

	@Override
	public void saveServantAuditResult(Long servantId,
			int auditingStatus) {
		RestaurantServantInfo servantPo = servantAccepter.queryServantInfoById(servantId);
		if(servantPo!=null){
			servantPo.setAuditingStatus(auditingStatus);
			servantPo.setLastModifyTime(System.currentTimeMillis());
			//servantPo.setAuditingPerson(auditingPerson);
			servantAccepter.saveServantInfo(servantPo);
		}
	}
}
