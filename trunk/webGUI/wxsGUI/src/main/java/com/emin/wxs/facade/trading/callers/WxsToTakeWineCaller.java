package com.emin.wxs.facade.trading.callers;

import com.emin.base.exception.EminException;
import com.emin.wxs.vo.trading.TakeWineRecordVO;

public interface WxsToTakeWineCaller {

	/**
	 * 
	* @Title: createOrGetTakeWineRecord
	* @Description: 调用取酒服务生产取酒单 若已经创建则直接返回结果
	* @param orderNumber
	* @param shouldTakeMl
	* @return TakeWineRecordVO
	* @throws EminException
	* @throws Exception 
	 */
	TakeWineRecordVO createOrGetTakeWineRecord(String orderNumber, Integer shouldTakeMl)
			throws EminException, Exception;

	/**
	 * 
	* @Title: getByOrderNumber
	* @Description: 根据订单号查询取酒信息 (取酒完成后)
	* @param orderNumber
	* @return TakeWineRecordVO
	 */
	TakeWineRecordVO getByOrderNumber(String orderNumber);

	/**
	 * 
	* @Title: getByRecordNumber
	* @Description: 根据取酒单号查询取酒信息 (取酒完成后)
	* @param recordNumber
	* @return TakeWineRecordVO
	 */
	TakeWineRecordVO getByRecordNumber(String recordNumber);

}
