package com.emin.wxs.service;

import java.util.List;

import com.emin.base.service.UndeleteableService;
import com.emin.wxs.domain.WeixinTempMsgConf;

public interface WeixinTempMsgConfService extends UndeleteableService<WeixinTempMsgConf>{

	void saveTempMsgConf(WeixinTempMsgConf conf);

	List<WeixinTempMsgConf> loadConf(Long woaId);

}
