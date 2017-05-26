package com.emin.igwmp.ords.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity;
import com.emin.igwmp.ords.dao.JSONBUserType;
import com.fasterxml.jackson.databind.node.ObjectNode;

import net.sf.json.JSONObject;
@Entity
@Table(schema="ords",name="order")
@TypeDefs( {@TypeDef( name= "JsonObject", typeClass = JSONBUserType.class)})
public class Order extends BaseEntity implements UndeleteableEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4728030437442066958L;
	
	public static final String PROP_ORDER_NUMBER = "orderNumber";
	
	public static final String PROP_VENDEE_ID = "vendeeId";
	
	public static final String PROP_VENDEE_INFO = "vendeeInfo";
	
	public static final String PROP_ORDER_STATUS = "orderStatus";
	
	public static final String PROP_VENDEE_TYPE = "vendeeType";
	
	public static final String PROP_SOURCE_OURDER_NUMBER = "vendeeType";
	
	//public static final String PROP_ITEMS_NAME = "items."+OrderItem.

	private String orderNumber;//订单号
	
	private OrderType orderType; // 订单类型
	
	private Long vendeeId;//下单方ID 对应微信粉丝ID （由WXS服务提供）
		
	private JSONObject vendeeInfo;//下单方信息 买方个人信息JSON
	
	private Integer vendeeType = 1;//下单发类型 (目前仅有微信消费者) 默认为1
	
	private Long createTime;
	
	private Long lastModifyTime;
	
	private Double originalTotalMoney;//订单原始金额
	
	private Double totalMoney;//订单实际金额
	
	private Double payedMoney;//订单已付金额
	
	private Long fininshTime;
	
	private int status = UndeleteableEntity.STATUS_VALID;
	
	private OrderStatus orderStatus;//订单状态码
	
	private Set<OrderItem> items;
	
	private GivenStatus givenStatus;//赠送状态
	
	private Long donorId;//赠送方ID
	
	private Integer donorType;//赠送方类型
	
	private JSONObject donorInfo;//赠送方信息
	
	private String sourceOrderNumber;//若为补偿订单 对应补偿来源的订单号
	
	@Id
	@Override
	@SequenceGenerator(name = "order_id_seq", sequenceName = "ords.order_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
	public Long getId() {
		return super.getId();
	}

	
	@Override
	@Column(name="create_time")
	public Long getCreateTime() {
		return this.createTime;
	}

	@Override
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Override
	@Column(name="last_modity_time")
	public Long getLastModifyTime() {
		return this.lastModifyTime;
	}

	@Override
	public void setLastModifyTime(Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	@Override
	@Column(name="status")
	public int getStatus() {
		return this.status;
	}

	@Override
	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name="order_number")
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	@Column(name="vendee_id")
	public Long getVendeeId() {
		return vendeeId;
	}

	public void setVendeeId(Long vendeeId) {
		this.vendeeId = vendeeId;
	}
	
	@Column(name="vendee_info")
	@Type(type="JsonObject")
	public JSONObject getVendeeInfo() {
		return vendeeInfo;
	}

	public void setVendeeInfo(JSONObject vendeeInfo) {
		this.vendeeInfo = vendeeInfo;
	}

	@Column(name="vendee_type")
	public Integer getVendeeType() {
		return vendeeType;
	}

	public void setVendeeType(Integer vendeeType) {
		this.vendeeType = vendeeType;
	}
	@Column(name="finish_time")
	public Long getFininshTime() {
		return fininshTime;
	}

	public void setFininshTime(Long fininshTime) {
		this.fininshTime = fininshTime;
	}

	@Column(name="order_status")
	@Enumerated(EnumType.STRING)
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	@OneToMany(fetch=FetchType.EAGER,cascade={CascadeType.ALL})
	@JoinColumn(name="order_id")
	@OrderBy(value="id")
	public Set<OrderItem> getItems() {
		return items;
	}


	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}


	@Column(name="order_type")
	@Enumerated(EnumType.STRING)
	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	@Column(name="original_total_money")
	public Double getOriginalTotalMoney() {
		return originalTotalMoney;
	}


	public void setOriginalTotalMoney(Double originalTotalMoney) {
		this.originalTotalMoney = originalTotalMoney;
	}


	@Column(name="total_money")
	public Double getTotalMoney() {
		return totalMoney;
	}


	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	@Column(name="payed_money")
	public Double getPayedMoney() {
		return payedMoney;
	}


	public void setPayedMoney(Double payedMoney) {
		this.payedMoney = payedMoney;
	}


	@Column(name="given_status")
	@Enumerated(EnumType.STRING)
	public GivenStatus getGivenStatus() {
		return givenStatus;
	}


	public void setGivenStatus(GivenStatus givenStatus) {
		this.givenStatus = givenStatus;
	}

	@Column(name="donor_id")
	public Long getDonorId() {
		return donorId;
	}


	public void setDonorId(Long donorId) {
		this.donorId = donorId;
	}

	@Column(name="donor_type")
	public Integer getDonorType() {
		return donorType;
	}


	public void setDonorType(Integer donorType) {
		this.donorType = donorType;
	}

	@Column(name="donor_info")
	@Type(type="JsonObject")
	public JSONObject getDonorInfo() {
		return donorInfo;
	}


	public void setDonorInfo(JSONObject donorInfo) {
		this.donorInfo = donorInfo;
	}


	@Column(name="source_order_number")
	public String getSourceOrderNumber() {
		return sourceOrderNumber;
	}


	public void setSourceOrderNumber(String sourceOrderNumber) {
		this.sourceOrderNumber = sourceOrderNumber;
	}

	
	
	
}
