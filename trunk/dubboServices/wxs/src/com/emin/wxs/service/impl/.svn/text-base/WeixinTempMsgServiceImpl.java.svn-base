package com.emin.wxs.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.dao.PreFilter;
import com.emin.base.dao.PreFilters;
import com.emin.base.service.Condition;
import com.emin.base.service.Conditions;
import com.emin.base.service.UndeleteableServiceImpl;
import com.emin.wxs.domain.Fans;
import com.emin.wxs.domain.FansItem;
import com.emin.wxs.domain.WeixinTempMsg;
import com.emin.wxs.domain.WeixinTempMsgSendDts;
import com.emin.wxs.service.WeixinTempMsgSendDtsService;
import com.emin.wxs.service.WeixinTempMsgService;
import com.emin.wxs.service.WeixinToolService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service("weixinTempMsgService")
public class WeixinTempMsgServiceImpl extends UndeleteableServiceImpl<WeixinTempMsg> implements WeixinTempMsgService{

	@Autowired
	@Qualifier("weixinToolService")
	private WeixinToolService weixinToolService;
	@Autowired
	@Qualifier("weixinTempMsgSendDtsService")
	private WeixinTempMsgSendDtsService tempMsgDtsService;
	@Override
	public String sendTemplateOne(Long woaId,String openid,String templateid,String url,String topcolor, Map<String,String> values) {
		JSONObject jo = new JSONObject();
		Set<String> keys = values.keySet();
		for(String key:keys){
			JSONObject jo1 = new JSONObject();
			jo1.put("value", values.get(key));
			jo1.put("color", "#173177");
			jo.put(key, jo1);
		}
		return weixinToolService.sendTmpmsgOne(woaId,openid, templateid, url, topcolor, jo.toString());
	}
	@Override
	public PagedResult<WeixinTempMsg> queryTempMsgsByCondition(PageRequest pRequest,
			List<Condition> conditions) {
		List<PreFilter> filters = new  ArrayList<PreFilter>();
		if(conditions != null && conditions.size() > 0){
			for(Condition con:conditions){
				filters.add(Conditions.convertToPropertyFilter(con));
			}
		}
		
		filters.add(PreFilters.eq(WeixinTempMsg.PROP_STATUS, WeixinTempMsg.STATUS_VALID));
		PreFilter[] preFilters = new PreFilter[filters.size()];
		preFilters = filters.toArray(preFilters);
		
		return this.getPage(pRequest, preFilters);
	}
	@Transactional
	@Override
	public void saveOrUpdateMsg(WeixinTempMsg tempMsg,JSONArray jsonArray,String topColor) {
		this.saveOrUpdate(tempMsg);
		//保存接收人详细
		this.saveDts(tempMsg,jsonArray,topColor);
	}
	private void saveDts(WeixinTempMsg tempMsg,JSONArray jsonArray,String topcolor) {
		Collection<?> list = JSONArray.toCollection(jsonArray,Fans.class);
		Iterator<?> iterator = list.iterator();
		//遍历
		while(iterator.hasNext()){
			FansItem user = (FansItem)iterator.next();
			
			JSONObject jsonResult = new JSONObject();
			//发送通知 
			if(tempMsg!=null){
				jsonResult = JSONObject.fromObject(weixinToolService.sendTmpmsgOne(tempMsg.getWeixinTempMsgConf().getWoaId(),user.getOpenid(),
						tempMsg.getTemplateid(),tempMsg.getUrl(), topcolor,tempMsg.getData()));
			}
			//依次获取返回结果：1.发送状态；2.提示消息；3.发送的msgid
			String flag = jsonResult.get("errcode") == null?"":jsonResult.get("errcode").toString();
			String stateMsg = jsonResult.get("errmsg") == null?"":jsonResult.get("errmsg").toString();
			Long msgid = jsonResult.get("msgid") != null ? Long.parseLong(jsonResult.get(
					"msgid").toString()) : null;

			WeixinTempMsgSendDts dts = new WeixinTempMsgSendDts();
			//设值到模板发送-状态
			if(flag.equals("0")){
				dts.setState(WeixinTempMsgSendDts.STATE_FINISH_SUCCESS);
			}else if(flag.equals("1")){
				dts.setState(WeixinTempMsgSendDts.STATE_SEND_ERR);
			}else if(flag.equals("2")){
				dts.setState(WeixinTempMsgSendDts.STATE_FINISH_SUCCESS);
			}else if(flag.equals("3")){
				dts.setState(WeixinTempMsgSendDts.STATE_FINISH_BLOCK);
			}else if(flag.equals("4")){
				dts.setState(WeixinTempMsgSendDts.STATE_FINISH_FAILED);
			}
			dts.setTempmsgid(tempMsg.getId());
			//设值到模板发送-msgid
			dts.setMsgid(msgid);
			//设值到模板发送-提示消息
			dts.setStatemsg(stateMsg);
			//设值到模板发送-接收人id
			dts.setUserid(user.getId());
			dts.setStatus(WeixinTempMsgSendDts.STATUS_VALID);
			tempMsgDtsService.saveOrUpdateMsgDts(dts);
		}
	}
}
