package com.emin.wxs.service.impl;


import org.springframework.stereotype.Service;

import com.emin.base.service.UndeleteableServiceImpl;
import com.emin.wxs.domain.WeixinTempMsgSendDts;
import com.emin.wxs.service.WeixinTempMsgSendDtsService;

@Service("weixinTempMsgSendDtsService")
public class WeixinTempMsgSendDtsServiceImpl extends
		UndeleteableServiceImpl<WeixinTempMsgSendDts> implements
		WeixinTempMsgSendDtsService {
	@Override
	public void saveOrUpdateMsgDts(WeixinTempMsgSendDts msgDts) {
		this.saveOrUpdate(msgDts);
	}

}
