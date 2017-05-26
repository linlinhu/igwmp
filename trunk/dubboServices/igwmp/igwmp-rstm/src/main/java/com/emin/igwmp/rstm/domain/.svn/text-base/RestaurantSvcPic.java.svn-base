package com.emin.igwmp.rstm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity;

/**
 * 饭店服务图片资源实体
 * @author lenovo
 *
 */
@Table(schema="rstm",name="restaurant_svc_pic")
@Entity
public class RestaurantSvcPic extends BaseEntity implements UndeleteableEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3244118889241290075L;

	private Long createTime;
	
	private Long lastModifyTime;
	
	private int status;
	
	private String name;//图片名称
	
	private String path;//存放路径
	
	private Integer actionScope;//作用域  
	
	@Id
	@Override
	@SequenceGenerator(name = "rstsp_id_seq", sequenceName = "rstm.rstsp_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rstsp_id_seq")
	public Long getId() { 
		return super.getId();
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
	
	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="path")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name="action_scope")
	public Integer getActionScope() {
		return actionScope;
	}

	public void setActionScope(Integer actionScope) {
		this.actionScope = actionScope;
	}
}
