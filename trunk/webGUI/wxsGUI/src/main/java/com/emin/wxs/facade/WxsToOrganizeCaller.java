package com.emin.wxs.facade;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.wxs.vo.OrganizeVO;
import com.emin.wxs.vo.PersonVO;

import net.sf.json.JSONArray;

public interface WxsToOrganizeCaller {

	/**
	 * 保存组织(部门)
	 * @param OrganizeVO orgVO
	 */
	void saveOrganize(OrganizeVO orgVO);

	/**
	 * 加载组织树
	 * @param pid
	 * @return
	 */
	JSONArray loadOrgTree(Long pid);

	/**
	 * 将员工加入到组织
	 * @param orgId
	 * @param personIds
	 */
	void addPersonToOrg(Long orgId, Long[] personIds);

	/**
	 * 将员工从组织中移除
	 * @param orgId
	 * @param personIds
	 */
	void removePersonFromOrg(Long orgId, Long[] personIds);

	/**
	 * 批量变更员工的组织
	 * @param oldOrgId
	 * @param newOrgId
	 * @param personIds
	 */
	void changePersonOrg(Long oldOrgId, Long newOrgId, Long[] personIds);

	/**
	 * 删除组织
	 * @param id
	 */
	void deleteOrg(Long id);

	/**
	 * 分页加载组织成员
	 * @param PageRequest pageRequest
	 * @param Long orgId
	 * @param String match 可匹配手机号、姓名
	 * @return
	 */
	PagedResult<PersonVO> loadOrgMembers(PageRequest pageRequest, Long orgId, String match);

	/**
	 * 分页加载未分配组织的员工
	 * @param pageRequest
	 * @return
	 */
	PagedResult<PersonVO> loadUnallocatedPersons(PageRequest pageRequest);

}
