package com.emin.wxs.controller.weixin.restaurant;

import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.base.service.Condition;
import com.emin.base.util.CookieUtil;
import com.emin.igwmp.rp.facade.accepters.RestaurantReportAccepter;
import com.emin.igwmp.rp.facade.accepters.ServantReportAccepter;
import com.emin.igwmp.rstm.domain.RestaurantServantInfo;
import com.emin.wxs.controller.WxsBaseController;
import com.emin.wxs.controller.restaurant.Const;
import com.emin.wxs.facade.restaurant.callers.WxsToOperatorCaller;
import com.emin.wxs.facade.restaurant.callers.WxsToServantCaller;
import com.emin.wxs.util.DateUtil;
import com.emin.wxs.vo.waiter.WaiterVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * user : shimingliang
 * time : 2017/4/18
 * des : 餐厅 ---- 销量排行榜统计，服务员打酒统计
 */
@Controller
@RequestMapping("/restaurant")
public class RestaurantStatistWxController extends WxsBaseController {

    private Logger logger = LoggerFactory.getLogger(RestaurantStatistWxController.class);

    @Reference(version = "0.0.2")
    private RestaurantReportAccepter restaurantReportAccepter;
    @Reference(version = "0.0.1")
    private ServantReportAccepter servantAccepter;
    @Autowired
    private WxsToServantCaller wxsToServantCaller;
    @Autowired
    private WxsToOperatorCaller wxsToOperatorCaller;


    /**
     * 餐厅当日销量排行榜
     *
     * @return
     */
    @RequestMapping("/rankTodayList")
    public ModelAndView rankTodayList() {
        ModelAndView mv = new ModelAndView("wxWebPage/restaurant/restaurantRank");   //历史排行榜
        try {
            Date curDate = new Date();
            Long startDate = DateUtil.getDate(DateUtil.getSomeDayStart(curDate)).getTime();
            Long endDate = DateUtil.getDate(DateUtil.getSomeDayEnd(curDate)).getTime();

            logger.info("餐厅当日销量排行榜参数：curDate======" + curDate + "startDate==" + startDate + "endDate===" + endDate);

            JSONArray jsonArray = restaurantReportAccepter.findSaleRandRanking(0L, endDate, null);
            if (jsonArray == null) {
                jsonArray = new JSONArray();
            }
            mv.addObject("list", jsonArray);
            logger.info("查询结果：" + jsonArray.toString());
        } catch (Exception e) {
            mv.addObject("msg", "对不起，服务器忙请稍后重试！");
            logger.error("餐厅当日销量排行榜统计出错  error======" + e.getMessage());
            e.printStackTrace();
        }

        return mv;
    }


