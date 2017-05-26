package com.emin.wxs.service;

import java.util.List;

import com.emin.base.service.UndeleteableService;
import com.emin.wxs.domain.WeixinMsgReply;

public interface WeixinMsgReplyService extends UndeleteableService<WeixinMsgReply>{

	void saveMsgReply(WeixinMsgReply msgReply);

	List<WeixinMsgReply> loadMsgReplies(Long woaId);

	WeixinMsgReply findByContent(String content,Long woaId);

}
