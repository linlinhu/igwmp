package com.emin.wxs.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.platform.domain.Person;
import com.emin.platform.facade.accepters.PersonAccepter;
import com.emin.wxs.facade.WxsToPersonCaller;
import com.emin.wxs.service.PermissionService;
import com.emin.wxs.util.WxsThreadLocalUtil;
import com.emin.wxs.vo.PersonVO;
@Component("wxsToPersonCaller")
public class WxsToPersonCallerImpl implements WxsToPersonCaller{

	@Reference(version="0.0.1")
	private PersonAccepter personAccepter;
	
	@Reference(version="0.0.1")
	private PermissionService permissionService;
	
	@Override
	public void savePerson(PersonVO personVO){
		Person person = null;
		if(personVO.getId()!=null && personVO.getId().longValue()!=0l){
			person = personAccepter.findById(personVO.getId());
		}		
		personAccepter.savePerson(personVO.convertToPerson(person));
	}
	
	@Override
	public PersonVO findById(Long id){
		Person person = personAccepter.findById(id);
		return PersonVO.personToVO(person);
	}
	@Override
	public PagedResult<PersonVO> loadPersonsByPage(PageRequest pageRequest, List<Condition> conditions){
		PagedResult<Person> persons = personAccepter.loadAllPersons(pageRequest, conditions);
		List<PersonVO> vos = new ArrayList<>();
		for(Person person : persons.getResultList()){
			vos.add(PersonVO.personToVO(person));
		}
		PagedResult<PersonVO> result = new PagedResult<>(vos, persons.getNextOffset(), persons.getTotalCount());
		return result;
	}
	@Override
	public void deletePerson(Long id){
		personAccepter.deletePerson(id);
	}
	@Override
	public void deletePersons(Long[] ids){
		personAccepter.deletePersons(ids);
	}
	@Override
	public PersonVO login(String cellNumber,String password){
		Person person = personAccepter.login(cellNumber, password);
		return PersonVO.personToVO(person);
	}
	@Override
	public void modifyPassword(String password,String newPassword){
		personAccepter.modifyPassword(WxsThreadLocalUtil.getPersonId(), password, newPassword);
	}
}
