package com.emin.igwmp.rstm.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity;

/**
 * 服务员积分兑换记录信息实体
 * @author zhaoqt
 *
 */
@Table(schema="rstm",name="servant_integral_record")
@Entity
public class ServantExchangeIntegralRecord extends BaseEntity implements UndeleteableEntity{
  	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7635707874276916176L;

	private Long createTime;
	
	private Long lastModifyTime;
	
	private int status;	
	
	private Long exchangeTime;//兑换时间
	
	private int expendIntegral;//消耗积分
	
	private String orderNo;//订单编号
	
	private Integer orderStatus;//订单状态
	
	private String servantName;//服务员姓名
	
	private String wineName;//酒品名称
	
	private String restaurantName;//餐厅名称
	
	private RestaurantServantInfo restaurantServantInfo;//饭店服务员信息
	
	@Id
	@Override
	@SequenceGenerator(name = "rst_cooperation_plan_id_seq", sequenceName = "rstm.rst_cooperation_plan_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rst_cooperation_plan_id_seq")
	public Long getId() { 
		return super.getId();
	}
	
	@Override
	@Column(name="create_time")
	public Long getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Override
	@Column(name="last_modify_time")
	public Long getLastModifyTime() {
		return lastModifyTime;
	}

	@Override
	public void setLastModifyTime(Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	@Override
	@Column(name="status")
	public int getStatus() {		
		return status;
	}

	@Override
	public void setStatus(int status) {
		this.status = status;
	}
	
	 @ManyToOne(cascade = {CascadeType.ALL})
	 @JoinColumn(name = "servant_id")
	public RestaurantServantInfo getRestaurantServantInfo() {
		return restaurantServantInfo;
	}

	public void setRestaurantServantInfo(RestaurantServantInfo restaurantServantInfo) {
		this.restaurantServantInfo = restaurantServantInfo;
	}

	@Column(name="exchange_time")
	public Long getExchangeTime() {
		return exchangeTime;
	}

	public void setExchangeTime(Long exchangeTime) {
		this.exchangeTime = exchangeTime;
	}

	@Column(name="expend_Integral")
	public int getExpendIntegral() {
		return expendIntegral;
	}

	public void setExpendIntegral(int expendIntegral) {
		this.expendIntegral = expendIntegral;
	}


	@Column(name="order_no")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name="order_status")
	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Column(name="servant_name")
	public String getServantName() {
		return servantName;
	}

	public void setServantName(String servantName) {
		this.servantName = servantName;
	}

	@Column(name="wine_name")
	public String getWineName() {
		return wineName;
	}

	public void setWineName(String wineName) {
		this.wineName = wineName;
	}

	@Column(name="restaurant_name")
	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
}
