package com.emin.wxs.vo.restaurant;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.emin.igwmp.rstm.domain.CooperationPlanInfo;
import com.emin.igwmp.rstm.domain.RestaurantPrivateInfo;
import com.emin.igwmp.rstm.domain.RestaurantPublicInfo;
import com.emin.igwmp.rstm.domain.RestaurantSvcPic;

public class ContractVO {
	private Long id;	
	/**
     * 状态
     * */
    private String status;	
    /**
     * 开始日期
     * */
    private String beginDate;	
    /**
     * 结束日期
     * */
    private String endDate;	
    /**
     * 公司名称
     * */
    private String companyName;	
    /**
     * 法人代表
     * */
    private String legalPerson;	
    /**
     * 工商执照注册号
     * */
    private String regLicenceNo;	
    /**
     * 工商营业执照
     * */
    private String regLicenceFile;	
    /**
     * 企业开户名称
     * */
    private String accountName;	
    /**
     * 企业开户银行名称
     * */
    private String accountBank;	
    /**
     * 企业开户银行账号
     * */
    private String accountBankCardNo;	
    /**
     * 合作方案信息
     * */
    private CooperationVO cooperationVO;	
    /**
     * 关联餐厅信息
     * */
    private RestaurantVO restaurantVO;
    
    /**
     * 合同编号
     */
	private String contractNum;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getRegLicenceNo() {
		return regLicenceNo;
	}
	public void setRegLicenceNo(String regLicenceNo) {
		this.regLicenceNo = regLicenceNo;
	}
	public String getRegLicenceFile() {
		return regLicenceFile;
	}
	public void setRegLicenceFile(String regLicenceFile) {
		this.regLicenceFile = regLicenceFile;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountBank() {
		return accountBank;
	}
	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}
	public String getAccountBankCardNo() {
		return accountBankCardNo;
	}
	public void setAccountBankCardNo(String accountBankCardNo) {
		this.accountBankCardNo = accountBankCardNo;
	}
	public CooperationVO getCooperationVO() {
		return cooperationVO;
	}
	public void setCooperationVO(CooperationVO cooperationVO) {
		this.cooperationVO = cooperationVO;
	}
	public RestaurantVO getRestaurantVO() {
		return restaurantVO;
	}
	public void setRestaurantVO(RestaurantVO restaurantVO) {
		this.restaurantVO = restaurantVO;
	}	
	
	public String getContractNum() {
		return contractNum;
	}
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	
	/**
	 * 
	 * @param restaurantPrivateInfo
	 * @param cascadeDepth   防止进入死循环
	 * @return
	 */
	public static ContractVO convertPoToVo(RestaurantPrivateInfo restaurantPrivateInfo,int cascadeDepth){
		ContractVO  contractVo = new ContractVO();
		if(restaurantPrivateInfo==null){
			return null;
		}
		contractVo.setId(restaurantPrivateInfo.getId());		
		contractVo.setCompanyName(restaurantPrivateInfo.getCorperationName());
		contractVo.setLegalPerson(restaurantPrivateInfo.getLegalRepresentative());
		contractVo.setRegLicenceNo(restaurantPrivateInfo.getBusinessRgstNum());
		contractVo.setAccountName(restaurantPrivateInfo.getCorperationAccountName());
		contractVo.setAccountBank(restaurantPrivateInfo.getCorperationAccountBank());
		contractVo.setAccountBankCardNo(restaurantPrivateInfo.getCorperationAccount());
		contractVo.setContractNum(restaurantPrivateInfo.getContractNum());
		contractVo.setStatus(String.valueOf(restaurantPrivateInfo.getIsSignedContract()));
		if(restaurantPrivateInfo.getContractStartTime() !=null){
			contractVo.setBeginDate(restaurantPrivateInfo.getContractStartTime().toString());
		}
		if(restaurantPrivateInfo.getContractEndTime() !=null){
			contractVo.setEndDate(restaurantPrivateInfo.getContractEndTime().toString());
		}
		//合作方案
		if(restaurantPrivateInfo.getCooperationPlan()!=null&&restaurantPrivateInfo.getCooperationPlan().size()>0){
			Set<CooperationPlanInfo> coperationInfos = restaurantPrivateInfo.getCooperationPlan();	
				contractVo.setCooperationVO(CooperationVO.convertPoToVo(coperationInfos.iterator().next(),cascadeDepth));			
		}	
		
		if(cascadeDepth>0){
			cascadeDepth--;
			//营业执照
			if(restaurantPrivateInfo.getRestaurantSvcPics()!=null&&restaurantPrivateInfo.getRestaurantSvcPics().size()>0){
				String picPath  = restaurantPrivateInfo.getRestaurantSvcPics().iterator().next().getPath();
				contractVo.setRegLicenceFile(picPath);		
			}
			//基本信息 
			if(restaurantPrivateInfo.getRestaurantPublicInfo()!=null){
				RestaurantPublicInfo rstInfo = restaurantPrivateInfo.getRestaurantPublicInfo();
				contractVo.setRestaurantVO(RestaurantVO.convertPoToVo(rstInfo,cascadeDepth));
			}
		}
	
		return contractVo;
	}
	
