package com.emin.igwmp.rstm.facade.accepters;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.igwmp.rstm.domain.RestaurantPublicInfo;
import com.emin.igwmp.rstm.domain.ServantVenoutRecord;

public interface ServantVenoutRecordAccepter {

	/**
     * 根据条件分页查询服务员出酒记录列表
     * @param pageRequest
     * @param conditions
     * @return
     */
	public PagedResult<ServantVenoutRecord> queryServantVenoutRecordByCondition(PageRequest pageRequest , List<Condition> conditions);

	/**
	 * 保存服务员出酒信息
	 * @param id
	 */
	Long saveServantVenoutRecord(ServantVenoutRecord record);
	
	 /**
     * 根据ID删除服务员出酒信息表
     *
     * @param id
     */
    void removeServantVenoutRecord(Long id);
    

    ServantVenoutRecord queryServantVenoutRecordById(Long id);
    
    /**
     * 根据条件查询打酒记录列表
     * 
     * @param conditions
     */
    List<ServantVenoutRecord> queryServantVenoutRecordForListByCondition(List<Condition> conditions);
    
    /**
     * 根据条件查询打酒记录单个对象
     * 
     * @param conditions
     */
    ServantVenoutRecord queryServantVenoutRecordObjByCondition(List<Condition> conditions);

    /**
     * 出酒报告后给服务员相关订单回写状态
     * @param orderNum
     * @param status
     * @param actualMl
     * @return
     */
    Long modifyServantVendoutRecords(String orderNum, int status,int actualMl);
}
