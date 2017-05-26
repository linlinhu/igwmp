package com.emin.wxs.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.dao.PreFilter;
import com.emin.base.dao.PreFilters;
import com.emin.base.dao.SortKey;
import com.emin.base.exception.EminException;
import com.emin.base.log.BussLog;
import com.emin.base.service.Condition;
import com.emin.base.service.Conditions;
import com.emin.base.service.UndeleteableServiceImpl;
import com.emin.base.util.CommonsUtil;
import com.emin.wxs.service.WeixinToolService;
import com.emin.wxs.util.DateUtil;
import com.emin.wxs.util.HttpTool;
import com.emin.wxs.domain.Fans;
import com.emin.wxs.domain.FansItem;
import com.emin.wxs.domain.NextOpenId;
import com.emin.wxs.domain.WxOfficialAccount;
import com.emin.wxs.exception.WXSExceptionCode;
import com.emin.wxs.service.FansService;

@Service("fansService")
public class FansServiceImpl extends UndeleteableServiceImpl<Fans> implements
		FansService {
	private static final Logger logger = Logger.getLogger(FansServiceImpl.class);
	@Autowired
	@Qualifier("weixinToolService")
	private WeixinToolService weixinToolService;

	@Override
	public void saveOrUpdateFans(Fans Fans) {
		this.saveOrUpdate(Fans);
	}

	@Override
	public PagedResult<Fans> queryUserByCondition(PageRequest pRequest,
			List<Condition> conditions) {
		List<PreFilter> filters = new ArrayList<PreFilter>();
		if (conditions != null && conditions.size() > 0) {
			for (Condition con : conditions) {
				filters.add(Conditions.convertToPropertyFilter(con));
			}
		}
		pRequest.setOrderBy(new SortKey[] { SortKey.desc(Fans.PROP_SUBSCRIBETIME) });
		filters.add(getStatusFilter());
		PreFilter[] preFilters = new PreFilter[filters.size()];
		preFilters = filters.toArray(preFilters);
		return this.getPage(pRequest, preFilters);
	}

	@Override
	@BussLog(description="同步公众号粉丝信息")
	public void saveAllFans(Long woaId) {
		NextOpenId nextOpenId = this.getEntityDao().findUniqueByPreFilter(NextOpenId.class, PreFilters.eq(NextOpenId.PROP_WOA_ID, woaId));
		boolean result = true;
		String openId = "";
		String nextOpen= "";
		if(nextOpenId!=null && StringUtils.isNoneBlank(nextOpenId.getNextOpenId())){
			openId = nextOpenId.getNextOpenId();
		}
		String url = WxOfficialAccount.URL_ALLUSERS + openId;
		String accessToken = this.weixinToolService.loadAccessToken(woaId);
		while(result){
			
			boolean rt = false;
			
			String resutlString = HttpTool.httpsGet(url.replace("ACCESS_TOKEN",
					accessToken));
			if (CommonsUtil.isNotEmpty(resutlString)) {
				JSONObject json = JSONObject.fromObject(resutlString);
			
				if (json.get("errcode") == null && json.getInt("count")>0) {
					Object obj = json.get("data");
					JSONObject dataJson = JSONObject.fromObject(obj);
					
					if (null != dataJson) {
						JSONArray list = JSONArray.fromObject(dataJson.get("openid"));
						if (null != list && list.size() > 0) {
							logger.info("粉丝数:"+list.size());
							// 获取数据库的所有微粉丝
							List<FansItem> fansList = this.getEntityDao().findByPreFilter(FansItem.class, PreFilters.eq(FansItem.PROP_WOAID, woaId));
							if (fansList.size() > 0) {
								List<Object> newList = new ArrayList<Object>();
								for (Object ob : list) {
									for (FansItem fans : fansList) {
										if (String.valueOf(ob).equals(fans.getOpenid())) {
											newList.add(ob);
											break;
										}
									}
								}
								if (newList.size() > 0) {
									list.removeAll(newList);
								}
							}
						}

						for (Object object : list) {
							String info = weixinToolService.loadUesrInfo(woaId,String.valueOf(object));
							
							
							if (CommonsUtil.isNotEmpty(info)) {
								JSONObject jsonObject = JSONObject.fromObject(info);
								String unionId = jsonObject.getString("unionid");
								Fans f = this.findUniqueByPreFilter(PreFilters.eq(Fans.PROP_UNIONID, unionId));
								if(f==null){
									if ("1".equals(jsonObject.getString("subscribe"))) {
										Fans fans = new Fans();
										fans.setNickname(jsonObject.getString("nickname"));
										fans.setUnionId(unionId);
										fans.setSex(jsonObject.getInt("sex"));
										fans.setCountry(jsonObject.getString("country"));
										fans.setCity(jsonObject.getString("city"));
										fans.setProvince(jsonObject.getString("province"));
										fans.setLanguage(jsonObject.getString("language"));
										fans.setHeadimgurl(jsonObject.getString("headimgurl"));
										fans.setSubscribeTime(jsonObject.getLong("subscribe_time")*1000);
										fans.setRemark("0");// 判断花舞人间是否参加了活动 0 是没有 1是一次
															// ，依次累加
										fans.setSubscribe(true);
										
										try {
											saveOrUpdateFans(fans);
										} catch (Exception e) {
											logger.error(e);
										}
										FansItem item = new FansItem();
										item.setFansId(fans.getId());
										item.setOpenid(object.toString());
										item.setWoaId(woaId);
										item.setSubscribe(true);
										item.setSubscribeTime(jsonObject.getLong("subscribe_time")*1000);
										this.getEntityDao().save(item);
									}
								}else{
									FansItem item = this.loadByOpenId(String.valueOf(object), woaId);
									if(item==null){
										item = new FansItem();
										item.setFansId(f.getId());
										item.setOpenid(object.toString());
										item.setWoaId(woaId);
										item.setSubscribe(true);
										item.setSubscribeTime(jsonObject.getLong("subscribe_time")*1000);
										this.getEntityDao().save(item);
									}
								}
							}
						}
					}
					String nxtOpenId = json.getString("next_openid");
					logger.info("nextOpenId:"+nxtOpenId);
					if (StringUtils.isNotEmpty(nxtOpenId)) {
						url = WxOfficialAccount.URL_ALLUSERS + nxtOpenId;
						nextOpen = nxtOpenId;
						rt = true;
					}
				}
			}
			result = rt;
		}
		logger.info("同步完毕!");
		if (StringUtils.isNotEmpty(nextOpen)) {
			if(nextOpenId==null){
				nextOpenId = new NextOpenId();
				nextOpenId.setWoaId(woaId);
			}
			nextOpenId.setNextOpenId(nextOpen);
			this.getEntityDao().saveOrUpdate(nextOpenId);
			logger.info("更新最后同步记录");
		}
			
		
	}

	@Override
	public void saveInitFans(Long woaId) {
		// 查询数据里面的最后一条数据
		String sql = "select nexopenid from wxs.nexopenid where woaid="+woaId;
		List<Object> openIdList = this.getEntityDao().findBySQL(sql);
		String openId = "";
		String nextOpen= "";
		if (null != openIdList && openIdList.size() == 1) {
			String st = openIdList.get(0).toString();
			if (StringUtils.isNotEmpty(st)) {
				openId = st;
			}
			
		}
		String url = WxOfficialAccount.URL_ALLUSERS + openId;
		boolean result = true;
		while (result) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			logger.info("开始循环了");
			boolean rt = false;
			String accessToken = this.weixinToolService.loadAccessToken(woaId);
			String resutlString = HttpTool.httpsGet(url.replace("ACCESS_TOKEN", accessToken));
			if (CommonsUtil.isNotEmpty(resutlString)) {
				JSONObject json = JSONObject.fromObject(resutlString);
				if (json.get("errcode") == null) {
					Object obj = json.get("data");
					Integer count = json.getInt("count");
					if (count > 0) {
						JSONObject dataJson = JSONObject.fromObject(obj);
						if (null != dataJson) {
							JSONArray list = JSONArray.fromObject(dataJson.get("openid"));
							if (null != list && list.size() > 0) {
								saveUserOpenId(list);
							}
						}
						String nextOpenId = json.getString("next_openid");
						if (StringUtils.isNotEmpty(nextOpenId)) {
							url = WxOfficialAccount.URL_ALLUSERS + nextOpenId;
							nextOpen = nextOpenId;
							rt = true;
						}
					}
				}
			}
			result = rt;
		}
		if (StringUtils.isNotEmpty(nextOpen)) {
			String updateSql = "update wxs.nexopenid set nexopenid = '"+nextOpen+"' where woaid = "+woaId;
			this.getEntityDao().execSQL(updateSql);
		}
	}

	@Override
	public FansItem loadByOpenId(String openId,Long woaId) {
		PreFilter openidFilter = PreFilters.eq(FansItem.PROP_OPENID, openId);
		PreFilter woaFilter = PreFilters.eq(FansItem.PROP_WOAID, woaId);
		FansItem item = this.getEntityDao().findUniqueByPreFilter(FansItem.class,openidFilter,woaFilter);
		if(item == null){
			//若数据库中不存在从公众号获取
			try {
				String info = weixinToolService.loadUesrInfo(woaId,openId);
				if (CommonsUtil.isNotEmpty(info)) {
					JSONObject jsonObject = JSONObject.fromObject(info);
					String unionId = jsonObject.getString("unionid");
					//是否关注了平台中的其他公众号
					Fans f = this.findUniqueByPreFilter(PreFilters.eq(Fans.PROP_UNIONID, unionId));
					boolean subscribe = "1".equals(jsonObject.getString("subscribe"));				
					if(f==null){
						//未关注过其他公众号
						Fans fans = new Fans();
						fans.setNickname(jsonObject.getString("nickname"));
						fans.setUnionId(unionId);
						fans.setSex(jsonObject.getInt("sex"));
						fans.setCountry(jsonObject.getString("country"));
						fans.setCity(jsonObject.getString("city"));
						fans.setProvince(jsonObject.getString("province"));
						fans.setLanguage(jsonObject.getString("language"));
						fans.setHeadimgurl(jsonObject.getString("headimgurl"));
						fans.setSubscribeTime(jsonObject.getLong("subscribe_time")*1000);
						fans.setRemark("0");// 判断花舞人间是否参加了活动 0 是没有 1是一次
											// ，依次累加
						fans.setSubscribe(subscribe);
						
						
						saveOrUpdateFans(fans);
					
						f= fans;
										
					}
					item = new FansItem();
					item.setFansId(f.getId());
					item.setOpenid(openId);
					item.setWoaId(woaId);
					item.setSubscribe(subscribe);
					item.setSubscribeTime(jsonObject.getLong("subscribe_time")*1000);
					this.getEntityDao().save(item);
				}	
			}
			catch (Exception e) {
				logger.error(e);
			}
		}
		
		
		return item;
	}
	@Override
	public FansItem loadByOpenId(String openId) {
		PreFilter openidFilter = PreFilters.eq(FansItem.PROP_OPENID, openId);
		
		return this.getEntityDao().findUniqueByPreFilter(FansItem.class,openidFilter);
	}

	private void saveUserOpenId(JSONArray list) {
		SaveUserOpenId saveUser = new SaveUserOpenId(list);
		saveUser.start();
	}
	
	private class SaveUserOpenId extends Thread{
			private JSONArray list;
			public SaveUserOpenId (JSONArray list){
				this.list = list;
			}
			@Override
			public void run() {
				logger.info("开始保存用户:"+list.size());
				StringBuffer sb = new StringBuffer("");
				for (Object object : list) {
					String openId = String.valueOf(object);
					Long time = System.currentTimeMillis();
					sb.append("insert into public.fans(subscribe,openid,nickname,sex,subscribe_time,remark,status,createtime,lastmodifytime) values(true,'"
							+ openId + "','',1," + time + ",'0',0," + time + "," + time + ");");
				}
				try {
					getEntityDao().execSQL(sb.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				// 判断是否有重复
				String sql2 = "DELETE FROM public.fans wx WHERE wx.ID NOT IN ( SELECT MAX (wu2.ID) FROM public.fans wu2 GROUP BY wu2.openid)";
				try {
					getEntityDao().execSQL(sql2);
				} catch (Exception e) {
					logger.warn("去重复失败");
				}
			}
			
	}
	
	

	@Override
	public void updateUserByOpenId(Long woaId) {
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		boolean flag = true;
		Integer  count = 0;
		String countSql = "select COUNT(*) FROM public.fans wx WHERE wx.status=0 and woaid="+woaId;
		Long id = 1L;
		try {
			List<Object> list = getEntityDao().findBySQL(countSql);
			count = Integer.valueOf(list.get(0).toString());
			String idSql = "select wx.id FROM public.fans wx WHERE wx.status=0 and woaid="+woaId+" order by wx.id desc limit 1";
			List<Object> Idlist = getEntityDao().findBySQL(idSql);
			if (null != Idlist && Idlist.size() == 1) {
				id = Long.valueOf(Idlist.get(0).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		while (flag) {
			if (count <= 0) {
				flag = false;
				break;
			}
			String sql = "select wx.openid,wx.id FROM public.fans wx WHERE wx.status=0 and woaid="+woaId+" and wx.id <= "+id+" order by wx.id desc  limit 100";
			List<Object[]> list = getEntityDao().findBySQL(sql);
			if (null == list || list.size() == 0) {
				flag = false;
			}
			id = Long.valueOf(String.valueOf(list.get(list.size()-1)[1]));
			JSONObject json = new JSONObject();
			JSONArray array = new JSONArray();
			for (int i = 0, x = list.size(); i < x; i++) {
				JSONObject user_list_json = new JSONObject();
				user_list_json.put("openid", String.valueOf(list.get(i)[0]));
				user_list_json.put("lang", "zh-CN");
				array.add(user_list_json);
			}
			if (array.size() > 0) {
				json.put("user_list", array);
				String accessToken = weixinToolService.loadAccessToken(woaId);
				String resutlString = HttpTool.httpsPost(
						WxOfficialAccount.URL_USER_LIST.replace("ACCESS_TOKEN", accessToken),
						json.toString());
				if (CommonsUtil.isNotEmpty(resutlString)) {
					JSONObject resutJson = JSONObject.fromObject(resutlString);
					JSONArray userArray = resutJson.getJSONArray("user_info_list");
					if (null != userArray && userArray.size() > 0) {
						StringBuffer updateSql = new StringBuffer();
						for (int i = 0, x = userArray.size(); i < x; i++) {
							JSONObject infoJson = userArray.getJSONObject(i);
							Integer subscribe = infoJson.getInt("subscribe");
							if (1 == subscribe) {
								String nickname = infoJson.getString("nickname").replaceAll("'", "‘");
								String openId = infoJson.getString("openid");
								Integer sex = infoJson.getInt("sex");
								String country = infoJson.getString("country").replaceAll("'", "‘");
								String city = infoJson.getString("city").replaceAll("'", "‘");
								String province = infoJson.getString("province").replaceAll("'", "‘");
								String language = infoJson.getString("language").replaceAll("'", "‘");
								String headimgurl = infoJson.getString("headimgurl");
								Long subscribe_time = infoJson.getLong("subscribe_time") * 1000;
								updateSql.append("update public.fans set nickname='" + nickname
										+ "',sex=" + sex + ",country ='" + country + "',city='"
										+ city + "',province='" + province + "',language='"
										+ language + "',headimgurl='" + headimgurl
										+ "',subscribe_time=" + subscribe_time + ",status=1 where openid='"
										+ openId + "';");
								
							}
						}
						if (StringUtils.isNotEmpty(updateSql.toString())) {
							logger.debug(updateSql.toString());
							String sqlStr = updateSql.toString();
							try {
								logger.info("数据库开始更新用户信息："+DateUtil.getCurrTime());
								getEntityDao().execSQL(sqlStr);
								logger.info("数据库更新用户信息结束:"+DateUtil.getCurrTime());
							} catch (Exception e) {
								e.printStackTrace();
							}
							
						}

					}

				}

			}
			count -= list.size();
		}
	}
	@Override
	public void syncWoaFansInfo(){
		List<Long> woaIds = this.getEntityDao().getIdsByPreFilter(WxOfficialAccount.class, getStatusFilter());
		for (Long woaId : woaIds) {
			this.saveAllFans(woaId);
		}
	}
	@Override
	public void saveFansItem(FansItem item){
		if(item.getSubscribe()==null ||
				StringUtils.isBlank(item.getOpenid())||
				item.getWoaId()==null ||
				item.getFansId()==null){
			throw new EminException(WXSExceptionCode.WOA_PARAMTERS_INVALID);
		}
		this.getEntityDao().saveOrUpdate(item);
	}
	@Override
	public Fans loadByUnionId(String unionId){
		return this.findUniqueByPreFilter(PreFilters.eq(Fans.PROP_UNIONID, unionId));
	}
}
