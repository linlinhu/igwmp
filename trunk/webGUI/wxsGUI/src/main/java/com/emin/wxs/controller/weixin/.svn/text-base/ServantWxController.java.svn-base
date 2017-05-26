package com.emin.wxs.controller.weixin;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.base.dao.PagedResult;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.base.service.Condition.ConditionOperator;
import com.emin.base.service.Condition.ConditionType;
import com.emin.igwmp.rstm.domain.RestaurantServantInfo;
import com.emin.igwmp.rstm.domain.ServantExchangeIntegralRecord;
import com.emin.igwmp.rstm.domain.ServantVenoutRecord;
import com.emin.wxs.controller.WxsBaseController;
import com.emin.wxs.controller.restaurant.Const;
import com.emin.wxs.domain.Fans;
import com.emin.wxs.domain.FansItem;
import com.emin.wxs.facade.WxOfficialAccountFacade;
import com.emin.wxs.facade.restaurant.callers.WxsToServantCaller;
import com.emin.wxs.service.FansService;
import com.emin.wxs.util.SMSJSONResult;
import com.emin.wxs.util.SmsUtil;
import com.emin.wxs.vo.waiter.WaiterVO;
//import com.emin.wxs.facade.trading.callers.WxsToPaymentCaller;


@Controller
@RequestMapping("/wxservant")
public class ServantWxController extends WxsBaseController {
	
	private Logger logger = LoggerFactory.getLogger(ServantWxController.class);
	
