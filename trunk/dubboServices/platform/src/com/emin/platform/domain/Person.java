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
@Table(schema="platform",name="person")
public class Person extends BaseEntity implements UndeleteableEntity{
	private static final long serialVersionUID = 6170352433244049733L;
	public static final String PROP_NAME= "name";
	public static final String PROP_MOBILE_PHONE= "mobilephone";
	public static final String PROP_TYPE= "type";
	public static final String PROP_EMPNO= "empno";
	public static final String PROP_NZM= "nzm";
	public static final String PROP_PASSWORD= "password";
	public static final String PROP_COMPANY_ID = "companyId";
	
	public final static int ACCESS_PERSON = 0;// 用户创建的帐号
	public final static int ACCESS_DEALERC = 1;// 经销商联系人
	public final static int ACCESS_ADMIN = 2;// 系统厂商级管理员
	public final static int ACCESS_WEIXIN = 3;// 微信账号
	public final static int ACCESS_ROOT = 4;// 系统平台级管理员
	
	public final static int ACTIVESTATUS_DISACTIVE = -1;//未激活
	public final static int ACTIVESTATUS_ACTIVE = 1;//激活
	
	public final static Long RES_TYPE_CREATER_ID = 1L;//数据库脚本里面的res_type里面的创建人id
	
	private String name;
	private Integer type;//类型
	private String mobilephone;
	private Integer gender;
	private Image image;
	private Long imageId;//头像
	private Long companyId;	
	private String password;
	private String empno;
	private String email;
	private String imsi;
	private String imei;
	private Integer activestatus;//是否激活
	private String description;//描述
	private String nzm;
	private int status;
	private Long createTime;
	private Long lastModifyTime;
	
	@Id
	@Override
	@SequenceGenerator(name = "person_id_seq", sequenceName = "platform.person_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id_seq")
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
	@Column(name="mobilephone")
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	@Column(name="gender")
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	@Column(name="empno")
	public String getEmpno() {
		return empno;
	}
	public void setEmpno(String empno) {
		this.empno = empno;
	}
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="activestatus")
	public Integer getActivestatus() {
		return activestatus;
	}
	public void setActivestatus(Integer activestatus) {
		this.activestatus = activestatus;
	}
	@Column(name="nzm")
	public String getNzm() {
		return nzm;
	}
	public void setNzm(String nzm) {
		this.nzm = nzm;
	}
	@ManyToOne
	@JoinColumn(name="image_id",insertable=false,updatable=false)
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	@Column(name="image_id")
	public Long getImageId() {
		return imageId;
	}
	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	@Column(name="imsi")
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	@Column(name="imei")
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	@Column(name="company_id")
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
}
