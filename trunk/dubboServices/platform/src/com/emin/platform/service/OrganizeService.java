package com.emin.platform.service;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.base.service.UndeleteableService;
import com.emin.platform.domain.OrgMember;
import com.emin.platform.domain.Organize;
import com.emin.platform.domain.Person;

import net.sf.json.JSONArray;

public interface OrganizeService extends UndeleteableService<Organize>{
	
	/**
	 * 查询所有组织
	 * @author rocky
	 * @param sortKeys 
	 * @param @param conditions
	 * @param @return
	 * @return List<Organize>
	 */
	List<Organize> loadByCompanyId(Long CompanyId);


	/**
	 * 增加或更新一个组织
	 *@author rocky
	 * @param organize
	 * @return 
	 */
	 void saveOrUpdateOrganize(Organize organize);
	

	/**
	 * 按照查询条件查询组织
	 * @author rocky
	 * @param organize
	 * @return PagedResult<Organize>
	 */
	
	 PagedResult<Organize> loadOrganizeByCondition(
			PageRequest pageRequestData, List<Condition> conditions) ;
	
	
	/**
	 * 公司组织结构
	 * @param pid
	 * @param companyId
	 * @return
	 */
	JSONArray loadCompanyOrgTree(Long pid, Long companyId);



	/**
	 * 删除组织
	 * @param organizeId
	 */
	void deleteOrganize(Long organizeId);

	
	void removePersonFromOrg(Long orgId, Long[] personIds);


	void addPersonToOrg(Long orgId, Long[] personIds);


	void changePersonOrg(Long oldOrgId, Long newOrgId, Long[] personIds);


	PagedResult<Person> loadMembersByOrgId(PageRequest pageRequest, Long orgId, String match);

	
	PagedResult<Person> loadUnallocatedPersons(PageRequest pageRequest);


	Long[] getPersonOrgIds(Long personId);


}
