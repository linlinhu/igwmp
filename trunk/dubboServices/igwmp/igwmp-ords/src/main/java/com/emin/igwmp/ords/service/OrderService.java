package com.emin.igwmp.ords.service;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.base.service.UndeleteableService;
import com.emin.igwmp.ords.domain.Order;

public interface OrderService extends UndeleteableService<Order>{

	String createOrder(Order order) throws EminException;

	PagedResult<Order> loadOrdersByConditions(PageRequest pageRequest, List<Condition> conditions);

	Order findOrderByOrderNumber(String orderNumber);

	String makeUpForLockedOrder(String orderNumber, Integer compensationAmount);

	String makeUpForExceptionOrder(String exceptionOrderNumber, Integer compensationAmount);

}
