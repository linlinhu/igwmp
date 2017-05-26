package com.emin.wxs.vo.restaurant;

import com.emin.igwmp.rstm.domain.CooperationPlanInfo;

public class CooperationVO {
	private Long id;	
	/**
     * 方案名称
     * */
    private String name;	
    /**
     * 方案描述
     * */
    private String memo;
    
    /**
     * 收益角色
     */
    private int roleType;
    
    /**
     * 收益比例
     */
	private Double profitPercent;
	
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
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}	
	
	public int getRoleType() {
		return roleType;
	}
	public void setRoleType(int roleType) {
		this.roleType = roleType;
	}
	public Double getProfitPercent() {
		return profitPercent;
	}
	public void setProfitPercent(Double profitPercent) {
		this.profitPercent = profitPercent;
	}
	public static CooperationVO convertPoToVo(CooperationPlanInfo cooperationPlanInfo,int cascadeDepth){
		if(cooperationPlanInfo == null){
			return null;
		}
		CooperationVO cooperationVo = new CooperationVO();
		cooperationVo.setId(cooperationPlanInfo.getId());
		cooperationVo.setMemo(cooperationPlanInfo.getMemo());
		cooperationVo.setName(cooperationPlanInfo.getName());
		cooperationVo.setProfitPercent(cooperationPlanInfo.getProfitPercent());
		cooperationVo.setRoleType(cooperationPlanInfo.getRoleType());
		return cooperationVo;
	}
	
	public static CooperationPlanInfo convertVoToPo(CooperationPlanInfo cooperationPlanInfo,CooperationVO cooperationVo,int cascadeDepth){
		CooperationPlanInfo po = null;
		if(cooperationVo == null) return null;
		if(cooperationPlanInfo != null){
			po = cooperationPlanInfo;
		}
		else{
			po = new CooperationPlanInfo();
			po.setCreateTime(System.currentTimeMillis());
		}			
	    po.setId(cooperationVo.getId());		
		po.setLastModifyTime(System.currentTimeMillis());
		po.setStatus(1);
		po.setMemo(cooperationVo.getMemo());
		po.setName(cooperationVo.getName());
		po.setProfitPercent(cooperationVo.getProfitPercent());
		po.setRoleType(cooperationVo.getRoleType());
		return po;
	}
}
