package com.emin.wxs.facade;

import com.emin.base.dao.PageRequest;
import com.emin.wxs.domain.WeixinWebPage;
import com.emin.wxs.domain.WeixinWebPageCategory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface WxWebPageFacade {

	/**
	 * 加载分类树
	 * @param woaId
	 * @param pid
	 * @return
	 */
	public JSONArray loadCategoryTree(Long woaId, Long pid);

	/**
	 * 保存分类
	 * @param category
	 */
	public void saveCategory(WeixinWebPageCategory category);
	/**
	 * 删除分类
	 * @param id
	 */
	public void deleteCategory(Long id);

	/**
	 * 保存文章
	 * @param webPage
	 */
	public void saveWebPage(WeixinWebPage webPage);
	
	/**
	 * 分页加载分类下的文章列表
	 * @param pageRequest
	 * @param categoryId
	 * @return
	 */
	public JSONObject loadWebPage(PageRequest pageRequest, Long categoryId);

	/**
	 * 批量删除文章
	 * @param ids
	 */
	public void deleteWebPages(Long[] ids);

	WeixinWebPage getWebPageById(Long id);

}
