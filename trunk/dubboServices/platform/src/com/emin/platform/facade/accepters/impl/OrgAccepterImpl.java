package com.emin.platform.facade.accepters.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.platform.domain.Organize;
import com.emin.platform.domain.Person;
import com.emin.platform.facade.accepters.OrgAccepter;
import com.emin.platform.service.OrganizeService;

import net.sf.json.JSONArray;
@Component("orgFacade")
@Service(version="0.0.1")
public class OrgAccepterImpl implements OrgAccepter{

	@Autowired
	@Qualifier("organizeService")
	private OrganizeService organizeService;
	
	@Override
	public void saveOrUpdateOrg(Organize org){
		organizeService.saveOrUpdateOrganize(org);
	}
	
	@Override
	public void deleteOrg(Long id){
		organizeService.deleteOrganize(id);
	}
	
	@Override
	public void addPersonToOrg(Long orgId,Long[] personIds){
		organizeService.addPersonToOrg(orgId, personIds);
	}
	@Override
	public void removePersonFromOrg(Long orgId,Long[] personIds){
		organizeService.removePersonFromOrg(orgId, personIds);
	}
	@Override
	public void changePersonOrg(Long oldOrgId, Long newOrgId, Long[] personIds){
		organizeService.changePersonOrg(oldOrgId, newOrgId, personIds);
	}
	
	@Override
	public JSONArray loadOrgTree(Long pid){
		return organizeService.loadCompanyOrgTree(pid, 1l);
	}
	
	@Override
	public PagedResult<Person> loadOrgMembers(PageRequest pageRequest,Long orgId,String match){
		return organizeService.loadMembersByOrgId(pageRequest, orgId, match);
	}
	
	@Override
	public PagedResult<Person> loadUnallocatedPersons(PageRequest pageRequest){
		return organizeService.loadUnallocatedPersons(pageRequest);
	}
	@Override
	public Long[] getPersonOrgIds(Long personId){
		return organizeService.getPersonOrgIds(personId);
	}
	@Override
	public Organize findById(Long id){
		return organizeService.findById(id);
	}
}
