package com.emin.wxs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.emin.base.domain.BaseEntity;
@Table(schema="public",name="next_openid")
@Entity
public class NextOpenId extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3556085255212845509L;
	
	public static final String PROP_WOA_ID = "woaId";
	
	private String nextOpenId;
	
	private Long woaId;
	
	@Id
	@Override
	@SequenceGenerator(name = "next_openid_id_seq", sequenceName = "public.next_openid_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "next_openid_id_seq")
	public Long getId(){
		return super.getId();
	}

	@Column(name="next_openid")
	public String getNextOpenId() {
		return nextOpenId;
	}

	public void setNextOpenId(String nextOpenId) {
		this.nextOpenId = nextOpenId;
	}
	
	@Column(name="woa_id")
	public Long getWoaId() {
		return woaId;
	}
	
	public void setWoaId(Long woaId) {
		this.woaId = woaId;
	}
	
	
}
