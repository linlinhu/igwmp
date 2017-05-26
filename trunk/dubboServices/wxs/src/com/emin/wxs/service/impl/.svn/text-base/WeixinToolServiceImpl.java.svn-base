package com.emin.wxs.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.net.ssl.SSLContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.emin.base.dao.PreFilters;
import com.emin.base.domain.UndeleteableEntity;
import com.emin.base.exception.BaseExCode;
import com.emin.base.exception.EminException;
import com.emin.base.service.UndeleteableServiceImpl;
import com.emin.wxs.domain.FansItem;
import com.emin.wxs.domain.RedPackConf;
import com.emin.wxs.domain.RedPackRecord;
import com.emin.wxs.domain.WxArticle;
import com.emin.wxs.domain.WxMenu;
import com.emin.wxs.domain.WxOfficialAccount;
import com.emin.wxs.pay.common.Configure;
import com.emin.wxs.pay.common.RandomStringGenerator;
import com.emin.wxs.pay.common.XMLParser;
import com.emin.wxs.pay.protocol.UnifiedOrderReqData;
import com.emin.wxs.pay.service.WxPayApi;
import com.emin.wxs.service.FansService;
import com.emin.wxs.service.RedPackConfService;
import com.emin.wxs.service.RedPackRecordService;
import com.emin.wxs.service.WeixinToolService;
import com.emin.wxs.service.WxOfficialAccountService;
import com.emin.wxs.util.EminMessageUtils;
import com.emin.wxs.util.EncryptUtil;
import com.emin.wxs.util.HttpTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

