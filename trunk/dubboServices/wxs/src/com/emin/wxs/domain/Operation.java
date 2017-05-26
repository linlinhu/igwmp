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
import javax.persistence.Transient;

import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity;
@Entity
@Table(schema="public",name="operation")
public class Operation extends BaseEntity implements UndeleteableEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8403939036349102587L;
	public static final String PROP_MENU_ID = "menuId";
	private Long menuId;
	private String code;
	private String name;
	private Integer type;
	private String remark;
	private String url;
	private int status;
	private Long createTime;
	private Long lastModifyTime;
	private Menu menu;
	@Id
	@Override
	@SequenceGenerator(name = "operation_id_seq", sequenceName = "public.operation_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operation_id_seq")
	public Long getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}
	@Column(name="menu_id")
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	@Column(name="code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	@Column(name="remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	@JoinColumn(name="menu_id",insertable=false,updatable=false)
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	@Transient
	public String getMenuName() {
		return this.menu.getName();
	}
	
}
