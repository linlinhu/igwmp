package com.emin.wxs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity;
@Entity
@Table(schema="public",name="wxwebpagecate")
public class WeixinWebPageCategory extends BaseEntity implements UndeleteableEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -661553997236922029L;
	public static final String PROP_PID = "pid";
	public static final String PROP_NAME = "name";
	public static final String PROP_WOAID = "woaId";
	private String name;
	private Long pid;
	private Byte[] img;
	private String text;
	private String remark;
	private int status;
	private Long createTime;
	private Long lastModifyTime;
	private Long woaId;
	@Id
	@Override
	@SequenceGenerator(name = "wxwebpagecate_id_seq", sequenceName = "public.wxwebpagecate_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wxwebpagecate_id_seq")
	public Long getId() {
		return super.getId();
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="pid")
	public Long getPid() {
		return pid;
	}
	
	public void setPid(Long pid) {
		this.pid = pid;
	}
	@Column(name="img")
	public Byte[] getImg() {
		return img;
	}
	public void setImg(Byte[] img) {
		this.img = img;
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
	@Transient
	public String getText() {
		this.text = this.getName();
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Column(name="woaid")
	public Long getWoaId() {
		return woaId;
	}
	public void setWoaId(Long woaId) {
		this.woaId = woaId;
	}
	
}
