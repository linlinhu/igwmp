package com.emin.wxs.controller.weixin.restaurant;

import com.alibaba.fastjson.JSONObject;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.base.util.CookieUtil;
import com.emin.igwmp.rstm.domain.RestaurantAdminInfo;
import com.emin.igwmp.rstm.domain.RestaurantPublicInfo;
import com.emin.wxs.controller.WxsBaseController;
import com.emin.wxs.controller.restaurant.Const;
import com.emin.wxs.controller.restaurant.OperatorController;
import com.emin.wxs.facade.restaurant.callers.WxsToOperatorCaller;
import com.emin.wxs.util.SMSJSONResult;
import com.emin.wxs.util.SmsUtil;
import com.emin.wxs.vo.restaurant.RestaurantVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * user : shimingliang
 * time : 2017/4/21
 * des :
 */
@Controller
@RequestMapping("/resLog")
public class RestaurantWxLoginContoller extends WxsBaseController {

    private Logger logger = LoggerFactory.getLogger(OperatorController.class);

    @Autowired
    private WxsToOperatorCaller wxsToOperatorCaller;

    /**
     * 发送验证码
     *
     * @param phoneNum
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/sendVerifyCode.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JSONObject sendVerifyCode(@RequestParam(required = true) String phoneNum) {
        JSONObject json = new JSONObject();
        String message = "";
        boolean success = false;
        try {
            SMSJSONResult smsResult = SmsUtil.sendVerifyCodeByDayu(phoneNum, "运营者登录");
            if (smsResult.getResultCode() == 0) {
                int code = (int) smsResult.getResultMsg();
                logger.info("验证码发送成功 code = " + code + "");
                wxsToOperatorCaller.putOperatorVerifyCode(phoneNum, code + "");
                logger.info("保存验证码到redis成功");
                success = true;
            }else{
                message = "验证码发送失败，请重试！";
            }
        } catch (EminException e) {
            message = e.getLocalizedMessage();
            logger.error(e.getLocalizedMessage(), e);
        } catch (Exception e) {
            message = e.getMessage();
            logger.error(e.getMessage(), e);
        }
        json.put("success", success);
        json.put("msg", message);
        return json;
    }

    /**
     * 跳转到餐厅老板登录页面
     *
     * @return
     */
    @RequestMapping("/index")
    public String goToLogHtml() {
        return "wxWebPage/restaurant/restaurantLogin";
    }

    /**
     * 运营者登录
     *
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/login.do")
    public ModelAndView login(@RequestParam(required = true) String verifyCode, @RequestParam(required = true) String phoneNum) {
        logger.info("verifyCode = " + verifyCode + ",phoneNum = " + phoneNum);
        String indexLog = "wxWebPage/restaurant/restaurantLogin";
        String indexMain = "restaurant/restaurantLocale.do";
        ModelAndView mv = new ModelAndView(indexLog);
        String message = "";
        try {
            if (StringUtils.isNotBlank(verifyCode) && StringUtils.isNotBlank(phoneNum)) {
                String realVerifyCode = wxsToOperatorCaller.validateOperatorVerifyCode(phoneNum);
                if (verifyCode.equals(realVerifyCode)) {
                    logger.info("验证码正确");
                } else {
                    message = "验证码有误";
                    mv.addObject("msg", message);
                    mv.addObject("phoneNum", phoneNum);
                    return mv;
                }

                logger.info("开始验证审核情况");
                List<Condition> conditions = new ArrayList<Condition>();
                conditions.add(new Condition("cellphone", Condition.ConditionOperator.EQ, Condition.ConditionType.CHARACTER, phoneNum));
                RestaurantAdminInfo servant = wxsToOperatorCaller.queryUniqueOperator(conditions);
                if (servant != null) {
                    List<RestaurantVO> restaurants = new ArrayList<RestaurantVO>();
                    if (servant.getRestaurants() != null && servant.getRestaurants().size() > 0) {
                        for (RestaurantPublicInfo restaurantPo : servant.getRestaurants()) {
                            restaurants.add(RestaurantVO.convertPoToVo(restaurantPo, Const.CASCADE_DEPTH_VALUE));
                        }
                    }
                    if (restaurants.size() == 0 || servant.getId() == null || restaurants.get(0).getId() == null) {
                        message = "未找到您的运营者信息，请核实后再登录";
                        logger.info("未找到运营者信息或饭店信息  servant==" + servant.toString() + "restaurants==" + restaurants.toString());
                        mv.addObject("msg", message);
                        mv.addObject("phoneNum", phoneNum);
                        return mv;
                    }
                    //把restaurants 存入Cookie
                    JSONObject json = new JSONObject();
                    json.put("bossId", servant.getId());
                    json.put("restaurantId", restaurants.get(0).getId());
                    CookieUtil.addCookie(getResponse(), "restaurants", json.toJSONString(), -1);

                    //设置Cookie
                    CookieUtil.addCookie(getResponse(), "restaurantLogin", getRequest().getSession().getId(), -1);
                    //成功登录
                    return new ModelAndView("redirect:/" + indexMain);
                } else {
                    message = "未找到您的运营者信息，请核实后再登录";
                    logger.info("未找到您的运营者信息，请核实后再登录");
                    mv.addObject("msg", message);
                    mv.addObject("phoneNum", phoneNum);
                    return mv;
                }
            } else {
                message = "传入参数有误";
                mv.addObject("msg", message);
                mv.addObject("phoneNum", phoneNum);
                return mv;
            }
        } catch (EminException e) {
            message = e.getLocalizedMessage();
            logger.error(e.getLocalizedMessage(), e);
        } catch (Exception e) {
            message = e.getMessage();
            logger.error(e.getMessage(), e);
        }
        message = "网络忙，请稍后重试！";
        mv.addObject("msg", message);
        mv.addObject("phoneNum", phoneNum);
        return mv;
    }
}