	@Autowired
	@Qualifier("wxsToServantCaller")
	private WxsToServantCaller wxsToServantCaller;
	@Autowired
	private WxOfficialAccountFacade wxOfficialAccountFacade;
 
	
	/**
	 * @return 主要入口
	 * 判断用户是否存在，是否关注公众号
	 * @throws Exception 
	 */
	private boolean isValid(String openId){
		boolean result = false;
		List<Condition> conditions = new ArrayList<>();
		try{
			FansItem fansItem = wxsToServantCaller.loadFansItemByOpenId(openId);
			conditions.add(new Condition("fansId", ConditionOperator.EQ, ConditionType.OTHER,fansItem.getFansId()));
			RestaurantServantInfo servantInfo = wxsToServantCaller.queryUniqueServant(conditions);
			if(servantInfo != null){
				result = true;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param 
	 * 用户中心
	 * @return 用户中心
	 * @throws Exception 
	 */
	@RequestMapping("/center.htm")
	public ModelAndView center(String openId) throws Exception {
		ModelAndView mv = new ModelAndView("wxWebPage/waiter/waiter_personal");
		String basePath = getBasePath();
		mv.addObject("basePath",basePath);
		List<Condition> conditions = new ArrayList<>();
		try {
			FansItem fansItem = wxsToServantCaller.loadFansItemByOpenId(openId);
			conditions.add(new Condition("fansId", ConditionOperator.EQ, ConditionType.CHARACTER,fansItem.getId()));
			RestaurantServantInfo servantInfo = wxsToServantCaller.queryUniqueServant(conditions); 
			mv.addObject("servantInfo",servantInfo);
			mv.addAllObjects(wxOfficialAccountFacade.getWxAboutConf(getRequest()));
			mv.addObject("openId",openId);
        } catch (Exception e) {
            mv.addObject("msg", "对不起，服务器忙请稍后重试！");
            e.printStackTrace();
        }
		
		/***获取当月第一天时间戳***/
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar c = Calendar.getInstance();   
	    c.add(Calendar.MONTH, 0);
	    c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
	    String first = format.format(c.getTime())+" 00:00:00";
	    Date date = format.parse(first);  
		try{
			List<Condition> conditionGetPoint = new ArrayList<>();
			conditionGetPoint.add(new Condition("exchangeTime", ConditionOperator.GE, ConditionType.OTHER, date.getTime()));
			conditionGetPoint.add(new Condition("restaurantServantInfo.cellphone", ConditionOperator.EQ, ConditionType.OTHER, openId));
			//wxsToServantCaller.
			//PagedResult<ServantExchangeIntegralRecord> result= wxsToServantCaller. .queryUniqueServant(conditionGetPoint);
		}catch(Exception e){
			 mv.addObject("msg", "对不起，服务器忙请稍后重试！");
	         e.printStackTrace();
		}
		return mv;
	}
	
	
	
	
	/**
	 *	
	 * @return 服务员待打酒列表，未打酒列表
	 * @throws Exception 
	 */
	@RequestMapping("/record.htm")
	public ModelAndView record(int vendoutStatus,String openId) throws Exception {
		ModelAndView mv = new ModelAndView("wxWebPage/waiter/waiter_take_wine");
		String basePath = getBasePath();
		mv.addObject("basePath",basePath);
		List<Condition> conditions = new ArrayList<>();
		FansItem fans = wxsToServantCaller.loadFansItemByOpenId(openId);
		conditions.add(new Condition("restaurantServantInfo.fansId", ConditionOperator.EQ, ConditionType.CHARACTER,fans.getFansId()));
		conditions.add(new Condition("vendoutStatus", ConditionOperator.EQ, ConditionType.OTHER,vendoutStatus));
		PagedResult<ServantVenoutRecord> recordList= wxsToServantCaller.queryServantVenoutRecordByCondition(getPageRequestData(), conditions);
		mv.addObject("recordList",recordList);
		mv.addAllObjects(wxOfficialAccountFacade.getWxAboutConf(getRequest()));
		return mv;
	}
	
	/**
	 *  查询服务员积分排名-服务员社区
	 */
	@RequestMapping("/community.htm")
	public ModelAndView community() throws Exception {
		ModelAndView mv = new ModelAndView("wxWebPage/waiter/waiter_community");
		String basePath = getBasePath();
		mv.addObject("basePath",basePath);
		JSONArray servantRankList= wxsToServantCaller.findTakeWineRanking();
		mv.addObject("servantRankList",servantRankList);
		return mv;
	}
	
	/**
	 *  查询服务员积分
	 */
	@RequestMapping("/point.htm")
	public ModelAndView point(String cellphone) throws Exception {
		ModelAndView mv = new ModelAndView("wxWebPage/waiter/waiter_check_details");
		String basePath = getBasePath();
		mv.addObject("basePath",basePath);
		mv.addObject("cellphone",cellphone);
		return mv;
	}
	

	@RequestMapping("/pointsearch.htm")
	public ModelAndView pointsearch(String cellphone,Long startDate,Long endDate) throws Exception {
		ModelAndView mv = new ModelAndView("wxWebPage/waiter/waiter_earnings_details");
		String basePath = getBasePath();
		mv.addObject("basePath",basePath);
          //重session中获取
		JSONArray servantEarningList = wxsToServantCaller.findTakeWineList((long) 10, startDate, endDate);  
		//TODO wxsToServantCaller.createServantVenoutRecordsByOpenIdAndTakeCode(openId, takeCode);
		mv.addObject("servantEarningList",servantEarningList);
		return mv;
	}
	
	
	
	/**
	 * @return 获取打酒码，进行展示
	 * @param  取酒码 codeInfo.takeCode
	 * @throws Exception 
	 */
	@RequestMapping("/takewine.htm")
	public ModelAndView takewine(String takeCode,String openId) throws Exception {
		ModelAndView mv = new ModelAndView("wxWebPage/waiter/waiter_get_tableNo");
		String basePath = getBasePath();
		Long result = null;
		boolean success = false;
		String message = "";
		try{
			result = wxsToServantCaller.createServantVenoutRecordsByOpenIdAndTakeCode(openId, takeCode);
			if(result==-1L){
				message = "订单获取失败.";
			}else{
				mv.addObject("orderId",result);
				message = "订单获取成功.";
				success = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		mv.addObject("basePath",basePath);
		mv.addObject("message",message);
		mv.addObject("success",success);
		mv.addObject("takeCode",takeCode);
		return mv;
	}
	
	/**
	 * @return 通过微信公众号的扫一扫对取酒码进行扫码
	 * @throws Exception 
	 */
	
	@RequestMapping("/scan.htm")
	public ModelAndView scan() throws Exception {
		Map<String, Object> woaInfo = wxOfficialAccountFacade.getWxAboutConf(getRequest());
		String openId =woaInfo.get("openId").toString();
		boolean isServant = isValid(openId);
		String url = "";
		if(isServant){
			url = "wxWebPage/waiter/scanCode";
		}else{
			url = "wxWebPage/waiter/waiterLogin";
		}
		ModelAndView mv = new ModelAndView(url);
		
		String basePath = getBasePath();
		mv.addObject("basePath",basePath);
		try {
			mv.addAllObjects(wxOfficialAccountFacade.getWxAboutConf(getRequest()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}
	

	
	/**
	 * 先通过localStorage判断是否有用户信息
	 * 如果有直接登录，如果没有，到登录界面
	 * @throws Exception 
	 */
	@RequestMapping("/wxlogin.htm")
	public ModelAndView wxlogin(String cellphone,String codeInfo,String verifyCode){
		ModelAndView mv =new ModelAndView("wxWebPage/waiter/waiterLogin");
		logger.info("开始验证审核情况"); 
		String message = "请先完成注册申请";
		boolean success = false;
		String basePath = getBasePath();
		if(StringUtils.isNotBlank(cellphone)){
			List<Condition> conditions = new ArrayList<Condition>();
	        conditions.add(new Condition("cellphone", ConditionOperator.EQ, ConditionType.CHARACTER, cellphone));
	        RestaurantServantInfo servant =   wxsToServantCaller.queryUniqueServant(conditions);
	          if(servant != null){
	         	  int auditingStatus = servant.getAuditingStatus();
	         	  if(auditingStatus == Const.SERVANT_AUDITING_STATUS_UNAUDITING ){
	         		 message = "对不起，您提交的申请还未进行审核，请耐心等待";
	         		success = false;
	         		logger.info("对不起，您提交的申请还未进行审核，请耐心等待"); 
	         		
	         	  }
	         	  else if(auditingStatus == Const.SERVANT_AUDITING_STATUS_DENIED ){
	         		 message = "对不起，您的审核未通过";
	         		 success = false;
	         		 logger.info("对不起，您的审核未通过"); 
	         	  }
	         	  else if(auditingStatus ==Const.SERVANT_AUDITING_STATUS_PASS ){
	         		 message = "";
	         		 success = true;
	         		 logger.info("审核通过");
	         		 
	         		 /*****
	         		  *如果有codeInfo,表示是通过扫客户取酒二维码过来的,直接进入打酒界面***
	         		  *如果没有,进入用户中心
	         		  ***/
	         		 mv.addObject("cellphone",cellphone); 
	         		 if(StringUtils.isNotBlank(codeInfo)){
	         			JSONObject json = JSONObject.fromObject(codeInfo);
	         			mv.addObject("codeInfo",json);
	         			mv=new ModelAndView("wxWebPage/waiter/waiter_get_tableNo");
	         		 }else{
	         			mv=new ModelAndView("wxWebPage/waiter/waiter_personal");
	         		 }
	         	  }    	                   	  
	          }
	          else{
	         	 message = "无效号码，请先完成注册申请";
	         	 success = false;
	         	 logger.info("无效号码，请先完成注册申请"); 
	          }	 
		}
		mv.addObject("success",success);
		mv.addObject("basePath",basePath);
		mv.addObject("message",message);
		return mv;
	}
	
	
	/**
	 * 先通过localStorage判断是否有用户信息
	 * 如果有直接登录，如果没有，到登录界面
	 * @throws Exception 
	 */
	@RequestMapping("/sendVerifyCode.do")
	@ResponseBody
	 public JSONObject sendVerifyCode(String phoneNum,boolean isRegister){
    	JSONObject json = new JSONObject();
        String message = "";
        boolean success = false;
        try {
        	String action = isRegister?"注册":"登录";
        	SMSJSONResult smsResult = SmsUtil.sendVerifyCodeByDayu(phoneNum, "服务员"+action);
        	if(smsResult.getResultCode()==0){        		 
        		 int code = (int)smsResult.getResultMsg();
        		 logger.info("验证码发送成功 code = " + code+"");
        		 wxsToServantCaller.putServantVerifyCode(phoneNum, code+"",isRegister);
        		 logger.info("保存验证码到redis成功");
        		 success = true;
        	}
        } catch (EminException e) {
            message = e.getLocalizedMessage();
            logger.error(e.getLocalizedMessage(), e);
        } catch (Exception e) {
            message = e.getMessage();
            logger.error(e.getMessage(), e);
        }
        json.put("success", success);
        json.put("message", message);
        return json;
    }
	
	/**
	 * 注册登录界面
	 * @param codeInfo
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/index.htm")
	public ModelAndView index() throws Exception {
		Map<String, Object> woaInfo = wxOfficialAccountFacade.getWxAboutConf(getRequest());
		String openId =woaInfo.get("openId").toString();
		boolean isServant = isValid(openId);
		String url ="";
		
		if(isServant){
			url = "wxWebPage/waiter/waiter_personal";
		}else{
			url = "wxWebPage/waiter/waiterLogin";
		}
		ModelAndView mv = new ModelAndView(url);
		mv.addAllObjects(woaInfo);
		String basePath = getBasePath(); 
		mv.addObject("basePath",basePath);
		return mv;
	}
	
	
	
	/**
     * 服务员注册
     * @param id
     * @return
     */
    @RequestMapping("/register.do")
    @ResponseBody
    public JSONObject resigterServant(@RequestParam(required = true) String verifyCode,
    		                         @RequestParam(required = true) String phoneNum,
    		                         @RequestParam(required = true) Long restaurantId,
    		                         @RequestParam(required = true) String name,
    		                         @RequestParam(required = true) String openId) { 
    	logger.info("verifyCode = "+ verifyCode +",phoneNum = "+ phoneNum + ",restaurantId = "+ restaurantId+",name = "+ name);
    	JSONObject json = new JSONObject();
        String message = "";
        boolean success = false;
        try {
        	 String realVerifyCode = wxsToServantCaller.validateServantVerifyCode(phoneNum,true);
	    		if(verifyCode.equals(realVerifyCode)){
	    			logger.info("验证码正确"); 
	    		}
	    		else{
	    			message = "验证码有误";
   	                json.put("message", message);
   	    			return json;
	    		}
	    		
	    		if(StringUtils.isNotBlank(verifyCode)&&StringUtils.isNotBlank(phoneNum)){
		    		 List<Condition> conditions = new ArrayList<Condition>();
	                 conditions.add(new Condition("cellphone", ConditionOperator.EQ, ConditionType.CHARACTER, phoneNum));
	                 RestaurantServantInfo servant =   wxsToServantCaller.queryUniqueServant(conditions);
	                 if(servant!=null){
	                	 Long fansId = servant.getFansId();
	                	 if(fansId!=null){
	                		 message = "该号码已被注册，请直接登录";
	            		     json.put("success", success);
	            	         json.put("message", message);
	                		 return json;
	                	 }
	                 }
	        	     wxsToServantCaller.bindServantAndRestaurant(servant,phoneNum,name,restaurantId,openId);
				     success = true;    		
		    	}
		    	else{
		    		message = "传入参数有误";
		    	}
        } catch (EminException e) {
            message = e.getLocalizedMessage();
            logger.error(e.getLocalizedMessage(), e);
        } catch (Exception e) {
            message = e.getMessage();
            logger.error(e.getMessage(), e);
        }
        json.put("success", success);
        json.put("message", message);
    	return json;   		
    }
    
    
    
    /**
     * 扫码完成后，输入桌号并保持保存
     */
    @RequestMapping(value = "/saveTable.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JSONObject saveTableNo(int tableNumber,
    							  @RequestParam(required = true) Long id) {
    	logger.info("verifyCode = "+tableNumber);
    	JSONObject json = new JSONObject();
        String message = "";
        boolean success = false;
    	 try {
    		 ServantVenoutRecord record = wxsToServantCaller.queryServantVenoutRecordById(id);
    		 record.setTableNum(tableNumber);
    		 wxsToServantCaller.saveServantVenoutRecord(record);
    		 success = true;
         } catch (EminException e) {
             message = e.getLocalizedMessage();
             logger.error(e.getLocalizedMessage(), e);
         } catch (Exception e) {
             message = e.getMessage();
             logger.error(e.getMessage(), e);
         }
         json.put("success", success);
     	 return json;   		
    }
    
    
    /**
     * 扫码完成后，输入桌号并保持保存
     */
    @RequestMapping(value = "/getOrderInfo.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JSONObject orderInfo(@RequestParam(required = true) Long orderId) {
    	logger.info("id = "+orderId);
    	JSONObject json = new JSONObject();
        String message = "";
        boolean success = false;
    	 try {
    		 ServantVenoutRecord record = wxsToServantCaller.queryServantVenoutRecordById(orderId);
    		 json.put("takeCode",record.getTakeCode());
    		 json.putAll(record.getVendeeInfo());
    		 success = true;
         } catch (EminException e) {
             message = e.getLocalizedMessage();
             logger.error(e.getLocalizedMessage(), e);
         } catch (Exception e) {
             message = e.getMessage();
             logger.error(e.getMessage(), e);
         }
         json.put("success", success);
     	 return json;   		
    }
    
    @RequestMapping(value = "/login.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JSONObject login(@RequestParam(required = true) String verifyCode,
                            @RequestParam(required = true) String phoneNum) {
    	logger.info("verifyCode = "+ verifyCode +",phoneNum = "+ phoneNum);
    	JSONObject json = new JSONObject();
        String message = "";
        boolean success = false;
    	 try {
 	    	//if(StringUtils.isNotBlank(verifyCode)&&StringUtils.isNotBlank(phoneNum)){
 	    		/* String realVerifyCode = wxsToServantCaller.validateServantVerifyCode(phoneNum,false);
   	    		if(verifyCode.equals(realVerifyCode)){   	    			
   	    			logger.info("验证码正确"); 
   	    		}
   	    		else{
   	    			message = "验证码有误";
   	                json.put("message", message);
   	    			return json;
   	    		}	 */
   	    		
   	    		logger.info("开始验证审核情况"); 
 	    	    List<Condition> conditions = new ArrayList<Condition>();
                conditions.add(new Condition("cellphone", ConditionOperator.EQ, ConditionType.CHARACTER, phoneNum));
                RestaurantServantInfo servant =   wxsToServantCaller.queryUniqueServant(conditions);
                
                  if(servant != null){
                 	  int auditingStatus = servant.getAuditingStatus();
                 	  if(auditingStatus == Const.SERVANT_AUDITING_STATUS_UNAUDITING ){
                 		 message = "对不起，您提交的申请还未进行审核，请耐心等待";
                 		logger.info("对不起，您提交的申请还未进行审核，请耐心等待"); 
                 	  }
                 	  else if(auditingStatus == Const.SERVANT_AUDITING_STATUS_DENIED ){
                 		 message = "对不起，您的审核未通过";
                 		logger.info("对不起，您的审核未通过"); 
                 	  }
                 	  else if(auditingStatus ==Const.SERVANT_AUDITING_STATUS_PASS ){
                 		 message = "";
                 		 success = true;
                 		logger.info("审核通过"); 
                 	  }    	                   	  
                  }
                  else{
                 	 message = "无效号码，请先完成注册申请";
                 	 logger.info("无效号码，请先完成注册申请"); 
                  }	    		
                  /*}
 	    	else{
 	    		message = "传入参数有误";
 	    	}*/
         } catch (EminException e) {
             message = e.getLocalizedMessage();
             logger.error(e.getLocalizedMessage(), e);
         } catch (Exception e) {
             message = e.getMessage();
             logger.error(e.getMessage(), e);
         }
         json.put("success", success);
         json.put("message", message);
     	return json;   		
    }
    
    @RequestMapping(value = "/loginMachine.do", method = {RequestMethod.GET, RequestMethod.POST})
    public JSONObject loginMachine(String machineTag) throws Exception{
    	JSONObject json = new JSONObject();
    	String message ="";
    	Map<String, Object> woaInfo = wxOfficialAccountFacade.getWxAboutConf(getRequest());
		String openId =woaInfo.get("openId").toString();
		boolean isServant = isValid(openId);
		List<Condition> conditions = new ArrayList<>();
		if(isServant){
			FansItem fansItem = wxsToServantCaller.loadFansItemByOpenId(openId);
			conditions.add(new Condition("fansId", ConditionOperator.EQ, ConditionType.OTHER,fansItem.getFansId()));
			RestaurantServantInfo servantInfo = wxsToServantCaller.queryUniqueServant(conditions);
			String cellphone =servantInfo.getCellphone();
			try{
				wxsToServantCaller.machineScanLogin(cellphone, machineTag);
				message="成功";
			} catch (Exception e){
				message = e.getMessage();
	            logger.error(e.getMessage(), e);
			}
		}
		json.put("success", isServant);
		json.put("message", message);
		return json;
    	
    }
}