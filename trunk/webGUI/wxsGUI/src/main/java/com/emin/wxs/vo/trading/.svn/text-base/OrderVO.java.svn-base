package com.emin.wxs.vo.trading;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.emin.igwmp.ords.domain.GivenStatus;
import com.emin.igwmp.ords.domain.Order;
import com.emin.igwmp.ords.domain.OrderItem;
import com.emin.igwmp.ords.domain.OrderStatus;
import com.emin.igwmp.ords.domain.OrderType; 

import net.sf.json.JSONObject;

public class OrderVO {

	private String orderNumber;//订单号
	
	private OrderType orderType; // 订单类型
	
	private Long vendeeId;//下单方ID 对应微信粉丝ID （由WXS服务提供）
		
	private JSONObject vendeeInfo;//下单方信息 买方个人信息JSON
	
	private Integer vendeeType = 1;//下单方类型 (目前仅有微信消费者) 默认为1
	
	private Long createTime;//下单时间
	
	private Double originalTotalMoney;//订单原始金额
	
	private Double totalMoney;//订单实际金额
	
	private Double payedMoney;//订单已付金额
	
	private Long fininshTime;//订单完成时间
	
	private OrderStatus orderStatus;//订单状态码
	
	private List<OrderItemVO> items;
	
	private GivenStatus givenStatus;//赠送状态
	
	
	public static OrderVO orderToVO(Order order){
		if(order==null){
			return null;
		}
		OrderVO orderVO = new OrderVO();
		BeanUtils.copyProperties(order, orderVO, "items");
		orderVO.setItems(OrderItemVO.orderItemToVO(order.getItems()));
		return orderVO;
	}
	
	public static List<OrderVO> orderToVO(Collection<Order> order){
		List<OrderVO> vos = new ArrayList<>();
		if(order==null){
			return vos;
		}
		for(Order o : order){
			vos.add(orderToVO(o));
		}
		return vos;
	}
	
	public Order converToOrder(){
		Order order = new Order();
		BeanUtils.copyProperties(this, order, "items");
		Set<OrderItem> items = new HashSet<>();
		for(OrderItemVO itemVO : this.items){
			items.add(itemVO.converToOrderItem());
		}
		order.setItems(items);
		//order.setVendeeInfo(this.vendeeInfo);
		return order;
	} 
	public Order converToOrder(Order order){
		if(order==null){
			return this.converToOrder();
		}
		BeanUtils.copyProperties(this, order, "items");
		Set<OrderItem> items = new HashSet<>();
		for(OrderItemVO itemVO : this.items){
			items.add(itemVO.converToOrderItem());
		}
		order.setItems(items);
		//order.setVendeeInfo(this.vendeeInfo);
		return order;
	} 
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	public Long getVendeeId() {
		return vendeeId;
	}

	public void setVendeeId(Long vendeeId) {
		this.vendeeId = vendeeId;
	}

	public JSONObject getVendeeInfo() {
		return vendeeInfo;
	}

	public void setVendeeInfo(JSONObject vendeeInfo) {
		this.vendeeInfo = vendeeInfo;
	}

	public Integer getVendeeType() {
		return vendeeType;
	}

	public void setVendeeType(Integer vendeeType) {
		this.vendeeType = vendeeType;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	

	public Double getOriginalTotalMoney() {
		return originalTotalMoney;
	}

	public void setOriginalTotalMoney(Double originalTotalMoney) {
		this.originalTotalMoney = originalTotalMoney;
	}

	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Double getPayedMoney() {
		return payedMoney;
	}

	public void setPayedMoney(Double payedMoney) {
		this.payedMoney = payedMoney;
	}

	public Long getFininshTime() {
		return fininshTime;
	}

	public void setFininshTime(Long fininshTime) {
		this.fininshTime = fininshTime;
	}


	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	

	public List<OrderItemVO> getItems() {
		return items;
	}

	public void setItems(List<OrderItemVO> items) {
		this.items = items;
	}

	public GivenStatus getGivenStatus() {
		return givenStatus;
	}

	public void setGivenStatus(GivenStatus givenStatus) {
		this.givenStatus = givenStatus;
	}
	
	
}
