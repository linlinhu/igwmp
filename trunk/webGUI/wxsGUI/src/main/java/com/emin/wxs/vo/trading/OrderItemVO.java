package com.emin.wxs.vo.trading;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.emin.igwmp.ords.domain.OrderItem;

import net.sf.json.JSONObject;

public class OrderItemVO {
	
	private Long id;
	
	private JSONObject productInfo;//产品信息JSON
	
	private Long orderId;
	
	private Long productId;
	
	private Integer count;//实际数量 毫升数 若要显示为 XX两 用这个值除以50即可
	
	private Double price;//实际条目单价 以毫升单位若要显示未xxx/斤 用这个值乘以500即可
	
	private Double amount;//条目总金额 保存时不传会自动计算 如果有值优先使用amount
	
	
	public static OrderItemVO orderItemToVO(OrderItem orderItem){
		if(orderItem==null){
			return null;
		}
		OrderItemVO vo = new OrderItemVO();
		BeanUtils.copyProperties(orderItem, vo);
		vo.setProductInfo(orderItem.getProductInfo());
		return vo;
	}
	public static List<OrderItemVO> orderItemToVO(Collection<OrderItem> orderItem){
		List<OrderItemVO> vos = new ArrayList<>();
		if(orderItem==null || orderItem.size()==0){
			return vos;
		}
		for(OrderItem item : orderItem){
			vos.add(orderItemToVO(item));
		}
		
		return vos;
	}
	
	public OrderItem converToOrderItem(){
		OrderItem orderItem = new OrderItem();
		BeanUtils.copyProperties(this, orderItem);
		return orderItem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public JSONObject getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(JSONObject productInfo) {
		this.productInfo = productInfo;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
		
	}

	

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	
}
