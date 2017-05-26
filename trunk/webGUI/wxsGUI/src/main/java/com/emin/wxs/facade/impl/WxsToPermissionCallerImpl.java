package com.emin.wxs.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.wxs.domain.Menu;
import com.emin.wxs.domain.Operation;
import com.emin.wxs.facade.WxsToPermissionCaller;
import com.emin.wxs.service.PermissionService;
import com.emin.wxs.util.WxsThreadLocalUtil;
import com.emin.wxs.vo.OperationVO;
@Component("wxsToPermissionCaller")
public class WxsToPermissionCallerImpl implements WxsToPermissionCaller{

	@Reference(version="0.0.1")
	private PermissionService permissionService;
	
	@Override
	public Long[] loadOperationIdsForOrg(Long orgId){
		List<Operation> operations = permissionService.getAllOperationsForOrg(orgId);
		if(operations!=null && operations.size()>0){
			Long[] operationIds = new Long[operations.size()];
			for (int i=0;i<operations.size();i++) {
				operationIds[i] = operations.get(i).getId();
			}
			return operationIds;
		}
		return new Long[0];
	}
	
	@Override	
	public void savePermission(Long orgId,Long[] operationIds){
		permissionService.savePermission(orgId, operationIds);
	}
	
	@Override
	public String getMenuOperationCodesForPerson(String menuCode){
		Long personId = WxsThreadLocalUtil.getPersonId();
		StringBuilder codes = new StringBuilder();
		List<Operation> operations = permissionService.getMenuOperationsForPerson(personId, menuCode);
		for (Operation operation : operations) {
			codes.append(operation.getCode()+",");
		}
		if(codes.length() > 0){
			codes = codes.deleteCharAt(codes.lastIndexOf(","));
		}
		return codes.toString();
	}
	@Override
	public String getAuthorizedMenuForPerson(){
		Long personId = WxsThreadLocalUtil.getPersonId();
		List<Menu> menus = permissionService.getAuthorizedMenuForPerson(personId);
		StringBuilder menuCodeBuilder = new StringBuilder();
		for (Menu menu : menus) {
			menuCodeBuilder.append(menu.getCode()).append(",");
		}
		if(menuCodeBuilder.length()>0){
			menuCodeBuilder.deleteCharAt(menuCodeBuilder.lastIndexOf(","));
		}
		return menuCodeBuilder.toString();
	}
	
	@Override
	public List<Menu> getAuthorizedMenuLstForPerson(){
		Long personId = WxsThreadLocalUtil.getPersonId();
		List<Menu> menus = permissionService.getAuthorizedMenuForPerson(personId);
		return menus;
	}
	
	@Override
	public List<OperationVO> loadAllOperations(){
		List<Operation> operations =  permissionService.loadAllOperations();
		List<OperationVO> vos = new ArrayList<>();
		for (Operation operation : operations) {
			vos.add(OperationVO.operationToVO(operation));
		}
		return vos;
	}
	

}
