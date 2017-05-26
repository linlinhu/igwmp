package com.emin.wxs.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity;
@Entity
@Table(schema="public",name="wxtmpmsg")
public class WeixinTempMsg extends BaseEntity implements UndeleteableEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9149448767414068020L;
	public static final String PROP_CONFNAME = "tempMsgConf.name";
	public static final String PROP_PERSONNAME = "person.name";
	
	private Long confid;//消息配置
	private String templateid;//微信模板消息id
	private String url;//消息发给用户后，用户点击消息进入的页面，可以空
	private String data;//发送消息的内容
	private int status;
	private Long createTime;
	private Long lastModifyTime;
	
	private WeixinTempMsgConf tempMsgConf;//消息配置
	private Long personId;//发送人
	private Set<WeixinTempMsgSendDts> tempMsgSendDts;
	
	@Id
	@Override
	@SequenceGenerator(name = "wxtmpmsg_id_seq", sequenceName = "public.wxtmpmsg_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wxtmpmsg_id_seq")
	public Long getId() {
		return super.getId();
	}
	@Column(name="confid")
	public Long getConfid() {
		return confid;
	}
	public void setConfid(Long confid) {
		this.confid = confid;
	}
	@Column(name="template_id")
	public String getTemplateid() {
		return templateid;
	}
	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}
	@Column(name="url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name="data")
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
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
	@ManyToOne
	@JoinColumn(name = "confid",insertable=false,updatable=false)
	public WeixinTempMsgConf getWeixinTempMsgConf() {
		return tempMsgConf;
	}
	public void setWeixinTempMsgConf(WeixinTempMsgConf tempMsgConf) {
		this.tempMsgConf = tempMsgConf;
	}
	
	@Column(name = "personid")
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	@OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.ALL},mappedBy="tempMsg")
	@Fetch(FetchMode.SELECT)
	public Set<WeixinTempMsgSendDts> getWeixinTempMsgSendDts() {
		return tempMsgSendDts;
	}
	public void setWeixinTempMsgSendDts(Set<WeixinTempMsgSendDts> tempMsgSendDts) {
		this.tempMsgSendDts = tempMsgSendDts;
	}
}
