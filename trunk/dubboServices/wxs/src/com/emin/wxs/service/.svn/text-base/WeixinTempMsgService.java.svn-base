package com.emin.wxs.service;

import java.util.List;
import java.util.Map;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.base.service.UndeleteableService;
import com.emin.wxs.domain.WeixinTempMsg;

import net.sf.json.JSONArray;

/**
 * 模板消息
 * */
public interface WeixinTempMsgService extends UndeleteableService<WeixinTempMsg>{
	
	PagedResult<WeixinTempMsg> queryTempMsgsByCondition(PageRequest pRequest,List<Condition> conditions);
	
	void  saveOrUpdateMsg(WeixinTempMsg tempMsg,JSONArray jsonArray,String topColor);
    
	public String sendTemplateOne(Long woaId,String openid,String templateid,String url,String topcolor,Map<String,String> values);
}
