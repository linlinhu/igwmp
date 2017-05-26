package com.emin.pms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity;
@Entity
@Table(schema="pms",name="payment_channel")
public class PaymentChannel extends BaseEntity implements UndeleteableEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8705919060264507666L;

	public static final String PROP_CHANNEL_CODE = "chanelCode";
	
	private String name;
	
	private String chanelCode;
	
	private Long createTime;
	
	private Long lastModifyTime;
	
	private int status;
	
	@Id
	@Override
	@SequenceGenerator(name = "payment_channel_id_seq", sequenceName = "pms.payment_channel_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_channel_id_seq")
	public Long getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="channel_code")
	public String getChanelCode() {
		return chanelCode;
	}

	public void setChanelCode(String chanelCode) {
		this.chanelCode = chanelCode;
	}

	@Column(name="create_time")
	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Column(name="last_modify_time")
	public Long getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	@Column(name="status")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
