package com.emin.wxs.controller.restaurant;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.emin.base.dao.PagedResult;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.base.service.Condition.ConditionOperator;
import com.emin.base.service.Condition.ConditionType;
import com.emin.igwmp.rstm.domain.ServantVenoutRecord;
import com.emin.wxs.controller.WxsBaseController;
import com.emin.wxs.facade.restaurant.callers.WxsToServantCaller;
import com.emin.wxs.vo.waiter.WaiterServiceRecordVO;

@Controller
@RequestMapping("/waiterServiceRecord")
public class ServantVendoutRecordController extends WxsBaseController {

    private Logger logger = LoggerFactory.getLogger(ServantVendoutRecordController.class);

    @Autowired
    private WxsToServantCaller wxsToServantCaller;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/page.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JSONObject loadVendoutRecordByCondition() {
    	List<Condition> conditions = new ArrayList<Condition>();
        PagedResult<ServantVenoutRecord>  poResult = null;
        List<WaiterServiceRecordVO> voResult = new ArrayList<WaiterServiceRecordVO>();
        String keyword =(String) getPageData().get("keyword");
        String beginDate =(String) getPageData().get("beginDate");
        String endDate =(String) getPageData().get("endDate");
        String waiterId =(String) getPageData().get("waiterId");
        try {
            if (StringUtils.isNotBlank(keyword)) {
            	 conditions.add(new Condition("restName", ConditionOperator.LIKE, ConditionType.CHARACTER, "%" + keyword + "%"));
                 conditions.add(new Condition("servantName", ConditionOperator.LIKE, ConditionType.CHARACTER, "%" + keyword + "%"));
                 conditions.add(new Condition("orderNo", ConditionOperator.LIKE, ConditionType.CHARACTER, "%" + keyword + "%"));
            }            
            if(StringUtils.isNotBlank(waiterId)){
            	 Long wId = Long.parseLong(waiterId);
            	 conditions.add(new Condition("restaurantServantInfo.id", ConditionOperator.EQ, ConditionType.CHARACTER, wId));            	
            }
            if(StringUtils.isNotBlank(beginDate)){
           	 Long bDate = Long.parseLong(beginDate);           	
           	 conditions.add(new Condition("createTime", ConditionOperator.GE, ConditionType.CHARACTER, bDate));            	
           }
	       if(StringUtils.isNotBlank(endDate)){
	          	 Long eDate = Long.parseLong(endDate);           	
	          	 conditions.add(new Condition("createTime", ConditionOperator.LT, ConditionType.CHARACTER, eDate));            	
	       }
           poResult = wxsToServantCaller.queryServantVenoutRecordByCondition(getPageRequestData(), conditions);
    		
            List<ServantVenoutRecord> resultList = poResult.getResultList();
    		
    		for(ServantVenoutRecord po:resultList){    		
    			 int cascadeDepth = Const.CASCADE_DEPTH_VALUE;
    			voResult.add(WaiterServiceRecordVO.convertPoToVo(po,cascadeDepth));
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
   

//    @RequestMapping(value = "/saveOrUpdate.do", method = {RequestMethod.POST, RequestMethod.PUT})
//    @ResponseBody
//    public JSONObject saveOrUpdateContract(ContractVo contractInfo) {
//    	 JSONObject json = new JSONObject();
//         String message = "";
//         boolean success = false;
//         try {
//             if(contractInfo == null){
//                 throw new EminException("参数异常");
//             }
// 	   		Long id = contractInfo.getId();
// 	   	    RestaurantPrivateInfo po = wxsToContractCaller.queryContractById(id);
// 	   		po = ContractVo.convertVoToPo(po, contractInfo,true);            
// 	   	     wxsToContractCaller.saveContract(po);
//             success = true;
//         } catch (EminException e) {
//             message = e.getLocalizedMessage();
//             success = false;
//             json.put("success", success);
//             json.put("message", message);
//             logger.error(e.getLocalizedMessage(), e);
//             return json;            
//         } catch (Exception e) {
//         	 message = e.getLocalizedMessage();
//              success = false;
//              json.put("success", success);
//              json.put("message", message);
//              logger.error(e.getMessage(), e);
//              return json;          
//         }
//         json.put("success", success);
//         json.put("message", message);
//         return json;
//    }
//
//    /**
//     * 删除合同信息
//     * @param id
//     * @return
//     */
//    @RequestMapping(value = "/delete.do", method = {RequestMethod.POST, RequestMethod.DELETE})
//    @ResponseBody
//    public JSONObject deleteContract(@RequestParam(required = true) Long id) {
//        JSONObject json = new JSONObject();
//        String message = "";
//        boolean success = false;
//        try {
//        	wxsToContractCaller.removeContract(id);
//            success = true;
//        } catch (EminException e) {
//            message = e.getLocalizedMessage();
//            logger.error(e.getLocalizedMessage(), e);
//        } catch (Exception e) {
//            message = e.getMessage();
//            logger.error(e.getMessage(), e);
//        }
//        json.put("success", success);
//        json.put("message", message);
//        return json;
//    }

}
