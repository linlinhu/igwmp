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
@Table(schema="public",name="redpack_record")
public class RedPackRecord extends BaseEntity implements UndeleteableEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7779999345449635729L;
	private Long woaId;
	private Long userId;
	private Fans fans;
	private Long createTime;
	private Long lastModifyTime;
	private int status;
	private String redPackStatus;
	private Integer totalNum;
	private Double totalAmount;
	private String wishing;
	private Integer type;
	private String actName;
	private String mchBillNO;
	private String actCode;
	
	@Override
	@Column(name="createtime")
	public Long getCreateTime() {
		return this.createTime;
	}

	@Override
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Override
	@Column(name="lastmodifytime")
	public Long getLastModifyTime() {
		return this.lastModifyTime;
	}

	@Override
	public void setLastModifyTime(Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	@Override
	@Column(name="status")
	public int getStatus() {
		return this.status;
	}

	@Override
	public void setStatus(int status) {
		this.status = status;
	}

	@Id
	@Override
	@SequenceGenerator(name = "redpack_record_seq", sequenceName = "public.redpack_record_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "redpack_record_seq")
	public Long getId() {
		return super.getId();
	}
	
	@Column(name="userid")
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@ManyToOne
	@JoinColumn(name="fans_id",insertable=false,updatable=false)
	public Fans getFans() {
		return fans;
	}
	
	public void setFans(Fans fans) {
		this.fans = fans;
	}
	@Column(name="redpack_status")
	public String getRedPackStatus() {
		return redPackStatus;
	}
	
	public void setRedPackStatus(String redPackStatus) {
		this.redPackStatus = redPackStatus;
	}
	@Column(name="total_num")
	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	@Column(name="total_amount")
	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	@Column(name="wishing")
	public String getWishing() {
		return wishing;
	}

	public void setWishing(String wishing) {
		this.wishing = wishing;
	}
	@Column(name="act_name")
	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}
	@Column(name="mch_billno")
	public String getMchBillNO() {
		return mchBillNO;
	}

	public void setMchBillNO(String mchBillNO) {
		this.mchBillNO = mchBillNO;
	}
	@Column(name="act_code")
	public String getActCode() {
		return actCode;
	}

	public void setActCode(String actCode) {
		this.actCode = actCode;
	}
	@Column(name="type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	@Column(name="woaid")
	public Long getWoaId() {
		return woaId;
	}

	public void setWoaId(Long woaId) {
		this.woaId = woaId;
	}

}
