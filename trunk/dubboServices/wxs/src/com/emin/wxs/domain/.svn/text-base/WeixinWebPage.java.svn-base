package com.emin.wxs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity;
@Entity
@Table(schema="public",name="wxwebpage")
public class WeixinWebPage extends BaseEntity implements UndeleteableEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4508847698412089411L;
	public static final String PROP_CATEGORYID = "categoryId";
	public static final String PROP_TITLE = "title";
	private String title;
	private String auther;
	private String tags;
	private String comments;
	private String info;
	private Boolean publish;
	private int status;
	private Long createTime;
	private Long lastModifyTime;
	private Long categoryId;
	private WeixinWebPageCategory category;
	@Id
	@Override
	@SequenceGenerator(name = "wxwebpage_id_seq", sequenceName = "public.wxwebpage_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wxwebpage_id_seq")
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
	@Column(name="auther")
	public String getAuther() {
		return auther;
	}
	public void setAuther(String auther) {
		this.auther = auther;
	}
	@Column(name="tags")
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	@Column(name="comments")
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	@Column(name="info")
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Column(name="publish")
	public Boolean getPublish() {
		return publish;
	}
	public void setPublish(Boolean publish) {
		this.publish = publish;
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
	@Column(name="lastmodifyTIME")
	public Long getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	@Column(name="cate_id")
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	@ManyToOne
	@JoinColumn(name="cate_id",insertable=false,updatable=false)
	public WeixinWebPageCategory getCategory() {
		return category;
	}
	public void setCategory(WeixinWebPageCategory category) {
		this.category = category;
	}
	
}
