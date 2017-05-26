package com.emin.platform.facade.accepters;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.platform.domain.Organize;
import com.emin.platform.domain.Person;

import net.sf.json.JSONArray;

public interface OrgAccepter {

	/**
	 * 保存组织（部门）信息
	 * @param Organize org
	 */
	public void saveOrUpdateOrg(Organize org);

	/**
	 * 删除组织(部门)
	 * @param Long id
	 */
	public void deleteOrg(Long id);
	
	/**
	 * 将员工加入到组织（部门）中
	 * @param Long orgId, Long[] personIds
	 */
	public void addPersonToOrg(Long orgId, Long[] personIds);

	/**
	 * 将员工从组织（部门）中移除
	 * @param orgId
	 * @param personIds
	 */
	void removePersonFromOrg(Long orgId, Long[] personIds);
	/**
	 * 批量变更员工的组织（部门）
	 * @param Long oldOrgId
	 * @param Long newOrgId
	 * @param Long[] personIds
	 */
	public void changePersonOrg(Long oldOrgId, Long newOrgId, Long[] personIds);

	/**
	 * 加载组织(部门)树
	 * @param pid
	 * @return
	 */
	public JSONArray loadOrgTree(Long pid);
	
	/**
	 * 分页加载组织（部门）成员
	 * @param PageRequest pageRequest
	 * @param Long orgId
	 * @param String match 匹配关键字 ：姓名，手机号
	 * @return
	 */
	public PagedResult<Person> loadOrgMembers(PageRequest pageRequest, Long orgId, String match);

	/**
	 * 分页加载未分配过组织（部门）的员工
	 * @param pageRequest
	 * @return
	 */
	PagedResult<Person> loadUnallocatedPersons(PageRequest pageRequest);

	/**
	 * 获取员工所在部门ID
	 * @param personId
	 * @return
	 */
	Long[] getPersonOrgIds(Long personId);

	/**
	 * 根据ID查找组织(部门)
	 * @param id
	 * @return Organize
	 */
	Organize findById(Long id);

	

}
