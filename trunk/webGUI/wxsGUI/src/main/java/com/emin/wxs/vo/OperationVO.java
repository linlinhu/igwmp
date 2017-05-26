package com.emin.wxs.vo;

import com.emin.wxs.domain.Operation;

public class OperationVO {

	private Long id;
	
	private String name;
	
	private String remark;
	
	private Long menuId;
	
	private String menuName;

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	public static OperationVO operationToVO(Operation operation){
		if(operation!=null){
			OperationVO vo = new OperationVO();
			vo.setId(operation.getId());
			vo.setMenuId(operation.getMenuId());
			vo.setMenuName(operation.getMenuName());
			vo.setName(operation.getName());
			vo.setRemark(operation.getRemark());
			return vo;
		}
		return null;
	}
}
