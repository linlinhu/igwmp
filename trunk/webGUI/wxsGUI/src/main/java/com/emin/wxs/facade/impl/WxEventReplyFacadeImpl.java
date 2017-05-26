package com.emin.wxs.facade.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.wxs.domain.WeixinEventReply;
import com.emin.wxs.facade.WxEventReplyFacade;
import com.emin.wxs.service.WeixinEventReplyService;

import net.sf.json.JSONArray;
@Component("wxEventReplyFacade")
public class WxEventReplyFacadeImpl implements WxEventReplyFacade{

	@Reference(version="0.0.1")
	private WeixinEventReplyService weixinEventReplyService;
	@Override
	public void saveOrUpdate(WeixinEventReply eventReply){
		weixinEventReplyService.saveEventReply(eventReply);
	}
	@Override
	public JSONArray loadEventReply(Long woaId){
		List<WeixinEventReply> reply = weixinEventReplyService.loadEventReplies(woaId);
		return JSONArray.fromObject(reply);
	}
	@Override
	public void deleteEventReply(Long id){
		weixinEventReplyService.deleteById(id);
	}
}
