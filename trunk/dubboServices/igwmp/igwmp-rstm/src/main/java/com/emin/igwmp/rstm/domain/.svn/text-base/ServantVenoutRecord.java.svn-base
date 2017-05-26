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

import net.sf.json.JSONObject;

import org.hibernate.annotations.Type;

import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity;
import com.emin.igwmp.rstm.dao.JSONBUserType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

/**
 * 服务员打酒记录信息实体
 * @author zhaoqt
 *
 */
@Table(schema="rstm",name="servant_venout_record")
@Entity
@TypeDefs( {@TypeDef( name= "JsonObject", typeClass = JSONBUserType.class)})
public class ServantVenoutRecord extends BaseEntity implements UndeleteableEntity{

    
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 513010573462377680L;

	private Long createTime;
	
	private Long lastModifyTime;
	
	private int status;
	
	private Integer gainIntegral;//获得的积分
	
	private String orderNo;//订单编号
	
	private Integer vendoutCapacity;//出酒量

	private String servantName;//服务员姓名
	
	private Long servantId;//服务员id

	private String wineName;//酒品名称
	
	private Long wineId;//酒品id
	
	private Long codeExpireTime;//取酒码过期时间
	
	private String takeCode;//取酒码过期时间
	
	private String restName;//饭店名称
	
	private Long restId;//饭店id
    
	private Long productId;//产品id
	
	private int tableNum;//桌号
	
	private JSONObject productInfo;//产品(酒品)信息
	
	private Long vendeeId;//购酒人fansId
	
	private JSONObject vendeeInfo;//购酒人信息
	
	private RestaurantServantInfo restaurantServantInfo;//饭店服务员信息
	
	private int vendoutStatus;//0.未取酒 1.已取酒
	
	private double totalMoney;//订单金额
	
	@Id
	@Override
	@SequenceGenerator(name = "rst_venout_record_id_seq", sequenceName = "rstm.rst_venout_record_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rst_venout_record_id_seq")
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

	@Column(name="gain_integral")
	public Integer getGainIntegral() {
		return gainIntegral;
	}

	public void setGainIntegral(Integer gainIntegral) {
		this.gainIntegral = gainIntegral;
	}

	@Column(name="order_no")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name="vendout_capacity")
	public Integer getVendoutCapacity() {
		return vendoutCapacity;
	}

	public void setVendoutCapacity(Integer vendoutCapacity) {
		this.vendoutCapacity = vendoutCapacity;
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

	@Column(name="servant_id")	
	public Long getServantId() {
		return servantId;
	}

	public void setServantId(Long servantId) {
		this.servantId = servantId;
	}
	
	
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "servant_id",insertable=false,updatable=false)
	public RestaurantServantInfo getRestaurantServantInfo() {
		return restaurantServantInfo;
	}

	public void setRestaurantServantInfo(RestaurantServantInfo restaurantServantInfo) {
		this.restaurantServantInfo = restaurantServantInfo;
	}

	@Column(name="rest_name")	
	public String getRestName() {
		return restName;
	}

	public void setRestName(String restName) {
		this.restName = restName;
	}
	
	@Column(name="wine_id")	
	public Long getWineId() {
		return wineId;
	}

	public void setWineId(Long wineId) {
		this.wineId = wineId;
	}
	@Column(name="rest_id")	
	public Long getRestId() {
		return restId;
	}

	public void setRestId(Long restId) {
		this.restId = restId;
	}
	
	@Column(name="product_id")	
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	@Column(name="product_info")
	@Type(type="JsonObject")
	public JSONObject getProductInfo() {
		return productInfo;
	}
	
	public void setProductInfo(JSONObject productInfo) {
		this.productInfo = productInfo;
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

	@Column(name="vendout_status")	
	public int getVendoutStatus() {
		return vendoutStatus;
	}

	public void setVendoutStatus(int vendoutStatus) {
		this.vendoutStatus = vendoutStatus;
	}

	@Column(name="code_expire_time")	
	public Long getCodeExpireTime() {
		return codeExpireTime;
	}

	public void setCodeExpireTime(Long codeExpireTime) {
		this.codeExpireTime = codeExpireTime;
	}

	@Column(name="take_code")	
	public String getTakeCode() {
		return takeCode;
	}

	public void setTakeCode(String takeCode) {
		this.takeCode = takeCode;
	}

	@Column(name="table_num")	
	public int getTableNum() {
		return tableNum;
	}

	public void setTableNum(int tableNum) {
		this.tableNum = tableNum;
	}

	@Column(name="total_money")	
	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}
	
	
}
