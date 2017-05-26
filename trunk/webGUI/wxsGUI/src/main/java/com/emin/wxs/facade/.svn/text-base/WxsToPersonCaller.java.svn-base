package com.emin.wxs.facade;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.wxs.vo.PersonVO;

import net.sf.json.JSONObject;

public interface WxsToPersonCaller {

	/**
	 * 调用平台服务保存用户
	 * @param PersonVO personVO
	 */
	public void savePerson(PersonVO personVO);
	
	
	/**
	 * 调用平台服务分页查询所有用户
	 * @param PageRequest pageRequest
	 * @param List<Condition> conditions
	 * @return PagedResult<PersonVO>
	 */
	PagedResult<PersonVO> loadPersonsByPage(PageRequest pageRequest, List<Condition> conditions);


	void deletePersons(Long[] ids);


	void deletePerson(Long id);

	/**
	 * 登录
	 * @param cellNumber
	 * @param password
	 * @return
	 */
	PersonVO login(String cellNumber, String password);

	/**
	 * 修改密码
	 * @param password
	 * @param newPassword
	 */
	void modifyPassword(String password, String newPassword);


	PersonVO findById(Long id);

}
