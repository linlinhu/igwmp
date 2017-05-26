package com.emin.platform.domain;

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
@Entity
@Table(schema="platform",name="orgmember")
public class OrgMember extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2601876208749279078L;
	
	public static final String PROP_ORGID = "orgId";
	
	public static final String PROP_PERSONNAME = "person."+Person.PROP_NAME;
	
	public static final String PROP_PERSONNZM = "person."+Person.PROP_NZM;
	
	public static final String PROP_PERSONID = "person."+Person.PROP_ID;
	
	public static final String PROP_PERSOMMOBILE = "person."+Person.PROP_MOBILE_PHONE;
	
	public static final String PROP_STATUS = "status";
	
	private Long orgId;
	
	private Person person;
	
	private Long personId;
	
	private Integer status;
	
	@Id
	@Override
	@SequenceGenerator(name = "orgmember_id_seq", sequenceName = "platform.orgmember_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orgmember_id_seq")
	public Long getId() {
		return super.getId();
	}

	@Column(name="org_id")
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	
	@ManyToOne
	@JoinColumn(name="person_id",insertable=false,updatable=false)
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	@Column(name="person_id")
	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	@Column(name="status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
}