@Service("weixinToolService")
public class WeixinToolServiceImpl extends UndeleteableServiceImpl<UndeleteableEntity> implements
		WeixinToolService {
	private static final Logger logger = Logger.getLogger(WeixinToolServiceImpl.class);
	/** 微信AccessToken失效时间，目前为2小时，但这里设置超过一小时就要去取一次 */
	public static final Long AccessTokenTime = 3600000l;
	public static final Long JSAPITicketTime = 3600000l;
	/**
	 * 卡券信息缓存
	 */
	public static final Map<String,String> cardMap = new HashMap<String,String>();
	/** 获取微信卡券JS接口票据时间 */
	public static Long LoadAPITicketTime = 0l;
	public static final Long APITicketTime = 3600000l;

	private static String appid = null;
	private static String secret = null;
	
	/**
	 * 各个公众号token缓存
	 */
	private static Map<Long,String> AccessTokens = new HashMap<>();
	private static Map<Long,String> JsApiTickets = new HashMap<>();
	private static Map<Long,String> ApiTickets = new HashMap<>();
	
	private static Map<Long,Long> LoadAccessTokenTimes = new HashMap<>();
	private static Map<Long,Long> LoadJSAPITicketTimes = new HashMap<>();
	private static Map<Long,Long> LoadAPITicketTimes = new HashMap<>();
	
	
	
	@Autowired
	@Qualifier("wxOfficialAccountService")
	private WxOfficialAccountService wxOfficialAccountService;
	@Autowired
	@Qualifier("redPackConfService")
	private RedPackConfService redPackConfService;
	@Autowired
	@Qualifier("redPackRecordService")
	private RedPackRecordService redPackRecordService;
	
	@Autowired
	@Qualifier("fansService")
	private FansService fansService;
	
	@Override
	public String getAppid(Long woaId) {
		if (appid == null) {
			WxOfficialAccount woa = wxOfficialAccountService.findById(woaId);
			appid = woa==null?null:woa.getAppId();
		}
		return appid;
	}

	@Override
	public String getSecret(Long woaId) {
		if (secret == null) {
			WxOfficialAccount woa = wxOfficialAccountService.findById(woaId);
			secret = woa==null?null:woa.getAppSecret();
		}
		return secret;
	}

	@Override
	public String loadAccessToken(Long woaId) {
		Long lastLoadTime = 0L;
		if(LoadAccessTokenTimes.containsKey(woaId)){
			lastLoadTime = LoadAccessTokenTimes.get(woaId);
		}
		String accesstoken = null;
		if(AccessTokens.containsKey(woaId)){
			accesstoken = AccessTokens.get(woaId);
		}
		logger.info("=====上次获取token时间："+lastLoadTime);
		logger.info("========token是否过期:"+(accesstoken==null||(System.currentTimeMillis() - lastLoadTime) > AccessTokenTime ));
		logger.info("=====token获取时间差："+(System.currentTimeMillis() - lastLoadTime + AccessTokenTime));
		if (accesstoken == null
				|| (System.currentTimeMillis() - lastLoadTime) > AccessTokenTime ) {
			// 如果当前时间超过上次取accessToken时间一小时，我们就再取一次
			String rtn = HttpTool.httpsPost(WxOfficialAccount.URL_ACCESS_TOKEN
					.replace("APPID", getAppid(woaId)).replace("APPSECRET", getSecret(woaId)), "");
			JSONObject rtno = JSONObject.fromObject(rtn);
			if (rtno.containsKey("errcode") && rtno.getInt("errcode") != 0) {
				throw new EminException(BaseExCode.BIZ_ILLEGAL_PARAMETER);
			}
			accesstoken = rtno.getString("access_token");
			lastLoadTime = System.currentTimeMillis();
			AccessTokens.put(woaId, accesstoken);
			LoadAccessTokenTimes.put(woaId, lastLoadTime);
		}
		logger.info("==========accessToken:" + accesstoken);
		return accesstoken;
	}

	@Override
	public String loadJSAPITicket(Long woaId) {
		Long lastLoadTime = 0L;
		if(LoadJSAPITicketTimes.containsKey(woaId)){
			lastLoadTime = LoadJSAPITicketTimes.get(woaId);
		}
		String jsApiTicket = null;
		if(JsApiTickets.containsKey(woaId)){
			jsApiTicket = JsApiTickets.get(woaId);
		}
		if (jsApiTicket == null
				|| (System.currentTimeMillis()- lastLoadTime) > JSAPITicketTime) {
			String rtn = HttpTool.httpsPost(
					WxOfficialAccount.URL_JSTICKET.replace("ACCESS_TOKEN", loadAccessToken(woaId)), "");
			JSONObject rtno = JSONObject.fromObject(rtn);
			if (rtno.containsKey("errcode") && rtno.getInt("errcode") != 0) {
				throw new EminException(BaseExCode.BIZ_ILLEGAL_PARAMETER);
			}
			jsApiTicket = rtno.getString("ticket");
			JsApiTickets.put(woaId, jsApiTicket);			
			lastLoadTime = System.currentTimeMillis();
			LoadJSAPITicketTimes.put(woaId, lastLoadTime);
		}
		logger.info("================jsApiTicket" + jsApiTicket);
		return jsApiTicket;
	}

	@Override
	public JSONObject loadJSAPIConf(Long woaId,String url) throws UnsupportedEncodingException {
		JSONObject jo = new JSONObject();
		String noncestr = "eminjsapinoncestr";
		logger.debug(url);
		Long time = System.currentTimeMillis() / 1000;
		String string1;
		String signature = "";

		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + loadJSAPITicket(woaId) + "&noncestr=" + noncestr + "&timestamp="
				+ time + "&url=" + URLDecoder.decode(url, "UTF-8");
		signature = EncryptUtil.encode("SHA1", string1);
		logger.info(signature + "======signature");
		jo.put("debug", false);
		jo.put("appId", getAppid(woaId));
		jo.put("timestamp", time);
		jo.put("nonceStr", noncestr);
		jo.put("signature", signature);

		return jo;
	}

	@Override
	public String convertUrlToOauthUrl(Long woaId,String url) throws UnsupportedEncodingException {
		return WxOfficialAccount.URL_OAUTH2_CODE.replace("APPID", getAppid(woaId))
				.replace("REDIRECT_URI", URLEncoder.encode(url, "UTF-8"))
				.replace("SCOPE", WxMenu.ACTIVETYPE_SNSAPIUSERINFO).replace("STATE", woaId.toString());
	}

	@Override
	public String convertUrlToSNSBASEUrl(Long woaId,String url) throws UnsupportedEncodingException {
		return WxOfficialAccount.URL_OAUTH2_CODE.replace("APPID", getAppid(woaId))
				.replace("REDIRECT_URI", URLEncoder.encode(url, "UTF-8"))
				.replace("SCOPE", WxMenu.ACTIVETYPE_SNSAPIBASE).replace("STATE", woaId.toString());
	}

	@Override
	public String convertUrlkeyToOauthUrl(Long woaId,String urlkey) throws UnsupportedEncodingException {
		return WxOfficialAccount.URL_OAUTH2_CODE.replace("APPID", getAppid(woaId))
				.replace("REDIRECT_URI", URLEncoder.encode(WxOfficialAccount.URL_MARKETING_KEY, "UTF-8"))
				.replace("SCOPE", WxMenu.ACTIVETYPE_SNSAPIUSERINFO).replace("STATE", urlkey);
	}

	@Override
	public String loadSnsTokenOpenid(Long woaId,String code) {
		String rtn = HttpTool.httpsPost(
				WxOfficialAccount.URL_ACCESS_TOKEN_OAUTH2.replace("APPID", getAppid(woaId))
						.replace("SECRET", getSecret(woaId)).replace("CODE", code), "");
		logger.info(rtn);
		JSONObject o = JSONObject.fromObject(rtn);
		if (o.containsKey("errcode") && o.getInt("errcode") > 0) {
			throw new RuntimeException(o.getString("errcode"));
		} else {
			return rtn;
		}
	}

	@Override
	public String freshSnsTokenOpenid(Long woaId,String refresh_token) {
		String rtn = HttpTool.httpsPost(
				WxOfficialAccount.URL_ACCESS_TOKEN_OAUTH2_FRESH.replace("APPID", getAppid(woaId)).replace(
						"REFRESH_TOKEN", refresh_token), "");
		JSONObject o = JSONObject.fromObject(rtn);
		if (o.containsKey("errcode") && o.getInt("errcode") > 0) {
			throw new RuntimeException(o.getString("errcode"));
		} else {
			return rtn;
		}
	}

	@Override
	public String loadSnsUesrInfo(Long woaId,String access_token, String openid) {
		return HttpTool.httpsPost(WxOfficialAccount.URL_USER_INFO_SNS
				.replace("ACCESS_TOKEN", access_token).replace("OPENID", openid), "");
	}

	@Override
	public String loadUesrInfo(Long woaId,String openid) {
		return HttpTool.httpsPost(
				WxOfficialAccount.URL_USER_INFO.replace("ACCESS_TOKEN", loadAccessToken(woaId)).replace(
						"OPENID", openid), "");
	}

	@Override
	public String sendServiceText(Long woaId,String openid, String msg) {
		JSONObject jo = new JSONObject();
		jo.put("touser", openid);
		jo.put("msgtype", "text");
		JSONObject text = new JSONObject();
		text.put("content", msg);
		jo.put("text", text);
		return HttpTool.httpsPost(
				WxOfficialAccount.URL_SERVICE_SEND.replace("ACCESS_TOKEN", loadAccessToken(woaId)),
				jo.toString());
	}

	@Override
	public String sendServiceImage(Long woaId,String openid, String mediaid) {
		JSONObject jo = new JSONObject();
		jo.put("touser", openid);
		jo.put("msgtype", "image");
		JSONObject image = new JSONObject();
		image.put("media_id", mediaid);
		jo.put("image", image);
		return HttpTool.httpsPost(
				WxOfficialAccount.URL_SERVICE_SEND.replace("ACCESS_TOKEN", loadAccessToken(woaId)),
				jo.toString());
	}

	@Override
	public String sendServiceImageText(Long woaId,String openid, List<WxArticle> articles) {
		if (openid == null || articles == null || articles.size() == 0 || articles.size() > 10) {
			throw new EminException(BaseExCode.BIZ_ILLEGAL_PARAMETER);
		}
		JSONObject jo = new JSONObject();
		jo.put("touser", openid);
		jo.put("msgtype", "news");
		JSONArray news = new JSONArray();
		for (WxArticle wa : articles) {
			JSONObject n = new JSONObject();
			n.put("title", wa.getTitle());
			n.put("description", wa.getDescription());
			n.put("url", wa.getUrl());
			n.put("picurl", wa.getPicUrl());
			news.add(n);
		}
		jo.put("news", news);
		return HttpTool.httpsPost(
				WxOfficialAccount.URL_SERVICE_SEND.replace("ACCESS_TOKEN", loadAccessToken(woaId)),
				jo.toString());
	}

	@Override
	public String sendAllText(Long woaId,String msg) {
		JSONObject jo = new JSONObject();
		JSONObject filter = new JSONObject();
		filter.put("is_to_all", true);
		jo.put("filter", filter);
		jo.put("msgtype", "text");
		JSONObject text = new JSONObject();
		text.put("content", msg);
		jo.put("text", text);
		return HttpTool.httpsPost(
				WxOfficialAccount.URL_MASS_SEND.replace("ACCESS_TOKEN", loadAccessToken(woaId)), jo.toString());
	}

	@Override
	public String sendAllImage(Long woaId,String mediaid) {
		JSONObject jo = new JSONObject();
		JSONObject filter = new JSONObject();
		filter.put("is_to_all", true);
		jo.put("filter", filter);
		jo.put("msgtype", "image");
		JSONObject image = new JSONObject();
		image.put("media_id", mediaid);
		jo.put("image", image);
		return HttpTool.httpsPost(
				WxOfficialAccount.URL_MASS_SEND.replace("ACCESS_TOKEN", loadAccessToken(woaId)), jo.toString());
	}

	@Override
	public String sendMassText(Long woaId,List<String> openids, String msg) {
		if (openids == null || openids.size() == 0) {
			throw new EminException(BaseExCode.BIZ_ILLEGAL_PARAMETER);
		}
		JSONObject jo = new JSONObject();
		JSONArray touser = new JSONArray();
		for (String openid : openids) {
			touser.add(openid);
		}
		jo.put("touser", touser);
		jo.put("msgtype", "text");
		JSONObject text = new JSONObject();
		text.put("content", msg);
		jo.put("text", text);
		return HttpTool.httpsPost(
				WxOfficialAccount.URL_MASS_SEND.replace("ACCESS_TOKEN", loadAccessToken(woaId)), jo.toString());
	}

	@Override
	public String sendMassImage(Long woaId,List<String> openids, String mediaid) {
		if (openids == null || openids.size() == 0) {
			throw new EminException(BaseExCode.BIZ_ILLEGAL_PARAMETER);
		}
		JSONObject jo = new JSONObject();
		JSONArray touser = new JSONArray();
		for (String openid : openids) {
			touser.add(openid);
		}
		jo.put("touser", touser);
		JSONObject image = new JSONObject();
		image.put("media_id", mediaid);
		jo.put("image", image);
		jo.put("msgtype", "image");
		return HttpTool.httpsPost(
				WxOfficialAccount.URL_MASS_SEND.replace("ACCESS_TOKEN", loadAccessToken(woaId)), jo.toString());
	}

	@Override
	public String sendGroupText(Long woaId,String groupid, String msg) {
		JSONObject jo = new JSONObject();
		JSONObject filter = new JSONObject();
		filter.put("is_to_all", false);
		filter.put("group_id", groupid);
		jo.put("filter", filter);
		jo.put("msgtype", "text");
		JSONObject text = new JSONObject();
		text.put("content", msg);
		jo.put("text", text);
		return HttpTool.httpsPost(
				WxOfficialAccount.URL_MASS_SEND.replace("ACCESS_TOKEN", loadAccessToken(woaId)), jo.toString());
	}

	@Override
	public String sendGroupImage(Long woaId,String groupid, String mediaid) {
		JSONObject jo = new JSONObject();
		JSONObject filter = new JSONObject();
		filter.put("is_to_all", false);
		filter.put("group_id", groupid);
		jo.put("filter", filter);
		jo.put("msgtype", "mpnews");
		JSONObject mpnews = new JSONObject();
		mpnews.put("media_id", mediaid);
		jo.put("mpnews", mpnews);
		return HttpTool.httpsPost(
				WxOfficialAccount.URL_MASS_SEND.replace("ACCESS_TOKEN", loadAccessToken(woaId)), jo.toString());
	}

	@Override
	public String sendTmpmsgOne(Long woaId,String openid, String templateid, String url, String topcolor,
			String data) {
		JSONObject jo = new JSONObject();
		jo.put("touser", openid);
		jo.put("template_id", templateid);
		jo.put("url", url);
		jo.put("topcolor", topcolor);
		jo.put("data", data);
		return HttpTool.httpsPost(
				WxOfficialAccount.URL_TEMPLATE_SEND.replace("ACCESS_TOKEN", loadAccessToken(woaId)),
				jo.toString());
	}

	@Override
	public String loadApiTicket(Long woaId) {
		Long lastLoadTime = 0L;
		if(LoadAPITicketTimes.containsKey(woaId)){
			lastLoadTime = LoadAPITicketTimes.get(woaId);
		}
		String apiTicket = null;
		if(ApiTickets.containsKey(woaId)){
			apiTicket = ApiTickets.get(woaId);
		}
		if (apiTicket == null ||  (System.currentTimeMillis()-lastLoadTime ) > APITicketTime) {
			String rtn = HttpTool.httpsGet(WxOfficialAccount.URL_JSTICKET.replace("ACCESS_TOKEN",
					loadAccessToken(woaId)).replace("jsapi", "wx_card"));
			JSONObject rtno = JSONObject.fromObject(rtn);
			if (rtno.containsKey("errcode") && rtno.getInt("errcode") != 0) {
				throw new EminException(BaseExCode.BIZ_ILLEGAL_PARAMETER);
			}
			apiTicket = rtno.getString("ticket");
			lastLoadTime = System.currentTimeMillis();
			ApiTickets.put(woaId, apiTicket);
			LoadAPITicketTimes.put(woaId, lastLoadTime);
		}
		logger.info("================apiTicket" + apiTicket);
		return apiTicket;
	}
	@Override
	public String cardCodeDec(Long woaId,String encryptCode){
		JSONObject json = new JSONObject();
		json.put("encrypt_code", encryptCode);
		String rtn = HttpTool.httpsPost(WxOfficialAccount.URL_CARDCODEDEC.replace("ACCESS_TOKEN", loadAccessToken(woaId)), json.toString());
		JSONObject result = JSONObject.fromObject(rtn);
		if (result.containsKey("errcode") && result.getInt("errcode") != 0) {
			throw new EminException(BaseExCode.BIZ_ILLEGAL_PARAMETER);
		}
		return result.getString("code");
	}
	
	@Override
	public JSONObject cardUsed(Long woaId,String code){
		JSONObject json = new JSONObject();
		json.put("code", code);
		String rtn = HttpTool.httpsPost(WxOfficialAccount.URL_CARDUSERD.replace("ACCESS_TOKEN", loadAccessToken(woaId)), json.toString());
		JSONObject result = JSONObject.fromObject(rtn);		
		return result;
	}
	@Override
	public JSONObject cardCheck(Long woaId,String code){
		JSONObject json = new JSONObject();
		json.put("code", code);
		json.put("check_consume", false);
		String rtn = HttpTool.httpsPost(WxOfficialAccount.URL_CARDCHECK.replace("ACCESS_TOKEN", loadAccessToken(woaId)), json.toString());
		JSONObject result = JSONObject.fromObject(rtn);		
		return result;
	}
	@Override
	public String loadCardExt(Long woaId,String openid, String cardId) {
		if (StringUtils.isEmpty(openid) || StringUtils.isEmpty(cardId)) {
			throw new RuntimeException("网络错误，请稍后再试");
		}
		String apiTicket2 = loadApiTicket(woaId);
		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
		String nonceStr = getUUID();
		String str = sort(apiTicket2, openid, timestamp, nonceStr, cardId);
		String signature = SHA1(str);
		JSONObject json = new JSONObject();
		json.put("openid", openid);
		json.put("timestamp", timestamp);
		json.put("nonce_str", nonceStr);
		json.put("signature", signature);
		return json.toString();
	}
	@Override
    public String chooseCardSign(Long woaId){
    	String apiTicket2 = loadApiTicket(woaId);
    	String appid = getAppid(woaId);
		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
		String nonceStr = getUUID();
		String cardId = "";
		String cardType = "";
		String shopId = "";
		String str = sort(apiTicket2, appid, timestamp, nonceStr, cardId,cardType,shopId);
		String signature = SHA1(str);
		JSONObject json = new JSONObject();
		json.put("cardId", cardId);
		json.put("cardType", cardType);
		json.put("signType", "SHA1");
		json.put("shopId", shopId);
		json.put("timestamp", timestamp);
		json.put("nonceStr", nonceStr);
		json.put("cardSign", signature);
		return json.toString();
    }
	private static String sort(String... str) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < str.length; i++) {
			list.add(str[i]);
		}
		Collections.sort(list);
		StringBuffer sb = new StringBuffer("");
		for (int i = 0, x = list.size(); i < x; i++) {
			sb.append(list.get(i));
		}
		logger.info("排序：" + sb.toString());
		return sb.toString();
	}

	private String getUUID() {
		String s = UUID.randomUUID().toString();
		// 去掉“-”符号
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23)
				+ s.substring(24);
	}

	private String SHA1(String inStr) {
		MessageDigest md = null;
		String outStr = null;
		try {
			md = MessageDigest.getInstance("SHA-1"); // 选择SHA-1，也可以选择MD5
			byte[] digest = md.digest(inStr.getBytes("UTF-8")); // 返回的是byet[]，要转化为String存储比较方便
			outStr = bytetoString(digest);
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return outStr;
	}

	private String bytetoString(byte[] digest) {
		String str = "";
		String tempStr = "";
		for (int i = 0; i < digest.length; i++) {
			tempStr = (Integer.toHexString(digest[i] & 0xff));
			if (tempStr.length() == 1) {
				str = str + "0" + tempStr;
			} else {
				str = str + tempStr;
			}
		}
		return str.toLowerCase();
	}
	@Override
	public JSONArray loadCardList(Long woaId){
		JSONArray cardList = new JSONArray();
		JSONObject json = new JSONObject();
		json.put("offset", 0);
		json.put("count", 50);
		json.put("status_list", new String[]{"CARD_STATUS_VERIFY_OK","CARD_STATUS_DISPATCH"});
		try {
			String rtn = HttpTool.httpsPost(WxOfficialAccount.URL_CARDLIST.replace("ACCESS_TOKEN", loadAccessToken(woaId)), json.toString());
			JSONObject j = JSONObject.fromObject(rtn);
			if(j.getInt("errcode")==0){
				JSONArray cardIdList = j.getJSONArray("card_id_list");
				for (int i = 0; i < cardIdList.size(); i++) {
					String cardId = cardIdList.getString(i);
					JSONObject card = new JSONObject();
					card.put("id", cardId);
					String name = "";
					if(cardMap.containsKey(cardId)){
						 name =  cardMap.get(cardId);
						
					}else{
						JSONObject c =  loadCardInfo(woaId,cardId);
						 String cardType = c.getJSONObject("card").getString("card_type");
						 String title = c.getJSONObject("card").getJSONObject(cardType.toLowerCase()).getJSONObject("base_info").getString("title");
						 String subTitle =  c.getJSONObject("card").getJSONObject(cardType.toLowerCase()).getJSONObject("base_info").getString("sub_title");
						 StringBuffer s = new StringBuffer(title);
					     if(StringUtils.isNotBlank(subTitle)){
							s.append("_").append(subTitle);
						 }
						 cardMap.put(cardId, s.toString());
						 name = s.toString();
					}
					;
					
					card.put("title", name);
					cardList.add(card);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		logger.info("==========获取微信卡券列表:"+cardList.toString());
		return cardList;
	}
	@Override
	public JSONObject loadCardInfo(Long woaId,String cardId){
		JSONObject json = new JSONObject();
		json.put("card_id", cardId);		
		String rtn = HttpTool.httpsPost(WxOfficialAccount.URL_CARDINFO.replace("ACCESS_TOKEN", loadAccessToken(woaId)), json.toString());
		JSONObject card = JSONObject.fromObject(rtn);
		
		return card;
	}
	@Override
	public JSONObject loadLocationInfo(Long woaId,Double lat,Double lng){
		StringBuffer lbs = new StringBuffer();
		lbs.append(lat).append(",").append(lng);
		String rtn = HttpTool.httpGet(WxOfficialAccount.URL_LOCATIONINFO.replaceAll("LOCATION", lbs.toString()));
		JSONObject location = JSONObject.fromObject(rtn);
		return location;
	}
	@Override
	public JSONObject loadAddressInfo(Long woaId,String address){
		String rtn = "";
		try {
			rtn = HttpTool.httpGet(WxOfficialAccount.URL_ADDRESSINFO.replaceAll("ADDRESS", URLEncoder.encode(address, "UTF-8")));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject location = JSONObject.fromObject(rtn);
		return location;
	}
	@Override
	public JSONObject loadWeixPay(Long woaId,String device_info, String body, String out_trade_no,
			Integer total_fee, String spbill_create_ip, String notify_url, String trade_type,
			String openid) {
		WxOfficialAccount woa = wxOfficialAccountService.findById(woaId);
		// 获取AppId
		String appId = woa.getAppId();
		// 商户号mch_id
		String mchId = woa.getMchId();

		// ///////////////////////////////
		// 获取AppkeY
		String paykey = woa.getApikey();
		Configure.setKey(paykey);
		Configure.setAppID(appId);
		Configure.setMchID(mchId);
		UnifiedOrderReqData reqData = new UnifiedOrderReqData.UnifiedOrderReqDataBuilder(
				Configure.getAppid(), Configure.getMchid(), body, out_trade_no, total_fee,
				spbill_create_ip, notify_url, trade_type).setOpenid(openid).build();
		try {
			Map<String, Object> resultMap = WxPayApi.UnifiedOrder(reqData);
			for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
				logger.debug(entry.getKey() + ":" + entry.getValue());
			}
			Object result_code = resultMap.get("result_code");
			logger.info("SUCCESS".equals(String.valueOf(result_code)));
			if (null != result_code && "SUCCESS".equals(String.valueOf(result_code))) {
				JSONObject json = new JSONObject();
				json.put("package", "prepay_id="+String.valueOf(resultMap.get("prepay_id")));
				json.put("appId", appId);
				json.put("timeStamp", String.valueOf(System.currentTimeMillis() / 1000));
				json.put("nonceStr", RandomStringGenerator.getRandomStringByLength(32));
				json.put("signType", "MD5");
				json.put("paySign", paySignmd5(woaId,json));
				return json;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("微信预支付失败");
		}
		return null;
	}
	@Override
	public JSONObject sendGroupRedPack(Long woaId,String openId){
		JSONObject result = new JSONObject();
		try {
			JSONObject jo = new JSONObject();
			WxOfficialAccount woa = wxOfficialAccountService.findById(woaId);
			String mchId = woa.getMchId();
			String time =String.valueOf(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String date = sdf.format(new Date());
			FansItem fansItem = this.fansService.loadByOpenId(openId,woaId);
			//Fans fans = this.fansService.findById(fansItem.getFansId());
			RedPackConf atcode = this.redPackConfService.findUniqueByPreFilter(PreFilters.eq("woaId", woaId),PreFilters.eq("enable", true));
			/*RedPackRecord r = this.redPackRecordService.findUniqueByPreFilter(PreFilters.eq("userId", fans.getId()),PreFilters.eq("actCode", atcode.getActCode()));
			if(r!=null){
				result.put("err_code", "ALREADYGET");
				return result;
			}*/
			String mchBillNo =  mchId + date+time.substring(3, time.length());
			jo.put("nonce_str", RandomStringGenerator.getRandomStringByLength(32));
			jo.put("mch_billno", mchBillNo);
			jo.put("mch_id",mchId );		
			jo.put("wxappid",  woa.getAppId());
			jo.put("send_name", woa.getCompanyName());
			//jo.put("send_name", "jinli");
			jo.put("re_openid", openId);
			Double finalAmount =atcode.getTotalAmount();
			finalAmount*=100;
			jo.put("total_amount", finalAmount.intValue());		
			//jo.put("total_amount", this.redPackConfService.loadByCode("total_amount").getValue());
			jo.put("total_num", atcode.getTotalNum());
			jo.put("amt_type", "ALL_RAND");
			jo.put("wishing",  atcode.getWishing());
			jo.put("act_name",atcode.getActName());
			jo.put("remark",atcode.getRemark());
			//jo.put("client_ip", "183.221.117.116");
			jo.put("sign", paySignmd5(woaId,jo));
			String xml = XMLParser.jsontoXml(jo);
			System.out.println(xml);
			StringBuffer sb = new StringBuffer();
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			FileInputStream instream = new FileInputStream(new File(
					EminMessageUtils.getApiclientCert()));
			try {
				keyStore.load(instream, mchId.toCharArray());
			} finally {
				instream.close();
			}
			SSLContext sslcontext = SSLContexts.custom()
					.loadKeyMaterial(keyStore,mchId.toCharArray()).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
					sslcontext,
					new String[] { "TLSv1" },
					null,
					SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			CloseableHttpClient httpclient = HttpClients.custom()
					.setSSLSocketFactory(sslsf).build();
			try {
				HttpPost httpost = new HttpPost(RedPackConf.URL_GROUPREDPACK);
				StringEntity entityStr = new StringEntity(new String(xml.getBytes("UTF-8"),"ISO8859-1"));
				entityStr.setContentType("text/xml");
				entityStr.setContentEncoding("utf-8");
				System.out.println(entityStr.getContentEncoding().getValue());
				httpost.addHeader("Cache-Control", "no-cache");
				httpost.setEntity(entityStr);
				//httpost.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");  
				logger.info("executing request" + httpost.getRequestLine());
				CloseableHttpResponse response = httpclient.execute(httpost);
				try {
					HttpEntity entity = response.getEntity();
					logger.info(response.getStatusLine());
					if (entity != null) {
						logger.info("Response content length: "
								+ entity.getContentLength());
						BufferedReader bufferedReader = new BufferedReader(
								new InputStreamReader(entity.getContent(),
										"UTF-8"));
						String text;
						while ((text = bufferedReader.readLine()) != null) {
							sb.append(text);
						}

					}
					EntityUtils.consume(entity);
				} finally {
					response.close();
				}
			} finally {
				httpclient.close();
			}
			JSONObject returnJson = JSONObject.fromObject(new XMLSerializer().read(sb.toString()));
			System.out.println(returnJson.toString());
			if(returnJson.containsKey("err_code")){
				
			
				
			}else if(returnJson.containsKey("mch_id")){
				//包含这个属性说明 微信接口的 return_code 和 result_code 都为true
				//记录红包发送记录
				RedPackRecord record = new RedPackRecord();
				record.setActName(jo.getString("act_name"));
				record.setStatus(1);
				record.setMchBillNO(jo.getString("mch_billno"));
				record.setTotalAmount(jo.getDouble("total_amount"));
				record.setTotalNum(jo.getInt("total_num"));
				record.setWishing(jo.getString("wishing"));
				record.setActName(jo.getString("act_name"));
				record.setUserId(fansItem.getId());
				record.setActCode(atcode.getActCode());
				record.setType(2);
				record.setWoaId(woaId);
				this.redPackRecordService.save(record);
			}
			 return returnJson;
		} catch (Exception e) {
			e.printStackTrace();
			//throw new RuntimeException("红包发送失败");
		}
		return result;
	}
	@Override
	public JSONObject sendRedPack(Long woaId,String openId){
		JSONObject result = new JSONObject();
		try {
			JSONObject jo = new JSONObject();
			WxOfficialAccount woa = wxOfficialAccountService.findById(woaId);
			String mchId = woa.getMchId();
			String time =String.valueOf(System.currentTimeMillis());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String date = sdf.format(new Date());
			String mchBillNo =  mchId + date+time.substring(3, time.length());
		//	String mchBillNo =  mchId + String.valueOf(System.currentTimeMillis());
			System.out.println(openId);
			FansItem u = fansService.loadByOpenId(openId,woaId);
			RedPackConf atcode = this.redPackConfService.findUniqueByPreFilter(PreFilters.eq("woaId", woaId),PreFilters.eq("enable", true));
			RedPackRecord r = this.redPackRecordService.findUniqueByPreFilter(PreFilters.eq("userId", u.getId()),PreFilters.eq("actCode",atcode.getActCode()));
			if(r!=null){
				result.put("err_code", "ALREADYGET");
				return result;
			}
			jo.put("nonce_str", RandomStringGenerator.getRandomStringByLength(32));
			jo.put("mch_billno", mchBillNo);
			jo.put("mch_id",mchId );		
			jo.put("wxappid",  woa.getAppId());
			jo.put("send_name", woa.getCompanyName());
			//jo.put("send_name", "jinli");
			jo.put("re_openid", openId);
			//DecimalFormat dcm = new DecimalFormat("#0.00");
			Double start = atcode.getMinAmount();
			Double end =atcode.getMinAmount();
			Double finalAmount = Math.random()*(end-start)+start;
			finalAmount*=100;
			jo.put("total_amount", finalAmount.intValue());		
			jo.put("total_num", atcode.getTotalNum());
			//jo.put("amt_type", "ALL_RAND");
			jo.put("wishing",atcode.getWishing());
			jo.put("act_name",atcode.getActName());
			jo.put("remark",atcode.getRemark());
			jo.put("client_ip", "192.168.0.1");
			jo.put("sign", paySignmd5(woaId,jo));
			String xml = XMLParser.jsontoXml(jo);
			System.out.println(xml);
			StringBuffer sb = new StringBuffer();
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			FileInputStream instream = new FileInputStream(new File(
					EminMessageUtils.getApiclientCert()));
			try {
				keyStore.load(instream, mchId.toCharArray());
			} finally {
				instream.close();
			}
			SSLContext sslcontext = SSLContexts.custom()
					.loadKeyMaterial(keyStore,mchId.toCharArray()).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
					sslcontext,
					new String[] { "TLSv1" },
					null,
					SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			CloseableHttpClient httpclient = HttpClients.custom()
					.setSSLSocketFactory(sslsf).build();
			try {
				HttpPost httpost = new HttpPost(RedPackConf.URL_REDPACK);
				StringEntity entityStr = new StringEntity(new String(xml.getBytes("UTF-8"),"ISO-8859-1"));
				entityStr.setContentType("text/xml");
				entityStr.setContentEncoding("utf-8");
				System.out.println(entityStr.getContentEncoding().getValue());
				httpost.addHeader("Cache-Control", "no-cache");
				httpost.setEntity(entityStr);
				//httpost.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");  
				logger.info("executing request" + httpost.getRequestLine());
				CloseableHttpResponse response = httpclient.execute(httpost);
				try {
					HttpEntity entity = response.getEntity();
					logger.info(response.getStatusLine());
					if (entity != null) {
						logger.info("Response content length: "
								+ entity.getContentLength());
						BufferedReader bufferedReader = new BufferedReader(
								new InputStreamReader(entity.getContent(),
										"UTF-8"));
						String text;
						while ((text = bufferedReader.readLine()) != null) {
							sb.append(text);
						}

					}
					EntityUtils.consume(entity);
				} finally {
					response.close();
				}
			} finally {
				httpclient.close();
			}
			JSONObject returnJson = JSONObject.fromObject(new XMLSerializer().read(sb.toString()));
			System.out.println(returnJson.toString());
			if(returnJson.containsKey("err_code")){
				
			
				
			}else if(returnJson.containsKey("mch_id")){
				//包含这个属性说明 微信接口的 return_code 和 result_code 都为true
				//记录红包发送记录
				
				RedPackRecord record = new RedPackRecord();
				record.setActName(jo.getString("act_name"));
				record.setStatus(1);
				record.setMchBillNO(jo.getString("mch_billno"));
				record.setTotalAmount(jo.getDouble("total_amount"));
				record.setTotalNum(jo.getInt("total_num"));
				record.setWishing(jo.getString("wishing"));
				record.setActName(jo.getString("act_name"));
				record.setUserId(u.getId());
				record.setActCode(atcode.getActCode());
				record.setType(1);
				record.setWoaId(woaId);
				this.redPackRecordService.save(record);
			}
			 return returnJson;
		} catch (Exception e) {
			e.printStackTrace();
			//throw new RuntimeException("红包发送失败");
		}
		return result;
	}
	/**
	 * 微支付签名机制
	 * */
	private String paySignmd5(Long woaId,JSONObject p) {
		try {
			Object[] objs = p.keySet().toArray();
			String[] keys = new String[objs.length];
			for (int i = 0, x = objs.length; i < x; i++) {
				keys[i] = String.valueOf(objs[i]);
			}
			Arrays.sort(keys);
			logger.debug(keys);
			StringBuffer strA = new StringBuffer();
			for (String k : keys) {
				strA.append(k + "=" + p.getString(k)).append("&");
			}
			WxOfficialAccount woa = wxOfficialAccountService.findById(woaId);
			String apikey = woa.getApikey();
			strA.append("key=" + apikey);
			return EncryptUtil.encryptMD5(strA.toString()).toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("生成前面错误");
		}
	}
	

	
}
