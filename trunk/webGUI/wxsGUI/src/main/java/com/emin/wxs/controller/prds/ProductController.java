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

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.emin.base.dao.PagedResult;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.base.service.Condition.ConditionOperator;
import com.emin.base.service.Condition.ConditionType; 
import com.emin.igwmp.prds.domain.Product;
import com.emin.igwmp.rp.facade.accepters.RestaurantReportAccepter;
import com.emin.wxs.controller.WxsBaseController;
import com.emin.wxs.facade.prds.WxsToProductCaller;
import com.emin.wxs.vo.PCProductVO;
import com.emin.wxs.vo.ProductVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/product")
public class ProductController extends WxsBaseController{

	private Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired 
	@Qualifier("wxsToProductCaller")
	private WxsToProductCaller wxsToProductCaller; 
	  
	
	@RequestMapping(value="/page.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JSONObject loadProducts(String name,Long categoryId){
		List<Condition> conditions = new ArrayList<Condition>();
		if(StringUtils.isNotBlank(name)){
			conditions.add(new Condition(Product.PROP_NAME, ConditionOperator.LIKE, ConditionType.CHARACTER, "%"+name+"%"));
		}
		if(null!=categoryId){
			conditions.add(new Condition(Product.PROP_CATEGORY_ID, ConditionOperator.EQ, ConditionType.OTHER, categoryId));
		}
		PagedResult<PCProductVO> result = wxsToProductCaller.loadPagedPCProductsByCondition(getPageRequestData(), conditions);
		JSONObject json = new JSONObject();
		json.put("total", result.getTotalCount());
		json.put("rows", result.getResultList()); 
		return json;
	} 
	
	@RequestMapping(value="/saveOrUpdate.do",method={RequestMethod.POST,RequestMethod.PUT})
	@ResponseBody 
	public JSONObject saveProduct(String dataStr){ 
		JSONObject json = new JSONObject();
		String message = "";
		boolean success = false;
		try {
			wxsToProductCaller.saveOrUpdateProduct(dataStr); 
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
	public JSONObject deleteProduct(@RequestParam(required=true)Long id){
		JSONObject json = new JSONObject();
		String message = "";
		boolean success = false;
		try {
			wxsToProductCaller.deleteProduct(id);
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
