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
import com.emin.base.domain.UndeleteableEntity;
@Entity
@Table(schema="platform",name="position")
public class Position extends BaseEntity implements UndeleteableEntity{
	private static final long serialVersionUID = 6170352433244049733L;
	public static final String PROP_NAME= "name";
	public static final String PROP_COMPANY_ID= "company."+Company.PROP_ID;
	public static final String PROP_COMPANY_STATUS= "company."+Company.PROP_STATUS;
	public static final String PROP_TYPE = "type";	
	public static final Integer POSTION_TYPE= 1;//职位
	public static final Integer GROUP_TYPE= 2;//群组

	private String name;
	private Integer type=1;
	private Company company;
	private Long companyId;
	private int status;
	private Long createTime;
	private Long lastModifyTime;
	
	
	@Id
	@Override
	@SequenceGenerator(name = "position_id_seq", sequenceName = "platform.position_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "position_id_seq")
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
	@Column(name="type")
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@ManyToOne
	@JoinColumn(name="company_id",insertable=false,updatable=false)
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	@Column(name="company_id")
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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
	
}
