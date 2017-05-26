package com.emin.wxs.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.emin.base.dao.PreFilters;
import com.emin.base.exception.BaseExCode;
import com.emin.base.exception.EminException;
import com.emin.base.service.UndeleteableServiceImpl;
import com.emin.wxs.domain.WeixinMsgReply;
import com.emin.wxs.service.WeixinMsgReplyService;
@Service("weixinMsgReplyService")
public class WeixinMsgReplyServiceImpl extends UndeleteableServiceImpl<WeixinMsgReply> implements WeixinMsgReplyService{
	@Override
	public void saveMsgReply(WeixinMsgReply MsgReply){
		this.beforeSaveMsgReply(MsgReply);
		super.saveOrUpdate(MsgReply);
	}
	private void beforeSaveMsgReply(WeixinMsgReply msgReply){
		if(msgReply.getMsgtype()==null
				||msgReply.getContent()==null
				||msgReply.getReplyType()==null
				||(msgReply.getReplyType().equals(WeixinMsgReply.REPLY_TYPE_NEWS)&& (msgReply.getArticles()==null || msgReply.getArticles().size()==0))
				||(msgReply.getReplyType().equals(WeixinMsgReply.REPLY_TYPE_TEXT)&& msgReply.getrContent()==null)){
			throw new EminException(BaseExCode.BIZ_ILLEGAL_PARAMETER);
		}
		if(msgReply.getId()==null){
			List<WeixinMsgReply> replies = this.findByPreFilter(
					PreFilters.eq(WeixinMsgReply.PROP_STATUS,WeixinMsgReply.STATUS_VALID),
					PreFilters.eq(WeixinMsgReply.REPLY_TYPE_CONTENT,msgReply.getContent()));
			if(replies!=null && replies.size()>0){
				//已存在
				throw new EminException("");
			}
		}
		
		msgReply.setStatus(WeixinMsgReply.STATUS_VALID);
	}
	@Override
	public List<WeixinMsgReply> loadMsgReplies(Long woaId){
		return this.findByPreFilter(PreFilters.eq("woaId", woaId),getStatusFilter());
	}
	@Override
	public WeixinMsgReply findByContent(String content,Long woaId){
		return this.findUniqueByPreFilter(
				PreFilters.eq(WeixinMsgReply.PROP_STATUS,WeixinMsgReply.STATUS_VALID),
				PreFilters.eq(WeixinMsgReply.REPLY_TYPE_CONTENT,content),
				PreFilters.eq("woaId", woaId));
	}
}
