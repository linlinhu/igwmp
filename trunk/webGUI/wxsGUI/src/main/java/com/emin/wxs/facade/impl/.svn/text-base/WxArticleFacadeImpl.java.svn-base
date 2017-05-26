package com.emin.wxs.facade.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.wxs.domain.WxArticle;
import com.emin.wxs.facade.WxArticleFacade;
import com.emin.wxs.service.WeixinArticalService;

import net.sf.json.JSONObject;
@Component("wxArticleFacade")
public class WxArticleFacadeImpl implements WxArticleFacade{

	@Reference(version="0.0.1")
	private WeixinArticalService weixinArticalService;
	
	@Override
	public void saveOrUpdate(WxArticle article){
		weixinArticalService.saveArtical(article);
	}
	@Override
	public JSONObject loadArticlesByPage(PageRequest pageRequest,Long woaId,String... match){
		PagedResult<WxArticle> page = weixinArticalService.loadArticals(pageRequest, woaId, match);
		JSONObject json = new JSONObject();
		json.put("total", page.getTotalCount());
		json.put("rows", page.getResultList());
		return json;
	}
	@Override
	public void deleteArticle(Long id){
		weixinArticalService.deleteById(id);
	}
	@Override
	public List<WxArticle> findByIds(Long[] ids){
		return weixinArticalService.findByIds(ids);
	}
}
