package com.emin.wxs.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.emin.base.dao.PreFilters;
import com.emin.base.exception.EminException;
import com.emin.base.service.UndeleteableServiceImpl;
import com.emin.wxs.domain.WeixinKeyReply;
import com.emin.wxs.exception.WXSExceptionCode;
import com.emin.wxs.service.WeixinKeyReplyService;
@Service("weixinKeyReplyService")
public class WeixinKeyReplyServiceImpl extends UndeleteableServiceImpl<WeixinKeyReply> implements WeixinKeyReplyService{
	@Override
	public void saveKeyReply(WeixinKeyReply keyReply){
		this.beforeSaveKeyReply(keyReply);
		super.saveOrUpdate(keyReply);
	}
	private void beforeSaveKeyReply(WeixinKeyReply keyReply){
		if(keyReply.getContent()==null
				||keyReply.getReplyType()==null
				||keyReply.getRemark()==null
				||keyReply.getWoaId()==null
				||(keyReply.getReplyType().equals(WeixinKeyReply.REPLY_TYPE_NEWS)&& (keyReply.getArticles()==null || keyReply.getArticles().size()==0))
				||(keyReply.getReplyType().equals(WeixinKeyReply.REPLY_TYPE_TEXT)&& keyReply.getrContent()==null)){
			throw new EminException(WXSExceptionCode.WOA_PARAMTERS_INVALID);
		}
		if(keyReply.getId()!=null && keyReply.getId().longValue()==0l){
			keyReply.setId(null);
		}
		if(keyReply.getId()==null){
			List<WeixinKeyReply> replies = this.findByPreFilter(PreFilters.eq(WeixinKeyReply.PROP_WOAID,keyReply.getWoaId()),PreFilters.eq(WeixinKeyReply.PROP_STATUS,WeixinKeyReply.STATUS_VALID),PreFilters.eq(WeixinKeyReply.PROP_CONTENT,keyReply.getContent()));
			if(replies!=null && replies.size()>0){
				//已存在
				throw new EminException(WXSExceptionCode.WOA_KEYREPLY_EXISTS);
			}
		}
		
		keyReply.setStatus(WeixinKeyReply.STATUS_VALID);
	}
	@Override
	public List<WeixinKeyReply> loadKeyReplies(Long woaId){
		return this.findByPreFilter(PreFilters.eq("woaId", woaId),getStatusFilter());
	}
	@Override
	public WeixinKeyReply findByContent(String content,Long woaId){
		return this.findUniqueByPreFilter(PreFilters.eq("woaId", woaId),PreFilters.eq(WeixinKeyReply.PROP_STATUS,WeixinKeyReply.STATUS_VALID),PreFilters.eq(WeixinKeyReply.PROP_CONTENT,content));
	}
	@Override
	public List<WeixinKeyReply> loadPreferences(Long woaId){
		return this.findByPreFilter(PreFilters.eq(WeixinKeyReply.PROP_STATUS,WeixinKeyReply.STATUS_VALID),PreFilters.eq(WeixinKeyReply.PROP_PREFERENCES,true),PreFilters.eq("woaId", woaId));
	}
}
