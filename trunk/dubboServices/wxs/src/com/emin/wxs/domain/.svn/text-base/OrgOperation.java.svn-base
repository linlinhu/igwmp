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
@Table(schema="public",name="org_operation")
public class OrgOperation extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3491091417698543797L;

	private Long orgId;
	
	private Long operationId;
	
	@Id
	@Override
	@SequenceGenerator(name = "org_operation_id_seq", sequenceName = "public.org_operation_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "org_operation_id_seq")
	public Long getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}

	@Column(name="org_id")
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	@Column(name="operation_id")
	public Long getOperationId() {
		return operationId;
	}

	public void setOperationId(Long operationId) {
		this.operationId = operationId;
	}
	
	
}
