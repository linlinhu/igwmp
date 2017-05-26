package com.emin.igwmp.tws.facade.accepters.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.emin.base.exception.EminException;
import com.emin.igwmp.tws.domain.TakeWineRecord;
import com.emin.igwmp.tws.facade.accepters.TakeWineRecordAccepter;
import com.emin.igwmp.tws.service.TakeWineRecordService;
@Component("takeWineRecordAccepter")
@Service(version="0.0.1")
public class TakeWineRecordAccepterImpl implements TakeWineRecordAccepter{

	@Autowired
	private TakeWineRecordService takeWineRecordService;
	
	@Override
	public TakeWineRecord createOrGetTakeWineRecord(String orderNumber,Integer shouldTakeMl) throws EminException, Exception{
		return takeWineRecordService.createOrGetRecord(orderNumber, shouldTakeMl);
	}
	@Override
	public TakeWineRecord getRecordByOrderNumber(String orderNumber){
		return takeWineRecordService.getByOrderNumber(orderNumber);
	}
	@Override
	public TakeWineRecord getRecordByRecordNumber(String recordNumber){
		return takeWineRecordService.getByRecordNumber(recordNumber);
	}
	@Override
	public TakeWineRecord lockTakeCode(String code){
		return takeWineRecordService.lockTakeCode(code);
	}
	@Override
	public TakeWineRecord queryByTakeCode(String code){
		return takeWineRecordService.queryRecordByTakeCode(code);
	}
}
