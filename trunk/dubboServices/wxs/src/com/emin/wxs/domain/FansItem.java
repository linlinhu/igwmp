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
@Table(schema = "public", name = "fans_item")
public class FansItem extends BaseEntity implements UndeleteableEntity{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4180268635670102785L;
	public static final String PROP_NICKNAME = "nickname";
	public static final String PROP_OPENID = "openid";
	public static final String PROP_SUBSCRIBETIME = "subscribeTime";
	public static final String PROP_FANSID = "fansId";
	public static final String PROP_WOAID = "woaId";
	private Boolean subscribe;
	private String openid;
	private Long subscribeTime;
	private int status;
	private Long createTime;
	private Long lastModifyTime;
	private Long fansId;
	private Long woaId;//所属公众号ID
	
	
	

	@Id
	@Override
	@SequenceGenerator(name = "fans_item_id_seq", sequenceName = "public.fans_item_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fans_item_id_seq")
	public Long getId() {
		return super.getId();
	}

	@Column(name = "subscribe")
	public Boolean getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(Boolean subscribe) {
		this.subscribe = subscribe;
	}

	@Column(name = "openid")
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	
	@Column(name = "subscribe_time")
	public Long getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(Long subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	@Column(name="fans_id")
	public Long getFansId() {
		return fansId;
	}

	public void setFansId(Long fansId) {
		this.fansId = fansId;
	}

	@Column(name = "status")
	public int getStatus() {
		return status;	
		
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name = "createtime")
	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Column(name = "lastmodifytime")
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
