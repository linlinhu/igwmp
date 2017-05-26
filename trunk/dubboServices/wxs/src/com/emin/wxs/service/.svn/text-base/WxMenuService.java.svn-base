/**
 * 
 */
package com.emin.wxs.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.emin.base.service.UndeleteableService;
import com.emin.wxs.domain.WxMenu;

/**
 * @author jim
 *
 */
public interface WxMenuService extends UndeleteableService<WxMenu>{

	/**查询公众号的自定义菜单
	 * @param woaId 公众号ID
	 * @param pid
	 * @return
	 */
	List<WxMenu> loadMenus(Long woaId, Long pid);

	/**
	 * @param menu
	 */
	void saveOrUpdateMenu(WxMenu menu);

	/**
	 * @return
	 */
	List<WxMenu> loadClickMenu(Long woaId);


	/**
	 * @param woaId
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	boolean auditMenu(Long woaId) throws UnsupportedEncodingException;

	

}
