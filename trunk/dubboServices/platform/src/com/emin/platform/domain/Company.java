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
import com.emin.base.util.EncryptUtils;

@Entity
@Table(schema="platform",name="company")
public class Company extends BaseEntity implements UndeleteableEntity{
	private static final long serialVersionUID = 6170352433244049733L;
	public static final Long PLAT_ADMIN_COMPANY_ID= 0L;
	
	public static final String PROP_NAME= "name";
	public static final String PROP_CODE= "code";
	public static final String PROP_TYPE= "type";
	
	private String name;
	private String code;
	private Image image;
	private Long imageId;//log
	private String description;
	private int status;
	private Long createTime;
	private Long lastModifyTime;
	private Long effectiveTime;//有效时间
	private Integer type;//类型
	private Integer personLimit;//人员数量限制 默认0
	private Integer totalPerson;//总数 默认0
	private Integer loginMode;//员工登录方式 默认1
	
	
	@Id
	@Override
	@SequenceGenerator(name = "company_id_seq", sequenceName = "platform.company_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_id_seq")
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
	@Column(name="code")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	@Column(name="effectivetime")
	public Long getEffectiveTime() {
		return effectiveTime;
	}
	public void setEffectiveTime(Long effectiveTime) {
		this.effectiveTime = effectiveTime;
	}
	@Column(name="type")
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Column(name="personlimit")
	public Integer getPersonLimit() {
		return personLimit;
	}
	public void setPersonLimit(Integer personLimit) {
		this.personLimit = personLimit;
	}
	@Column(name="totalperson")
	public Integer getTotalPerson() {
		return totalPerson;
	}
	public void setTotalPerson(Integer totalPerson) {
		this.totalPerson = totalPerson;
	}
	
	@Column(name="loginmode")
	public Integer getLoginMode() {
		return loginMode;
	}
	public void setLoginMode(Integer loginMode) {
		this.loginMode = loginMode;
	}
	
	public static void main(String[] args) {
		String password = EncryptUtils.encodeMD5("888888");
		System.out.println(password);
	}
	
}
