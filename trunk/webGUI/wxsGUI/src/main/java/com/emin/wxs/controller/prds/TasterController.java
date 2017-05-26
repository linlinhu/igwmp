package com.emin.wxs.controller.prds;
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
import com.emin.igwmp.prds.domain.Product;
import com.emin.igwmp.prds.domain.WineTaster;
import com.emin.wxs.controller.WxsBaseController;
import com.emin.wxs.facade.prds.WxsToTasterCaller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/taster")
public class TasterController extends WxsBaseController{

	private Logger logger = LoggerFactory.getLogger(TasterController.class);
	
	@Autowired 
	@Qualifier("wxsToTasterCaller")
	private WxsToTasterCaller wxsToTasterCaller;
	
	@RequestMapping(value="/page.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JSONObject loadTasters(String name){
		List<Condition> conditions = new ArrayList<Condition>();
		if(StringUtils.isNotBlank(name)){
			conditions.add(new Condition(Product.PROP_NAME, ConditionOperator.LIKE, ConditionType.CHARACTER, "%"+name+"%"));
		}
		PagedResult<WineTaster> result = wxsToTasterCaller.loadPagedTastersByCondition(getPageRequestData(), conditions);
		JSONObject json = new JSONObject();
		json.put("total", result.getTotalCount()); 
		json.put("rows", result.getResultList());
		return json;
	} 
	
	

	@RequestMapping(value="/list.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JSONObject getTeasters(){
		JSONObject json = new JSONObject();
		List<Condition> conditions = new ArrayList<Condition>();
		PagedResult<WineTaster> result = wxsToTasterCaller.loadPagedTastersByCondition(getPageRequestData(), conditions);
		JSONArray jsonArr = new JSONArray();
		List<WineTaster> list = result.getResultList();
		for(WineTaster w :list){
			JSONObject teasterJSON = new JSONObject();
			teasterJSON.put("id", w.getId());
			teasterJSON.put("name", w.getName());
			jsonArr.add(teasterJSON);
		}
		json.put("data", jsonArr);
		return json;
	}
	
	
	
	@RequestMapping(value="/saveOrUpdate.do",method={RequestMethod.POST,RequestMethod.PUT})
	@ResponseBody
	public JSONObject saveTeaster(WineTaster Teaster){
		JSONObject json = new JSONObject();
		String message = "";
		boolean success = false;
		try {
			wxsToTasterCaller.saveOrUpdateTaster(Teaster);
			success = true;
		} catch (EminException e) {
			message = e.getLocalizedMessage();
			logger.error(e.getLocalizedMessage(),e);
		} catch (Exception e) {
			message = e.getMessage();
			logger.error(e.getMessage(),e);
		}
		json.put("success", success);
		json.put("message", message);
		return json;
	}
	
	
	/*@RequestMapping(value = "/delete.do",method={RequestMethod.POST,RequestMethod.DELETE})
	@ResponseBody
	public JSONObject deleteTeaster(@RequestParam(required=true)Long id){
		JSONObject json = new JSONObject();
		String message = "";
		boolean success = false;
		try {
			//wxsToTeasterCaller.deleteTeaster(id);
			success = true;
		} catch (EminException e) {
			message = e.getLocalizedMessage();
			logger.error(e.getLocalizedMessage(),e);
		} catch (Exception e) {
			message = e.getMessage();
			logger.error(e.getMessage(),e);
		}
		json.put("success", success);
		json.put("message", message);
		return json;
	}*/
}
