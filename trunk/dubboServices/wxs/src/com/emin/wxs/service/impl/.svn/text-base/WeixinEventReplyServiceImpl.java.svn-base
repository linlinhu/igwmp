package com.emin.wxs.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.emin.base.dao.PreFilters;
import com.emin.base.exception.BaseExCode;
import com.emin.base.exception.EminException;
import com.emin.base.service.UndeleteableServiceImpl;
import com.emin.wxs.domain.WeixinEventReply;
import com.emin.wxs.domain.WeixinKeyReply;
import com.emin.wxs.service.WeixinEventReplyService;
@Service("weixinEventReplyService")
public class WeixinEventReplyServiceImpl extends UndeleteableServiceImpl<WeixinEventReply> implements WeixinEventReplyService{
	@Override
	public void saveEventReply(WeixinEventReply eventReply){
		this.beforeSaveEventReply(eventReply);
		super.saveOrUpdate(eventReply);
	}
	private void beforeSaveEventReply(WeixinEventReply eventReply){
		if(eventReply.getEvent()==null
				||eventReply.getEventKey()==null
				||eventReply.getReplyType()==null
				||eventReply.getWoaId()==null
				||(eventReply.getReplyType().equals(WeixinEventReply.REPLY_TYPE_NEWS)&& (eventReply.getArticles()==null || eventReply.getArticles().size()==0))
				||(eventReply.getReplyType().equals(WeixinEventReply.REPLY_TYPE_TEXT)&& eventReply.getrContent()==null)){
			throw new EminException(BaseExCode.BIZ_ILLEGAL_PARAMETER);
		}
		if(eventReply.getId()==null){
			List<WeixinEventReply> replies = this.findByPreFilter(PreFilters.eq(WeixinKeyReply.PROP_WOAID,eventReply.getWoaId()),PreFilters.eq(WeixinEventReply.PROP_STATUS,WeixinEventReply.STATUS_VALID),PreFilters.eq(WeixinEventReply.PROP_EVENTKEY,eventReply.getEventKey()));
			if(replies!=null && replies.size()>0){
				//已存在
				throw new EminException("");
			}
		}
		
		eventReply.setStatus(WeixinEventReply.STATUS_VALID);
	}
	@Override
	public List<WeixinEventReply> loadEventReplies(Long woaId){
		
		return this.findByPreFilter(PreFilters.eq("woaId", woaId),getStatusFilter());
	}
	@Override
	public WeixinEventReply findByEventKey(String eventKey,Long woaId){
		return this.findUniqueByPreFilter(PreFilters.eq(WeixinEventReply.PROP_STATUS,WeixinEventReply.STATUS_VALID),PreFilters.eq(WeixinEventReply.PROP_EVENTKEY,eventKey),PreFilters.eq("woaId", woaId));
	}
}
