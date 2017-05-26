package com.emin.platform.service;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.dao.SortKey;
import com.emin.base.service.Condition;
import com.emin.base.service.UndeleteableService;
import com.emin.platform.domain.Person;
import com.emin.platform.service.impl.PersonServiceImpl;

public interface PersonService extends UndeleteableService<Person> {
	/**
	 * 判断登陆信息是否正确
	 * @param sortKeys 
	 * @param @param conditions
	 * @param @return
	 * @return List<Person>
	 */

	public Person login(String mobilePhone,String password);
	
	/**
	 * @param sortKeys 
	 * @param @param conditions
	 * @param @return
	 * @return List<Person>
	 */
	List<Person> getAllPersons(SortKey sortKey);


	/**
	 * 保存用户信息
	 * @author jim
	 * @since 2016-09-01
	 * @param person
	 * @see Person 	
	 * @return Long	(用户ID) 
	 * 
	 */
	public Long saveOrUpdatePerson(Person person);
	
	/**
	 * @author rocky.yang
	 * @param pageRequest
	 * @param conditions
	 * @return PagedResult<Person>
	 */
	public PagedResult<Person> getPagedForUserByConditions(
			PageRequest pageRequest, List<Condition> conditions);

	/**
	 * 
	 * @author rocky.yang
	 * @param person
	 */
	
	public PagedResult<Person> loadPersonByCondition(
			PageRequest pageRequestData, List<Condition> conditions) ;
	
	/**
	 * 根据电话号码获取person 
	 * id:主键 可以为空
	 */
	Person findByMobilePhone(String mobilephone, Long id);

	/**
	 * 修改密码
	 * @param personId
	 * @param oldPwd
	 * @param newPwd
	 */
	public void modifyPassword(Long personId,String oldPwd,String newPwd);
	
	/**
	 * 重置密码为888888
	 * @param personIds
	 */
	public void resetPersonPassword(Long[] personIds);
	

	/**
	 * 重置imsi
	 * @param personIds
	 */
	public void resetPersonImsi(Long[] personIds);

	/**
	 * 
	 * @author Johnny.xiang
	 * @Since  Sep 10, 2015
	 * @param conditions
	 * @return
	 */
	public List<Person> findPersonsByConditions(List<Condition> conditions);

	void registerDevice(String mobilephone, String clientId);

	List<String> loadChannelIdsByMobilephones(String[] mobilephones);

	/**
	 * @param ids
	 * @return
	 */
	List<String> loadChannelIdsByPercompanyIds(Long[] ids);
	
	
}

