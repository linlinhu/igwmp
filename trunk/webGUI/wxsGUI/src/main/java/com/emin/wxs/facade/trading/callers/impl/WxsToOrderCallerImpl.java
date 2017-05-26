package com.emin.wxs.facade.trading.callers.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.base.service.Condition.ConditionOperator;
import com.emin.base.service.Condition.ConditionType;
import com.emin.igwmp.ords.domain.Order;
import com.emin.igwmp.ords.domain.OrderStatus;
import com.emin.igwmp.ords.facade.accepters.OrderAccepter;
import com.emin.wxs.domain.Fans;
import com.emin.wxs.domain.FansItem;
import com.emin.wxs.facade.trading.callers.WxsToOrderCaller;
import com.emin.wxs.service.FansService;
import com.emin.wxs.service.WeixinToolService;
import com.emin.wxs.vo.trading.OrderItemVO;
import com.emin.wxs.vo.trading.OrderVO;

import net.sf.json.JSONObject;
@Component("wxsToOrderCaller")
public class WxsToOrderCallerImpl implements WxsToOrderCaller{

	@Reference(version="0.0.1")
	private OrderAccepter orderAccepter;
	
	@Reference(version="0.0.1")
	private FansService FansService;
	@Reference(version="0.0.1")
	private WeixinToolService weixinToolService;
	
	@Override
	public String createOrderByFansOpenId(String openId,List<OrderItemVO> itemVO) throws EminException,Exception{
		FansItem fansItem = FansService.loadByOpenId(openId);
		Fans fans = FansService.findById(fansItem.getFansId());
		OrderVO orderVO = new OrderVO();
		orderVO.setItems(itemVO);
		orderVO.setVendeeId(fans.getId());
		orderVO.setVendeeInfo(JSONObject.fromObject(fans));
		return orderAccepter.createOrder(orderVO.converToOrder());
	}
	
	@Override
	public String createOrderByFansUnionId(String unionId,List<OrderItemVO> itemVO) throws EminException,Exception{		
		Fans fans = FansService.loadByUnionId(unionId);
		OrderVO orderVO = new OrderVO();
		orderVO.setItems(itemVO);
		orderVO.setVendeeId(fans.getId());
		orderVO.setVendeeInfo(JSONObject.fromObject(fans));
		return orderAccepter.createOrder(orderVO.converToOrder());
	}
	
	public String getGivingOrderUrl(Long woaId,String url){
		try {
			return weixinToolService.convertUrlToOauthUrl(woaId, url);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	@Override
	public List<OrderVO> loadPersonalOrder(String openId,OrderStatus orderStatus){

		FansItem fansItem = FansService.loadByOpenId(openId);
		List<Condition> conditions = new ArrayList<>();
		conditions.add(new Condition(Order.PROP_VENDEE_ID, ConditionOperator.EQ, ConditionType.OTHER, fansItem.getFansId()));
		conditions.add(new Condition(Order.PROP_ORDER_STATUS, ConditionOperator.EQ, ConditionType.CHARACTER, orderStatus));
		List<Order> orders = orderAccepter.loadOrderByCondition(conditions);	 
		return OrderVO.orderToVO(orders);
	}
	@Override
	public OrderVO findOrderById(Long orderId){
		return OrderVO.orderToVO(orderAccepter.findOrderById(orderId));
	}
	@Override
	public OrderVO findOrderByNumber(String orderNumber){
		return OrderVO.orderToVO(orderAccepter.findOrderByNumber(orderNumber));
	}

	@Override
	public void giveOrder(String orderNumber) {
		orderAccepter.giveOrder(orderNumber);
		
	}

	@Override
	public void getGivingOrder(String orderNumber, Long receiverId, JSONObject receiverInfo) {
		orderAccepter.getGivingOrder(orderNumber, receiverId, receiverInfo);
		
	}

	@Override
	public void cancelGiveOrder(String orderNumber) {
		orderAccepter.cancelGiveOrder(orderNumber);
		
		
	}
}
