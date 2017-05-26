package com.emin.wxs.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.platform.domain.Organize;
import com.emin.platform.domain.Person;
import com.emin.platform.facade.accepters.OrgAccepter;
import com.emin.wxs.facade.WxsToOrganizeCaller;
import com.emin.wxs.vo.OrganizeVO;
import com.emin.wxs.vo.PersonVO;

import net.sf.json.JSONArray;
@Component("wxsToOrganizeCaller")
public class WxsToOrganizeCallerImpl implements WxsToOrganizeCaller{

	@Reference(version="0.0.1")
	private OrgAccepter orgAccepter;
	
	@Override
	public void saveOrganize(OrganizeVO orgVO){
		Organize organize = null;
		if(orgVO.getId()!=null && orgVO.getId().longValue()!=0l){
			organize = orgAccepter.findById(orgVO.getId());
		}
		orgAccepter.saveOrUpdateOrg(orgVO.convertToOrganize(organize));
	}
	@Override
	public JSONArray loadOrgTree(Long pid){
		return orgAccepter.loadOrgTree(pid);
	}
	@Override
	public void addPersonToOrg(Long orgId,Long[] personIds){
		orgAccepter.addPersonToOrg(orgId, personIds);
	}
	@Override
	public void removePersonFromOrg(Long orgId,Long[] personIds){
		orgAccepter.removePersonFromOrg(orgId, personIds);
	}
	@Override
	public void changePersonOrg(Long oldOrgId,Long newOrgId,Long[] personIds){
		orgAccepter.changePersonOrg(oldOrgId, newOrgId, personIds);
	}
	@Override
	public void deleteOrg(Long id){
		orgAccepter.deleteOrg(id);
	}
	@Override
	public PagedResult<PersonVO> loadOrgMembers(PageRequest pageRequest,Long orgId,String match){
		PagedResult<Person> page = orgAccepter.loadOrgMembers(pageRequest, orgId, match);
		List<PersonVO> vos = new ArrayList<>();
		for(Person person : page.getResultList()){
			vos.add(PersonVO.personToVO(person));
		}
		PagedResult<PersonVO> result = new PagedResult<>(vos, page.getNextOffset(), page.getTotalCount());
		return result;
	}
	@Override
	public PagedResult<PersonVO> loadUnallocatedPersons(PageRequest pageRequest){
		PagedResult<Person> page = orgAccepter.loadUnallocatedPersons(pageRequest);
		List<PersonVO> vos = new ArrayList<>();
		for(Person person : page.getResultList()){
			vos.add(PersonVO.personToVO(person));
		}
		PagedResult<PersonVO> result = new PagedResult<>(vos, page.getNextOffset(), page.getTotalCount());
		return result;
	}
	
}
