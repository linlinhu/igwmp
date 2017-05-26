package com.emin.wxs.service;

import java.util.List;

import com.emin.base.service.UndeleteableService;
import com.emin.wxs.domain.WeixinEventReply;

public interface WeixinEventReplyService extends UndeleteableService<WeixinEventReply>{

	void saveEventReply(WeixinEventReply eventReply);

	List<WeixinEventReply> loadEventReplies(Long woaId);

	WeixinEventReply findByEventKey(String eventKey,Long woaId);

}
