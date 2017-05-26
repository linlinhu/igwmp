package com.emin.igwmp.rs.domain;

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
@Table(schema="rs",name="region")
public class Region extends BaseEntity implements UndeleteableEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9216591844612591973L;
	public static final String PROP_NAME = "name";
	public static final String PROP_NZM = "nzm";
	public static final String PROP_TYPE = "type";
	public static final String PROP_PID = "pid";
	
	private String name;
	private String nzm;
	private Long pid;
	private String remark;
	private int status;
	private Long createTime;
	private Long lastModifyTime;
	
	@Id
	@Override
	@SequenceGenerator(name = "region_id_seq", sequenceName = "jz.region_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "region_id_seq")
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
	@Column(name="nzm")
	public String getNzm() {
		return nzm;
	}
	public void setNzm(String nzm) {
		this.nzm = nzm;
	}
	
	@Column(name="pid")
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	@Column(name="remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="status")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Column(name="createtime")
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	@Column(name="lastmodifytime")
	public Long getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	
	
}
