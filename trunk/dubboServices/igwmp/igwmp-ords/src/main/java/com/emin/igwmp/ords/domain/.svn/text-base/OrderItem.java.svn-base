package com.emin.igwmp.ords.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.emin.base.domain.BaseEntity;
import com.emin.igwmp.ords.dao.JSONBUserType; 

import net.sf.json.JSONObject;
@Entity
@Table(schema="ords",name="order_item")
@TypeDefs( {@TypeDef( name= "JsonObject", typeClass = JSONBUserType.class)})
public class OrderItem extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4728030437442066958L;
	
	private Long orderId;//订单ID
		
	private JSONObject productInfo;//产品信息JSON
	
	private Long productId;
			
	private Integer count;//条目产品数量

	private Double price;//条目单价
	
	private Double amount;//条目总金额	
	
	
	@Id
	@Override
	@SequenceGenerator(name = "order_item_id_seq", sequenceName = "ords.order_item_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_id_seq")
	public Long getId() {
		return super.getId();
	}


	@Column(name="order_id")
	public Long getOrderId() {
		return orderId;
	}



	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}


	@Column(name="product_info")
	@Type(type="JsonObject")
	public JSONObject getProductInfo() {
		return productInfo;
	}



	public void setProductInfo(JSONObject productInfo) {
		this.productInfo = productInfo;
	}


	@Column(name="product_id")
	public Long getProductId() {
		return productId;
	}



	public void setProductId(Long productId) {
		this.productId = productId;
	}


	@Column(name="count")
	public Integer getCount() {
		return count;
	}



	public void setCount(Integer count) {
		this.count = count;
	}


	@Column(name="price")
	public Double getPrice() {
		return price;
	}



	public void setPrice(Double price) {
		this.price = price;
	}


	@Column(name="amount")
	public Double getAmount() {
		return amount;
	}



	public void setAmount(Double amount) {
		this.amount = amount;
	}

	

}
