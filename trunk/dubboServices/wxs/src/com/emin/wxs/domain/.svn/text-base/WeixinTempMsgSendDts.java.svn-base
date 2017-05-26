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
@Table(schema="public",name="wxtmpmsg_senddts")
public class WeixinTempMsgSendDts extends BaseEntity implements UndeleteableEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2055724352495377769L;
	
	/** 发送成功 */
	public static final Integer STATE_SEND_OK = 0;
	/** 发送失败 */
	public static final Integer STATE_SEND_ERR = 1;
	/** 用户已接收 */
	public static final Integer STATE_FINISH_SUCCESS = 2;
	/** 用户拒绝接收 */
	public static final Integer STATE_FINISH_BLOCK = 3;
	/** 其它原因未接收 */
	public static final Integer STATE_FINISH_FAILED = 4;
	
	private Long tempmsgid;
	private Long msgid;//发送消息后微信返回的msgid，当用户回调更新用户接收状态时，根据此id判断
	private Long userid;
	private Integer state;//发送状态
	private String statemsg;//发送状态描述
	
	private int status;
	private Long createTime;
	private Long lastModifyTime;
	
	private Fans wxuser;//接收人
	private WeixinTempMsg tempMsg;
	
	@Id
	@Override
	@SequenceGenerator(name = "wxtmpmsg_senddts_id_seq", sequenceName = "public.wxtmpmsg_senddts_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wxtmpmsg_senddts_id_seq")
	public Long getId() {
		return super.getId();
	}
	@Column(name="tempmsgid")
	public Long getTempmsgid() {
		return tempmsgid;
	}
	public void setTempmsgid(Long tempmsgid) {
		this.tempmsgid = tempmsgid;
	}
	@Column(name="msgid")
	public Long getMsgid() {
		return msgid;
	}
	public void setMsgid(Long msgid) {
		this.msgid = msgid;
	}
	@Column(name="wxuserid")
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	@Column(name="state")
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	@Column(name="statemsg")
	public String getStatemsg() {
		return statemsg;
	}
	public void setStatemsg(String statemsg) {
		this.statemsg = statemsg;
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
	@JoinColumn(name = "wxuserid",insertable=false,updatable=false)
	public Fans getWxuser() {
		return wxuser;
	}
	public void setWxuser(Fans wxuser) {
		this.wxuser = wxuser;
	}
	
	
	@ManyToOne
	@JoinColumn(name = "tempmsgid",insertable=false,updatable=false)
	public WeixinTempMsg getTempMsg() {
		return tempMsg;
	}

	public void setTempMsg(WeixinTempMsg tempMsg) {
		this.tempMsg = tempMsg;
	}
}
