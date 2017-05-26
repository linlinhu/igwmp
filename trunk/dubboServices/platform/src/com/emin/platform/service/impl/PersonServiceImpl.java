package com.emin.platform.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.dao.PreFilter;
import com.emin.base.dao.PreFilters;
import com.emin.base.dao.SortKey;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.base.service.Conditions;
import com.emin.base.service.UndeleteableServiceImpl;
import com.emin.base.util.CommonsUtil;
import com.emin.base.util.EncryptUtils;
import com.emin.base.util.PreFilterUtil;
import com.emin.platform.domain.Person;
import com.emin.platform.service.PersonService;

@Service("personService")
public class PersonServiceImpl extends UndeleteableServiceImpl<Person> implements PersonService {
	

	/**
	 * 登陆
	 * 
	 * @author rocky.yang
	 * @param mobilePhone
	 * @param password
	 * @return Person
	 */
	@Override
	public Person login(String mobilePhone, String password)  {
		if (null == mobilePhone || null == password) {
			throw new RuntimeException("账号或密码不能为空");
		}
		PreFilter mobileFilter = PreFilters.eq(Person.PROP_MOBILE_PHONE,
				mobilePhone);
		PreFilter passwordFilter = PreFilters
				.eq(Person.PROP_PASSWORD, password);
		PreFilter statusFilter = PreFilters.ge(Person.PROP_STATUS, 0);
		Person person = this.findUniqueByPreFilter(mobileFilter,
				passwordFilter, statusFilter);
		if (null == person) {
			throw new EminException("2.3.2");
		}
		return person;
	}

	@Transactional
	@Override
	public Long saveOrUpdatePerson(Person person) {
		boolean isNewPerson = false;
		if (person.getId() == null || person.getId() == 0L) {
			isNewPerson = true;
		}
		this.beforeSaveOrUpdate(person);
		if (isNewPerson) {
			Person oldPerson = findByMobilePhone(person.getMobilephone(),null);
			if (oldPerson == null || Person.STATUS_INVALID == oldPerson.getStatus()) {
				if(StringUtils.isBlank(person.getPassword())){
					person.setPassword(EncryptUtils.encodeMD5("888888"));
				}
				super.saveOrUpdate(person);
				this.flush();
			}else {
				person.setId(oldPerson.getId());
			}
		}else{
			Person oldPerson = this.findById(person.getId());
			if(null != person.getGender()){
				oldPerson.setGender(person.getGender());
			}
			if(StringUtils.isNotEmpty(person.getEmail())){
				oldPerson.setEmail(person.getEmail());
			}
			this.update(oldPerson);
		}
		
		
		return person.getId();
	}

	/**
	 * 增加或更新之前对一个人员的校验
	 * 
	 * @author rocky
	 * @param person
	 * @return
	 */
	private void beforeSaveOrUpdate(Person person) {
		if (!CommonsUtil.isNotEmpty(person.getName()) || !CommonsUtil.isNotEmpty(person.getMobilephone())
			|| person.getType()==null || !CommonsUtil.isNotEmpty(person.getCompanyId())) {
			throw new RuntimeException("信息不完整");
		}
		// 判断手机重复
		Person oldPerson = findByMobilePhone(person.getMobilephone(), person.getId());
		if (oldPerson != null && Person.STATUS_VALID == oldPerson.getStatus()) {
			//查找当前人员的公司
			Person p = this.findById(oldPerson.getId());
			if (null != p) {
				throw new RuntimeException("用户已存在");
			}
		}
		person.setPassword(EncryptUtils.encodeSHA("888888"));
		if (null == person.getActivestatus()) {
			person.setActivestatus(Person.ACTIVESTATUS_DISACTIVE);
			
		}
	}

