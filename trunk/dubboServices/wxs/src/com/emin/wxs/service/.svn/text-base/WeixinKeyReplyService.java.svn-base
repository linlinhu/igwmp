package com.emin.wxs.service;

import java.util.List;

import com.emin.base.service.UndeleteableService;
import com.emin.wxs.domain.WeixinKeyReply;

public interface WeixinKeyReplyService extends UndeleteableService<WeixinKeyReply>{

	void saveKeyReply(WeixinKeyReply keyReply);

	List<WeixinKeyReply> loadKeyReplies(Long woaId);

	WeixinKeyReply findByContent(String content,Long woaId);

	List<WeixinKeyReply> loadPreferences(Long woaId);

}
