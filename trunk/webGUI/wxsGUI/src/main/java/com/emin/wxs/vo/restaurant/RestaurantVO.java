package com.emin.wxs.vo.restaurant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.emin.igwmp.rstm.domain.RestaurantAdminInfo;
import com.emin.igwmp.rstm.domain.RestaurantPublicInfo;


public class RestaurantVO {

	private Long id;//id
	
	private String name;//饭店名称
	
	private String address;//饭店地址
	
	private Double longitude;//经度
	
	private Double latitude;//纬度
	
	private Integer runType ;//使用场景
	
	private String province;//
	
	private String city;//
	
	private String area;//
	
	private Integer cuisine ;//菜系
	
	private String telephone;//饭店电话
	
	private String perCapitaConsumption;//人均消费
	
	private String businessScope;//经营范围
	
	private String memo;//说明
	
	private List<OperatorVO> operatorList;//运营者信息

	private ContractVO contractVo;//合同信息

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Integer getRunType() {
		return runType;
	}

	public void setRunType(Integer runType) {
		this.runType = runType;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getCuisine() {
		return cuisine;
	}

	public void setCuisine(Integer cuisine) {
		this.cuisine = cuisine;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPerCapitaConsumption() {
		return perCapitaConsumption;
	}

	public void setPerCapitaConsumption(String perCapitaConsumption) {
		this.perCapitaConsumption = perCapitaConsumption;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<OperatorVO> getOperatorList() {
		return operatorList;
	}

	public void setOperatorList(List<OperatorVO> operatorList) {
		this.operatorList = operatorList;
	}

	public ContractVO getContractVo() {
		return contractVo;
	}

	public void setContractVo(ContractVO contractVo) {
		this.contractVo = contractVo;
	}

	public static  RestaurantPublicInfo convertVoToPo(RestaurantPublicInfo restaurantPublicInfo,RestaurantVO restaurantVo,int cascadeDepth){
     		RestaurantPublicInfo po = null;
     		if(restaurantVo == null) return null;
		if(restaurantPublicInfo != null){
			po = restaurantPublicInfo;			
		}
		else{
			po = new RestaurantPublicInfo();
			po.setCreateTime(System.currentTimeMillis());
		}	
		
		po.setId(restaurantVo.getId());
		
		po.setLastModifyTime(System.currentTimeMillis());

		po.setStatus(1);
		
		po.setName(restaurantVo.getName()); 

		po.setAddress(restaurantVo.getAddress());

		po.setLongitude(restaurantVo.getLongitude());		

		po.setLatitude(restaurantVo.getLatitude());

		po.setUsingScene(restaurantVo.getRunType());

		po.setProvince(restaurantVo.getProvince());

		po.setCity(restaurantVo.getCity());
		
		po.setArea(restaurantVo.getArea());

		po.setCuisine(restaurantVo.getCuisine());

		po.setPhone(restaurantVo.getTelephone());

		po.setBusinessScope(restaurantVo.getBusinessScope()) ;

		po.setExplaination(restaurantVo.getMemo()) ;
		
		po.setPerCapitaConsumption(restaurantVo.getPerCapitaConsumption());
		
		 if(cascadeDepth>0){
			 cascadeDepth--;
			 List<OperatorVO> opVos = restaurantVo.getOperatorList();
        	 Set<RestaurantAdminInfo> rstAdminPos = new HashSet<RestaurantAdminInfo>();
             if(opVos!=null){
            	 for(OperatorVO opVo: opVos){ 
            		 RestaurantAdminInfo rstAdminPo = null;
            		 if(po.getAdmins()!=null){
            			Set<RestaurantAdminInfo> tempadmins =  po.getAdmins();
            			for(RestaurantAdminInfo restPublicInfo:tempadmins){
            				if(restPublicInfo.getId() == opVo.getId()){
            					rstAdminPo = OperatorVO.convertVoToPo(restPublicInfo,opVo,cascadeDepth);    
            				}
            			}
            		 }
            		 else{
            			 rstAdminPo = OperatorVO.convertVoToPo(null,opVo,cascadeDepth);    
            		 }
            		if(rstAdminPo == null){
            			rstAdminPo = OperatorVO.convertVoToPo(null,opVo,cascadeDepth);    	
            		}
               		rstAdminPos.add(rstAdminPo);
               }
             }             
	          po.setAdmins(rstAdminPos);
	          po.setRestaurantPrivateInfo(ContractVO.convertVoToPo(po.getRestaurantPrivateInfo(), restaurantVo.getContractVo(), cascadeDepth));
         }		
	     return po;
	}
	
	public static RestaurantVO convertPoToVo(RestaurantPublicInfo restaurantPublicInfo,int cascadeDepth){
		RestaurantVO restaurantVo = new RestaurantVO();
		if(restaurantPublicInfo ==null){
			return null;
		}		
		restaurantVo.setId(restaurantPublicInfo.getId());

		restaurantVo.setName(restaurantPublicInfo.getName()); 

		restaurantVo.setAddress(restaurantPublicInfo.getAddress());

		restaurantVo.setLongitude(restaurantPublicInfo.getLongitude());		

		restaurantVo.setLatitude(restaurantPublicInfo.getLatitude());

		restaurantVo.setRunType(restaurantPublicInfo.getUsingScene());

		restaurantVo.setProvince(restaurantPublicInfo.getProvince());

		restaurantVo.setCity(restaurantPublicInfo.getCity());
		
        restaurantVo.setArea(restaurantPublicInfo.getArea());

        restaurantVo.setCuisine(restaurantPublicInfo.getCuisine());

        restaurantVo.setTelephone(restaurantPublicInfo.getPhone());
        
        restaurantVo.setPerCapitaConsumption(restaurantPublicInfo.getPerCapitaConsumption());
        
        restaurantVo.setBusinessScope(restaurantPublicInfo.getBusinessScope()) ;

        restaurantVo.setMemo(restaurantPublicInfo.getExplaination()) ;	
        
        if(cascadeDepth>0){
        	   cascadeDepth--;
        	   Set<RestaurantAdminInfo> restaurantAdminInfos = restaurantPublicInfo.getAdmins();
               List<OperatorVO> operatorList = new ArrayList<OperatorVO>();
                for(RestaurantAdminInfo restaurantAdminInfo: restaurantAdminInfos){
                	
                		OperatorVO operatorVo = OperatorVO.convertPoToVo(restaurantAdminInfo,cascadeDepth);        		
                		operatorList.add(operatorVo);

                }
                restaurantVo.setOperatorList(operatorList);
                restaurantVo.setContractVo(ContractVO.convertPoToVo( restaurantPublicInfo.getRestaurantPrivateInfo(),cascadeDepth)) ;
        }   

		return restaurantVo;
	}

}