	/**
	 * 
	 * @param restaurantPrivateInfo
	 * @param contractVo
	 * @param cascadeDepth 级联深度，保证不丢数据的同时又不出现死循环
	 * @return
	 */
	public static RestaurantPrivateInfo convertVoToPo(RestaurantPrivateInfo restaurantPrivateInfo,ContractVO contractVo,int cascadeDepth){
		
		RestaurantPrivateInfo po = null;
		if(contractVo == null) return null;
		if(restaurantPrivateInfo != null){
			po = restaurantPrivateInfo;
		}
		else{
			po = new RestaurantPrivateInfo();
			po.setCreateTime(System.currentTimeMillis());
		}		
		
	    po.setId(contractVo.getId());		
		po.setLastModifyTime(System.currentTimeMillis());
		po.setStatus(1);
		po.setCorperationName(contractVo.getCompanyName());
		po.setLegalRepresentative(contractVo.getLegalPerson());
		po.setBusinessRgstNum(contractVo.getRegLicenceNo());
		po.setCorperationAccountName(contractVo.getAccountName());
		po.setCorperationAccountBank(contractVo.getAccountBank());
		po.setCorperationAccount(contractVo.getAccountBankCardNo());
		po.setContractNum(contractVo.getContractNum());
		if(StringUtils.isNotBlank(contractVo.getStatus())){
			po.setIsSignedContract(Integer.valueOf(contractVo.getStatus()));
		}	
		if(contractVo.getBeginDate()!=null){
			po.setContractStartTime(Long.parseLong(contractVo.getBeginDate()));
		}
		if(contractVo.getBeginDate()!=null){
			po.setContractEndTime(Long.parseLong(contractVo.getEndDate()));
		}
		
		if(cascadeDepth>0){
			cascadeDepth--;
			//营业执照
			if(StringUtils.isNotBlank(contractVo.getRegLicenceFile())){
				String path = contractVo.getRegLicenceFile();
				RestaurantSvcPic restSvcPic = new RestaurantSvcPic();
				restSvcPic.setPath(path);
				Set<RestaurantSvcPic> pic = new HashSet<RestaurantSvcPic>();
				pic.add(restSvcPic);
				po.setRestaurantSvcPics(pic);
			}

			//合作方案
			if(contractVo.getCooperationVO()!=null){				
				CooperationVO cooperationVo = contractVo.getCooperationVO();
				Set<CooperationPlanInfo> plans= new HashSet<CooperationPlanInfo>();
				plans.add(CooperationVO.convertVoToPo(null,cooperationVo,cascadeDepth));
				po.setCooperationPlan(plans);
			}
			
			//基本信息
			if(contractVo.getRestaurantVO()!=null){
				RestaurantVO rstVo = contractVo.getRestaurantVO();
				po.setRestaurantPublicInfo(RestaurantVO.convertVoToPo(po.getRestaurantPublicInfo(),rstVo,cascadeDepth));
			}
		}
		
		return po;
	} 
    
}
