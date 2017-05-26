package com.emin.wxs.controller.restaurant;

import com.alibaba.fastjson.JSONObject;
import com.emin.base.dao.PagedResult;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.base.service.Condition.ConditionOperator;
import com.emin.base.service.Condition.ConditionType;
import com.emin.igwmp.rstm.domain.RestaurantAdminInfo;
import com.emin.igwmp.rstm.domain.RestaurantPublicInfo;
import com.emin.igwmp.rstm.domain.RestaurantServantInfo;
import com.emin.wxs.controller.WxsBaseController;
import com.emin.wxs.facade.restaurant.callers.WxsToOperatorCaller;
import com.emin.wxs.facade.restaurant.callers.WxsToServantCaller;
import com.emin.wxs.util.SMSJSONResult;
import com.emin.wxs.util.SmsUtil;
import com.emin.wxs.vo.restaurant.OperatorVO;
import com.emin.wxs.vo.restaurant.RestaurantVO;
import com.emin.wxs.vo.waiter.WaiterVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/operator")
public class OperatorController extends WxsBaseController {

    private Logger logger = LoggerFactory.getLogger(OperatorController.class);

    @Autowired
    private WxsToOperatorCaller wxsToOperatorCaller;

    @Autowired
    private WxsToServantCaller wxsToServantCaller;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/page.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JSONObject loadOperatorByCondition() {

        List<Condition> conditions = new ArrayList<Condition>();
        PagedResult<RestaurantAdminInfo> poResult = null;
        List<OperatorVO> voResult = new ArrayList<OperatorVO>();
        String keyword = (String) getPageData().get("keyword");
        try {
            if (StringUtils.isNotBlank(keyword)) {
                conditions.add(new Condition("name", ConditionOperator.LIKE, ConditionType.CHARACTER, "%" + keyword + "%"));
                conditions.add(new Condition("restaurants.name", ConditionOperator.LIKE, ConditionType.CHARACTER, "%" + keyword + "%"));
                conditions.add(new Condition("restaurants.restaurantPrivateInfo.contractNum", ConditionOperator.LIKE, ConditionType.CHARACTER, "%" + keyword + "%"));
            }
//              if (StringUtils.isNotBlank(contractNum)) {
//                  conditions.add(new Condition("restaurantPrivateInfo.contractNum", ConditionOperator.LIKE, ConditionType.CHARACTER, "%" + contractNum + "%"));
//                       
            poResult = wxsToOperatorCaller.queryOperatorByCondition(getPageRequestData(), conditions);
            if (poResult != null) {
                List<RestaurantAdminInfo> resultList = poResult.getResultList();

                for (RestaurantAdminInfo po : resultList) {
                    int cascadeDepth = Const.CASCADE_DEPTH_VALUE;
                    voResult.add(OperatorVO.convertPoToVo(po, cascadeDepth));
                }
            } else {
                JSONObject json = new JSONObject();
                json.put("total", 0);
                json.put("rows", voResult);
                return json;
            }
        } catch (EminException e) {
            logger.error("分页查询运营者信息失败!");
            e.printStackTrace();
        }
        JSONObject json = new JSONObject();
        json.put("total", poResult.getTotalCount());
        json.put("rows", voResult);
        return json;
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/saveOrUpdate.do", method = {RequestMethod.POST, RequestMethod.PUT})
    @ResponseBody
    public JSONObject saveOrUpdateRestaurant(@RequestParam String jsonStr) {
//    public JSONObject saveOrUpdateOperator(@RequestBody OperatorVO operatorVo) {
        JSONObject json = new JSONObject();
        String message = "";
        boolean success = false;
        logger.info("运营者json字符串：" + jsonStr);
        try {
            OperatorVO operatorVo = JSONObject.parseObject(jsonStr, OperatorVO.class);
            if (operatorVo == null) {
                throw new EminException("参数异常");
            }
            Long id = operatorVo.getId();
            RestaurantAdminInfo po = null;
            if (id != null) {
                po = wxsToOperatorCaller.queryOperatorById(id);
            }
            int cascadeDepth = Const.CASCADE_DEPTH_VALUE;
            po = OperatorVO.convertVoToPo(po, operatorVo, cascadeDepth);
            wxsToOperatorCaller.saveOperatorInfo(po);
            success = true;

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
     * 删除运营者信息
     *
     * @param id
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/delete.do", method = {RequestMethod.POST, RequestMethod.PUT})
    @ResponseBody
    public JSONObject deleteOperator(@RequestParam(required = true) Long id) {
        JSONObject json = new JSONObject();
        String message = "";
        boolean success = false;
        try {
            RestaurantAdminInfo po = wxsToOperatorCaller.queryOperatorById(id);
            if (po != null) {
                po.setStatus(-1);
                wxsToOperatorCaller.saveOperatorInfo(po);
                success = true;
                message = "";
            } else {
                success = false;
                message = "运营者信息删除失败";
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
     * 获取服务员审核列表
     *
     * @param operatorId 运营者id
     * @param isOperated 是否为操作过的状态
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/servantList.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JSONObject getServantList(@RequestParam(required = true) Long operatorId,
                                     @RequestParam(required = true) boolean isAudited) {
        logger.info("operatorId = " + operatorId + ",isAudited = " + isAudited);
        JSONObject json = new JSONObject();
        String message = "";
        List<WaiterVO> waiters = new ArrayList<WaiterVO>();
        try {
            List<Condition> conditions = new ArrayList<Condition>();
            conditions.add(new Condition("restaurants.admins.id", ConditionOperator.EQ, ConditionType.CHARACTER, operatorId));
            if (isAudited) {//已审核的
                conditions.add(new Condition("auditingStatus", ConditionOperator.NE, ConditionType.CHARACTER, Const.SERVANT_AUDITING_STATUS_UNAUDITING));
            } else {//未审核的
                conditions.add(new Condition("auditingStatus", ConditionOperator.EQ, ConditionType.CHARACTER, Const.SERVANT_AUDITING_STATUS_UNAUDITING));
            }
            List<RestaurantServantInfo> servantsPOs = wxsToServantCaller.queryServantList(conditions);

            if (servantsPOs != null && servantsPOs.size() > 0) {
                for (RestaurantServantInfo servantPO : servantsPOs) {
                    waiters.add(WaiterVO.convertPoToVo(servantPO, Const.CASCADE_DEPTH_VALUE));
                }
            }
        } catch (EminException e) {
            message = e.getLocalizedMessage();
            logger.error(e.getLocalizedMessage(), e);
        } catch (Exception e) {
            message = e.getMessage();
            logger.error(e.getMessage(), e);
        }
        json.put("results", waiters);
        json.put("message", message);
        return json;
    }

    /**
     * 运营者审核服务员操作
     *
     * @param servantId
     * @param auditingStatus
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/saveAudit.do", method = {RequestMethod.POST, RequestMethod.PUT})
    @ResponseBody
    public JSONObject saveOrUpdateRestaurant(@RequestParam(required = true) Long servantId, @RequestParam(required = true) int auditingStatus) {
        JSONObject json = new JSONObject();
        String message = "";
        boolean success = false;
        logger.info("servantId = " + servantId + ",auditingStatus = " + auditingStatus);
        try {
            wxsToOperatorCaller.saveServantAuditResult(servantId, auditingStatus);
            success = true;
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
}
