/**
 * 
 */
package com.emin.wxs.domain;

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
 * @author jim
 *
 */
@Entity
@Table(schema = "public", name = "fans")
public class Fans extends BaseEntity implements UndeleteableEntity{

	private static final long serialVersionUID = 5611612261275004404L;
	public static final String PROP_NICKNAME = "nickname";
	public static final String PROP_UNIONID = "unionId";
	public static final String PROP_SUBSCRIBETIME = "subscribeTime";

	private Boolean subscribe;
	private String nickname;
	private Integer sex;
	private String country;
	private String province;
	private String city;
	private String language;
	private String headimgurl;
	private Long subscribeTime;
	private String unionId;
	private String remark;
	private int status;
	private Long createTime;
	private Long lastModifyTime;
	
	
	
	

	@Id
	@Override
	@SequenceGenerator(name = "fans_id_seq", sequenceName = "public.fans_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fans_id_seq")
	public Long getId() {
		return super.getId();
	}

	@Column(name = "subscribe")
	public Boolean getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(Boolean subscribe) {
		this.subscribe = subscribe;
	}


	@Column(name = "nickname")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "sex")
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Column(name = "country")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "province")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "language")
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Column(name = "headimgurl")
	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	@Column(name = "subscribe_time")
	public Long getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(Long subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	@Column(name = "unionid")
	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "status")
	public int getStatus() {
		return status;	
		
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name = "createtime")
	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Column(name = "lastmodifytime")
	public Long getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	
}
