package com.emin.platform.facade.accepters.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.dao.PreFilter;
import com.emin.base.domain.UndeleteableEntity;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.base.service.Conditions;
import com.emin.platform.domain.Person;
import com.emin.platform.facade.accepters.PersonAccepter;
import com.emin.platform.service.PersonService;
@Component("personAccepter")
@Service(version="0.0.1")
public class PersonAccetperImpl implements PersonAccepter{

	@Autowired
	@Qualifier("personService")
	private PersonService personService;
	
	@Override
	public Long savePerson(Person person) throws EminException{
		if(person.getType()==null || person.getType().intValue()!=Person.ACCESS_PERSON){
			person.setType(Person.ACCESS_PERSON);
		}
		return personService.saveOrUpdatePerson(person);
	}
	
	@Override
	public Person login(String cellNumber,String password){
		return personService.login(cellNumber, password);
	}
	@Override
	public void deletePerson(Long id){
		personService.deleteById(id);
	}
	@Override
	public void deletePersons(Long[] ids){
		personService.deleteByIds(ids);
	}
	
	
	@Override
	public PagedResult<Person> loadAllPersons(PageRequest pageRequest,List<Condition> conditions){
		List<PreFilter> filters = new ArrayList<>();
		boolean needStatusFilters = true;
		if(conditions!=null && conditions.size()>0){
			for (Condition condition : conditions) {
				filters.add(Conditions.convertToPropertyFilter(condition));
				if(condition.getPropertyName().equals(UndeleteableEntity.PROP_STATUS)){
					needStatusFilters = false;
				}
			}
		}
		if(needStatusFilters){
			filters.add(personService.getStatusFilter());
		}
		PreFilter[] fs = new PreFilter[filters.size()];
		return personService.getPage(pageRequest, filters.toArray(fs));
	}
	@Override
	public void modifyPassword(Long personId,String password,String newPassword){
		personService.modifyPassword(personId, password, newPassword);
	}
	@Override
	public Person findById(Long id){
		return personService.findById(id);
	}
}
