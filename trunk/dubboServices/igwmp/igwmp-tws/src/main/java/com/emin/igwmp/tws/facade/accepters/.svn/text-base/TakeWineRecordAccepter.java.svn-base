package com.emin.igwmp.tws.facade.accepters;

import com.emin.base.exception.EminException;
import com.emin.igwmp.tws.domain.TakeWineRecord;

public interface TakeWineRecordAccepter {

	/**
	 * 
	* @Title: createOrGetTakeWineRecord
	* @Description: 创建或获取 取酒单
	* @param orderNumber
	* @param shouldTakeMl
	* @return TakeWineRecord
	* @throws EminException
	* @throws Exception TakeWineRecord
	 */
	TakeWineRecord createOrGetTakeWineRecord(String orderNumber, Integer shouldTakeMl) throws EminException, Exception;

	/**
	 * 
	* @Title: getRecordByOrderNumber
	* @Description: 根据订单号获取取酒单 （历史记录）
	* @param orderNumber
	* @return TakeWineRecord
	 */
	TakeWineRecord getRecordByOrderNumber(String orderNumber);

	/**
	 * 
	* @Title: getRecordByRecordNumber
	* @Description: 根据取酒单号获取取酒单 （历史记录）
	* @param recordNumber
	* @return TakeWineRecord
	* 
	 */
	TakeWineRecord getRecordByRecordNumber(String recordNumber);

	/**
	 * 
	* @Title: lockTakeCode
	* @Description: 使用并锁定取酒码，返回对应取酒记录
	* @param code
	* @return TakeWineRecord
	* @throws EminException 
	 */
	TakeWineRecord lockTakeCode(String code) throws EminException;

	/**
	 * 
	* @Title: queryByTakeCode
	* @Description: 根据取酒码查询取酒记录
	* @param code
	* @return TakeWineRecord
	 */
	TakeWineRecord queryByTakeCode(String code);

}
