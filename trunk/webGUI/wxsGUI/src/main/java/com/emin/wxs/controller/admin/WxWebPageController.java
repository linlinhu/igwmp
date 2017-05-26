package com.emin.wxs.controller.admin;

import java.util.Date;

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
import org.springframework.web.servlet.ModelAndView;

import com.emin.base.exception.EminException;
import com.emin.base.util.CommonsUtil;
import com.emin.wxs.controller.WxsBaseController;
import com.emin.wxs.domain.WeixinWebPage;
import com.emin.wxs.domain.WeixinWebPageCategory;
import com.emin.wxs.domain.WxOfficialAccount;
import com.emin.wxs.facade.WxOfficialAccountFacade;
import com.emin.wxs.facade.WxWebPageFacade;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Controller
@RequestMapping("/webPage")
public class WxWebPageController extends WxsBaseController{

	private Logger logger = LoggerFactory.getLogger(WxWebPageController.class);
	
	@Autowired
	@Qualifier("wxWebPageFacade")
	private WxWebPageFacade wxWebPageFacade;
	
	@Autowired
	@Qualifier("wxOfficialAccountFacade")
	private WxOfficialAccountFacade wxOfficialAccountFacade;
	/**
	 * 保存文章分类
	 * url:webPage/saveCategory.do
	 * @param webPageCategory
	 * @return
	 */
	@RequestMapping(value="/saveCatrgory.do",method={RequestMethod.POST})
	@ResponseBody
	public JSONObject saveOrUpdateCategory(WeixinWebPageCategory webPageCategory){
		JSONObject json = new JSONObject();
		StringBuilder sb = new StringBuilder();
		boolean success = false;
		try {
			wxWebPageFacade.saveCategory(webPageCategory);
			success = true;
		} catch (EminException e) {
			sb.append(e.getLocalizedMessage());
			logger.error(e.getLocalizedMessage(),e);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			sb.append("保存分类失败");
		}
		json.put("success", success);
		json.put("message", sb.toString());
		return json;
	}
	
	@RequestMapping(value = "/categoryTree.do",method = {RequestMethod.POST})
	@ResponseBody
	public JSONArray loadCategoryTree(@RequestParam(required=true)Long woaId,@RequestParam(required=true)Long pid){
		return wxWebPageFacade.loadCategoryTree(woaId, pid);
	}
	
	@RequestMapping(value = "/deleteCategory.do",method = {RequestMethod.POST})
	@ResponseBody
	public JSONObject deleteCategory(Long id){
		JSONObject json = new JSONObject();
		StringBuilder sb = new StringBuilder();
		boolean success = false;
		try {
			wxWebPageFacade.deleteCategory(id);
			success = true;
		} catch (EminException e) {
			sb.append(e.getLocalizedMessage());
			logger.error(e.getLocalizedMessage(),e);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			sb.append("删除分类失败");
		}
		json.put("success", success);
		json.put("message", sb.toString());
		return json;
	}
	
	@RequestMapping(value = "/saveWebPage.do",method={RequestMethod.POST})
	@ResponseBody
	public JSONObject saveWebPage(WeixinWebPage webPage){
		JSONObject json = new JSONObject();
		StringBuilder sb = new StringBuilder();
		boolean success = false;
		try {
			wxWebPageFacade.saveWebPage(webPage);
			success = true;
		} catch (EminException e) {
			sb.append(e.getLocalizedMessage());
			logger.error(e.getLocalizedMessage(),e);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			sb.append("保存文章");
		}
		json.put("success", success);
		json.put("message", sb.toString());
		return json;
	}
	
	@RequestMapping("/webPages.do")
	@ResponseBody
	public JSONObject loadWebPages(Long categoryId){
		return wxWebPageFacade.loadWebPage(getPageRequestData(), categoryId);
	}
	
	@RequestMapping(value = "/deleteWebPages.do",method={RequestMethod.POST,RequestMethod.DELETE})
	@ResponseBody
	public JSONObject deleteWebPages(@RequestParam(required=true)String ids){
		JSONObject json = new JSONObject();
		StringBuilder sb = new StringBuilder();
		boolean success = false;
		try {
			if(StringUtils.isNotBlank(ids)){
				wxWebPageFacade.deleteWebPages(CommonsUtil.stringToLongArr(ids));
			}
			success = true;
		} catch (EminException e) {
			sb.append(e.getLocalizedMessage());
			logger.error(e.getLocalizedMessage(),e);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			sb.append("删除文章失败");
		}
		json.put("success", success);
		json.put("message", sb.toString());
		return json;
	}
	@RequestMapping("/viewWebPage.do")
	public ModelAndView viewWebPage(Long id){	
		WeixinWebPage page = wxWebPageFacade.getWebPageById(id);
		WxOfficialAccount account = wxOfficialAccountFacade.findById(page.getCategory().getWoaId());
		ModelAndView mv = new ModelAndView("webpage");	
		mv.addObject("companyName", account.getCompanyName());
		mv.addObject("title", page.getTitle());
		mv.addObject("auther", page.getAuther());
		mv.addObject("comments", page.getComments());
		mv.addObject("info", page.getInfo());
		mv.addObject("companyCode", account.getCompanyCode());
		mv.addObject("date", CommonsUtil.dateToString(new Date(page.getCreateTime())));
		return mv;
	}
}
