package com.emin.wxs.vo.restaurant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.emin.igwmp.rstm.domain.RestaurantAdminInfo;
import com.emin.igwmp.rstm.domain.RestaurantPublicInfo;


public class OperatorVO{
	
	private Long id;//id
	private String name;//姓名
	private int dept ;//角色
	private String telephone;//手机号码
	private String wechatId;
	private int status;
	private String memo;
	List<RestaurantVO> restaurantVos;
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
	public int getDept() {
		return dept;
	}
	public void setDept(int dept) {
		this.dept = dept;
	}
	public String getWechatId() {
		return wechatId;
	}
	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public List<RestaurantVO> getRestaurantVos() {
		return restaurantVos;
	}
	public void setRestaurantVos(List<RestaurantVO> restaurantVos) {
		this.restaurantVos = restaurantVos;
	}	
	public static OperatorVO convertPoToVo(RestaurantAdminInfo restaurantAdminInfo,int cascadeDepth){
		
		if(restaurantAdminInfo == null){
			return null;
		}
		OperatorVO operatorVo= new OperatorVO();
		List<RestaurantVO> restaurantVos = new ArrayList<RestaurantVO>();
		operatorVo.setId(restaurantAdminInfo.getId());
		operatorVo.setDept(restaurantAdminInfo.getRoleType());
		operatorVo.setName(restaurantAdminInfo.getName());
		operatorVo.setWechatId(restaurantAdminInfo.getWechatId());
		operatorVo.setStatus(restaurantAdminInfo.getNowStatus());
		operatorVo.setMemo(restaurantAdminInfo.getMemo());
		operatorVo.setTelephone(restaurantAdminInfo.getCellphone());
		//饭店信息
		if(cascadeDepth>0){		
			cascadeDepth--;
			if(restaurantAdminInfo.getRestaurants()!=null&&restaurantAdminInfo.getRestaurants().size()>0){
				   for(RestaurantPublicInfo restInfo: restaurantAdminInfo.getRestaurants()){                		
	                		restaurantVos.add(RestaurantVO.convertPoToVo(restInfo,cascadeDepth)); 
	               }
	        }
			operatorVo.setRestaurantVos(restaurantVos);
		}					
		return operatorVo;
}
	
	public static RestaurantAdminInfo convertVoToPo(RestaurantAdminInfo restaurantAdminInfo,OperatorVO operatorVo,int cascadeDepth){
		
		RestaurantAdminInfo po = null;
		if(operatorVo == null) return null;
		if(restaurantAdminInfo != null){
			po = restaurantAdminInfo;
		}
		else{
			po = new RestaurantAdminInfo();
			po.setCreateTime(System.currentTimeMillis());
		}		
		
	    po.setId(operatorVo.getId());		
		po.setLastModifyTime(System.currentTimeMillis());
		po.setStatus(1);
		po.setMemo(operatorVo.getMemo());
		po.setRoleType(operatorVo.getDept());
		po.setName(operatorVo.getName());
		po.setCellphone(operatorVo.getTelephone());
		po.setWechatId(operatorVo.getWechatId());

		Set<RestaurantPublicInfo> rstPos = new HashSet<RestaurantPublicInfo>();
		if(cascadeDepth>0){
			cascadeDepth--;
			if(operatorVo.getRestaurantVos()!=null&&operatorVo.getRestaurantVos().size()>0){
				List<RestaurantVO> restVos= operatorVo.getRestaurantVos();
				for(RestaurantVO vo:restVos){
					rstPos.add(RestaurantVO.convertVoToPo(null, vo,cascadeDepth));
				}				
			}
			po.setRestaurants(rstPos);
		}	
		return po;
	}
}
