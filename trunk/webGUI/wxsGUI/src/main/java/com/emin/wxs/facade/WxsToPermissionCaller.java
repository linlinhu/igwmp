package com.emin.wxs.facade;

import java.util.List;

import com.emin.wxs.domain.Menu;
import com.emin.wxs.domain.Operation;
import com.emin.wxs.vo.OperationVO;

public interface WxsToPermissionCaller {

	/**
	 * 获取组织（部门）已分配的操作权限
	 * @param orgId
	 * @return operationIds
	 */
	Long[] loadOperationIdsForOrg(Long orgId);

	/**
	 * 保存权限信息
	 * @param orgId
	 * @param operationIds
	 */
	void savePermission(Long orgId, Long[] operationIds);

	/**
	 * 获取用户在菜单中的操作权限码
	 * @param menuCode
	 * @return String 逗号分隔的权限码
	 */
	String getMenuOperationCodesForPerson(String menuCode);
	
	/**
	 * 获取用户被授权访问的菜单Code
	 * @return
	 */
	String getAuthorizedMenuForPerson();
	
	/**
	 * 获取用户被授权访问的菜单列表
	 * @return
	 */
	List<Menu> getAuthorizedMenuLstForPerson();
	/**
	 * 获取所有操作权限 用于授权
	 * @return
	 */
	List<OperationVO> loadAllOperations();

}
