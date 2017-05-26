package com.emin.wxs.controller.restaurant;

import java.util.ArrayList;
import java.util.List;

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
import com.emin.wxs.controller.WxsBaseController;
import com.emin.wxs.facade.restaurant.callers.WxsToRestaurantCaller;
import com.emin.wxs.vo.restaurant.RestaurantVO;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController extends WxsBaseController {

    private Logger logger = LoggerFactory.getLogger(RestaurantController.class);

    @Autowired
    private WxsToRestaurantCaller restaurantCaller;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/page.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JSONObject loadRestaurantByCondition() {
        List<Condition> conditions = new ArrayList<Condition>();
        PagedResult<RestaurantPublicInfo>  poResult = null;
        List<RestaurantVO> voResult = new ArrayList<RestaurantVO>();
        String keyword =(String) getPageData().get("keyword");
        String runType = (String) getPageData().get("runType");
        String id = (String) getPageData().get("id");
        try {
            if (StringUtils.isNotBlank(keyword)) {
                conditions.add(new Condition("name", ConditionOperator.LIKE, ConditionType.CHARACTER, "%" + keyword + "%"));
                conditions.add(new Condition("restaurantPrivateInfo.contractNum", ConditionOperator.LIKE, ConditionType.CHARACTER, "%" + keyword + "%"));
            }
            
            if (StringUtils.isNotBlank(runType) && (Integer.parseInt(runType))!=-1) {
                conditions.add(new Condition("usingScene", ConditionOperator.EQ, ConditionType.OTHER, Integer.parseInt(runType)));
            }
            if (StringUtils.isNotBlank(id) && (Integer.parseInt(id))!=-1) {
                conditions.add(new Condition("id", ConditionOperator.EQ, ConditionType.OTHER, Integer.parseInt(id)));
            }
         
            poResult = restaurantCaller.queryRestaurantPublicInfoByCondition(getPageRequestData(), conditions);
    		
            if(poResult != null){
            	List<RestaurantPublicInfo> resultList = poResult.getResultList();
        		
        		for(RestaurantPublicInfo po:resultList){   
        			 int cascadeDepth = Const.CASCADE_DEPTH_VALUE;
        			voResult.add(RestaurantVO.convertPoToVo(po,cascadeDepth));
        		}    
            }
  		   else{
			  JSONObject json = new JSONObject();
	          json.put("total", 0);
	          json.put("rows",  voResult);
	          return json;
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
 // public JSONObject saveOrUpdateRestaurant(@RequestBody RestaurantVO restaurantVo) {
    	  JSONObject json = new JSONObject();
          String message = "";
          String result = "";
          boolean success = false;
          logger.info("saveOrUpdateRestaurant 饭店json字符串："+jsonStr);
    	try {
    		RestaurantVO restaurantVo = JSONObject.parseObject(jsonStr, RestaurantVO.class);        
            if(restaurantVo == null){
                throw new EminException("参数异常");
            }
	   		Long id = restaurantVo.getId();
	   		RestaurantPublicInfo po = null;
	   		if(id!=null){
		   		po = restaurantCaller.queryRestaurantPublicInfoById(id);
	   		}
	        int cascadeDepth = Const.CASCADE_DEPTH_VALUE;
	   		po = RestaurantVO.convertVoToPo(po, restaurantVo,cascadeDepth);            
            Long poId = restaurantCaller.saveRestaurantInfo(po);
            po.setId(poId);
//            logger.info("饭店信息保存成功  poId = "+poId+"");
//            logger.info("调用一个findbyid 把级联对象返回给页面!");
//            po = restaurantCaller.queryRestaurantPublicInfoById(poId);   
            
            success = true;
            message ="";
            cascadeDepth = Const.CASCADE_DEPTH_VALUE;
            result = JSONObject.toJSONString(RestaurantVO.convertPoToVo(po,cascadeDepth));
        } catch (EminException e) {
            message = e.getLocalizedMessage();
            success = false;
            json.put("success", success);
            json.put("message", message);
            logger.error(e.getLocalizedMessage(), e);
            return json;            
        } catch (Exception e) {
        	 message = e.getLocalizedMessage();
             success = false;
             json.put("success", success);
             json.put("message", message);
             logger.error(e.getMessage(), e);
             return json;          
        }
        json.put("success", success);
        json.put("message", message);
        json.put("result", result);
        return json;
    }

    /**
     * 删除饭店公共信息
     * @param id
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/delete.do")
    @ResponseBody
    public JSONObject deleteRestaurant(@RequestParam(required = true) Long id) {
        JSONObject json = new JSONObject();
        String message = "";
        boolean success = false;
        try {
//        	restaurantCaller.removeRestaurantInfo(id);    
        	RestaurantPublicInfo po = restaurantCaller.queryRestaurantPublicInfoById(id);
            	if(po!=null){
            		  po.setStatus(-1);
            		  restaurantCaller.saveRestaurantInfo(po);
            		  success = true;
               		  message = "";
            	}
            	else{
            		 success = false;
            		 message = "饭店信息删除失败";
            	}           
        } catch (EminException e) {
            message = e.getLocalizedMessage();
            json.put("success", success);
            json.put("message", message);
            logger.error(e.getMessage(), e);
            return json;
        } catch (Exception e) {
            message = e.getMessage();           
            json.put("success", success);
            json.put("message", message);
            logger.error(e.getMessage(), e);
            return json;
        }
        json.put("success", success);
        json.put("message", message);
        return json;
    }

}
