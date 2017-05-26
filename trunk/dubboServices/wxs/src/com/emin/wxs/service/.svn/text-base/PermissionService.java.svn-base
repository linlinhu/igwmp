package com.emin.wxs.service;

import java.util.List;

import com.emin.base.exception.EminException;
import com.emin.base.service.CRUDService;
import com.emin.wxs.domain.Menu;
import com.emin.wxs.domain.Operation;
import com.emin.wxs.domain.OrgOperation;

public interface PermissionService extends CRUDService<OrgOperation>{

	/**
	 * 保存组织（部门）操作权限
	 * @param orgId
	 * @param operationIds
	 * @throws EminException
	 */
	void savePermission(Long orgId, Long[] operationIds) throws EminException;

	/**
	 * 获取所有操作权限列表
	 * @return
	 */
	List<Operation> loadAllOperations();

	/**
	 * 获取用户能放问的功能菜单
	 * @param personId
	 * @return
	 */
	List<Menu> getAuthorizedMenuForPerson(Long personId);

	/**
	 * 获取功能菜单中用户有权限的操作
	 * @param personId
	 * @param menuId
	 * @return
	 */
	List<Operation> getMenuOperationsForPerson(Long personId, Long menuId);
	
	List<Operation> getMenuOperationsForPerson(Long personId, String menuCode);

	/**
	 * 获取组织（部门）已分配的操作权限
	 * @param orgId
	 * @return
	 */
	List<Operation> getAllOperationsForOrg(Long orgId);

	

}
