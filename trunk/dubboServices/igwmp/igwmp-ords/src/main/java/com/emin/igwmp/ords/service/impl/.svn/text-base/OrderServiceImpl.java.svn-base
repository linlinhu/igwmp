package com.emin.igwmp.ords.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.dao.PreFilter;
import com.emin.base.dao.PreFilters;
import com.emin.base.domain.UndeleteableEntity;
import com.emin.base.exception.EminException;
import com.emin.base.log.BussLog;
import com.emin.base.service.Condition;
import com.emin.base.service.Conditions;
import com.emin.base.service.UndeleteableServiceImpl;
import com.emin.igwmp.ords.domain.GivenStatus;
import com.emin.igwmp.ords.domain.Order;
import com.emin.igwmp.ords.domain.OrderItem;
import com.emin.igwmp.ords.domain.OrderStatus;
import com.emin.igwmp.ords.domain.OrderType;
import com.emin.igwmp.ords.exception.OrdsExceptionCode;
import com.emin.igwmp.ords.redis.RedisDao;
import com.emin.igwmp.ords.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONObject;
@Service("orderService")
public class OrderServiceImpl extends UndeleteableServiceImpl<Order> implements OrderService{

	@Autowired
	private RedisDao redisDao;
	
	@Override
	@BussLog(description="创建订单")
	public String createOrder(Order order){
		this.beforeCreateOrder(order);
		//生成订单号
		order.setOrderNumber(generateOrderNumber());
		//计算订单金额
		if(order.getTotalMoney()==null){
			Double totalMoney = 0.0d;
			for(OrderItem item :order.getItems()){
				if(item.getAmount()!=null){
					totalMoney+=item.getAmount();
				}else{
					totalMoney+=item.getCount()*item.getPrice();
				}
				
			}
			order.setTotalMoney(totalMoney);
		}
		if(order.getOriginalTotalMoney()==null){
			order.setOriginalTotalMoney(order.getTotalMoney());
		}
		
		//初始化订单状态
		order.setOrderStatus(OrderStatus.CREATE);
		
		//验证订单类型
		if(order.getOrderType()==null){
			//如果未指定默认为常规订单
			order.setOrderType(OrderType.NORMAL);
		}
		//这里先放入redis待支付完成后再写入数据库
		redisDao.put("Order", "ORDER_"+order.getOrderNumber(), JSONObject.fromObject(order).toString());
		return order.getOrderNumber();
	}
	
