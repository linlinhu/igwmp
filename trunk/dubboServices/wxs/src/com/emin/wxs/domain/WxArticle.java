package com.emin.wxs.domain;

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
@Table(schema="public",name="wxarticle")
public class WxArticle extends BaseEntity implements UndeleteableEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1858789472257525594L;
	public static final String PROP_TITLE = "title";
	public static final String PROP_DESCRIPTION = "description";
	public static final String PROP_WOAID = "woaId";
	public static final Integer SIZE_BIG = 1;
	public static final Integer SIZE_SMALL = 2;
	private String title;
	private String picUrl;
	private String url;
	private String description;
	private Integer size;
	private int status;
	private Long createTime;
	private Long lastModifyTime;
	private Long woaId;//所属公众号ID
	
	@Id
	@Override
	@SequenceGenerator(name = "wxarticle_id_seq", sequenceName = "public.wxarticle_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wxarticle_id_seq")
	public Long getId() {
		return super.getId();
	}
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="picUrl")
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	@Column(name="url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	@Column(name="size")
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	@Column(name="woaid")
	public Long getWoaId() {
		return woaId;
	}
	public void setWoaId(Long woaId) {
		this.woaId = woaId;
	}
}
