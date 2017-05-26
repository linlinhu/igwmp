package com.emin.wxs.controller.restaurant;

import com.alibaba.fastjson.JSONObject;
import com.emin.base.dao.PagedResult;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.base.service.Condition.ConditionOperator;
import com.emin.base.service.Condition.ConditionType;
import com.emin.igwmp.rstm.domain.CooperationPlanInfo;
import com.emin.wxs.controller.WxsBaseController;
import com.emin.wxs.facade.restaurant.callers.WxsToContractCaller;
import com.emin.wxs.vo.restaurant.CooperationVO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cooperation")
public class CooperationPlanController extends WxsBaseController {

    private Logger logger = LoggerFactory.getLogger(CooperationPlanController.class);

    @Autowired
    private WxsToContractCaller wxsToContractCaller;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/page.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JSONObject loadCooperationByCondition() {
    	List<Condition> conditions = new ArrayList<Condition>();
        PagedResult<CooperationPlanInfo>  poResult = null;
        List<CooperationVO> voResult = new ArrayList<CooperationVO>();
        String keyword =(String) getPageData().get("keyword");
        String roleType = (String) getPageData().get("roleType");
        try {
            if (StringUtils.isNotBlank(keyword)) {
                conditions.add(new Condition("name", ConditionOperator.LIKE, ConditionType.CHARACTER, "%" + keyword + "%"));               
            }  
            if (StringUtils.isNotBlank(roleType)) {
                conditions.add(new Condition("roleType", ConditionOperator.EQ, ConditionType.CHARACTER, Integer.valueOf(roleType)));
            } 
            poResult = wxsToContractCaller.queryCooperationPlanInfoByCondition(getPageRequestData(), conditions);
    		
            List<CooperationPlanInfo> resultList = poResult.getResultList();
    		
    		for(CooperationPlanInfo po:resultList){  
    			 int cascadeDepth = Const.CASCADE_DEPTH_VALUE;
    			voResult.add(CooperationVO.convertPoToVo(po,cascadeDepth));
    		}  		

        } catch (EminException e) {
            logger.error("分页查询合同信息失败!");
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
    public JSONObject saveOrUpdateCooperationPlan(String jsonStr) {
   // public JSONObject saveOrUpdateOperator(@RequestBody CooperationVO cooperationVO) {
    	 JSONObject json = new JSONObject();
         String message = "";
         boolean success = false;
         try {
        	 CooperationVO cooperationVO = JSONObject.parseObject(jsonStr, CooperationVO.class);
             if(cooperationVO == null){
                 throw new EminException("参数异常");
             }
 	   		Long id = cooperationVO.getId();
     	   	CooperationPlanInfo po = null;
 	   		if(id != null){
 	   		  po = wxsToContractCaller.queryCooperationPlanById(id);
 	   		} 	     
 	        int cascadeDepth = Const.CASCADE_DEPTH_VALUE;
 	   		po = CooperationVO.convertVoToPo(po, cooperationVO,cascadeDepth);            
 	   	    wxsToContractCaller.saveCooperationPlanInfo(po);
 	   	   success = true;
           message = "";
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
         return json;
    }

    /**
     * 删除合作方案信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete.do")
    @ResponseBody
    public JSONObject deleteCooperationPlan(@RequestParam(required = true) Long id) {
        JSONObject json = new JSONObject();
        String message = "";
        boolean success = false;
        try {
        	CooperationPlanInfo po = wxsToContractCaller.queryCooperationPlanById(id);
        	if(po!=null){
        		  po.setStatus(-1);
        		  wxsToContractCaller.saveCooperationPlanInfo(po);
        		  success = true;
           		  message = "";
        	}
        	else{
        		 success = false;
        		 message = "合作方案信息删除失败";
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

}
