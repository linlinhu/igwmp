package com.emin.wxs.service;


import com.emin.base.service.UndeleteableService;
import com.emin.wxs.domain.WeixinTempMsgSendDts;

public interface WeixinTempMsgSendDtsService extends UndeleteableService<WeixinTempMsgSendDts>{
    void saveOrUpdateMsgDts(WeixinTempMsgSendDts dts);
}
