/**
 * 
 */
package com.emin.wxs.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emin.base.dao.PreFilter;
import com.emin.base.dao.PreFilters;
import com.emin.base.dao.SortKey;
import com.emin.base.exception.EminException;
import com.emin.base.service.UndeleteableServiceImpl;
import com.emin.wxs.domain.WxMenu;
import com.emin.wxs.domain.WxOfficialAccount;
import com.emin.wxs.exception.WXSExceptionCode;
import com.emin.wxs.service.WeixinToolService;
import com.emin.wxs.service.WxMenuService;
import com.emin.wxs.util.HttpTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author jim
 *
 */
@Service("wxMenuService")
public class WxMenuServiceImpl extends UndeleteableServiceImpl<WxMenu> implements WxMenuService{
	private static final int PMENU_MAX_LENGTH = 3;
	private static final int CMENU_MAX_LENGTH = 5;
	private static final int PMENU_STRING_MAX_LENGTH = 16;
	private static final int CMENU_STRING_MAX_LENGTH = 40;
	
	private static final String MENU_BUTTON = "button";
	private static final String MENU_BUTTON_SUB = "sub_button";
	
	private static final String MENU_STR_NAME = "name";
	private static final String MENU_STR_TYPE = "type";
	private static final String MENU_STR_URL = "url";
	private static final String MENU_STR_KEY = "key";
	@Autowired
	@Qualifier("weixinToolService")
	private WeixinToolService weixinToolService;
	@Override
	public List<WxMenu> loadMenus(Long woaId,Long pid){
		PreFilter pidFilter = null;
		if((pid!=null && pid==0) || pid==null){
			pidFilter = PreFilters.isNull(WxMenu.PROP_PID);
		}else{
			pidFilter = PreFilters.eq(WxMenu.PROP_PID, pid);
		}
		PreFilter woaFilter = PreFilters.eq(WxMenu.PROP_WOAID, woaId);
		return this.findByPreFilter(new SortKey[]{SortKey.asc(WxMenu.PROP_SORT)},woaFilter,pidFilter,getStatusFilter());
	}
	@Transactional
	@Override
	public void saveOrUpdateMenu(WxMenu menu){
		this.beforeSaveOrUpdate(menu);
		super.saveOrUpdate(menu);
		if(menu.getPid()!=null){
			WxMenu parent = this.findById(menu.getPid());
			parent.setActivekey(null);
			parent.setActivetype(null);
			super.update(menu);
		}
	}
	private void beforeSaveOrUpdate(WxMenu menu){
		if((menu.getActivekey()==null  && menu.getId()==null && menu.getBtntype()==1)
				||(menu.getActivetype()==null && menu.getId()==null && menu.getBtntype()==1)
				||menu.getName()==null
				||menu.getSort()==null){
			throw new EminException(WXSExceptionCode.WOA_PARAMTERS_INVALID);
		}
		menu.setAudit(false);
		if(menu.getPid()==0){
			menu.setPid(null);
		}
		if(menu.getPid()==null){
			menu.setBtntype(WxMenu.BTNTYPE_PARENT);
		}else{
			menu.setBtntype(WxMenu.BTNTYPE_SUB);
		}
		if(menu.getId()==0){
			menu.setId(null);
		}
	}
	@Override
	public List<WxMenu> loadClickMenu(Long woaId){
		return this.findByPreFilter(PreFilters.eq(WxMenu.PROP_WOAID, woaId),PreFilters.eq(WxMenu.PROP_ACTIVETYPE, WxMenu.ACTIVETYPE_CLICK),PreFilters.eq(WxMenu.PROP_STATUS, WxMenu.STATUS_VALID));
	}
	@Override
	public boolean auditMenu(Long woaId) throws UnsupportedEncodingException {
		List<WxMenu> menus = this.findByPreFilter(new SortKey[]{SortKey.asc(WxMenu.PROP_SORT)}, getStatusFilter(),PreFilters.eq(WxMenu.PROP_WOAID, woaId));
		if(menus.size()>0){
			List<WxMenu> pmenus = new ArrayList<WxMenu>();
			List<WxMenu> cmenus = new ArrayList<WxMenu>();
			for(WxMenu m:menus){
				if(m.getBtntype()==WxMenu.BTNTYPE_PARENT){
					pmenus.add(m);
				}else{
					cmenus.add(m);
				}
			}
			if(pmenus.size()>PMENU_MAX_LENGTH||pmenus.size()==0){
				//TODO 一级菜单数组，个数不能超过3个
				logger.info("=======一级菜单数："+pmenus.size());
				return false;
			}else{
				//生成菜单链接的随机数，避免缓存
//				long random = Calendar.getInstance().getTimeInMillis();
				JSONObject wmenus = new JSONObject();
				wmenus.put(MENU_BUTTON, new JSONArray());
				for(WxMenu m:pmenus){
					if(m.getName().getBytes("UTF-8").length>PMENU_STRING_MAX_LENGTH){
						//TODO 一级菜单标题，不超过16个字节
						logger.info("=======一级菜单("+m.getName()+")标题长度："+m.getName().getBytes("UTF-8").length);
						return false;
					}else{
						JSONArray wpms = wmenus.getJSONArray(MENU_BUTTON);
						JSONObject wpm = new JSONObject();
						wpm.put(MENU_BUTTON_SUB, new JSONArray());
						wpm.put(MENU_STR_NAME, m.getName());
						for(WxMenu mc:cmenus){
							logger.info(mc.getPid()+"=========="+m.getId());
							logger.info(mc.getPid().equals(m.getId()));
							if(mc.getPid().equals(m.getId())){
								if(mc.getName().getBytes().length>CMENU_STRING_MAX_LENGTH){
									//TODO 二级菜单标题，不超过40个字节
									logger.info("=======二级菜单("+mc.getName()+")标题长度："+mc.getName().getBytes().length);
									return false;
								}else{
									JSONArray wcms = wpm.getJSONArray(MENU_BUTTON_SUB);
									JSONObject wcm = new JSONObject();
									wcm.put(MENU_STR_NAME, mc.getName());
									wcm.put(MENU_STR_TYPE, mc.getActivetype());
									if(WxMenu.ACTIVETYPE_VIEW.equals(mc.getActivetype())){//一般网页
										if(!mc.getActivekey().startsWith("http://")&&!mc.getActivekey().startsWith("https://")){
											//TODO 链接菜单要以http://或https://开头
											logger.info("=======链接菜单("+mc.getName()+")请以http://或https://开头");
											return false;
										}
										wcm.put(MENU_STR_URL, mc.getActivekey());
									}else if(WxMenu.ACTIVETYPE_SNSAPIBASE.equals(mc.getActivetype())){//基本授权网页
										wcm.put(MENU_STR_TYPE, WxMenu.ACTIVETYPE_VIEW);
										if(!mc.getActivekey().startsWith("http://")&&!mc.getActivekey().startsWith("https://")){
											//TODO 链接菜单要以http://或https://开头
											logger.info("=======链接菜单("+mc.getName()+")请以http://或https://开头");
											return false;
										}
										wcm.put(MENU_STR_URL, 
												WxOfficialAccount.URL_OAUTH2_CODE
													.replace("APPID", weixinToolService.getAppid(woaId))
													.replace("REDIRECT_URI", URLEncoder.encode(mc.getActivekey(),"UTF-8"))
													.replace("SCOPE", WxMenu.ACTIVETYPE_SNSAPIBASE)
													.replace("STATE", woaId.toString())
												);
									}else if(WxMenu.ACTIVETYPE_SNSAPIUSERINFO.equals(mc.getActivetype())){//用户信息授权网页
										wcm.put(MENU_STR_TYPE, WxMenu.ACTIVETYPE_VIEW);
										if(!mc.getActivekey().startsWith("http://")&&!mc.getActivekey().startsWith("https://")){
											//TODO 链接菜单要以http://或https://开头
											logger.info("=======链接菜单("+mc.getName()+")请以http://或https://开头");
											return false;
										}
										wcm.put(MENU_STR_URL, 
												WxOfficialAccount.URL_OAUTH2_CODE
													.replace("APPID", weixinToolService.getAppid(woaId))
													.replace("REDIRECT_URI", URLEncoder.encode(mc.getActivekey(),"UTF-8"))
													.replace("SCOPE", WxMenu.ACTIVETYPE_SNSAPIUSERINFO)
													.replace("STATE", woaId.toString())
												);
									}else{
										wcm.put(MENU_STR_KEY, mc.getActivekey());
									}
									wcms.add(wcm);
								}
							}
						}
						if(wpm.getJSONArray(MENU_BUTTON_SUB).size()==0){
							if(m.getActivetype()==null){
								//TODO 没有子菜单的主菜单，应该有响应动作
								logger.info("=======一级菜单("+m.getName()+")没有子菜单，也没有动作");
								return false;
							}else{
								wpm.put(MENU_STR_TYPE, m.getActivetype());
								if(WxMenu.ACTIVETYPE_VIEW.equals(m.getActivetype())){
									if(!m.getActivekey().startsWith("http://")&&!m.getActivekey().startsWith("https://")){
										//TODO 链接菜单要以http://或https://开头
										logger.info("=======链接菜单("+m.getName()+")请以http://或https://开头");
										return false;
									}
									wpm.put(MENU_STR_URL, m.getActivekey());
								}else if(WxMenu.ACTIVETYPE_SNSAPIBASE.equals(m.getActivetype())){//基本授权网页
									wpm.put(MENU_STR_TYPE, WxMenu.ACTIVETYPE_VIEW);
									if(!m.getActivekey().startsWith("http://")&&!m.getActivekey().startsWith("https://")){
										//TODO 链接菜单要以http://或https://开头
										logger.info("=======链接菜单("+m.getName()+")请以http://或https://开头");
										return false;
									}
									wpm.put(MENU_STR_URL, 
											WxOfficialAccount.URL_OAUTH2_CODE
												.replace("APPID", weixinToolService.getAppid(woaId))
												.replace("REDIRECT_URI", URLEncoder.encode(m.getActivekey(),"UTF-8"))
												.replace("SCOPE", WxMenu.ACTIVETYPE_SNSAPIBASE)
												.replace("STATE", woaId.toString())
											);
								}else if(WxMenu.ACTIVETYPE_SNSAPIUSERINFO.equals(m.getActivetype())){//用户信息授权网页
									wpm.put(MENU_STR_TYPE, WxMenu.ACTIVETYPE_VIEW);
									if(!m.getActivekey().startsWith("http://")&&!m.getActivekey().startsWith("https://")){
										//TODO 链接菜单要以http://或https://开头
										logger.info("=======链接菜单("+m.getName()+")请以http://或https://开头");
										return false;
									}
									wpm.put(MENU_STR_URL, 
											WxOfficialAccount.URL_OAUTH2_CODE
												.replace("APPID", weixinToolService.getAppid(woaId))
												.replace("REDIRECT_URI", URLEncoder.encode(m.getActivekey(),"UTF-8"))
												.replace("SCOPE", WxMenu.ACTIVETYPE_SNSAPIUSERINFO)
												.replace("STATE", woaId.toString())
											);
								}else{
									wpm.put(MENU_STR_KEY, m.getActivekey());
								}
							}
						}else if(wpm.getJSONArray(MENU_BUTTON_SUB).size()>CMENU_MAX_LENGTH){
							//TODO 二级菜单数组，个数不能超过5个
							logger.info("=======二级菜单数："+wpm.getJSONArray(MENU_BUTTON_SUB).size());
							return false;
						}
						wpms.add(wpm);
					}
				}
				logger.debug(wmenus.toString());
				String rtn = HttpTool.httpsPost(WxOfficialAccount.URL_MENU_ADD.replace("ACCESS_TOKEN", weixinToolService.loadAccessToken(woaId)), wmenus.toString());
				JSONObject rtno = JSONObject.fromObject(rtn);
				if(rtno.getInt("errcode")==0){
					this.getEntityDao().updateByHQL("UPDATE com.emin.wxs.domain.WxMenu SET audit=true", new Object[]{});
					//TODO 菜单生成成功
					logger.info("=======菜单生成成功");
					return true;
				}else{
					//TODO 菜单生成失败
					logger.info("=======菜单生成失败："+rtno.toString());
					return false;
				}
			}
		}
		return false;
	}
}
