package com.emin.wxs.controller.admin;

import java.util.List;

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
import com.emin.base.util.CommonsUtil;
import com.emin.wxs.controller.WxsBaseController;
import com.emin.wxs.domain.WeixinEventReply;
import com.emin.wxs.domain.WxArticle;
import com.emin.wxs.facade.WxArticleFacade;
import com.emin.wxs.facade.WxEventReplyFacade;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Controller
@RequestMapping("/eventReply")
public class WxEventReplyController extends WxsBaseController{

	private Logger logger = LoggerFactory.getLogger(WxEventReplyController.class);
	
	@Autowired
	@Qualifier("wxEventReplyFacade")
	private WxEventReplyFacade wxEventReplyFacade;
	
	@Autowired
	@Qualifier("wxArticleFacade")
	private WxArticleFacade wxArticleFacade;
	/**
	 * 保存事件回复
	 * @param eventReply
	 * @param articalIds 图文库ID 逗号分隔的字符串
	 * @return
	 */
	@RequestMapping(value="/saveOrUpdate.do",method={RequestMethod.POST,RequestMethod.PUT})
	@ResponseBody
	public JSONObject saveKeyReply(WeixinEventReply eventReply,String articalIds){
		JSONObject result = new JSONObject();
		StringBuilder message = new StringBuilder();
		boolean success = false;
		try {
			if(articalIds!=null&&!articalIds.equalsIgnoreCase("")){
				List<WxArticle> articles = wxArticleFacade.findByIds(CommonsUtil.stringToLongArr(articalIds));
				eventReply.setArticles(articles);
			}
			eventReply.setId(eventReply.getId()==0?null:eventReply.getId());
			wxEventReplyFacade.saveOrUpdate(eventReply);
			success = true;
		} catch (EminException e) {
			logger.warn(e.getLocalizedMessage(), e);
			message.append(e.getLocalizedMessage());
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
			message.append("事件回复添加失败");
		}
		result.put("success", success);
		result.put("message", message.toString());
		return result;
	}
	/**
	 * 加载事件回复列表
	 * @param keyReply
	 * @return
	 */
	@RequestMapping(value="/list.do",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public JSONArray loadKeyReplies(Long woaId){
		return wxEventReplyFacade.loadEventReply(woaId);
	}
	
	/**
	 * 删除事件回复
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete.do",method={RequestMethod.POST})
	@ResponseBody
	public JSONObject deleteEventReply(Long id){
		JSONObject result = new JSONObject();
		StringBuilder message = new StringBuilder();
		boolean success = false;
		try {			
			wxEventReplyFacade.deleteEventReply(id);
			success = true;
		} catch (EminException e) {
			logger.warn(e.getLocalizedMessage(), e);
			message.append(e.getLocalizedMessage());
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
			message.append("服务器繁忙！请稍后再试");
		}
		result.put("success", success);
		result.put("message", message.toString());
		return result;
	}
}
