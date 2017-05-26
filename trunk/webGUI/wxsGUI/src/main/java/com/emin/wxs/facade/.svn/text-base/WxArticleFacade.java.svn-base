package com.emin.wxs.facade;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.wxs.domain.WxArticle;

import net.sf.json.JSONObject;

public interface WxArticleFacade {

	/**
	 * 保存图文
	 * @param article
	 */
	public void saveOrUpdate(WxArticle article);

	/**
	 * 分页加载图文
	 * @param pageRequest
	 * @param woaId
	 * @param match
	 * @return
	 */
	public JSONObject loadArticlesByPage(PageRequest pageRequest, Long woaId, String... match);
	
	/**
	 * 删除图文
	 * @param id
	 */
	void deleteArticle(Long id);

	List<WxArticle> findByIds(Long[] ids);

}