	private void beforeCreateOrder(Order order){
		if(order==null || order.getVendeeId()==null ||
				order.getItems()==null || order.getItems().size()==0 || 
				order.getVendeeInfo()==null){
			throw new EminException(OrdsExceptionCode.PARAMTER_INVALID);
		}
		for(OrderItem item : order.getItems()){
			if(item.getProductId()==null ||
					item.getProductInfo()==null ||
					item.getPrice()==null ||
					item.getCount()==null || item.getCount().intValue()==0){
				throw new EminException(OrdsExceptionCode.PARAMTER_INVALID);
			}
		}
		
		order.setVendeeType(1);
		
	}
	@Override
	@BussLog(description="分页查询订单")
	public PagedResult<Order> loadOrdersByConditions(PageRequest pageRequest,List<Condition> conditions){
		List<PreFilter> filters = new ArrayList<>();
		Boolean needStatus = true;
		if(conditions!=null && conditions.size()>0){
			for(Condition condition : conditions){
				if(condition.getPropertyName().equals(UndeleteableEntity.PROP_STATUS)){
					needStatus = false;
				}
				filters.add(Conditions.convertToPropertyFilter(condition));
			}
		}
		if (needStatus) {
			filters.add(this.getStatusFilter());
		}
		PreFilter[] preFilters = new PreFilter[filters.size()];
		return this.getPage(pageRequest,filters.toArray(preFilters));
	}
	@Override
	@BussLog(description="根据订单号查询订单")
	public Order findOrderByOrderNumber(String orderNumber){
		Order order = this.findUniqueByPreFilter(PreFilters.eq(Order.PROP_ORDER_NUMBER, orderNumber));
		//如果数据库中没有就从redis获取
		if(order==null){
			String orderJSON = redisDao.get("Order", "ORDER_"+orderNumber);
			System.out.println("从Redis中获取的订单信息:"+orderJSON);
			if(StringUtils.isBlank(orderJSON)){
				return null;
			}
			ObjectMapper mapper = new ObjectMapper(); 
			try {
				order = mapper.readValue(orderJSON, Order.class);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return order;
	}
	private String generateOrderNumber(){
		StringBuffer sb = new StringBuffer();
		String randomStr = RandomStringUtils.random(8, true, true);
		//订单号规则：OD开头 UNIX时间戳 8位字母数字组合随机字符串 共计20位
		sb.append("OD").append(System.currentTimeMillis()/1000).append(randomStr.toUpperCase());
		return sb.toString();
	}
	@Override
	public String makeUpForExceptionOrder(String exceptionOrderNumber,Integer compensationAmount){
		if(StringUtils.isBlank(exceptionOrderNumber) || compensationAmount==null || compensationAmount.intValue()==0){
			throw new EminException(OrdsExceptionCode.PARAMTER_INVALID);
		}
		int count = this.getCountByPreFilter(PreFilters.eq(Order.PROP_SOURCE_OURDER_NUMBER,exceptionOrderNumber));
		if(count>0){
			throw new EminException(OrdsExceptionCode.ORDER_HAS_BEEN_COMPENSATED);
		}
		Order exceptionOrder = this.findOrderByOrderNumber(exceptionOrderNumber);
		if(exceptionOrder!=null && exceptionOrder.getId()!=null && exceptionOrder.getId()!=0 && exceptionOrder.getOrderStatus().equals(OrderStatus.EXCEPTION)){
			Order compensationOrder = new Order();			
			compensationOrder.setId(null);
			compensationOrder.setOrderNumber(generateOrderNumber());
			compensationOrder.setGivenStatus(GivenStatus.NORMAL);
			compensationOrder.setOrderType(OrderType.COMPENSATE);
			compensationOrder.setOrderStatus(OrderStatus.TOBETAKE);
			compensationOrder.setOriginalTotalMoney(0d);
			compensationOrder.setTotalMoney(0d);
			compensationOrder.setPayedMoney(0d);
			compensationOrder.setDonorId(exceptionOrder.getDonorId());
			compensationOrder.setDonorType(exceptionOrder.getDonorType());
			compensationOrder.setDonorInfo(exceptionOrder.getDonorInfo());
			compensationOrder.setCreateTime(System.currentTimeMillis());
			compensationOrder.setLastModifyTime(System.currentTimeMillis());
			compensationOrder.setStatus(Order.STATUS_VALID);
			compensationOrder.setVendeeId(exceptionOrder.getVendeeId());
			compensationOrder.setVendeeType(exceptionOrder.getVendeeType());
			compensationOrder.setVendeeInfo(exceptionOrder.getVendeeInfo());
			compensationOrder.setSourceOrderNumber(exceptionOrderNumber);
			Set<OrderItem> items = new HashSet<>();
			for(OrderItem excepItem : exceptionOrder.getItems()){
				OrderItem item = new OrderItem();
				item.setPrice(excepItem.getPrice());
				item.setCount(compensationAmount);
				item.setAmount(item.getPrice()*item.getCount());
				item.setProductId(excepItem.getProductId());
				item.setProductInfo(excepItem.getProductInfo());
				items.add(item);
			}
			compensationOrder.setItems(items);
			
			this.save(compensationOrder);
			return compensationOrder.getOrderNumber();
		}else{
			throw new EminException(OrdsExceptionCode.PARAMTER_INVALID);
		}
	}
	@Override
	public String makeUpForLockedOrder(String orderNumber,Integer compensationAmount){
		if(StringUtils.isBlank(orderNumber) || compensationAmount==null || compensationAmount.intValue()==0 ||compensationAmount.intValue()<20){
			throw new EminException(OrdsExceptionCode.PARAMTER_INVALID);
		}
		int count = this.getCountByPreFilter(PreFilters.eq(Order.PROP_SOURCE_OURDER_NUMBER,orderNumber));
		if(count>0){
			throw new EminException(OrdsExceptionCode.ORDER_HAS_BEEN_COMPENSATED);
		}
		Order order = this.findOrderByOrderNumber(orderNumber);
		if(order!=null && order.getId()!=null && order.getId()!=0 && order.getOrderStatus().equals(OrderStatus.LOCKED)){
			Order compensationOrder = new Order();			
			compensationOrder.setId(null);
			compensationOrder.setOrderNumber(generateOrderNumber());
			compensationOrder.setGivenStatus(GivenStatus.NORMAL);
			compensationOrder.setOrderType(OrderType.COMPENSATE);
			compensationOrder.setOrderStatus(OrderStatus.TOBETAKE);
			compensationOrder.setOriginalTotalMoney(0d);
			compensationOrder.setTotalMoney(0d);
			compensationOrder.setPayedMoney(0d);
			compensationOrder.setDonorId(order.getDonorId());
			compensationOrder.setDonorType(order.getDonorType());
			compensationOrder.setDonorInfo(order.getDonorInfo());
			compensationOrder.setCreateTime(System.currentTimeMillis());
			compensationOrder.setLastModifyTime(System.currentTimeMillis());
			compensationOrder.setStatus(Order.STATUS_VALID);
			compensationOrder.setVendeeId(order.getVendeeId());
			compensationOrder.setVendeeType(order.getVendeeType());
			compensationOrder.setVendeeInfo(order.getVendeeInfo());
			compensationOrder.setSourceOrderNumber(orderNumber);
			Set<OrderItem> items = new HashSet<>();
			for(OrderItem excepItem : order.getItems()){
				OrderItem item = new OrderItem();
				item.setPrice(excepItem.getPrice());
				item.setCount(compensationAmount);
				item.setAmount(item.getPrice()*item.getCount());
				item.setProductId(excepItem.getProductId());
				item.setProductInfo(excepItem.getProductInfo());
				items.add(item);
			}
			compensationOrder.setItems(items);
			
			this.save(compensationOrder);
			return compensationOrder.getOrderNumber();
		}else{
			throw new EminException(OrdsExceptionCode.PARAMTER_INVALID);
		}
	}
}
