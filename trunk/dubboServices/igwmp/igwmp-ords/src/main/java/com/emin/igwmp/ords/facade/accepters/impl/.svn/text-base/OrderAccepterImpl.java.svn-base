package com.emin.igwmp.ords.facade.accepters.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.dao.PreFilter;
import com.emin.base.domain.UndeleteableEntity;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.base.service.Conditions;
import com.emin.igwmp.ords.domain.GivenStatus;
import com.emin.igwmp.ords.domain.Order;
import com.emin.igwmp.ords.domain.OrderItem;
import com.emin.igwmp.ords.domain.OrderStatus;
import com.emin.igwmp.ords.domain.OrderType;
import com.emin.igwmp.ords.exception.OrdsExceptionCode;
import com.emin.igwmp.ords.facade.accepters.OrderAccepter;
import com.emin.igwmp.ords.redis.RedisDao;
import com.emin.igwmp.ords.service.OrderService;
import com.emin.igwmp.ords.util.EminPropertyPlaceholderConfigurer;

import net.sf.json.JSONObject;
@Component("orderAccepter")
@Service(version="0.0.1")
public class OrderAccepterImpl implements OrderAccepter{

	@Autowired
	private OrderService orderService;
	@Autowired
	private RedisDao redisDao;
	
	@Override
	public String createOrder(Order order){
		return orderService.createOrder(order);
	}
	@Override
	public String createOrderForMachine(Order order){
		if(order==null){
			throw new EminException(OrdsExceptionCode.PARAMTER_INVALID);
		}
		order.setVendeeId(-1l);
		order.setVendeeInfo(new JSONObject());
		return orderService.createOrder(order);
	}
	@Override
	public List<Order> loadOrderByCondition(List<Condition> conditions){
		List<PreFilter> filters = new ArrayList<>();
		boolean needStatus = true;
		if(conditions!=null && conditions.size()>0){
			for(Condition condition:conditions){
				filters.add(Conditions.convertToPropertyFilter(condition));
				if(condition.getPropertyName().equals(UndeleteableEntity.PROP_STATUS)){
					needStatus = false;
				}
			}
		}
		if(needStatus){
			filters.add(orderService.getStatusFilter());
		}
		PreFilter[] preFilters = new PreFilter[filters.size()];
		return orderService.findByPreFilter(filters.toArray(preFilters));
	}
	@Override
	public PagedResult<Order> loadOrderByCondition(PageRequest pageRequest,List<Condition> conditions){
		return orderService.loadOrdersByConditions(pageRequest, conditions);
	}
	@Override
	public Order findOrderById(Long id){
		return orderService.findById(id);
	}
	@Override
	public Order findOrderByNumber(String orderNumber){
		return orderService.findOrderByOrderNumber(orderNumber);
	}
	@Override
	public void giveOrder(String orderNumber ){
		Order order = this.findOrderByNumber(orderNumber);
		if(order.getOrderStatus().equals(OrderStatus.TOBETAKE) && 
				(GivenStatus.NORMAL.equals(order.getGivenStatus()) || GivenStatus.UNTREAD.equals(order.getGivenStatus()))){
			order.setGivenStatus(GivenStatus.GIVING);
			orderService.update(order);
			//将订单加入到待领取队列并设置有效期
			redisDao.put("Order", "GIVING_"+orderNumber,JSONObject.fromObject(order).toString());
			String hours = EminPropertyPlaceholderConfigurer.getContextProperty("order.givenExipreHours");
			redisDao.exipre("GIVING_"+orderNumber,Long.valueOf(hours),TimeUnit.HOURS);
		}else{
			throw new EminException(OrdsExceptionCode.ORDER_CAN_NOT_GIVE);
		}
	}
	@Override
	public void getGivingOrder(String orderNumber,Long receiverId,JSONObject receiverInfo){
		Order order = this.findOrderByNumber(orderNumber);
		if(GivenStatus.GIVING.equals(order.getGivenStatus())){
			Order newOrder = new Order();
			Set<OrderItem> items = new HashSet<>();
			BeanUtils.copyProperties(order, newOrder);
			newOrder.setId(null);
			newOrder.setDonorId(order.getVendeeId());
			newOrder.setDonorType(order.getVendeeType());
			newOrder.setDonorInfo(order.getVendeeInfo());
			newOrder.setVendeeId(receiverId);
			newOrder.setVendeeInfo(receiverInfo);
			newOrder.setOrderType(OrderType.GIVEN);
			for(OrderItem item : order.getItems()){
				OrderItem newItem = new OrderItem();
				BeanUtils.copyProperties(item, newItem);
				newItem.setId(null);
				newItem.setOrderId(null);
				items.add(newItem);
			}
			newOrder.setGivenStatus(GivenStatus.NORMAL);
			order.setGivenStatus(GivenStatus.GIVEN);
			orderService.save(newOrder);
			orderService.update(order);
		}else{
			throw new EminException(OrdsExceptionCode.GIVINGORDER_CAN_NOT_GET);
		}
	}
	@Override
	public void cancelGiveOrder(String orderNumber){
		Order order = this.findOrderByNumber(orderNumber);
		if(order.getId()!=null && order.getId().longValue()==0l){
			order.setGivenStatus(GivenStatus.NORMAL);
			orderService.update(order);
			//删除待领取订单
			redisDao.delete("Order", "GIVING_"+orderNumber);
		}else{
			//未知订单不做处理
		}		
	}
	@Override
	public String makeUpForLockedOrder(String orderNumber,Integer compensationAmount){
		return orderService.makeUpForLockedOrder(orderNumber, compensationAmount);
	}
}
