package com.emin.wxs.controller.woa;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.base.dao.PreFilters;
import com.emin.wxs.controller.WxsBaseController;
import com.emin.wxs.domain.Fans;
import com.emin.wxs.domain.FansItem;
import com.emin.wxs.domain.RedPackActivityRecord;
import com.emin.wxs.domain.RedPackConf;
import com.emin.wxs.domain.WeixinEventReply;
import com.emin.wxs.domain.WeixinKeyReply;
import com.emin.wxs.domain.WxArticle;
import com.emin.wxs.domain.WxOfficialAccount;
import com.emin.wxs.service.FansService;
import com.emin.wxs.service.RedPackActivityRecordService;
import com.emin.wxs.service.RedPackConfService;
import com.emin.wxs.service.RedPackRecordService;
import com.emin.wxs.service.WeixinEventReplyService;
import com.emin.wxs.service.WeixinKeyReplyService;
import com.emin.wxs.service.WeixinToolService;
import com.emin.wxs.service.WxOfficialAccountService;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


@Controller
@RequestMapping("/woa")
public class WeixinController extends WxsBaseController {

	private static final Logger logger = Logger.getLogger(WeixinController.class);
	@Reference(version="0.0.1")
	private WxOfficialAccountService wxOfficialAccountService;
	@Reference(version="0.0.1")
	private WeixinEventReplyService weixinEventReplyService;
	@Reference(version="0.0.1")
	private WeixinKeyReplyService weixinKeyReplyService;
	
