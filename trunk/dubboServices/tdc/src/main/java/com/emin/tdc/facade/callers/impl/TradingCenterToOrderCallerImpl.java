package com.emin.tdc.facade.callers.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.igwmp.ords.facade.accepters.OrderAccepter;
import com.emin.tdc.facade.callers.TradingCenterToOrderCaller;
@Component("tradingCenterToOrderCaller")
public class TradingCenterToOrderCallerImpl implements TradingCenterToOrderCaller{

	@Reference(version="0.0.1")
	private OrderAccepter orderAccepter;
	
	@Override
	public void cancelGiveOrder(String orderNumber){
		orderAccepter.cancelGiveOrder(orderNumber);
	}
}
