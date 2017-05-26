package com.emin.wxs.service;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.UndeleteableService;
import com.emin.wxs.domain.WxArticle;

public interface WeixinArticalService extends UndeleteableService<WxArticle>{

	PagedResult<WxArticle> loadArticals(PageRequest request,Long woaId, String... match);

	void saveArtical(WxArticle article);

}
