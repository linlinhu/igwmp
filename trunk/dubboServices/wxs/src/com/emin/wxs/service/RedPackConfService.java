package com.emin.wxs.service;

import com.emin.base.service.CRUDService;
import com.emin.wxs.domain.RedPackConf;

public interface RedPackConfService extends CRUDService<RedPackConf>{

	

	void updateConf(RedPackConf redPackConf);

	/**
	 * @param woaId
	 * @return
	 */
	RedPackConf loadRedPackConf(Long woaId);

}
