package com.emin.igwmp.rstm.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity;

/**
 * 饭店管理员信息实体
 * @author zhaoqt
 *
 */
@Table(schema="rstm",name="restaurant_admin_info")
@Entity
public class RestaurantAdminInfo extends BaseEntity implements UndeleteableEntity{
    
	private static final long serialVersionUID = 7880111616104533247L;

	public static final String PROP_NAME="name";   
	
	public static final String PROP_RST_PB_INFO_NAME="restaurants."+RestaurantPublicInfo.PROP_NAME;
	
    public static final String PROP_ROLE_TYPE="roleType";
    
	public static final int ROLE_TYPE_BOSS = 1; 
    
	public static final int ROLE_TYPE_MANAGER = 2;
    
	
	private Long createTime;
	
	private Long lastModifyTime;
	
	private int status;	
	
	private String name;//姓名
	private String cellphone;//手机号码
	private String wechatId;//微信id(openid或userid)
	private int roleType;//角色类型
	private String memo;//备注
	private int nowStatus;//在职状态
	private Set<RestaurantPublicInfo> restaurants = new HashSet<RestaurantPublicInfo>();//管理的饭店
	
	@Id
	@Override
	@SequenceGenerator(name = "rsta_id_seq", sequenceName = "rstm.rsta_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rsta_id_seq")
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
	
	public String getWechatId() {
		return wechatId;
	}

	@Column(name="wechat_Id")
	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	
	public int getRoleType() {
		return roleType;
	}
	
	@Column(name="role_type")
	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}

	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)  
	@JoinTable(name="ref_restaurant_admin",
	           joinColumns={ @JoinColumn(name="admin_id",referencedColumnName="id")},
	           inverseJoinColumns={@JoinColumn(name="restaurant_id",referencedColumnName="id")})
	public Set<RestaurantPublicInfo> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(Set<RestaurantPublicInfo> restaurants) {
		this.restaurants = restaurants;
	}

	public String getMemo() {
		return memo;
	}
	
	@Column(name="memo")
	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name="now_status")
	public int getNowStatus() {
		return nowStatus;
	}

	public void setNowStatus(int nowStatus) {
		this.nowStatus = nowStatus;
	}	
	
	
}
