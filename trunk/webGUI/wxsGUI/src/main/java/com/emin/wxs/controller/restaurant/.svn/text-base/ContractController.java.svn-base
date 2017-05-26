package com.emin.wxs.controller.restaurant;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.emin.base.dao.PagedResult;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.base.service.Condition.ConditionOperator;
import com.emin.base.service.Condition.ConditionType;
import com.emin.igwmp.rstm.domain.RestaurantPrivateInfo;
import com.emin.igwmp.rstm.domain.RestaurantPublicInfo;
import com.emin.wxs.controller.WxsBaseController;
import com.emin.wxs.facade.restaurant.callers.WxsToContractCaller;
import com.emin.wxs.vo.restaurant.ContractVO;
import com.emin.wxs.vo.restaurant.CooperationVO;
import com.emin.wxs.vo.restaurant.OperatorVO;

@Controller
@RequestMapping("/contract")
public class ContractController extends WxsBaseController {

    private Logger logger = LoggerFactory.getLogger(ContractController.class);

    @Autowired
    private WxsToContractCaller wxsToContractCaller;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/page.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JSONObject loadContractByCondition() {
    	List<Condition> conditions = new ArrayList<Condition>();
        PagedResult<RestaurantPrivateInfo>  poResult = null;
        List<ContractVO> voResult = new ArrayList<ContractVO>();
        String keyword =(String) getPageData().get("keyword");
        String restaurantId = (String) getPageData().get("restaurantId");
        try {
            if (StringUtils.isNotBlank(keyword)) {
                conditions.add(new Condition("restaurantPublicInfo.name", ConditionOperator.LIKE, ConditionType.CHARACTER, "%" + keyword + "%"));
                conditions.add(new Condition("corperationName", ConditionOperator.LIKE, ConditionType.CHARACTER, "%" + keyword + "%"));
                conditions.add(new Condition("restaurantPublicInfo.admins.name", ConditionOperator.LIKE, ConditionType.CHARACTER, "%" + keyword + "%"));
            }   
            if (StringUtils.isNotBlank(restaurantId) && ( Long.parseLong(restaurantId))!=-1) {
                conditions.add(new Condition("restaurantPublicInfo.id", ConditionOperator.EQ, ConditionType.OTHER, Long.parseLong(restaurantId)));
            }
           poResult = wxsToContractCaller.queryContractByCondition(getPageRequestData(), conditions);
    		
            List<RestaurantPrivateInfo> resultList = poResult.getResultList();
    		
    		for(RestaurantPrivateInfo po:resultList){    		
    			int cascaseDepth = Const.CASCADE_DEPTH_VALUE;
    			voResult.add(ContractVO.convertPoToVo(po,cascaseDepth));
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
   public JSONObject saveOrUpdateContract(@RequestParam String jsonStr) {
 //  public JSONObject saveOrUpdateContract(@RequestBody ContractVO contractVo) {
        JSONObject json = new JSONObject();
         String message = "";
         boolean success = false;
         logger.info("saveOrUpdateContract 合同json字符串："+jsonStr);
         try {
        	ContractVO contractVo = JSONObject.parseObject(jsonStr, ContractVO.class);
             if(contractVo == null){
                 throw new EminException("参数异常");
             }
 	   		Long id = contractVo.getId();
 	    	RestaurantPrivateInfo po = null;
 	   		if(id!=null){
 	   		   po = wxsToContractCaller.queryContractById(id);
 	   		} 	   	    
 	   	    int cascadeDepth = Const.CASCADE_DEPTH_VALUE;
 	   		po = ContractVO.convertVoToPo(po, contractVo,cascadeDepth);            
 	   	     wxsToContractCaller.saveContract(po);
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
     * 删除合同信息
     * @param id
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/delete.do")
    @ResponseBody
    public JSONObject deleteContract(@RequestParam(required = true) Long id) {
        JSONObject json = new JSONObject();
        String message = "";
        boolean success = false;
        try {
        	RestaurantPrivateInfo po = wxsToContractCaller.queryContractById(id);
        	if(po!=null){
        		  po.setStatus(-1);
        		  wxsToContractCaller.saveContract(po);
        		  success = true;
           		  message = "";
        	}
        	else{
        		 success = false;
        		 message = "饭店合同信息删除失败";
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
    
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/getRgstNum.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JSONObject getRgstNum(@RequestParam(required = true) boolean isSigned) {
        JSONObject json = new JSONObject();
        String message = "";
        String result = "";
        boolean success = false;
        try {
           String rgstNum = wxsToContractCaller.getRgstNumBySignedFlag(isSigned);
           success = true;
           result = rgstNum;
        } catch (EminException e) {
            logger.error("获取合同编号失败!");
            success = true;
            message = e.getLocalizedMessage();
            e.printStackTrace();
        }
        json.put("success", success);
        json.put("message", message);
        json.put("result", result);
        return json;
    }
    
}
