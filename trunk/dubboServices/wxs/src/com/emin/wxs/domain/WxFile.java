package com.emin.wxs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.emin.base.domain.BaseEntity;
@Entity
@Table(schema="public",name="wxfile")
public class WxFile extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4331374892692606261L;
	public static final String PROP_WOAID = "woaId";
	public static final Integer TYPE_DOC = 1;
	public static final Integer TYPE_IMG = 2;
	public static final Integer TYPE_AUDIO = 3;
	public static final Integer TYPE_VIDEO = 4;
	public static final Integer TYPE_RAR = 5;
	private Integer type;
	private String name;
	private String path;
	private Long size;
	private int status;
	private Long createTime;
	private Long lastModifyTime;	
	private Long woaId;//所属公众号ID
	
	@Id
	@Override
	@SequenceGenerator(name = "wxfile_id_seq", sequenceName = "public.wxfile_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wxfile_id_seq")
	public Long getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}
	@Column(name="type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name="path")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	@Column(name="size")
	public Long getSize() {
		return size;
	}
	
	public void setSize(Long size) {
		this.size = size;
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
	@Column(name="woaid")
	public Long getWoaId() {
		return woaId;
	}
	public void setWoaId(Long woaId) {
		this.woaId = woaId;
	}
	
}
