package com.emin.igwmp.rstm.facade.accepters;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.igwmp.rstm.domain.ServantExchangeIntegralRecord;
import com.emin.igwmp.rstm.domain.ServantVenoutRecord;

public interface ServantExchangeIntegralRecordAccepter {
   
	/**
     * 根据条件分页查询服务员兑换积分记录列表
     * @param pageRequest
     * @param conditions
     * @return
     */
	public PagedResult<ServantExchangeIntegralRecord> queryServantExchangeIntegral(PageRequest pageRequest , List<Condition> conditions);

	/**
	 * 保存服务员兑换积分信息
	 * @param id
	 */
	void saveServantExchangeIntegral(ServantExchangeIntegralRecord id);
	
	 /**
     * 根据ID删除服务员兑换积分信息表
     *
     * @param id
     */
    void removeServantExchangeIntegral(Long id);
    

    ServantExchangeIntegralRecord queryServantExchangeIntegralById(Long id);
    
    /**
     * 根据条件查询服务员积分记录列表
     * 
     * @param conditions
     */
    List<ServantExchangeIntegralRecord> queryServantExchangeIntegralRecordForListByCondition(List<Condition> conditions);
    
    /**
     * 根据条件查询服务员积分记录单个对象
     * 
     * @param conditions
     */
    ServantExchangeIntegralRecord queryServantExchangeIntegralRecordObjByCondition(List<Condition> conditions);   
    
}