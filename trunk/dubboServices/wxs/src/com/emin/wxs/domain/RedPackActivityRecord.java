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
@Table(schema="public",name="redpack_activity_record")
public class RedPackActivityRecord extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8614730242611453046L;
	
	private String code;
	private Long startTime;
	private Long endTime;
	private Integer maxCount;
	private Long woaId;
	@Id
	@Override
	@SequenceGenerator(name = "redpack_activity_record_id_seq", sequenceName = "public.redpack_activity_record_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "redpack_activity_record_id_seq")
	public Long getId() {
		return super.getId();
	}

	@Column(name="code")
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}

	@Column(name="starttime")
	public Long getStartTime() {
		return startTime;
	}


	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	@Column(name="endtime")
	public Long getEndTime() {
		return endTime;
	}


	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	@Column(name="max_count")
	public Integer getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(Integer maxCount) {
		this.maxCount = maxCount;
	}
	@Column(name="woaid")
	public Long getWoaId() {
		return woaId;
	}

	public void setWoaId(Long woaId) {
		this.woaId = woaId;
	}
	
}
