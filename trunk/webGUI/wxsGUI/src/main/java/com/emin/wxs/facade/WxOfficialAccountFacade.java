package com.emin.wxs.facade;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.emin.base.dao.PageRequest;
import com.emin.wxs.domain.WxOfficialAccount;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface WxOfficialAccountFacade {

	/**
	 * 加载可用的微信公众号列表
	 * @return
	 */
	public JSONArray loadValidWxOfficialAccounts();

	/**
	 * 保存微信公众号信息
	 * @return
	 */
	public void saveOrUpdate(WxOfficialAccount wxOfficialAccount);
	/**
	 * 分页加载可用的微信公众号列表
	 * @return
	 */
	public JSONObject loadValidWxOfficialAccountsByPage(PageRequest pageRequest);

	/**
	 * 删除微信公众号
	 * @param woaId
	 */
	public void deleteWOA(Long woaId);

	WxOfficialAccount findById(Long id);

	/**
	 * 
	* @Title: getWxAboutConf
	* @Description: 获取微信页面相关配置
	* @param request
	* @param woaId
	* @return {@link Map}
	* @throws Exception Map<String,Object>
	 */
	Map<String, Object> getWxAboutConf(HttpServletRequest request) throws Exception;

}
