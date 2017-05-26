package com.emin.platform.facade.accepters;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.platform.domain.Person;

public interface PersonAccepter {

	/**
	 * 保存用户信息
	 * @param Person person
	 * @return Long 被保存的用户ID
	 * @throws EminException
	 */
	Long savePerson(Person person) throws EminException;

	/**
	 * 分页查询用户信息
	 * @param PageRequest pageRequest 分页条件
	 * @param List<Condition> conditions 过滤条件
	 * @return PagedResult<Person>
	 */
	public PagedResult<Person> loadAllPersons(PageRequest pageRequest,List<Condition> conditions);

	/**
	 * 登录
	 * @param cellNumber
	 * @param password
	 * @return
	 */
	Person login(String cellNumber, String password) throws EminException;

	/**
	 * 删除用户 single
	 * @param id
	 */
	void deletePerson(Long id);

	/**
	 * 删除用户 batch
	 * @param ids
	 */
	void deletePersons(Long[] ids);

	/**
	 * 修改密码
	 * @param personId
	 * @param password
	 * @param newPassword
	 */
	void modifyPassword(Long personId, String password, String newPassword);

	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	Person findById(Long id);

}
