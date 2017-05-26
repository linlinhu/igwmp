package com.emin.igwmp.prds.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.emin.base.domain.BaseEntity; 
import com.emin.base.domain.UndeleteableEntity;
@Table(schema="prds",name="category")
@Entity
public class Category extends BaseEntity implements UndeleteableEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6618706197323695160L;
	
	public static final String PROP_NAME = "name";
	
	public static final String PROP_PARENT_ID = "parentId";

	private String name;
	
	private Long parentId;//父级分类ID 第一级的分类父级ID为0
	
	private Long createTime;
	
	private Long lastModifyTime;
	
	private int status;
	


	@Id
	@Override
	@SequenceGenerator(name = "category_id_seq", sequenceName = "prds.category_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id_seq")
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
	@Column(name="parent_id")
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Override
	@Column(name="create_time")
	public Long getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Override
	@Column(name="lastmodify_time")
	public Long getLastModifyTime() {
		return lastModifyTime;
	}

	@Override
	public void setLastModifyTime(Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	@Override
	@Column(name="status")
	public int getStatus() {		
		return status;
	}

	@Override
	public void setStatus(int status) {
		this.status = status;
	}
	 
 

	
}
