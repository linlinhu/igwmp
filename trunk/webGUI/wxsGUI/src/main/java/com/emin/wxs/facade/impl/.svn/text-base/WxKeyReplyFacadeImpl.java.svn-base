package com.emin.wxs.facade.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.wxs.domain.WeixinKeyReply;
import com.emin.wxs.facade.WxKeyReplyFacade;
import com.emin.wxs.service.WeixinKeyReplyService;

import net.sf.json.JSONArray;
@Component("wxKeyReplyFacade")
public class WxKeyReplyFacadeImpl implements WxKeyReplyFacade{

	@Reference(version="0.0.1")
	private WeixinKeyReplyService weixinKeyReplyService;
	
	@Override
	public void saveOrUpdate(WeixinKeyReply keyReply){
		weixinKeyReplyService.saveKeyReply(keyReply);
	}
	@Override
	public JSONArray loadKeyReply(Long woaId){
		List<WeixinKeyReply> keyReply = weixinKeyReplyService.loadKeyReplies(woaId);
		return JSONArray.fromObject(keyReply);
	}
	@Override
	public void deleteKeyReply(Long id){
		weixinKeyReplyService.deleteById(id);
	}
}
