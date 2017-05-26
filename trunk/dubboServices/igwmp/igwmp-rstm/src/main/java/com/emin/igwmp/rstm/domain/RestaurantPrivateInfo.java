package com.emin.igwmp.rstm.domain;

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

import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity;

/**
 * 饭店私有信息实体
 * @author zhaoqt
 *
 */
@Table(schema="rstm",name="restaurant_private_info")
@Entity
public class RestaurantPrivateInfo extends BaseEntity implements UndeleteableEntity{
    
	private static final long serialVersionUID = -1469389877142145168L;
	public static final String CPRT_NAME = "corperationName";
	public static final String CONTRACT_NUM = "contractNum";
	
	private Long createTime;
	
	private Long lastModifyTime;
	
	private int status;
	
	private String corperationName;//企业名称
	
	private String legalRepresentative;//法人代表
	 

	private String businessRgstNum;//工商执照注册号
	
	private String corperationAccountName;//企业开户名称	

	private String corperationAccountBank;//企业开户银行	

	private String corperationAccount;//企业开户账号    

	private Set<RestaurantSvcPic> restaurantSvcPics;//企业工商营业执照图片	
 
	private String contractNum;//合同编号

    private Long contractStartTime;//合同开始时间
    
    private Long contractEndTime;//合同结束时间	
    
    private Integer isSignedContract;//是否签署合同

	private RestaurantPublicInfo restaurantPublicInfo;  //饭店公共信息
	
    private Set<CooperationPlanInfo> cooperationPlan;
    
    @ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="ref_private_cooperation",
	           joinColumns={ @JoinColumn(name="private_info_id",referencedColumnName="id")},
	           inverseJoinColumns={@JoinColumn(name="cooperation_plan_id",referencedColumnName="id")})
    public Set<CooperationPlanInfo> getCooperationPlan() {
		return cooperationPlan;
	}

	public void setCooperationPlan(Set<CooperationPlanInfo> cooperationPlan) {
		this.cooperationPlan = cooperationPlan;
	}

	@Id
	@Override
	@SequenceGenerator(name = "rstpri_id_seq", sequenceName = "rstm.rstpri_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rstpri_id_seq")
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
    
	@Column(name="corperation_name")
	public String getCorperationName() {
		return corperationName;
	}

	public void setCorperationName(String corperationName) {
		this.corperationName = corperationName;
	}

	@Column(name="legal_rpt")
	public String getLegalRepresentative() {
		return legalRepresentative;
	}

	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}

	@Column(name="business_rgst_num")
	public String getBusinessRgstNum() {
		return businessRgstNum;
	}

	public void setBusinessRgstNum(String businessRgstNum) {
		this.businessRgstNum = businessRgstNum;
	}

	@Column(name="cprt_account_name")
	public String getCorperationAccountName() {
		return corperationAccountName;
	}

	public void setCorperationAccountName(String corperationAccountName) {
		this.corperationAccountName = corperationAccountName;
	}

	@Column(name="cprt_account_bank")
	public String getCorperationAccountBank() {
		return corperationAccountBank;
	}

	public void setCorperationAccountBank(String corperationAccountBank) {
		this.corperationAccountBank = corperationAccountBank;
	}

	@Column(name="cprt_account")
	public String getCorperationAccount() {
		return corperationAccount;
	}

	public void setCorperationAccount(String corperationAccount) {
		this.corperationAccount = corperationAccount;
	}

	@OneToMany(cascade={ CascadeType.ALL},fetch=FetchType.EAGER)
	@JoinColumn(name = "master_id")
	public Set<RestaurantSvcPic> getRestaurantSvcPics() {
		return restaurantSvcPics;
	}

	public void setRestaurantSvcPics(Set<RestaurantSvcPic> restaurantSvcPics) {
		this.restaurantSvcPics = restaurantSvcPics;
	}

	@Column(name="contract_num")
	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

    
    @Column(name="contract_start_time")
	public Long getContractStartTime() {
		return contractStartTime;
	}

	public void setContractStartTime(Long contractStartTime) {
		this.contractStartTime = contractStartTime;
	}

    @Column(name="contract_end_time")
	public Long getContractEndTime() {
		return contractEndTime;
	}

	public void setContractEndTime(Long contractEndTime) {
		this.contractEndTime = contractEndTime;
	}
     

	public Integer getIsSignedContract() {
		return isSignedContract;
	}

	public void setIsSignedContract(Integer isSignedContract) {
		this.isSignedContract = isSignedContract;
	}

	@OneToOne(mappedBy="restaurantPrivateInfo", cascade={ CascadeType.ALL})  
	public RestaurantPublicInfo getRestaurantPublicInfo() {
		return restaurantPublicInfo;
	}

	public void setRestaurantPublicInfo(RestaurantPublicInfo restaurantPublicInfo) {
		this.restaurantPublicInfo = restaurantPublicInfo;
	}    
	 	
}
