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
import com.emin.igwmp.prds.domain.Category;
import com.emin.wxs.controller.WxsBaseController;
import com.emin.wxs.facade.prds.WxsToCategoryCaller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/category")
public class CategoryController extends WxsBaseController{

	private Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	@Autowired 
	@Qualifier("wxsToCategoryCaller")
	private WxsToCategoryCaller wxsToCategoryCaller;
	
	@RequestMapping(value="/page.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JSONObject loadCategorys(String name){
		List<Condition> conditions = new ArrayList<Condition>();
		if(StringUtils.isNotBlank(name)){
			conditions.add(new Condition(Product.PROP_NAME, ConditionOperator.LIKE, ConditionType.CHARACTER, "%"+name+"%"));
		}
		PagedResult<Category> result = wxsToCategoryCaller.loadPagedCategorysByCondition(getPageRequestData(), conditions);
		JSONObject json = new JSONObject();
		json.put("total", result.getTotalCount());
		json.put("rows", result.getResultList());
		return json;
	} 
	

	@RequestMapping(value="/list.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JSONObject getCategorys(){
		JSONObject json = new JSONObject();
		List<Condition> conditions = new ArrayList<Condition>();
		PagedResult<Category> result = wxsToCategoryCaller.loadPagedCategorysByCondition(getPageRequestData(), conditions);
		JSONArray jsonArr = new JSONArray();
		List<Category> list = result.getResultList();
		for(Category w :list){
			JSONObject CategoryJSON = new JSONObject();
			CategoryJSON.put("id", w.getId());
			CategoryJSON.put("name", w.getName());
			jsonArr.add(CategoryJSON);
		}
		json.put("rows", jsonArr);
		return json;
	} 
	
	
	
	@RequestMapping(value="/saveOrUpdate.do",method={RequestMethod.POST,RequestMethod.PUT})
	@ResponseBody
	public JSONObject saveCategory(Category Category){
		JSONObject json = new JSONObject();
		String message = "";
		boolean success = false;
		try {
			wxsToCategoryCaller.saveOrUpdateCategory(Category);
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
	
	
	@RequestMapping(value = "/delete.do",method={RequestMethod.POST,RequestMethod.DELETE})
	@ResponseBody
	public JSONObject deleteCategory(@RequestParam(required=true)Long id){
		JSONObject json = new JSONObject();
		String message = "";
		boolean success = false;
		try {
			wxsToCategoryCaller.deleteCategory(id);
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
}
