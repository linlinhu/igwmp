package com.emin.igwmp.ords.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.emin.base.dao.PreFilters;
import com.emin.igwmp.ords.domain.Order;
import com.emin.igwmp.ords.domain.OrderItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import net.sf.json.JSONObject;
@ContextConfiguration(locations={"/spring-servlet.xml"})
public class OrderServiceTest extends AbstractJUnit4SpringContextTests{

	private static ObjectMapper mapper = new ObjectMapper();
	@Autowired
	private OrderService orderService;
	@Before
	public void test0(){
		
		ObjectNode vendee = mapper.createObjectNode();
		vendee.put("id", 1);
		vendee.put("name", "jim");
		Order order = new Order();
		order.setVendeeId(1l);
		order.setVendeeInfo(vendee);
		order.setOrderNumber("23467273941");
		Set<OrderItem> items = new HashSet<>();
		OrderItem item = new OrderItem();
		item.setAmount(100d);
		item.setCount(1);
		item.setPrice(100d);
		item.setProductId(1l);
		ObjectNode productInfo = mapper.createObjectNode();
		productInfo.put("id", 1);
		productInfo.put("name", "习酒");
		item.setProductInfo(productInfo);
		items.add(item);
		order.setItems(items);
		orderService.createOrder(order);
	}
	
	
	@Test
	public void test1(){ 
		Order order = orderService.findOrderByOrderNumber("23467273941");
		
		System.out.println(order.getVendeeInfo());
	}
}