	@Override
	public Person findByMobilePhone(String mobilephone, Long id) {
		PreFilter mobilephoneFilter = PreFilters.eq(Person.PROP_MOBILE_PHONE,mobilephone);
		if (null == id) {
			return this.findUniqueByPreFilter(mobilephoneFilter);
		} else {
			PreFilter notIdFilter = PreFilters.notEq(Person.PROP_ID, id);
			return this.findUniqueByPreFilter(notIdFilter, mobilephoneFilter);
		}
	}
	@Override
	public void registerDevice(String mobilephone,String clientId){
		Person person = findByMobilePhone(mobilephone, null);
		person.setImsi(clientId);
		this.update(person);
	}
	@Override
	public List<String> loadChannelIdsByMobilephones(String[] mobilephones){
		List<String> channelIds = new ArrayList<String>();
		PreFilter phoneFilter = PreFilters.in(Person.PROP_MOBILE_PHONE, (Object[])mobilephones);
		PreFilter statusFilter = PreFilters.eq(Person.PROP_STATUS, Person.STATUS_VALID);
		List<Person> persons = this.findByPreFilter(phoneFilter,statusFilter);
		if(persons==null){
			return channelIds;
		}
		for(int i=0;i<persons.size();i++){
			channelIds.add(persons.get(i).getImsi());
		}
		return channelIds;
	}
	@Override
	public List<String> loadChannelIdsByPercompanyIds(Long[] ids){
		List<String> channelIds = new ArrayList<String>();
		
		/*List<PerCompany> persons = perCompanyService.findByIds(ids);
		if(persons==null){
			return channelIds;
		}
		for(int i=0;i<persons.size();i++){
			channelIds.add(persons.get(i).getPerson().getImsi());
		}*/
		return channelIds;
	}
	public PagedResult<Person> getPagedForUserByConditions(
			PageRequest pageRequest, List<Condition> conditions) {
		List<PreFilter> filters = new ArrayList<PreFilter>();
		if (conditions != null && conditions.size() > 0) {
			for (Condition condition : conditions) {
				filters.add(Conditions.convertToPropertyFilter(condition));
			}
		}
		PreFilter[] preFilters = new PreFilter[filters.size()];
		preFilters = filters.toArray(preFilters);
		return this.getPage(pageRequest, preFilters);
	}

	/**
	 * 获取所有人员信息
	 * 
	 * @author rocky
	 * @param username
	 * @param password
	 * @return List<Person>
	 */
	@Override
	public List<Person> getAllPersons(SortKey sortKey) {
		return this.findAll(new SortKey[] { SortKey.DEFAULT });
	}

	/**
	 * 按照查询条件分页查询
	 * 
	 * @author rocky
	 * @param person
	 * @return PagedResult<Person>
	 */
	public PagedResult<Person> loadPersonByCondition(
			PageRequest pageRequestData, List<Condition> conditions) {
		List<PreFilter> filters = new ArrayList<PreFilter>(conditions.size());
		if (null != conditions && conditions.size() > 0) {
			for (Condition condition : conditions) {
				filters.add(Conditions.convertToPropertyFilter(condition));
			}
		}
		pageRequestData.setOrderBy(new SortKey[] { SortKey.DEFAULT });
		PreFilter[] preFilters = new PreFilter[filters.size()];
		preFilters = filters.toArray(preFilters);
		return this.getPage(pageRequestData, preFilters);
	}

	@Override
	public void resetPersonPassword(Long[] ids){
		String password = EncryptUtils.encodeSHA("888888");
		this.getEntityDao().updateByHQL("update Person set password =? where id in ("+CommonsUtil.longArrToString(ids)+")", password);
	}
	@Override
	public void resetPersonImsi(Long[] ids){
		this.getEntityDao().updateByHQL("update Person set imsi =null where id in ("+CommonsUtil.longArrToString(ids)+")");
	}


	@Override
	public void modifyPassword(Long personId,String oldPwd, String newPwd) {
		if(oldPwd==null)throw new RuntimeException("旧密码不能为空");
		Person p = this.findById(personId);
		if(p==null)throw new RuntimeException("用户不存在");
		if(!oldPwd.equals(p.getPassword()))throw new RuntimeException("旧密码错误");
		p.setPassword(EncryptUtils.encodeSHA(newPwd));
		this.saveOrUpdate(p);
	}

	@Override
	public List<Person> findPersonsByConditions(List<Condition> conditions) {
		if(null == conditions) {
			throw new RuntimeException("信息不完整");
		}
		List<Person> persons = this.findByPreFilter(PreFilterUtil.toArrayByConditions(conditions));
		return persons;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void deleteById(Long personId){
		//禁用员工的同时，将其所有岗位空缺出来
		super.deleteById(personId);
		this.getEntityDao().updateByHQL("update OrgMember set status = -1 where personId = ?", personId);
	}
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void enableById(Long personId){
		//启用员工的同时将其岗位同时恢复
		super.enableById(personId);
		this.getEntityDao().updateByHQL("update OrgMember set status = 1 where personId = ?", personId);
	}

}
