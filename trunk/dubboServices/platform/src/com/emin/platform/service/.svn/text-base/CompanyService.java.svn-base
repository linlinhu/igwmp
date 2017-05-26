package com.emin.platform.service;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.base.service.UndeleteableService;
import com.emin.platform.domain.Company;


public interface CompanyService extends UndeleteableService<Company> {
	
	/**
	 * 查询所有的公司数据
	 * @param @param conditions
	 * @param @return
	 * @return List<GPSTrack>
	 */
	List<Company> loadAllCompanys();

	/**
	 * 添加或更新一条数据
	 * @author
	 * @param company
	 */
	public void saveOrUpdateCompany(Company company,Long dbserver);

	/**
	 * 根据条件分页查询公司数据
	 * @author
	 * @param pageRequest
	 * @param conditions
	 * @return
	 */
	PagedResult<Company> loadCompanyByCondition(PageRequest pageRequestData,List<Condition> conditions);

	void initRootPerson();

	/**
	 * @param company
	 * @return
	 */
	Long saveOrUpdateCompany(Company company);
	
	
}
