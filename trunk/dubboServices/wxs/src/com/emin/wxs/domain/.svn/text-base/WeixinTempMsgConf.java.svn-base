package com.emin.wxs.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity;
@Entity
@Table(schema="public",name="wxtmpmsg_conf")
public class WeixinTempMsgConf extends BaseEntity implements UndeleteableEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5378444779633655899L;
	private Long woaId;
	private String name;//消息模板名称
	private String templateId;//微信模板消息id
	private String topcolor;//消息标题顶部颜色
	private String url;//消息发给用户后，用户点击消息进入的页面，可以空
	private String remark;//描述
	private int status;
	private Long createTime;
	private Long lastModifyTime;
	private List<WeixinTempMsgConfItem> items;
	@Id
	@Override
	@SequenceGenerator(name = "wxtmpmsg_conf_id_seq", sequenceName = "public.wxtmpmsg_conf_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wxtmpmsg_conf_id_seq")
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
	@Column(name="template_id")
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	@Column(name="topcolor")
	public String getTopcolor() {
		return topcolor;
	}
	public void setTopcolor(String topcolor) {
		this.topcolor = topcolor;
	}
	@Column(name="url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "conf")
	public List<WeixinTempMsgConfItem> getItems() {
		return items;
	}
	public void setItems(List<WeixinTempMsgConfItem> items) {
		this.items = items;
	}
	@Column(name="woaid")
	public Long getWoaId() {
		return woaId;
	}
	public void setWoaId(Long woaId) {
		this.woaId = woaId;
	}
	
}
