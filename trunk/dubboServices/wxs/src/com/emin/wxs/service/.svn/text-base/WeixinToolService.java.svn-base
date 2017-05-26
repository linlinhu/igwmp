package com.emin.wxs.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity;
import com.emin.base.service.UndeleteableService;
import com.emin.wxs.domain.WxArticle;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface WeixinToolService extends UndeleteableService<UndeleteableEntity> {

	/**
	 * 获取appid
	 * */
	public String getAppid(Long woaId);
	/**
	 * 获取secret
	 * */
	public String getSecret(Long woaId);
	/**
	 * 获取access_token
	 * */
	public String loadAccessToken(Long woaId);
	/**
	 * 把普通url转换为微信授权页url
	 * */
	public String convertUrlToOauthUrl(Long woaId,String url) throws UnsupportedEncodingException;
	/**
	 * 把urlkey转换为微信授权页url
	 * */
	public String convertUrlkeyToOauthUrl(Long woaId,String urlkey) throws UnsupportedEncodingException;
	/**
	 * 获取openid,当用户点击菜单链接时<br>
	 * 返回格式：{"access_token":"ACCESS_TOKEN","expires_in":7200,"refresh_token":"REFRESH_TOKEN","openid":"OPENID","scope":"SCOPE"}<br>
	 * access_token:网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同<br>
	 * expires_in:access_token接口调用凭证超时时间，单位（秒）<br>
	 * refresh_token:用户刷新access_token<br>
	 * scope:用户授权的作用域，使用逗号（,）分隔
	 * */
	public String loadSnsTokenOpenid(Long woaId,String code);
	/**
	 * 刷新access_token<br>
	 * 返回格式：{"access_token":"ACCESS_TOKEN","expires_in":7200,"refresh_token":"REFRESH_TOKEN","openid":"OPENID","scope":"SCOPE"}<br>
	 * access_token:网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同<br>
	 * expires_in:access_token接口调用凭证超时时间，单位（秒）<br>
	 * refresh_token:用户刷新access_token<br>
	 * scope:用户授权的作用域，使用逗号（,）分隔
	 * */
	public String freshSnsTokenOpenid(Long woaId,String refresh_token);
	/**
	 * 获取未关注（但已授权）用户基本信息
	 * */
	public String loadSnsUesrInfo(Long woaId,String access_token,String openid);
	/**
	 * 根据openid取得已关注用户基本信息
	 * */
	public String loadUesrInfo(Long woaId,String openid);
	/**
	 * 客服接口-发送文字消息
	 * */
	public String sendServiceText(Long woaId,String openid,String msg);
	/**
	 * 客服接口-发送图片消息
	 * */
	public String sendServiceImage(Long woaId,String openid,String mediaid);
	/**
	 * 客服接口-发送图文消息
	 * */
	public String sendServiceImageText(Long woaId,String openid,List<WxArticle> articles);
	/**
	 * 群发接口(所有人)-发送文字消息
	 * */
	public String sendAllText(Long woaId,String msg);
	/**
	 * 群发接口(所有人)-发送图片消息
	 * */
	public String sendAllImage(Long woaId,String mediaid);
	/**
	 * 群发接口(按人)-发送文字消息
	 * */
	public String sendMassText(Long woaId,List<String> openids,String msg);
	/**
	 * 群发接口(按人)-发送图片消息
	 * */
	public String sendMassImage(Long woaId,List<String> openids,String mediaid);
	/**
	 * 群发接口(按组)-发送文字消息
	 * */
	public String sendGroupText(Long woaId,String groupid,String msg);
	/**
	 * 群发接口(按组)-发送图片消息
	 * */
	public String sendGroupImage(Long woaId,String groupid,String mediaid);
	/**
	 * 模板消息
	 * */
	public String sendTmpmsgOne(Long woaId,String openid,String templateid,String url,String topcolor,String data);
	String loadJSAPITicket(Long woaId);
	JSONObject loadJSAPIConf(Long woaId,String url) throws UnsupportedEncodingException;
	
	/**
	 * 获取微信卡券api_ticket
	 */
	String loadApiTicket(Long woaId);
	
	/**
	 * 获取卡券 cardExt
	 */
	String loadCardExt(Long woaId,String openid,String carId);
	
	
	String convertUrlToSNSBASEUrl(Long woaId,String url)throws UnsupportedEncodingException;
	
	/**
	 * 获取微信支付网页端接口参数列表
	 * @param device_info 设备号  PC网页或公众号内支付请传"WEB"
	 * @param body 商品或支付单简要描述
	 * @param out_trade_no 商户订单号
	 * @param total_fee 总金额  单位 分
	 * @param spbill_create_ip 终端IP
	 * @param notify_url 通知地址
	 * @param trade_type 交易类型
	 * @param openid  用户标识
	 * @return
	 */
	JSONObject loadWeixPay(Long woaId,String device_info,String body,String out_trade_no,Integer total_fee,String spbill_create_ip,String notify_url,String trade_type,String openid);
	/**
	 * 
	 * @param openId
	 * @return
	 */
	JSONObject sendGroupRedPack(Long woaId,String openId);
	JSONObject sendRedPack(Long woaId,String openId);
	String chooseCardSign(Long woaId);
	String cardCodeDec(Long woaId,String encryptCode);
	JSONObject cardUsed(Long woaId,String code);
	JSONObject cardCheck(Long woaId,String code);
	JSONObject loadCardInfo(Long woaId,String cardId);
	JSONArray loadCardList(Long woaId);
	JSONObject loadLocationInfo(Long woaId,Double lat, Double lng);
	JSONObject loadAddressInfo(Long woaId,String address);
	
}
