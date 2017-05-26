/**
 * 
 */
package com.emin.igwmp.rstm.facade.accepters.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.igwmp.rstm.domain.RestaurantPublicInfo;
import com.emin.igwmp.rstm.domain.ServantExchangeIntegralRecord;
import com.emin.igwmp.rstm.facade.accepters.ServantExchangeIntegralRecordAccepter;
import com.emin.igwmp.rstm.service.RstmBaseService;
import com.emin.igwmp.rstm.service.ServantExchangeIntegralRecodService; 

/**
 * @author lenovo
 *
 */
@Service(version="0.0.1")
@Component("servantExchangeIntegralRecordAccepter")
public class ServantExchangeIntegralRecordAccepterImpl implements
		ServantExchangeIntegralRecordAccepter {

	@Resource
	ServantExchangeIntegralRecodService servantExchangeIntegralRecodService;
	@Resource
	RstmBaseService<RestaurantPublicInfo> rstmBaseService;
	
	/* (non-Javadoc)
	 * @see com.emin.igwmp.rstm.facade.accepters.WxsToServantExchangeIntegralRecordAccepter#queryContractByCondition(com.emin.base.dao.PageRequest, java.util.List)
	 */
	@Override
	public PagedResult<ServantExchangeIntegralRecord> queryServantExchangeIntegral(PageRequest pageRequest , List<Condition> conditions){
		
		return servantExchangeIntegralRecodService.loadPagedExchangeIntegralRecodByCondition(pageRequest, conditions);
	}

	/* (non-Javadoc)
	 * @see com.emin.igwmp.rstm.facade.accepters.WxsToServantExchangeIntegralRecordAccepter#saveServantExchangeIntegral(com.emin.igwmp.rstm.domain.ServantExchangeIntegralRecord)
	 */
	@Override
	public void saveServantExchangeIntegral(ServantExchangeIntegralRecord id) {
		servantExchangeIntegralRecodService.saveOrUpdate(id);

	}

	/* (non-Javadoc)
	 * @see com.emin.igwmp.rstm.facade.accepters.WxsToServantExchangeIntegralRecordAccepter#removeServantExchangeIntegral(java.lang.Long)
	 */
	@Override
	public void removeServantExchangeIntegral(Long id) {
		servantExchangeIntegralRecodService.deleteById(id);		
	}

	/* (non-Javadoc)
	 * @see com.emin.igwmp.rstm.facade.accepters.WxsToServantExchangeIntegralRecordAccepter#queryServantExchangeIntegralById(java.lang.Long)
	 */
	@Override
	public ServantExchangeIntegralRecord queryServantExchangeIntegralById(
			Long id) {
		
		return servantExchangeIntegralRecodService.findById(id);
	}

	@Override
	public List<ServantExchangeIntegralRecord> queryServantExchangeIntegralRecordForListByCondition(
			List<Condition> conditions) {
		return servantExchangeIntegralRecodService.findByPreFilter(rstmBaseService.transferConditionsToPreFilter(conditions));
	}

	@Override
	public ServantExchangeIntegralRecord queryServantExchangeIntegralRecordObjByCondition(
			List<Condition> conditions) {		
		return servantExchangeIntegralRecodService.findUniqueByPreFilter(rstmBaseService.transferConditionsToPreFilter(conditions));
	}
}
