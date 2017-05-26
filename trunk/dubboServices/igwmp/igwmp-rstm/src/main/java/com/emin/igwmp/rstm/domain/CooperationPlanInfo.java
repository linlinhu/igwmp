package com.emin.igwmp.rstm.domain;

import java.util.Set;

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

import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity;

/**
 * 方案管理员信息实体
 * @author zhaoqt
 *
 */
@Table(schema="rstm",name="cooperation_plan_info")
@Entity
public class CooperationPlanInfo extends BaseEntity implements UndeleteableEntity{
    
	private static final long serialVersionUID = 345138808705567947L;

	private Long createTime;
	
	private Long lastModifyTime;
	
	private int status;	
	
	private String name;//方案名称
	private String memo;//方案描述
	private int roleType;//收益角色
	private Double profitPercent;//收益比例
	private Set<RestaurantPrivateInfo> restaurantPrivateInfo;
	
	@Id
	@Override
	@SequenceGenerator(name = "rstc_id_seq", sequenceName = "rstm.rstc_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rstc_id_seq")
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

	public String getName() {
		return name;
	}


	@Column(name="name")
	public void setName(String name) {
		this.name = name;
	}

	public String getMemo() {
		return memo;
	}

	@Column(name="memo")
	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name="role_type")
	public int getRoleType() {
		return roleType;
	}

	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}

	@Column(name="profit_percent")
	public Double getProfitPercent() {
		return profitPercent;
	}

	public void setProfitPercent(Double profitPercent) {
		this.profitPercent = profitPercent;
	}
   
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="ref_private_cooperation",
	           joinColumns={ @JoinColumn(name="cooperation_plan_id",referencedColumnName="id")},
	           inverseJoinColumns={@JoinColumn(name="private_info_id",referencedColumnName="id")})
	public Set<RestaurantPrivateInfo> getRestaurantPrivateInfo() {
		return restaurantPrivateInfo;
	}

	public void setRestaurantPrivateInfo(
			Set<RestaurantPrivateInfo> restaurantPrivateInfo) {
		this.restaurantPrivateInfo = restaurantPrivateInfo;
	} 	
}
