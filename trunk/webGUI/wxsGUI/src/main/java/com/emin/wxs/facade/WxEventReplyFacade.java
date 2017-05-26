package com.emin.wxs.facade;

import com.emin.wxs.domain.WeixinEventReply;

import net.sf.json.JSONArray;

public interface WxEventReplyFacade {
	/**
	 * 保存事件回复
	 * @param id
	 */
	void saveOrUpdate(WeixinEventReply eventReply);
	/**
	 * 加载事件回复
	 * @param id
	 */
	JSONArray loadEventReply(Long woaId);
	/**
	 * 删除事件回复
	 * @param id
	 */
	public void deleteEventReply(Long id);

}
