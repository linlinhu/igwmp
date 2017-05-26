package com.emin.platform.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.dao.PreFilter;
import com.emin.base.dao.PreFilters;
import com.emin.base.dao.SortKey;
import com.emin.base.service.Condition;
import com.emin.base.service.Conditions;
import com.emin.base.service.UndeleteableServiceImpl;
import com.emin.base.util.CommonsUtil;
import com.emin.base.util.EminEnvUtil;
import com.emin.base.util.EncryptUtils;
import com.emin.platform.domain.Company;
import com.emin.platform.domain.Person;
import com.emin.platform.service.CompanyService;
import com.emin.platform.service.PersonService;

@Service("companyService")
public class CompanyServiceImpl extends UndeleteableServiceImpl<Company> implements CompanyService{

	@Autowired
	@Qualifier("personService")
	private PersonService personService;
	
	
	/**
	 * 添加或更新一条数据
	 * @author
	 * @param company
	 */
	//@Transactional(rollbackFor=Exception.class)
	@Override
	public void saveOrUpdateCompany(Company company,Long serverId) {
		this.beforeSaveOrUpdate(company);
		Long companyId = company.getId();		
		if(companyId != null){			
			this.update(company);
		}else{
			this.save(company);			
			//初始化公司超级管理员
			this.initRootPersonForCompany(company.getId());
		}
	}
	

	

	private void beforeSaveOrUpdate(Company company) {
			if (!CommonsUtil.isNotEmpty(company.getName()) || null == company.getEffectiveTime()
				|| null == company.getPersonLimit() || null == company.getTotalPerson()) {
				
				throw new RuntimeException("信息不完整");
			}
			
			//判断name重复
			if (findByName(company.getName(), company.getId()) != null) {
				throw new RuntimeException("公司名字已存在");
			}
			
			if (null != company.getCode()) {
				//判断code重复
				if (findByCode(company.getCode(), company.getId()) != null) {
					throw new RuntimeException("公司简码已存在");
				}
			}
			
			if (null == company.getLoginMode()) {
				company.setLoginMode(1);
			}
			
	}

	/**
	 * 查询所公司数据
	 * @param @param conditions
	 * @param @return
	 * @return List<Company>
	 */
	@Override
	public List<Company> loadAllCompanys() {
		return this.findAllByStatus(Company.STATUS_VALID, SortKey.DEFAULT);
	}
	
	

	/**
	 * 查询所公司数据
	 * @param @param conditions
	 * @param @return
	 * @return List<Company>
	 */
	@Override
	public PagedResult<Company> loadCompanyByCondition(
			PageRequest pageRequestData, List<Condition> conditions) {
		List<PreFilter> filters = new ArrayList<PreFilter>(conditions.size());
		if (null != conditions && conditions.size() > 0) {
			for (Condition condition : conditions) {
				filters.add(Conditions.convertToPropertyFilter(condition));
			}
		}
		pageRequestData.setOrderBy(new SortKey[]{SortKey.DEFAULT });
		PreFilter[] preFilters = new PreFilter[filters.size()];
		preFilters = filters.toArray(preFilters);
		return this.getPage(pageRequestData, preFilters);
	}


	/**
	 * @param name
	 * @param id
	 * @return
	 */
	public Company findByName(String name, Long id) {
		PreFilter nameFilter = PreFilters.eq(Company.PROP_NAME, name);
		PreFilter statusFilter = PreFilters.eq(Company.PROP_STATUS,Company.STATUS_VALID);
		if (null == id) {
			return this.findUniqueByPreFilter(nameFilter, statusFilter);
		} else {
			PreFilter notIdFilter = PreFilters.notEq(Company.PROP_ID, id);
			return this.findUniqueByPreFilter(nameFilter, notIdFilter,statusFilter);
		}

	}

	/**
	 * @param name
	 * @param id
	 * @return
	 */
	public Company findByCode(String code, Long id) {
		PreFilter codeFilter = PreFilters.eq(Company.PROP_CODE, code);
		PreFilter statusFilter = PreFilters.eq(Company.PROP_STATUS,Company.STATUS_VALID);
		if (null == id) {
			return this.findUniqueByPreFilter(codeFilter, statusFilter);
		} else {
			PreFilter notIdFilter = PreFilters.notEq(Company.PROP_ID, id);
			return this.findUniqueByPreFilter(codeFilter, notIdFilter,statusFilter);
		}

	}
	
	
	
//	@Override
	private Person initRootPersonForCompany(Long companyId) {
		Person person = new Person();
		EminEnvUtil emEnvUtil = EminEnvUtil.getInstance();
		String password = emEnvUtil.getAdminInitialPwd();
		String adminPhone = emEnvUtil.getAdminPhone();
		adminPhone = Long.parseLong(adminPhone)+companyId+"";
		//查看此号码是否被使用
		Person p = this.personService.findByMobilePhone(adminPhone,null);
		if(p!=null){
			throw new RuntimeException("手机号已存在");
		}
		person.setMobilephone(adminPhone);
		logger.info("The password for root user in company:"+companyId+" is "+password);
		person.setPassword(EncryptUtils.encodeSHA(password));
		person.setType(Person.ACCESS_ADMIN);
		person.setMobilephone(adminPhone);
		person.setActivestatus(Person.ACTIVESTATUS_ACTIVE);
		person.setDescription("新建公司创建超级管理员");
		person.setName("admin");
		person.setCompanyId(companyId);
		this.personService.saveOrUpdate(person);
		return person;
	}
	@Override
	public void initRootPerson(){
		Person p = this.personService.findByMobilePhone("13800000000",null);
		if(p!=null){
			return;
		}
		Person person = new Person();
		logger.info("初始化平台超级管理员用户....");
		String password = "eminroot";
		String adminPhone = "13800000000";
		person.setMobilephone(adminPhone);
		person.setPassword(EncryptUtils.encodeMD5(password));
		person.setType(Person.ACCESS_ROOT);
		person.setMobilephone(adminPhone);
		person.setActivestatus(Person.ACTIVESTATUS_ACTIVE);
		person.setDescription("平台超级管理员");
		person.setName("超级管理员");
		this.personService.saveOrUpdate(person);
	}
	@Override
	public Long saveOrUpdateCompany(Company company){
		this.saveOrUpdateCompany(company, null);
		return company.getId();
	}

	

}
