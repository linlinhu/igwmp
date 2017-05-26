package com.emin.wxs.controller.admin;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emin.base.exception.EminException;
import com.emin.wxs.controller.WxsBaseController;
import com.emin.wxs.domain.WxArticle;
import com.emin.wxs.facade.WxArticleFacade;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("/article")
public class WxArticleController extends WxsBaseController{

	private Logger logger = LoggerFactory.getLogger(WxArticleController.class);
	
	@Autowired
	@Qualifier("wxArticleFacade")
	private WxArticleFacade wxArticleFacade;
	
	
	@RequestMapping(value="/saveOrUpdate.do",method={RequestMethod.POST,RequestMethod.PUT})
	@ResponseBody
	public JSONObject saveArticle(WxArticle article){
		JSONObject json = new JSONObject();
		StringBuilder message = new StringBuilder();
		boolean success = false;
		try {
			wxArticleFacade.saveOrUpdate(article);
			success = true;
		} catch (EminException e) {
			message.append(e.getLocalizedMessage());
			logger.error(e.getLocalizedMessage(), e);
		} catch (Exception e) {
			message.append("保存图文失败");
			logger.error(e.getMessage(), e);
		}
		json.put("success", success);
		json.put("message", message);
		return json;
	}
	
	@RequestMapping(value="/page.do",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public JSONObject loadArticals(Long woaId,String match){
		String[] mathces = null;
		if(StringUtils.isNotEmpty(match)){
			mathces = match.split(",");
		}
		JSONObject result = wxArticleFacade.loadArticlesByPage(getPageRequestData(), woaId, mathces);
		return result;
	}
	@RequestMapping(value="/delete.do",method={RequestMethod.POST,RequestMethod.DELETE})
	@ResponseBody
	public JSONObject deleteArticle(Long id){
		JSONObject json = new JSONObject();
		StringBuilder message = new StringBuilder();
		boolean success = false;
		try {
			wxArticleFacade.deleteArticle(id);
			success = true;
		} catch (EminException e) {
			message.append(e.getLocalizedMessage());
			logger.error(e.getLocalizedMessage(), e);
		} catch (Exception e) {
			message.append("删除图文失败");
			logger.error(e.getMessage(), e);
		}
		json.put("success", success);
		json.put("message", message.toString());
		return json;
	}
	
}
