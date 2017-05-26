package com.emin.wxs.facade.trading.callers.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.base.exception.EminException;
import com.emin.igwmp.tws.domain.TakeWineRecord;
import com.emin.igwmp.tws.facade.accepters.TakeWineRecordAccepter;
import com.emin.wxs.facade.trading.callers.WxsToTakeWineCaller;
import com.emin.wxs.vo.trading.TakeWineRecordVO;
@Component("wxsToTakeWineCaller")
public class WxsToTakeWineCallerImpl implements WxsToTakeWineCaller{

	@Reference(version="0.0.1")
	private TakeWineRecordAccepter takeWineRecordAccepter;
	
	@Override
	public TakeWineRecordVO createOrGetTakeWineRecord(String orderNumber,Integer shouldTakeMl) throws EminException, Exception{
		TakeWineRecord record = takeWineRecordAccepter.createOrGetTakeWineRecord(orderNumber, shouldTakeMl);
		return TakeWineRecordVO.takeWineRecordToVO(record);
	}
	@Override
	public TakeWineRecordVO getByOrderNumber(String orderNumber){
		TakeWineRecord record = takeWineRecordAccepter.getRecordByOrderNumber(orderNumber);
		return TakeWineRecordVO.takeWineRecordToVO(record);
	}
	@Override
	public TakeWineRecordVO getByRecordNumber(String recordNumber){
		TakeWineRecord record = takeWineRecordAccepter.getRecordByRecordNumber(recordNumber);
		return TakeWineRecordVO.takeWineRecordToVO(record);
	}
}
