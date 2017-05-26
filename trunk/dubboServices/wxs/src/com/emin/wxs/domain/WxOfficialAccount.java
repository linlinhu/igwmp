/**
 * 
 */
package com.emin.wxs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.emin.base.domain.CompanyEntity;
import com.emin.base.domain.UndeleteableEntity;

/**
 * @author jim
 * 微信公众号
 */
@Entity
@Table(name="wx_official_account",schema="public")
public class WxOfficialAccount extends CompanyEntity implements UndeleteableEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1190082397231007203L;
	public static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public static final String URL_ACCESS_TOKEN_OAUTH2 = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	public static final String URL_ACCESS_TOKEN_OAUTH2_FRESH = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
	public static final String URL_SERVICE_SEND = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	public static final String URL_MASS_SEND = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
	public static final String URL_USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	public static final String URL_USER_INFO_SNS = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	public static final String URL_TEMPLATE_SEND = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	public static final String URL_OAUTH2_CODE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	public static final String URL_MENU_ADD = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	public static final String URL_JSTICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	public static final String URL_ALLUSERS = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=";
	public static final String URL_CARDCODEDEC = "https://api.weixin.qq.com/card/code/decrypt?access_token=ACCESS_TOKEN";
	public static final String URL_CARDUSERD = "https://api.weixin.qq.com/card/code/consume?access_token=ACCESS_TOKEN";
	public static final String URL_CARDCHECK = "https://api.weixin.qq.com/card/code/get?access_token=ACCESS_TOKEN";
	
	//查询卡券列表
	public static final String URL_CARDLIST = "https://api.weixin.qq.com/card/batchget?access_token=ACCESS_TOKEN";
	//查询卡券相信
	public static final String URL_CARDINFO = "https://api.weixin.qq.com/card/get?access_token=ACCESS_TOKEN";
	//微信统一下单地址
	public static final String URL_PAY = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	//TODO 此地址以后要处理，不能在这里写死
	public static final String URL_MARKETING_KEY = "http://qa.emin-tech.com/ewm/weixin-marketing!enter";
	
	
	//微信批量获取粉丝信息
	public static final String URL_USER_LIST = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";
	
	//申请退款
	public static final String URL_REFUND = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	//退款查询
	public static final String URL_REFUNDQUERY = "https://api.mch.weixin.qq.com/pay/refundquery";
	//退款查询
	public static final String URL_ORDERQUERY = "https://api.mch.weixin.qq.com/pay/orderquery";
	
	public static final String URL_LOCATIONINFO = "http://apis.map.qq.com/ws/geocoder/v1/?location=LOCATION&key=2NXBZ-ZVZW2-V37UI-CCIB7-A3OA5-FPFGT";
	public static final String URL_ADDRESSINFO = "http://apis.map.qq.com/ws/geocoder/v1/?address=ADDRESS&key=2NXBZ-ZVZW2-V37UI-CCIB7-A3OA5-FPFGT";
	
	
	public static final String PROP_COMPANY_NAME = "companyName";
	
	public static final String PROP_COMPANY_CODE = "companyCode";
	
	public static final String PROP_COMPANY_ID = "companyId";
	
	private Long createTime;
	
	private Long lastModifyTime;
	
	private int status;
	
	private int type;//公众号类型:1 订阅 2服务 3企业 4小程序
	
	private String companyName;//公众号企业名称
	
	private String apikey;//微信支付API密钥
	
	private String appId;//公众号AppId 用于取accessToken
	
	private String appSecret;// Appsecret 用于取accessToken
	
	private String companyCode;//公众号的微信号 
	
	private String mchId;//微信商户号
	
	private String token;//Token （接入微信必填）微信接入配置所填的Token必须与此Token一致
	
	@Id
	@Override
	@SequenceGenerator(name = "wx_official_account_id_seq", sequenceName = "public.wx_official_account_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wx_official_account_id_seq")
	public Long getId() {
		return super.getId();
	}
	
	@Override
	@Column(name="createtime")
	public Long getCreateTime() {
		return createTime;
	}

	
	@Override
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	
	@Override
	@Column(name="lastmodifytime")
	public Long getLastModifyTime() {
		return lastModifyTime;
	}

	
	@Override
	public void setLastModifyTime(Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	
	@Override
	@Column(name="status")
	public int getStatus() {
		return status;
	}

	
	@Override
	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	@Column(name="company_id")
	public Long getCompanyId() {
		// TODO Auto-generated method stub
		return super.getCompanyId();
	}

	@Column(name="company_name")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name="apikey")
	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

	@Column(name="appid")
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	@Column(name="appsecret")
	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	@Column(name="companycode")
	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	@Column(name="mchid")
	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	
	@Column(name="token")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	@Column(name="type")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	

}
