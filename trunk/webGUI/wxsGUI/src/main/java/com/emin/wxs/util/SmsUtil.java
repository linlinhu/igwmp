package com.emin.wxs.util;

import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/**
 * 通过短信接口发送短信
 */
public class SmsUtil {

    //日志
    private static Logger logger = Logger.getLogger(SmsUtil.class);

   public static final String appkey = "23418660";
   public static final String secret = "8a165352b23911ca87e27663581249d9";

    /**
     * 阿里大鱼短信发送平台接口封装
     */
    public static SMSJSONResult sendVerifyCodeByDayu(String mobile, String purpose) {
        SMSJSONResult jsonResult = new SMSJSONResult();
        String url = "http://gw.api.taobao.com/router/rest";
//        String appkey = "", secret = "";
//        appkey = "23418660";
//        secret = "8a165352b23911ca87e27663581249d9";
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        //公共回传参数，在“消息返回”中会透传回该参数；举例：用户可以传入自己下级的会员ID，在消息返回时，该会员ID会包含在内，用户可以根据该会员ID识别是哪位会员使用了你的应用
        //	req.setExtend("123456");
        //短信类型，传入值请填写normal
        req.setSmsType("normal");
        //短信签名，传入的短信签名必须是在阿里大于“管理中心-短信签名管理”中的可用签名。如“阿里大于”已在短信签名管理中通过审核，则可传入”阿里大于“（传参时去掉引号）作为短信签名。短信效果示例：【阿里大于】欢迎使用阿里大于服务。
        req.setSmsFreeSignName("注册验证");
        //短信模板变量，传参规则{"key":"value"}，key的名字须和申请模板中的变量名一致，多个变量之间以逗号隔开。示例：针对模板“验证码${code}，您正在进行${product}身份验证，打死不要告诉别人哦！”，传参时需传入{"code":"1234","product":"alidayu"}
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        String Param = "{\"purpose\":\'" + purpose + "',\"code\":'" + code + "'}";
        //	System.out.println(Param);
        req.setSmsParamString(Param);
        //短信接收号码。支持单个或多个手机号码，传入号码为11位手机号码，不能加0或+86。群发短信需传入多个号码，以英文逗号分隔，一次调用最多传入200个号码。示例：18600000000,13911111111,13322222222
        req.setRecNum(mobile);
        //短信模板ID，传入的模板必须是在阿里大于“管理中心-短信模板管理”中的可用模板。示例：SMS_585014
        req.setSmsTemplateCode("SMS_12800172");
        AlibabaAliqinFcSmsNumSendResponse rsp;
        try {
            rsp = client.execute(req);
            //	System.out.println(rsp.getBody());
            JSONObject temp = JSON.parseObject(rsp.getBody());
            //	System.out.println(temp.get("error_response").toString());
            if (Tools.isNullOrEmpty(temp.get("error_response"))) {
                //发送成功
                jsonResult.setResultCode(0);
                jsonResult.setResultMsg(code);
            } else {
                jsonResult.setResultCode(-1);
                jsonResult.setResultMsg(temp.get("error_response").toString());
            }

        } catch (ApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return jsonResult;
    }

    // =================================================================================================

    /**
  

    /**
     * 通过模版发送短信
     *
     * @param param    参数
     * @param template 模版
     */
    public static SMSJSONResult sendSmsByTemplate(Map<String, String> param, Map<String, String> template) {
        SMSJSONResult jsonResult = new SMSJSONResult();
        String url = "http://gw.api.taobao.com/router/rest";
//     
//            appkey = "23418660";
//            secret = "8a165352b23911ca87e27663581249d9";
 
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        //公共回传参数，在“消息返回”中会透传回该参数；举例：用户可以传入自己下级的会员ID，在消息返回时，该会员ID会包含在内，用户可以根据该会员ID识别是哪位会员使用了你的应用
        //	req.setExtend("123456");
        //短信类型，传入值请填写normal
        req.setSmsType("normal");
        //短信签名，传入的短信签名必须是在阿里大于“管理中心-短信签名管理”中的可用签名。如“阿里大于”已在短信签名管理中通过审核，则可传入”阿里大于“（传参时去掉引号）作为短信签名。短信效果示例：【阿里大于】欢迎使用阿里大于服务。
        req.setSmsFreeSignName(param.get("templateName"));
        //短信模板变量，传参规则{"key":"value"}，key的名字须和申请模板中的变量名一致，多个变量之间以逗号隔开。示例：针对模板“验证码${code}，您正在进行${product}身份验证，打死不要告诉别人哦！”，传参时需传入{"code":"1234","product":"alidayu"}
        req.setSmsParamString(JSON.toJSONString(template));
        //短信接收号码。支持单个或多个手机号码，传入号码为11位手机号码，不能加0或+86。群发短信需传入多个号码，以英文逗号分隔，一次调用最多传入200个号码。示例：18600000000,13911111111,13322222222
        req.setRecNum(param.get("mobile"));
        //短信模板ID，传入的模板必须是在阿里大于“管理中心-短信模板管理”中的可用模板。示例：SMS_585014
        req.setSmsTemplateCode(param.get("templateId"));
        AlibabaAliqinFcSmsNumSendResponse rsp;
        try {
            rsp = client.execute(req);
//            	System.out.println(rsp.getBody());
            JSONObject temp = JSON.parseObject(rsp.getBody());
//            	System.out.println(temp.get("error_response").toString());
            if (Tools.isNullOrEmpty(temp.get("error_response"))) {
                //发送成功
                jsonResult.setResultCode(0);
                jsonResult.setResultMsg("发送短信成功！");
                logger.info("发送短信成功");
                System.out.printf("发送短信成功");

            } else {
                jsonResult.setResultCode(-1);
                jsonResult.setResultMsg(temp.get("error_response").toString());
                logger.info("发送短信失败");
                System.out.printf("发送短信失败");
            }

        } catch (ApiException e) {
            e.printStackTrace();
            logger.info("发送短信失败");
        }
        return jsonResult;

    }
    
    public static void main(String[] args){
    	sendVerifyCodeByDayu("18685061210","服务员注册");
    }
   
}