    /**
     * 餐厅历史销量排行榜
     *
     * @return
     */
    @RequestMapping("/rankHistoryList")
    public ModelAndView rankHistoryList() {
        ModelAndView mv = new ModelAndView("wxWebPage/restaurant/restaurantHistoryRank");   //历史排行榜
        try {
            String paramDate = getRequest().getParameter("curDate");
            logger.info("传入参数param=====" + paramDate);
            Date curDate = null;
            if (!StringUtils.isBlank(paramDate)) {
                curDate = DateUtil.getDate(paramDate, "YYYY-mm-dd");
            } else {
                curDate = new Date();
            }
            Long startDate = DateUtil.getDate(DateUtil.getSomeDayStart(curDate)).getTime();
            Long endDate = DateUtil.getDate(DateUtil.getSomeDayEnd(curDate)).getTime();
            logger.info("餐厅历史销量排行榜参数：curDate======" + curDate + "startDate==" + startDate + "endDate===" + endDate);
            JSONArray jsonArray = restaurantReportAccepter.findSaleRandRanking(0L, endDate, null);
            if (jsonArray == null) {
                jsonArray = new JSONArray();
            }
            mv.addObject("list", jsonArray);
            mv.addObject("curDate", paramDate == null ? "" : DateUtil.getZhCNDate(paramDate)); //返回查询时间
            logger.info("查询结果：" + jsonArray.toString());
        } catch (RuntimeException e) {
            mv.addObject("msg", e.getMessage());
            logger.error("餐厅历史销量排行榜统计出错  error======" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            mv.addObject("msg", "对不起，服务器忙请稍后重试！");
            logger.error("餐厅历史销量排行榜统计出错  error======" + e.getMessage());
            e.printStackTrace();
        }

        return mv;
    }

    /**
     * 餐厅服务员打酒记录
     *
     * @return
     */
    @RequestMapping("/waiterSaleWineList")
    public ModelAndView waiterSaleWineList() {
        ModelAndView mv = new ModelAndView("wxWebPage/restaurant/waiterTakeWineRecord");
        try {
            String paramDate = getRequest().getParameter("curDate");
            String flag = getRequest().getParameter("flag");
            logger.info("传入参数paramDate=====" + paramDate + "paramFlag====" + flag);
            Date curDate = null;
            //历史打酒记录
            if (StringUtils.isBlank(paramDate)) {
                curDate = new Date();
            } else {
                curDate = DateUtil.getDate(paramDate, "YYYY-mm-dd");
                mv.addObject("curDate", DateUtil.getZhCNDateFormat(paramDate, "yyyy年MM月")); //返回查询显示时间
                mv.addObject("queryDate", paramDate); //返回查询时间
            }
            //当日打酒记录
            if (!StringUtils.isBlank(flag) && !"2".equals(flag)) {
                curDate = new Date();
            }

            Long startDate = DateUtil.getDate(DateUtil.getSomeDayStart(curDate)).getTime();
            Long endDate = DateUtil.getDate(DateUtil.getSomeDayEnd(curDate)).getTime();
            //重session中获取
            String res = CookieUtil.getValue(getRequest(), "restaurants");
            com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSON.parseObject(res);
            if (jsonObject.get("bossId") == null){
                mv.addObject("msg", "对不起，服务器忙请稍后重试！");
                logger.info("餐厅历史销量排行榜异常！ 从Cookie中获取老板信息失败！ bossId == "+jsonObject.get("bossId"));
            }else {
                Long bossId = Long.parseLong(jsonObject.get("bossId").toString());;
                logger.info("餐厅历史销量排行榜参数：curDate======" + curDate + "startDate==" + startDate + "endDate===" + endDate + " bossId=====" + bossId);

                JSONArray jsonArray = servantAccepter.findSaleStatistics(bossId, startDate, endDate);
                if (jsonArray == null) {
                    jsonArray = new JSONArray();
                }
                mv.addObject("list", jsonArray);
                mv.addObject("flag", flag);
                logger.info("查询结果：" + jsonArray.toString());
            }
        } catch (Exception e) {
            mv.addObject("msg", "对不起，服务器忙请稍后重试！");
            logger.error("餐厅服务员打酒记录统计出错  error======" + e.getMessage());
            e.printStackTrace();
        }

        return mv;
    }


    /**
     * 餐厅销量统计
     *
     * @return
     */
    @RequestMapping("/restaurantSale")
    public ModelAndView restaurantSale() {
        ModelAndView mv = new ModelAndView("wxWebPage/restaurant/restaurantSalesStatistics");
        try {

            String paramDate = getRequest().getParameter("curDate");
            String flag = getRequest().getParameter("flag");
            logger.info("餐厅销量统计传入参数paramDate=====" + paramDate + "paramFlag====" + flag);
            Date curDate = null;
            //历史打酒记录
            if (StringUtils.isBlank(paramDate)) {
                curDate = new Date();
            } else {
                curDate = DateUtil.getDate(paramDate, "YYYY-mm-dd");
                mv.addObject("curDate", DateUtil.getZhCNDateFormat(paramDate, "yyyy年MM月")); //返回查询显示时间
                mv.addObject("queryDate", paramDate); //返回查询时间
            }
            //当日打酒记录
            if (!StringUtils.isBlank(flag) && !"2".equals(flag)) {
                curDate = new Date();
            }

            Long startDate = DateUtil.getDate(DateUtil.getSomeDayStart(curDate)).getTime();
            Long endDate = DateUtil.getDate(DateUtil.getSomeDayEnd(curDate)).getTime();
            //重session中获取
            String res = CookieUtil.getValue(getRequest(), "restaurants");
            com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSON.parseObject(res);
            if (jsonObject.get("restaurantId") == null){
                mv.addObject("msg", "对不起，服务器忙请稍后重试！");
                logger.info("餐厅销量统计异常！ 从Cookie中获取饭店信息失败！ restaurantId == "+jsonObject.get("restaurantId"));
            }else {
//            Long restaurantId = 12L;
                Long restaurantId = Long.parseLong(jsonObject.get("restaurantId").toString());
                logger.info("餐厅销量统计参数：curDate======" + curDate + "startDate==" + startDate + "endDate===" + endDate + " restaurantId =====" + restaurantId);

                JSONArray jsonArray = restaurantReportAccepter.findWineSaleStatistics(0L, endDate, restaurantId);
                if (jsonArray != null && jsonArray.get(0).toString().equals("{}")) {
                    jsonArray = new JSONArray();
                }
                //当日打酒记录
                int total = 0;
                if (!StringUtils.isBlank(flag) && !"2".equals(flag)) {
                    for (int i = 0; i < jsonArray.size(); i++) {
                        total += Integer.parseInt(jsonArray.getJSONObject(i).get("amount").toString());
                    }
                }
                mv.addObject("list", jsonArray);
                mv.addObject("flag", flag);
                mv.addObject("total", total);
                logger.info("查询结果：" + jsonArray.toString());
            }
        } catch (Exception e) {
            mv.addObject("msg", "对不起，服务器忙请稍后重试！");
            logger.error("餐厅销量统计出错  error======" + e.getMessage());
            e.printStackTrace();
        }

        return mv;
    }

    /**
     * 餐厅坐标及今日售酒量
     *
     * @return
     */
    @RequestMapping("/restaurantLocale")
    public ModelAndView restaurantLocale() {
        ModelAndView mv = new ModelAndView("wxWebPage/restaurant/restaurantManage");
        try {
            //重session中获取
            String res = CookieUtil.getValue(getRequest(), "restaurants");
            com.alibaba.fastjson.JSONObject json = com.alibaba.fastjson.JSON.parseObject(res);
            if (json.get("restaurantId") == null){
                mv.addObject("msg", "对不起，服务器忙请稍后重试！");
                logger.info("餐厅坐标及今日售酒量异常！ 从Cookie中获取饭店信息失败！ restaurantId == "+json.get("restaurantId"));
            }else {
//            Long restaurantId = 12L;
                Long restaurantId = Long.parseLong(json.get("restaurantId").toString());
                Date curDate = new Date();
                Long startDate = DateUtil.getDate(DateUtil.getSomeDayStart(curDate)).getTime();
                Long endDate = DateUtil.getDate(DateUtil.getSomeDayEnd(curDate)).getTime();
                logger.info("餐厅坐标及今日售酒量参数：curDate======" + curDate + "startDate==" + startDate + "endDate===" + endDate + " restaurantId=====" + restaurantId);
                net.sf.json.JSONObject jsonObject = restaurantReportAccepter.findRestaurantInfo(0L, endDate, restaurantId);
                if (jsonObject == null) {
                    jsonObject = new JSONObject();
                }
                mv.addObject("obj", jsonObject);
                logger.info("查询结果：" + jsonObject.toString());
            }
        } catch (Exception e) {
            mv.addObject("msg", "对不起，服务器忙请稍后重试！");
            logger.error("餐厅坐标及今日售酒量出错  error======" + e.getMessage());
            e.printStackTrace();
        }

        return mv;
    }


    /**
     * 餐厅服务员审批
     *
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping("/resWaiterApprove")
    @ResponseBody
    public JSONObject resWaiterApprove(@RequestParam int status, @RequestParam Long servantId) {
        JSONObject json = new JSONObject();
        try {
            logger.info("餐厅服务员审批传入参数status====" + status + " servantId===" + servantId);
            wxsToOperatorCaller.saveServantAuditResult(servantId, status);
            json.put("success", true);
        } catch (Exception e) {
            json.put("success", false);
            json.put("msg", "对不起审批失败，请稍后重试！");
            logger.error("餐厅服务员审批出错  error======" + e.getMessage());
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 餐厅服务员审批申请列表
     *
     * @return
     */
    @RequestMapping("/resWaiterApproveList")
    public ModelAndView resWaiterApproveList() {
        ModelAndView mv = new ModelAndView("wxWebPage/restaurant/waiterManage");
        try {
            String flag = getRequest().getParameter("flag");
            logger.info("餐厅服务员审批申请列表传入参数paramFlag====" + flag);
            //重session中获取
            String res = CookieUtil.getValue(getRequest(), "restaurants");
            com.alibaba.fastjson.JSONObject json = com.alibaba.fastjson.JSON.parseObject(res);
            if (json.get("restaurantId") == null){
                mv.addObject("msg", "对不起，服务器忙请稍后重试！");
                logger.info("餐厅服务员审批申请列表异常！ 从Cookie中获取老板信息失败！ bossId == "+json.get("bossId"));
            }else {
                Long bossId = Long.parseLong(json.get("bossId").toString());
//            Long bossId = 47L;
                logger.info("餐厅服务员审批申请列表参数： bossId =====" + bossId);
                List<WaiterVO> waiters = new ArrayList<WaiterVO>();

                List<Condition> conditions = new ArrayList<Condition>();
                conditions.add(new Condition("restaurants.admins.id", Condition.ConditionOperator.EQ, Condition.ConditionType.CHARACTER, bossId));

                //历史申请记录
                if (!StringUtils.isBlank(flag) && "2".equals(flag)) {
                    flag = "2";
                    conditions.add(new Condition("auditingStatus", Condition.ConditionOperator.NE, Condition.ConditionType.CHARACTER, Const.SERVANT_AUDITING_STATUS_UNAUDITING));
                } else {  //新申请服务员
                    flag = "1";
                    conditions.add(new Condition("auditingStatus", Condition.ConditionOperator.EQ, Condition.ConditionType.CHARACTER, Const.SERVANT_AUDITING_STATUS_UNAUDITING));
                }
                List<RestaurantServantInfo> servantsPOs = wxsToServantCaller.queryServantList(conditions);

                if (servantsPOs != null && servantsPOs.size() > 0) {
                    for (RestaurantServantInfo servantPO : servantsPOs) {
                        waiters.add(WaiterVO.convertPoToVo(servantPO, Const.CASCADE_DEPTH_VALUE));
                    }
                }

                JSONArray jsonArray = new JSONArray();

                if (waiters.size() > 0) {
                    for (WaiterVO vo : waiters) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", vo.getId());
                        map.put("name", vo.getName());
                        String u = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492681414061&di=5518f9af0b403b63109daf341450acad&imgtype=0&src=http%3A%2F%2Fimage.tianjimedia.com%2FuploadImages%2F2015%2F018%2F38%2F3KL58LZ3R11L.JPEG";
                        map.put("url", u); // TODO: 2017/4/20 服务员头像暂无
                        map.put("apprDate", DateUtil.getStringFormatDate(vo.getCreateDate(), "yyyy-MM-dd HH:mm"));
                        map.put("status", vo.getAuditingStatus());
                        jsonArray.add(map);
                    }
                }

                mv.addObject("list", jsonArray);
                mv.addObject("flag", flag);
                logger.info("查询结果：" + jsonArray.toString());
            }
        } catch (Exception e) {
            mv.addObject("msg", "对不起，服务器忙请稍后重试！");
            logger.error("餐厅服务员审批申请列表出错  error======" + e.getMessage());
            e.printStackTrace();
        }

        return mv;
    }

    /**
     * 跳转到Map页面
     *
     * @return
     */
    @RequestMapping("/goToMap")
    public ModelAndView goToMap() {
        return new ModelAndView("wxWebPage/restaurant/mapLocation");
    }

}
