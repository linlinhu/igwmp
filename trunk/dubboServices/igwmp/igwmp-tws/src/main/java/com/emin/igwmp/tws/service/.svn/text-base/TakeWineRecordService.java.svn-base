package com.emin.igwmp.tws.service;

import com.emin.base.exception.EminException;
import com.emin.base.service.CRUDService;
import com.emin.igwmp.tws.domain.TakeWineRecord;

public interface TakeWineRecordService extends CRUDService<TakeWineRecord>{

	
	
	/**
	* 
	* @Title: createOrGetRecord
	* @Description: 创建或获取 取酒单  
	* @param orderNumber 订单号
	* @param shouldTakeMl 取酒量 (毫升)
	* @return TakeWineRecord 取酒单信息
	* @throws EminException
	 */
	TakeWineRecord createOrGetRecord(String orderNumber, Integer shouldTakeMl) throws EminException,Exception;

	/**
	 * 
	* @Title: getByOrderNumber
	* @Description: 根据订单号查询取酒单
	* @param orderNumber
	* @return TakeWineRecord
	 */
	TakeWineRecord getByOrderNumber(String orderNumber);

	/**
	 * 
	* @Title: getByRecordNumber
	* @Description: 根据取酒单号查询取酒单
	* @param recordNumber
	* @return TakeWineRecord
	 */
	TakeWineRecord getByRecordNumber(String recordNumber);

	/**
	* @Title: lockTakeCode
	* @Description: 使用并锁定取酒码 返回对应的取酒记录
	* @param takeCode
	* @return TakeWineRecord
	 */
	TakeWineRecord lockTakeCode(String takeCode);

	/**
	* @Title: unLockTakeCode
	* @Description: 解锁取酒码
	* @param takeCode
	 */
	void unLockTakeCode(String takeCode);

	/**
	 * 
	* @Title: queryRecordByTakeCode
	* @Description: 根据取酒码查询取酒记录
	* @param takeCode
	* @return TakeWineRecord
	 */
	TakeWineRecord queryRecordByTakeCode(String takeCode);

}
