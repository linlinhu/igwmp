/**
 * 
 */
package com.emin.igwmp.rstm.facade.accepters.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.base.service.Condition.ConditionOperator;
import com.emin.base.service.Condition.ConditionType;
import com.emin.igwmp.rstm.domain.RestaurantPublicInfo;
import com.emin.igwmp.rstm.domain.ServantVenoutRecord;
import com.emin.igwmp.rstm.facade.accepters.ServantVenoutRecordAccepter;
import com.emin.igwmp.rstm.service.RstmBaseService;
import com.emin.igwmp.rstm.service.ServantVenoutRecordService;

/**
 * @author lenovo
 *
 */
@Service(version="0.0.1")
@Component("servantVenoutRecordAccepter")
public class ServantVenoutRecordAccepterImpl implements
		ServantVenoutRecordAccepter {

	@Resource
	ServantVenoutRecordService servantVenoutRecordService;
	@Resource
	RstmBaseService<RestaurantPublicInfo> rstmBaseService;
	
	/* (non-Javadoc)
	 * @see com.emin.igwmp.rstm.facade.accepters.WxsToServantVenoutRecordAccepter#queryContractByCondition(com.emin.base.dao.PageRequest, java.util.List)
	 */
	@Override
	public PagedResult<ServantVenoutRecord> queryServantVenoutRecordByCondition(
			PageRequest pageRequest, List<Condition> conditions) {
		return servantVenoutRecordService.loadPagedServantVenoutRecordByCondition(pageRequest, conditions);
		
	}

	/* (non-Javadoc)
	 * @see com.emin.igwmp.rstm.facade.accepters.WxsToServantVenoutRecordAccepter#saveContract(com.emin.igwmp.rstm.domain.ServantVenoutRecord)
	 */
	@Override
	public Long saveServantVenoutRecord(ServantVenoutRecord servantVenoutRecord) {
		servantVenoutRecordService.saveOrUpdate(servantVenoutRecord);
		return 	servantVenoutRecord.getId();
		
	}

	/* (non-Javadoc)
	 * @see com.emin.igwmp.rstm.facade.accepters.WxsToServantVenoutRecordAccepter#removeContract(java.lang.Long)
	 */
	@Override
	public void removeServantVenoutRecord(Long id) {
		servantVenoutRecordService.deleteById(id);
    }

	/* (non-Javadoc)
	 * @see com.emin.igwmp.rstm.facade.accepters.WxsToServantVenoutRecordAccepter#queryContractById(java.lang.Long)
	 */
	@Override
	public ServantVenoutRecord queryServantVenoutRecordById(Long id) {
		// TODO Auto-generated method stub
		return servantVenoutRecordService.findById(id);
	}

	@Override
	public List<ServantVenoutRecord> queryServantVenoutRecordForListByCondition(
			List<Condition> conditions) {
		return servantVenoutRecordService.findByPreFilter(rstmBaseService.transferConditionsToPreFilter(conditions));
	}

	@Override
	public ServantVenoutRecord queryServantVenoutRecordObjByCondition(
			List<Condition> conditions) {
		
		return servantVenoutRecordService.findUniqueByPreFilter(rstmBaseService.transferConditionsToPreFilter(conditions));
	}
	
	@Override
	public Long modifyServantVendoutRecords(String orderNum, int status,
			int actualMl) {
		 List<Condition> conditions = new ArrayList<Condition>();
         conditions.add(new Condition("orderNo", ConditionOperator.EQ, ConditionType.CHARACTER, orderNum));
         ServantVenoutRecord servantVenoutRecord =  servantVenoutRecordService.findUniqueByPreFilter(rstmBaseService.transferConditionsToPreFilter(conditions));
        if(servantVenoutRecord!=null){
        	 servantVenoutRecord.setStatus(status);
             servantVenoutRecord.setVendoutCapacity(actualMl);
             servantVenoutRecordService.saveOrUpdate(servantVenoutRecord);
             return servantVenoutRecord.getId();             
        }        
		return -1L;
	}

}