	@Reference(version="0.0.1")
	private WeixinToolService weixinToolService;
	@Reference(version="0.0.1")
	private FansService fansService;
	@Reference(version="0.0.1")
	private RedPackConfService redPackConfService;
	@Reference(version="0.0.1")
	private RedPackRecordService redPackRecordService;
	/*@Autowired
	@Qualifier("weixinCardLogService")
	private WeixinCardLogService weixinCardLogService;*/
	@Reference(version="0.0.1")
	private RedPackActivityRecordService redPackActivityRecordService;
	/**
	 * 微信接入接口
	 * */
	@RequestMapping("/impl.do")
	public void impl(){
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		try {
			if(request.getParameter("echostr")!=null){//接口验证
				logger.info("=========验证");
				String echostr = request.getParameter("echostr").toString();//随机字符串
				/*String token = wxOfficialAccountService.loadByCode(WeixinConf.CODE_TOKEN).getValue();
				String signature = getParameterValue(WeixinConf.CODE_SIGNATURE).toString();// 微信加密签名
				String timestamp = getParameterValue(WeixinConf.CODE_TIMESTAMP).toString();// 时间戳
				String nonce = getParameterValue(WeixinConf.CODE_NONCE).toString();// 随机数
				String echostr = getParameterValue("echostr").toString();//随机字符串

				String[] ss = new String[] { token, timestamp, nonce };
				Arrays.sort(ss);
				String sha1 = EncryptUtil.encode("SHA1", ss[0]+ss[1]+ss[2]);
//				System.out.println("=========sha1:"+sha1);
				if (sha1.equals(signature)) {
					//保存这些验证信息，以便以后可以再次验证所以发来的请求
					wxOfficialAccountService.updateByCode(WeixinConf.CODE_SIGNATURE, signature);
					wxOfficialAccountService.updateByCode(WeixinConf.CODE_TIMESTAMP, timestamp);
					wxOfficialAccountService.updateByCode(WeixinConf.CODE_NONCE, nonce);
					getResponse().getWriter().write(echostr);
				}*/
				getResponse().getWriter().write(echostr);
			}else{
				InputStream is = request.getInputStream();
				try {
					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					DocumentBuilder db = dbf.newDocumentBuilder();
					Document d = db.parse(is);
					/*BufferedReader br = new BufferedReader(new InputStreamReader(is));
					StringBuffer str = new StringBuffer();
					String line = null;
					while ((line = br.readLine())!=null) {
						str.append(line);
					}
					System.out.println(str.toString());*/
					String msgtype = getDocText(d,"MsgType");
					if("event".equals(msgtype)){
						treatMsgEvent(d,response);//事件处理
						/*WeixinUserEvent ue = new WeixinUserEvent();
						ue.setAppid(getDocText(d,"ToUserName"));
						ue.setOpenid(getDocText(d,"FromUserName"));
						ue.setMsgtype(msgtype);
						ue.setEvent(getDocText(d,"Event"));
						ue.setEventkey(getDocText(d,"EventKey"));
						ue.setScanTicket(getDocText(d,"Ticket"));
						String lng = getDocValue(d, "Longitude");
						ue.setLocx(lng==null?null:Double.valueOf(lng));
						String lat = getDocValue(d, "Latitude");
						ue.setLocy(lat==null?null:Double.valueOf(lat));
						String scale = getDocValue(d, "Precision");
						ue.setLocScale(scale==null?null:Integer.valueOf(scale));
						weixinUserEventService.save(ue);*/
					}else{
						/*WeixinUserMsg um = new WeixinUserMsg();
						um.setAppid(getDocText(d,"ToUserName"));
						um.setOpenid(getDocText(d,"FromUserName"));
						um.setMsgtype(msgtype);
						um.setMsgId(Long.valueOf(getDocValue(d, "MsgId")));*/
						if("text".equals(msgtype)){
							//um.setContent(getDocText(d,"Content"));
							treatMsgText(d,response);
						}else if("image".equals(msgtype)){
							//um.setImgPicUrl(getDocText(d,"PicUrl"));
							//um.setMediaId(getDocText(d,"MediaId"));
							treatMsgImage(d,response);
						}else if("voice".equals(msgtype)){
							//um.setVoiceFormat(getDocText(d,"Format"));
							//um.setMediaId(getDocText(d,"MediaId"));
							treatMsgVoice(d,response);
						}else if("video".equals(msgtype)){
							//um.setVideoThumbid(getDocText(d,"ThumbMediaId"));
							//um.setMediaId(getDocText(d,"MediaId"));
							treatMsgVideo(d,response);
						}else if("location".equals(msgtype)){
							//um.setLocx(Double.valueOf(getDocValue(d, "Location_X")));
							//um.setLocy(Double.valueOf(getDocValue(d, "Location_Y")));
							//um.setLocScale(Integer.valueOf(getDocValue(d, "Scale")));
							//um.setLocLabel(getDocText(d,"Label"));
							treatMsgLocation(d,response);
						}else if("link".equals(msgtype)){
							//um.setLinkTitle(getDocText(d,"Title"));
							//um.setLinkDescription(getDocText(d,"Description"));
							//um.setLinkUrl(getDocText(d,"Url"));
							treatMsgLink(d,response);
						}else{
							String server = getDocText(d,"ToUserName");
							String user = getDocText(d,"FromUserName");
							answerMsgText(response,user,server,"敬请期待");
						}
						//weixinUserMsgService.save(um);
					}
				} catch (ParserConfigurationException e) {
					
					logger.error(e);
				} catch (SAXException e) {
					logger.error(e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}
	private void treatMsgText(Document d,HttpServletResponse response) throws IOException{
		String server = getDocText(d,"ToUserName");
		String user = getDocText(d,"FromUserName");
		String content = getDocText(d,"Content");
		WxOfficialAccount woa = wxOfficialAccountService.loadByCompanyCode(server);
		WeixinKeyReply mr = weixinKeyReplyService.findByContent(content,woa.getId());
		RedPackConf conf = redPackConfService.loadRedPackConf(woa.getId());
		if(conf!=null && conf.getEnable() && content.equals("红包")){
			//红包活动开启 并且关键字和红包配置中的关键字相同 则发送红包
			
			
			if(conf.getType().intValue()==1){
				//现金红包
				JSONObject jo = weixinToolService.sendRedPack(woa.getId(),user);
				if(jo.containsKey("err_code")){
					if(jo.getString("err_code").equalsIgnoreCase("NOTENOUGH")){
						answerMsgText(response, user, server,conf.getOverText());
					}else if(jo.getString("err_code").equalsIgnoreCase("FREQ_LIMIT")){
						answerMsgText(response, user, server, conf.getLimitText());
					}else if(jo.getString("err_code").equalsIgnoreCase("ALREADYGET")){
						answerMsgText(response, user, server, "您已参与过本次活动！");
					}
				}
			}else{
				//裂变红包
				JSONObject jo = weixinToolService.sendGroupRedPack(woa.getId(),user);
				if(jo.containsKey("err_code")){
					if(jo.getString("err_code").equalsIgnoreCase("NOTENOUGH")){
						answerMsgText(response, user, server,conf.getOverText());
					}else if(jo.getString("err_code").equalsIgnoreCase("FREQ_LIMIT")){
						answerMsgText(response, user, server, conf.getLimitText());
					}
				}
			}
			
		}else if(mr!=null){
			if(WeixinEventReply.REPLY_TYPE_NEWS.equals(mr.getReplyType())){
				answerMsgTextImg(response, user, server, mr.getArticles());
			}else{
				answerMsgText(response,user,server,mr.getrContent());
			}
		}else{
		
			List<WeixinKeyReply> ks = weixinKeyReplyService.loadPreferences(woa.getId());
			if(ks.size()>0){
				StringBuffer sb = new StringBuffer("你可回复下列关键词查看相关信息：");
				for(WeixinKeyReply k:ks){
					sb.append("\n["+k.getContent()+"] "+k.getRemark());
				}
				answerMsgText(response,user,server,sb.toString());
			}
			else{
				/*String c = wxOfficialAccountService.loadByCode(Wx.CODE_UNKNOWNKEY).getValue();
				mr = weixinKeyReplyService.findByContent(c);
				if(mr!=null){
					if(WeixinEventReply.REPLY_TYPE_NEWS.equals(mr.getReplyType())){
						answerMsgTextImg(response, user, server, mr.getArticles());
					}else{
						answerMsgText(response,user,server,mr.getrContent());
					}
				}else{
					answerMsgText(response,user,server,c);
				}*/
				
			
			}
		}
	}
	private void treatMsgImage(Document d,HttpServletResponse response) throws IOException{
		
	}
	private void treatMsgVoice(Document d,HttpServletResponse response) throws IOException{
		String server = getDocText(d,"ToUserName");
		String user = getDocText(d,"FromUserName");
		if(d.getElementsByTagName("Recognition")!=null){//语音识别结果
			String recognition = getDocText(d,"Recognition");
			answerMsgText(response,user,server,"收到你的语音识别："+recognition);
		}else{
			answerMsgText(response,user,server,"收到你的语音");
		}
	}
	private void treatMsgVideo(Document d,HttpServletResponse response) throws IOException{
		
	}
	private void treatMsgLocation(Document d,HttpServletResponse response) throws IOException{
		String server = getDocText(d,"ToUserName");
		String user = getDocText(d,"FromUserName");
		String lat = getDocValue(d,"Location_X");
		String lng = getDocValue(d,"Location_Y");
		//String address = getDocText(d,"Label");
		String url = "http://h.wlyfw.com/ewm/shop!shopNearby?lat="+lat+"&lon="+lng;
		WxOfficialAccount woa = wxOfficialAccountService.loadByCompanyCode(server);
		url = weixinToolService.convertUrlToOauthUrl(woa.getId(),url);
		String s = "<a href='"+url+"'>点击查看</a>";
		answerMsgText(response,user,server,s);
	}
	private void treatMsgLink(Document d,HttpServletResponse response) throws IOException{
		
	}
	private void treatMsgEvent(Document d,HttpServletResponse response) throws IOException{
		String server = getDocText(d,"ToUserName");
		String user = getDocText(d,"FromUserName");
		String event = getDocText(d,"Event");
		WxOfficialAccount woa = wxOfficialAccountService.loadByCompanyCode(server);
		if("subscribe".equals(event)){//关注
			logger.info("关注推送");
			logger.info("被关注公众号:"+woa.getCompanyName());
			//保存微信用户表
			FansItem item = fansService.loadByOpenId(user);
			if(item!=null){
				return;
			}
			String userResult=weixinToolService.loadUesrInfo(woa.getId(),user);
			JsonConfig config = new JsonConfig();
			config.setExcludes(new String[]{"groupid","tagid_list"});
			JSONObject ju = JSONObject.fromObject(userResult,config);
			logger.info(ju.toString());
			//Fans weixinUser=(Fans)JSONObject.toBean(ju,Fans.class);
			
			Fans u = fansService.loadByUnionId(ju.getString("unionid"));
			if(u==null){
				u = new Fans();
				u.setSubscribeTime(ju.getLong("subscribe_time")*1000);
				u.setSubscribe(true);
				u.setUnionId(ju.getString("unionid"));
				if (StringUtils.isNotEmpty(ju.getString("headimgurl"))) {
					u.setHeadimgurl(ju.getString("headimgurl").substring(0, ju.getString("headimgurl").length()-1));
				}
				fansService.save(u);
				
			}else{
				u.setSubscribeTime(ju.getLong("subscribe_time")*1000);
				u.setSubscribe(true);
				u.setNickname(ju.getString("nickname"));
				u.setCountry(ju.getString("country"));
				u.setSex(ju.getInt("sex"));
				u.setCity(ju.getString("city"));
				u.setProvince(ju.getString("province"));
				if (StringUtils.isNotEmpty(ju.getString("headimgurl"))) {
					u.setHeadimgurl(ju.getString("headimgurl").substring(0, ju.getString("headimgurl").length()-1));
				}
				fansService.update(u);
			}
			FansItem fansItem = fansService.loadByOpenId(ju.getString("openid"), woa.getId());
			if(fansItem==null){
				fansItem = new FansItem();
				fansItem.setFansId(u.getId());
				fansItem.setOpenid(user);
				fansItem.setWoaId(woa.getId());
			}			
			fansItem.setSubscribe(true);
			fansItem.setSubscribeTime(ju.getLong("subscribe_time")*1000);
			fansService.saveFansItem(fansItem);
			
			/*String content = wxOfficialAccountService.loadByCode(WeixinConf.CODE_SUBSCRIBECONTENT).getValue();
			WeixinKeyReply mr = weixinKeyReplyService.findByContent(content);
			if(mr!=null){
				if(WeixinEventReply.REPLY_TYPE_NEWS.equals(mr.getReplyType())){
					answerMsgTextImg(response, user, server, mr.getArticles());
				}else{
					answerMsgText(response,user,server,mr.getrContent());
				}
			}else{
				answerMsgText(response,user,server,content);
			}*/
			//answerMsgText(response,user,server,content);
			/*if(d.getElementsByTagName("Ticket")!=null){//扫描二维码
				String eventKey = getDocText(d,"EventKey");
				if(eventKey!=null){
					answerMsgText(response,user,server,"欢迎扫描关注:"+eventKey);
				}else{
					answerMsgText(response,user,server,"欢迎扫描关注");
				}
				
			}else{
				answerMsgText(response,user,server,"欢迎关注");
			}*/
		}else if("unsubscribe".equals(event)){//取消关注
			FansItem fansItem = fansService.loadByOpenId(user,woa.getId());
			//Fans u = fansService.findUniqueByPreFilter(PreFilters.eq(Fans.PROP_UNIONID,user));
			if(fansItem!=null){
				fansItem.setSubscribe(false);
				fansService.saveFansItem(fansItem);
				logger.info("用户"+user+"已取消关注公众号:"+woa.getCompanyName());
			}
			
		}else if("SCAN".equals(event)){//已关注的扫描事件
			String eventKey = getDocText(d,"EventKey");
			answerMsgText(response,user,server,"欢迎回来:"+eventKey);
		}else if("LOCATION".equals(event)){//采集的地理位置
			String latitude = getDocText(d,"Latitude");
			String longitude = getDocText(d,"Longitude");
			String precision = getDocText(d,"Precision");
			answerMsgText(response,user,server,"您的位置(经度："+latitude+"，纬度："+longitude+"，精确度："+precision+")");
		}else if("CLICK".equals(event)){//菜单事件
			String eventKey = getDocText(d,"EventKey");
			WeixinEventReply er = weixinEventReplyService.findByEventKey(eventKey,woa.getId());
			if(er!=null){
				if(WeixinEventReply.REPLY_TYPE_NEWS.equals(er.getReplyType())){
					answerMsgTextImg(response, user, server, er.getArticles());
				}else{
					answerMsgText(response,user,server,er.getrContent());
				}
			}else{
				answerMsgText(response,user,server,"您点击了菜单："+eventKey);
			}
		}else if("VIEW".equals(event)){//菜单连接事件
			String eventKey = getDocText(d,"EventKey");
			answerMsgText(response,user,server,"您点击了菜单连接："+eventKey);
		}else if("TEMPLATESENDJOBFINISH".equals(event)){//模板消息发送结果通知
//			String status = getDocText(d,"Status");
//			String msgid = getDocValue(d, "MsgID");
			
		}else if("user_get_card".equals(event)){
			//卡券领取事件
			
			/*String cardId = getDocText(d, "CardId");
			String cardCode = getDocText(d,"UserCardCode");
			String isGiveByFriend = getDocText(d, "IsGiveByFriend");
			String oldUser = null;
			String oldCode = null;
			logger.info("=======用户:"+user+"领取卡券:"+cardId+",卡券唯一码:"+cardCode);
			if(isGiveByFriend.equals("1")){
				oldUser = getDocText(d, "FriendUserName");
				oldCode = getDocText(d, "OldUserCardCode");
				logger.info("=======本次为转赠领取,转赠放ID:"+oldUser);
			}
			weixinCardLogService.getCard(user, cardId, cardCode, isGiveByFriend, oldUser, oldCode);
			*/
			
		}
		else if("user_consume_card".equals(event)){
			//卡券核销事件
			/*
			String cardId = getDocText(d, "CardId");
			String cardCode = getDocText(d,"UserCardCode");
			String isGiveByFriend = getDocText(d, "IsGiveByFriend");
			String oldUser = null;
			String oldCode = null;
			logger.info("=======用户:"+user+"领取卡券:"+cardId+",卡券唯一码:"+cardCode);
			if(isGiveByFriend.equals("1")){
				oldUser = getDocText(d, "FriendUserName");
				oldCode = getDocText(d, "OldUserCardCode");
				logger.info("=======本次为转赠领取,转赠放ID:"+oldUser);
			}
			weixinCardLogService.getCard(user, cardId, cardCode, isGiveByFriend, oldUser, oldCode);
			*/
			
		}
	}
	
	
	private void answerMsgText(HttpServletResponse response,String user,String server,String msg) throws IOException{
		response.setCharacterEncoding("UTF-8");
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		sb.append("<ToUserName><![CDATA["+user+"]]></ToUserName>");
		sb.append("<FromUserName><![CDATA["+server+"]]></FromUserName>");
		sb.append("<CreateTime>"+System.currentTimeMillis()+"</CreateTime>");
		sb.append("<MsgType><![CDATA[text]]></MsgType>");
		sb.append("<Content><![CDATA["+msg+"]]></Content>");
		sb.append("</xml>");
		response.getWriter().print(sb.toString());
	}
	private void answerMsgTextImg(HttpServletResponse response,String user,String server,List<WxArticle> articles) throws IOException{
		if(articles==null||articles.size()==0||articles.size()>10){
			answerMsgText(response,user,server,"敬请期待...");
		}else{
			response.setCharacterEncoding("UTF-8");
			StringBuffer sb = new StringBuffer();
			sb.append("<xml>");
			sb.append("<ToUserName><![CDATA["+user+"]]></ToUserName>");
			sb.append("<FromUserName><![CDATA["+server+"]]></FromUserName>");
			sb.append("<CreateTime>"+System.currentTimeMillis()+"</CreateTime>");
			sb.append("<MsgType><![CDATA[news]]></MsgType>");
			sb.append("<ArticleCount>"+articles.size()+"</ArticleCount>");
			sb.append("<Articles>");
			for(WxArticle a:articles){
				sb.append("<item>");
				sb.append("<Title><![CDATA["+a.getTitle()+"]]></Title>");
				sb.append("<Description><![CDATA["+a.getDescription()+"]]></Description>");
				sb.append("<PicUrl><![CDATA["+a.getPicUrl()+"]]></PicUrl>");
				sb.append("<Url><![CDATA["+a.getUrl()+"]]></Url>");
				sb.append("</item>");
			}
			sb.append("<Articles>");
			sb.append("</xml>");
			response.getWriter().print(sb.toString());
		}
	}
	private String getDocValue(Document d,String name){
		if(d.getElementsByTagName(name).getLength()==0) return null;
		Node n = d.getElementsByTagName(name).item(0);
		
		if(n==null){
			return null;
		}else{
			return n.getTextContent();
		}
	}
	private String getDocText(Document d,String name){
		if(d.getElementsByTagName(name).getLength()==0) return null;
		Node n = d.getElementsByTagName(name).item(0).getFirstChild();
		if(n==null){
			return null;
		}else{
			return n.getTextContent();
		}
	}
	
	@RequestMapping(value = "/oauth.do", method = {RequestMethod.GET, RequestMethod.POST})
	public void convertUrlToOauthUrl() {
		try {
			String url = getParameterValue("url").toString();
			String woaIda = getParameterValue("woaId").toString();
			long woaId = Long.parseLong(woaIda);	
			renderText(weixinToolService.convertUrlToOauthUrl(woaId, URLDecoder.decode(url, "UTF-8")));
		} catch (Exception e) {
			renderText("e:服务器出错了，请稍候重试。\n 错误代码：" + e.getMessage());
		}
	}
		
}
