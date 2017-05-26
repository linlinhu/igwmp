package com.emin.igwmp.rstm.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity;
import com.emin.igwmp.rstm.dao.JSONBUserType;

import net.sf.json.JSONObject;


/**
 * 饭店管理员信息实体
 * @author zhaoqt
 *
 */
@Table(schema="rstm",name="restaurant_servant_info")
@Entity
@TypeDefs( {@TypeDef( name= "JsonObject", typeClass = JSONBUserType.class)})
public class RestaurantServantInfo extends BaseEntity implements UndeleteableEntity{
    

	private static final long serialVersionUID = 7834913656390648803L;

	private Long createTime;
	
	private Long lastModifyTime;
	
	private int status;	
	
	private String name;//姓名
	private Integer gender;//性别
	private String cellphone;//手机号码
	private String wechatId;//微信id(openid或userid)
	private Set<RestaurantPublicInfo> restaurants;//管理的饭店
	private int integral;
	private int historyIntegral;
	private int exchangeIntegral;
	private int auditingStatus;//审核状态
	private String auditingPerson;//审核人
	private String auditingMemo;//审核备注
	private Long fansId;//粉丝表里的对应的id
	private JSONObject fansInfo;//粉丝表信息
	@Id
	@Override
	@SequenceGenerator(name = "rstser_id_seq", sequenceName = "rstm.rstser_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rstser_id_seq")
	public Long getId() { 
		return super.getId();
	}
	
	@Override	
	public Long getCreateTime() {
		return createTime;
	}

	@Override
	@Column(name="create_time")
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Override
	public Long getLastModifyTime() {
		return lastModifyTime;
	}

	@Override
	@Column(name="last_modify_time")
	public void setLastModifyTime(Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	@Override
	public int getStatus() {		
		return status;
	}

	@Override
	@Column(name="status")
	public void setStatus(int status) {
		this.status = status;
	}


	public String getName() {
		return name;
	}

	@Column(name="name")
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCellphone() {
		return cellphone;
	}
	
	@Column(name="cellphone")
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	@Column(name="wechatId")
	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	@Column(name="integral")
	public int getIntegral() {
		return integral;
	}


	public void setIntegral(int integral) {
		this.integral = integral;
	}

	@Column(name="history_integral")
	public int getHistoryIntegral() {
		return historyIntegral;
	}

	public void setHistoryIntegral(int historyIntegral) {
		this.historyIntegral = historyIntegral;
	}

	@Column(name="exchange_integral")
	public int getExchangeIntegral() {
		return exchangeIntegral;
	}

	public void setExchangeIntegral(int exchangeIntegral) {
		this.exchangeIntegral = exchangeIntegral;
	}

	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="ref_restaurant_servant",
	           joinColumns={ @JoinColumn(name="servant_id",referencedColumnName="id")},
	           inverseJoinColumns={@JoinColumn(name="restaurant_id",referencedColumnName="id")})
	public Set<RestaurantPublicInfo> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(Set<RestaurantPublicInfo> restaurants) {
		this.restaurants = restaurants;
	}

	@Column(name="gender")
	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	@Column(name = "auditing_status")
	public int getAuditingStatus() {
		return auditingStatus;
	}

	public void setAuditingStatus(int auditingStatus) {
		this.auditingStatus = auditingStatus;
	}

	@Column(name = "auditing_person")
	public String getAuditingPerson() {
		return auditingPerson;
	}

	public void setAuditingPerson(String auditingPerson) {
		this.auditingPerson = auditingPerson;
	}

	@Column(name = "auditing_memo")
	public String getAuditingMemo() {
		return auditingMemo;
	}

	public void setAuditingMemo(String auditingMemo) {
		this.auditingMemo = auditingMemo;
	}

	public Long getFansId() {
		return fansId;
	}

	public void setFansId(Long fansId) {
		this.fansId = fansId;
	}

	@Column(name = "fans_info")
	@Type(type="JsonObject")
	public JSONObject getFansInfo() {
		return fansInfo;
	}

	public void setFansInfo(JSONObject fansInfo) {
		this.fansInfo = fansInfo;
	}
}
