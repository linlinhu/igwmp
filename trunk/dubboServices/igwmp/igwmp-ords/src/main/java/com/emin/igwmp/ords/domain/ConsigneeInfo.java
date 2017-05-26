package com.emin.igwmp.ords.domain;

import javax.persistence.Column;

import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity;

public class ConsigneeInfo extends BaseEntity implements UndeleteableEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2466653626884586676L;

	private String name;//姓名
	
	private String mobilePhone;//手机号
	
	private String province;//省
	
	private String city;//市
	
	private String district;//区
	
	private String address;//详细地址
	
	private int status;
	
	private Long createTime;
	
	private Long lastModifyTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	@Column(name="address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
