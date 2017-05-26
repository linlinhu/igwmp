package com.emin.igwmp.rstm.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity;

/**
 * 饭店公有信息实体
 * @author zhaoqt
 *
 */
@Table(schema="rstm",name="restaurant_public_info")
@Entity
public class RestaurantPublicInfo extends BaseEntity implements UndeleteableEntity{
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1822174082173568787L;

	public static final String PROP_NAME = "name";
	
	public static final String PROP_ADDRESS = "address";
	
	public static final String PROP_PRIVATE_INFO_CPRT_NAME="restaurantPrivateInfo."+RestaurantPrivateInfo.CPRT_NAME;

	private String name;//饭店名称
	
	private String address;//饭店地址
	
	private Double longitude;//经度
	
	private Double latitude;//纬度
	
	private Integer usingScene ;//使用场景
	
	private Integer cuisine ;//菜系
	
	private String phone;//饭店电话
	
    private String province;//省
	
	private String city;//市
	
	private String area;//区
	
	private String perCapitaConsumption;//人均消费
	
	private String businessScope;//经营范围
	
	private String explaination;//说明
	
	private Long createTime;
	
	private Long lastModifyTime;
	
	private int status;
	
//	private String privateInfoId;//饭店私有信息id
	private RestaurantPrivateInfo restaurantPrivateInfo;//饭店私有信息

	private Set<RestaurantSvcPic> restaurantSvcPics;//饭店各种图片
	
//	private Set<RestaurantServantInfo> servants = new HashSet<RestaurantServantInfo>();//服务员

	private Set<RestaurantAdminInfo> admins = new HashSet<RestaurantAdminInfo>();//饭店管理员：店长，老板
	
	@Id
	@Override
	@SequenceGenerator(name = "rstpi_id_seq", sequenceName = "rstm.rstpi_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rstpi_id_seq")
	public Long getId() { 
		return super.getId();
	}


	@Column(name="name")
	public String getName() {
		return name;
	}

	public String getProvince() {
		return province;
	}

	@Column(name="province")
	public void setProvince(String province) {
		this.province = province;
	}


	public String getCity() {
		return city;
	}

	@Column(name="city")
	public void setCity(String city) {
		this.city = city;
	}


	public String getArea() {
		return area;
	}
	
	@Column(name="area")
	public void setArea(String area) {
		this.area = area;
	}


	public String getPerCapitaConsumption() {
		return perCapitaConsumption;
	}

	@Column(name="per_capita_consumption")
	public void setPerCapitaConsumption(String perCapitaConsumption) {
		this.perCapitaConsumption = perCapitaConsumption;
	}


	public String getBusinessScope() {
		return businessScope;
	}

	@Column(name="business_scope")
	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name="longitude")
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	@Column(name="latitude")
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
	@Column(name="using_scene")
	public Integer getUsingScene() {
		return usingScene;
	}
	public void setUsingScene(Integer usingScene) {
		this.usingScene = usingScene;
	}
	
	@Column(name="cuisine")
	public Integer getCuisine() {
		return cuisine;
	}
	
	
	public void setCuisine(Integer cuisine) {
		this.cuisine = cuisine;
	}
	
	@Column(name="phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(name="explaination")
	public String getExplaination() {
		return explaination;
	}
	public void setExplaination(String explaination) {
		this.explaination = explaination;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="private_info_id")
	public RestaurantPrivateInfo getRestaurantPrivateInfo() {
		return restaurantPrivateInfo;
	}
	
	public void setRestaurantPrivateInfo(RestaurantPrivateInfo restaurantPrivateInfo) {
		this.restaurantPrivateInfo = restaurantPrivateInfo;
	}
	
	@OneToMany(cascade={ CascadeType.ALL },fetch=FetchType.EAGER)
	@JoinColumn(name="master_id")
	@Fetch(FetchMode.SUBSELECT)
	public Set<RestaurantSvcPic> getRestaurantSvcPics() {
		return restaurantSvcPics;
	}


	public void setRestaurantSvcPics(Set<RestaurantSvcPic> restaurantSvcPics) {
		this.restaurantSvcPics = restaurantSvcPics;
	}

	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="ref_restaurant_admin",
	           joinColumns={ @JoinColumn(name="restaurant_id",referencedColumnName="id")},
	           inverseJoinColumns={@JoinColumn(name="admin_id",referencedColumnName="id")})
	public Set<RestaurantAdminInfo> getAdmins() {
		return admins;
	}	
	public void setAdmins(Set<RestaurantAdminInfo> admins) {
		this.admins = admins;
	}

//	@ManyToMany(fetch=FetchType.EAGER)
//	@JoinTable(name="ref_restaurant_servant",
//	           joinColumns={ @JoinColumn(name="restaurant_id",referencedColumnName="id")},
//	           inverseJoinColumns={@JoinColumn(name="servant_id",referencedColumnName="id")})
//	public Set<RestaurantServantInfo> getServants() {
//		return servants;
//	}

//	public void setServants(Set<RestaurantServantInfo> servants) {
//		this.servants = servants;
//	}	

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
	@Column(name="last_modify_time")
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
