package com.emin.wxs.controller.restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.emin.base.dao.PagedResult;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.base.service.Condition.ConditionOperator;
import com.emin.base.service.Condition.ConditionType;
import com.emin.igwmp.rstm.domain.RestaurantPublicInfo;
import com.emin.igwmp.rstm.domain.RestaurantServantInfo;
import com.emin.wxs.controller.WxsBaseController;
import com.emin.wxs.facade.restaurant.callers.WxsToRestaurantCaller;
import com.emin.wxs.facade.restaurant.callers.WxsToServantCaller;
import com.emin.wxs.util.SMSJSONResult;
import com.emin.wxs.util.SmsUtil;
import com.emin.wxs.vo.restaurant.RestaurantVO;
import com.emin.wxs.vo.waiter.WaiterVO;

@Controller
@RequestMapping("/servant")
public class ServantController extends WxsBaseController {

    private Logger logger = LoggerFactory.getLogger(ServantController.class);

    @Autowired
    private WxsToServantCaller wxsToServantCaller;   
    
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/page.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JSONObject loadServantByCondition() {
    	  List<Condition> conditions = new ArrayList<Condition>();
          PagedResult<RestaurantServantInfo>  poResult = null;
          List<WaiterVO> voResult = new ArrayList<WaiterVO>();
          String keyword =(String) getPageData().get("keyword");
        try {
            if (StringUtils.isNotBlank(keyword)) {
                conditions.add(new Condition("restaurants.name", ConditionOperator.LIKE, ConditionType.CHARACTER, "%" + keyword + "%"));
                conditions.add(new Condition("name", ConditionOperator.LIKE, ConditionType.CHARACTER, "%" + keyword + "%"));
            }
            poResult = wxsToServantCaller.queryServantInfoByCondition(getPageRequestData(), conditions);
    		
            List<RestaurantServantInfo> resultList = poResult.getResultList();
    		
    		for(RestaurantServantInfo po:resultList){    	
    			int cascadeDepth = Const.CASCADE_DEPTH_VALUE;
    			voResult.add(WaiterVO.convertPoToVo(po,cascadeDepth));
    		}    		
        } catch (EminException e) {
            logger.error("分页查询饭店信息失败!");
            e.printStackTrace();
        }
        JSONObject json = new JSONObject();
        json.put("total", poResult.getTotalCount());
        json.put("rows",  voResult);
        return json;
    }
   
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/saveOrUpdate.do", method = {RequestMethod.POST, RequestMethod.PUT})
    @ResponseBody
    public JSONObject saveOrUpdateRestaurant(@RequestParam String jsonStr) {
    //public JSONObject saveOrUpdateServant(@RequestBody WaiterVO waiterVo) {
        JSONObject json = new JSONObject();
        String message = "";
        boolean success = false;
        logger.info("服务员json字符串："+jsonStr);
        try {
        	WaiterVO waiterVo = JSONObject.parseObject(jsonStr, WaiterVO.class);        
            if(waiterVo == null){
                throw new EminException("参数异常");
            }
            Long id = waiterVo.getId();
            RestaurantServantInfo po = null;
      		if(id!=null){
	   	          po = wxsToServantCaller.queryRestaurantServantInfoById(id);
      		}
      		int cascadeDepth = Const.CASCADE_DEPTH_VALUE;
	   		po = WaiterVO.convertVoToPo(po, waiterVo,cascadeDepth);  
	        cascadeDepth = Const.CASCADE_DEPTH_VALUE;
	   		wxsToServantCaller.saveServantInfo(po);
            success = true;
            message = "";
        } catch (EminException e) {
            message = e.getLocalizedMessage();
            success = false;
            logger.error(e.getLocalizedMessage(), e);
        } catch (Exception e) {
            message = e.getMessage();
            success = false;
            logger.error(e.getMessage(), e);
        }
        json.put("success", success);
        json.put("message", message);
        return json;
    }

    /**
     * 删除服务员信息
     * @param id
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/delete.do")
    @ResponseBody
    public JSONObject deleteServant(@RequestParam(required = true) Long id) {
        JSONObject json = new JSONObject();
        String message = "";
        boolean success = false;
        try {
        	RestaurantServantInfo po = wxsToServantCaller.queryRestaurantServantInfoById(id);
        	if(po!=null){
        		  po.setStatus(-1);
        		  wxsToServantCaller.saveServantInfo(po);
        		  success = true;
           		  message = "";
        	}
        	else{
        		 success = false;
        		 message = "饭店服务员信息失败";
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
     * 发送验证码
     * @param phoneNum
     * @param isRegister
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/sendVerifyCode.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JSONObject sendVerifyCode(@RequestParam(required = true) String phoneNum,@RequestParam(required = true) boolean isRegister){
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
     * 服务员注册
     * @param id
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/register.do", method = {RequestMethod.GET, RequestMethod.POST})
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
                 if(servant == null){
                	wxsToServantCaller.bindServantAndRestaurant(servant,phoneNum,name,restaurantId,openId);
 	    			success = true;
                 }
                 else{
                	 message = "该电话号码已经注册了服务员";
                 }	    		
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
     * 服务员登录
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/login.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JSONObject login(@RequestParam(required = true) String verifyCode,
                            @RequestParam(required = true) String phoneNum) {
    	logger.info("verifyCode = "+ verifyCode +",phoneNum = "+ phoneNum);
    	JSONObject json = new JSONObject();
        String message = "";
        boolean success = false;
    	 try {
 	    	if(StringUtils.isNotBlank(verifyCode)&&StringUtils.isNotBlank(phoneNum)){
 	    		 String realVerifyCode = wxsToServantCaller.validateServantVerifyCode(phoneNum,true);
   	    		if(verifyCode.equals(realVerifyCode)){   	    			
   	    			logger.info("验证码正确"); 
   	    		}
   	    		else{
   	    			message = "验证码有误";
   	                json.put("message", message);
   	    			return json;
   	    		}	 
   	    		
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
     * 获取饭店列表
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/restaurantList.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JSONObject loadrestaurantListByKeyword() {
    	  List<Condition> conditions = new ArrayList<Condition>();
          String keyword =(String) getPageData().get("keyword");
          List<Map<String,Object>> results =  new ArrayList<Map<String,Object>>();
        try {
            if (StringUtils.isNotBlank(keyword)) {
                conditions.add(new Condition("restaurants.name", ConditionOperator.LIKE, ConditionType.CHARACTER, "%" + keyword + "%"));
            }
            results = wxsToServantCaller.queryRestaurantsByCondition(conditions);   		
        } catch (EminException e) {
            logger.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
        JSONObject json = new JSONObject();
        json.put("rows",  results);
        return json;
    }
    
}
