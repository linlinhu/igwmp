package com.emin.wxs.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emin.base.dao.PagedResult;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.base.service.Condition.ConditionOperator;
import com.emin.base.service.Condition.ConditionType;
import com.emin.base.util.CommonsUtil;
import com.emin.platform.domain.Person;
import com.emin.wxs.controller.WxsBaseController;
import com.emin.wxs.facade.WxsToPersonCaller;
import com.emin.wxs.vo.PersonVO;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("/person")
public class PersonController extends WxsBaseController{

	private Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	@Autowired
	@Qualifier("wxsToPersonCaller")
	private WxsToPersonCaller wxsToPersonCaller;
	
	@RequestMapping(value="/page.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JSONObject loadPersons(String name,String mobile){
		List<Condition> conditions = new ArrayList<Condition>();
		if(StringUtils.isNotBlank(name)){
			conditions.add(new Condition(Person.PROP_NAME, ConditionOperator.LIKE, ConditionType.CHARACTER, "%"+name+"%"));
		}
		if(StringUtils.isNotBlank(mobile)){
			conditions.add(new Condition(Person.PROP_MOBILE_PHONE, ConditionOperator.LIKE, ConditionType.CHARACTER, "%"+mobile+"%"));
		}
		PagedResult<PersonVO> result = wxsToPersonCaller.loadPersonsByPage(getPageRequestData(), conditions);
		JSONObject json = new JSONObject();
		json.put("total", result.getTotalCount());
		json.put("rows", result.getResultList());
		return json;
	}
	@RequestMapping(value="/saveOrUpdate.do",method={RequestMethod.POST,RequestMethod.PUT})
	@ResponseBody
	public JSONObject savePerson(PersonVO personVO){
		JSONObject json = new JSONObject();
		String message = "";
		boolean success = false;
		try {
			wxsToPersonCaller.savePerson(personVO);
			success = true;
		} catch (EminException e) {
			success = false;
			message = e.getLocalizedMessage();
			logger.error(e.getLocalizedMessage(),e);
		} catch (Exception e) {
			success = false;
			message = e.getMessage();
			logger.error(e.getMessage(),e);
		}
		json.put("success", success);
		json.put("message", message);
		return json;
	}
	@RequestMapping(value = "/delete.do",method={RequestMethod.POST,RequestMethod.DELETE})
	@ResponseBody
	public JSONObject deletePerson(@RequestParam(required=true)Long id){
		JSONObject json = new JSONObject();
		String message = "";
		boolean success = false;
		try {
			wxsToPersonCaller.deletePerson(id);
			success = true;
		} catch (EminException e) {
			success = false;
			message = e.getLocalizedMessage();
			logger.error(e.getLocalizedMessage(),e);
		} catch (Exception e) {
			success = false;
			message = e.getMessage();
			logger.error(e.getMessage(),e);		}
		json.put("success", success);
		json.put("message", message);
		return json;
	}
	@RequestMapping(value = "/batchDelete.do",method={RequestMethod.POST,RequestMethod.DELETE})
	public JSONObject deletePersons(@RequestParam(required=true)String ids){
		JSONObject json = new JSONObject();
		String message = "";
		boolean success = false;
		try {
			wxsToPersonCaller.deletePersons(CommonsUtil.stringToLongArr(ids));
			success = true;
		} catch (EminException e) {
			success = false;
			message = e.getLocalizedMessage();
			logger.error(e.getLocalizedMessage(),e);
		} catch (Exception e) {
			success = false;
			message = e.getMessage();
			logger.error(e.getMessage(),e);
		}
		json.put("success", success);
		json.put("message", message);
		return json;
	}
}
