package com.emin.wxs.controller.device;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.emin.base.dao.PagedResult;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.igwmp.ms.domain.MachineChannel;
import com.emin.igwmp.ms.domain.MachineInfo;
import com.emin.igwmp.ms.domain.MachineInfoVo;
import com.emin.igwmp.ms.facade.accepters.MachineInfoAccepter;
import com.emin.igwmp.rstm.domain.RestaurantPublicInfo;
import com.emin.wxs.controller.WxsBaseController;
import com.emin.wxs.controller.restaurant.Const;
import com.emin.wxs.facade.restaurant.callers.WxsToRestaurantCaller;
import com.emin.wxs.vo.restaurant.RestaurantVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/device")
public class MachineController extends WxsBaseController {

    private Logger logger = LoggerFactory.getLogger(MachineController.class);

    @Reference(version = "0.0.1")
    private MachineInfoAccepter machineInfoAccepter;
    @Autowired
    private WxsToRestaurantCaller restaurantCaller;

    /**
     * 分页查询机器信息
     *
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/page.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JSONObject loadMachine(String keyword, Integer runType, String restaurant) {
        List<Condition> conditions = new ArrayList<Condition>();
        PagedResult<MachineInfo> result = null;
        JSONObject json = new JSONObject();
        try {
            if (StringUtils.isNotBlank(keyword)) {
                if (keyword.equals(new String(keyword.getBytes("ISO-8859-1"), "ISO-8859-1"))) {
                    keyword = new String(keyword.getBytes("ISO-8859-1"), "utf-8");
                }
                conditions.add(new Condition("keyword", Condition.ConditionOperator.EQ, Condition.ConditionType.CHARACTER, keyword));
            }
            if (runType != null && runType != -1) {
                conditions.add(new Condition("machineControl.runType", Condition.ConditionOperator.EQ, Condition.ConditionType.CHARACTER, runType));
            }
            if (!StringUtils.isBlank(restaurant)) {
                conditions.add(new Condition("restaurantId", Condition.ConditionOperator.NE, Condition.ConditionType.CHARACTER, -1L));
                conditions.add(new Condition("restaurantId", Condition.ConditionOperator.NE, Condition.ConditionType.CHARACTER, 0L));
                conditions.add(new Condition("machineControl.bindStatus", Condition.ConditionOperator.NE, Condition.ConditionType.CHARACTER, 1));
            }

            result = machineInfoAccepter.queryMachineInfoForList(getPageRequestData(), conditions);
            if (result == null || result.getResultList().size() == 0) {
                json.put("total", 0);
                json.put("rows", "");
                logger.info("分页查询机器信息结果：" + result);
                return json;
            }

            List<JSONObject> objectList = new ArrayList<>();

            for (int i = 0; i < result.getResultList().size(); i++) {

                MachineInfo m = result.getResultList().get(i);
                if (m == null) {
                    throw new RuntimeException("分页查询机器信息失败!，Machine为空！");
                }
                JSONObject object = new JSONObject();
                List<JSONObject> channelList = new ArrayList<>();

                //通道信息
                if (m.getChannelList() != null && m.getChannelList().size() > 0) {
                    object.put("alarmValue", m.getChannelList().get(0).getAlarmValue());
                    object.put("warningValue", m.getChannelList().get(0).getWarningValue());
                    for (int k = 0; k < m.getChannelList().size(); k++) {
                        JSONObject objectChannel = new JSONObject();
                        objectChannel.put("id", m.getChannelList().get(k).getId());
                        objectChannel.put("liquorInfoId", m.getChannelList().get(k).getLiquorInfoId());
                        objectChannel.put("liquorName", m.getChannelList().get(k).getLiquorName());
                        objectChannel.put("allowance", m.getChannelList().get(k).getAllowance());
                        objectChannel.put("sort", m.getChannelList().get(k).getSort());
                        channelList.add(objectChannel);
                    }
                }

                //版本控制信息
                if (m.getMachineControl() != null) {
                    object.put("bindStatus", m.getMachineControl().getStatus());
                    object.put("bindTime", m.getMachineControl().getBindTime());
                }

                //关联餐厅信息
                if (m.getRestaurantId() != null) {
//                    RestaurantPublicInfo publicInfo = null;
                    RestaurantPublicInfo publicInfo = restaurantCaller.queryRestaurantPublicInfoById(m.getRestaurantId());
                    if (publicInfo != null) {
                        object.put("runType", publicInfo.getUsingScene());
                        int cascadeDepth = Const.CASCADE_DEPTH_VALUE;
                        object.put("restaurant", RestaurantVO.convertPoToVo(publicInfo, cascadeDepth));
                    }
                }

                //设备基本信息
                object.put("channelList", channelList);
                object.put("id", m.getId());
                object.put("code", m.getCode());
                object.put("ipcCode", m.getIpcCode());
                object.put("simCode", m.getSimCode());
                object.put("restaurantId", m.getRestaurantId());
                object.put("restaurantName", m.getRestaurantName());
                object.put("channelSum", m.getChannelSum());

                objectList.add(object);
            }
            json.put("total", result.getTotalCount());
            json.put("rows", objectList);
            logger.info("分页查询机器信息结果：" + objectList.toString());

            return json;
        } catch (Exception e) {
            logger.error("分页查询机器信息失败!");
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 查询机器信息 list
     *
     * @return list<machineInfo></>
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/list.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JSONObject loadMachineList(@RequestBody MachineInfo info) {
        List<Condition> conditions = new ArrayList<Condition>();
        List<MachineInfo> result = null;
        JSONObject json = new JSONObject();
        try {
            if (info != null) {
                if (info.getId() != null) {
                    conditions.add(new Condition("id", Condition.ConditionOperator.EQ, Condition.ConditionType.CHARACTER, info.getId()));
                }
            }

            result = machineInfoAccepter.queryMachineInfoForList(conditions);
            if (result == null) {
                json.put("total", 0);
                json.put("rows", "");
                logger.info("查询机器信息结果：" + result);
                return json;
            }

            json.put("rows", result);
            logger.info("查询机器信息结果：" + result.toString());

            return json;
        } catch (Exception e) {
            logger.error("查询机器信息失败!");
            e.printStackTrace();
            return null;
        }
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/handle.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JSONObject handleMachine(String jsonStr) {
        logger.info("请求参数：" + jsonStr);

        JSONObject json = new JSONObject();
        MachineInfo machineInfo = (MachineInfo) JSON.parseObject(jsonStr, MachineInfo.class);
        List<MachineChannel> channelList = new ArrayList<>();
        String message = "";
        boolean success = false;
        try {
            if (machineInfo == null) {
                throw new EminException("参数异常");
            }

            Long id = machineInfoAccepter.updateMachineInfo(machineInfo);
            json.put("result",id); //返回ID
            success = true;
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
    @RequestMapping(value = "/saveOrUpdate.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JSONObject saveOrUpdateMachine(String jsonStr) {
        logger.info("请求参数：" + jsonStr);

        JSONObject json = new JSONObject();
        MachineInfoVo machineInfo = (MachineInfoVo) JSON.parseObject(jsonStr, MachineInfoVo.class);
        List<MachineChannel> channelList = new ArrayList<>();
        String message = "";
        boolean success = false;
        try {
            if (machineInfo == null || machineInfo.getId() == null || machineInfo.getChannelList() == null) {
                throw new EminException("参数异常");
            }
            machineInfo.setChannelList(channelList);
            machineInfoAccepter.addOrUpdateMachineInfo(machineInfo);
            success = true;
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
     * 删除机器信息
     *
     * @param id
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/delete.do", method = {RequestMethod.POST, RequestMethod.DELETE})
    @ResponseBody
    public JSONObject deleteMachine(@RequestParam(required = true) Long id) {
        JSONObject json = new JSONObject();
        String message = "";
        boolean success = false;
        try {
            machineInfoAccepter.removeMachineInfo(id);
            success = true;
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
     * 餐厅与机器取消绑定
     *
     * @param ids
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/cancelBind.do", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public JSONObject cancelBind(@RequestParam(value = "ids", required = true) Long[] ids) {
        JSONObject json = new JSONObject();
        String message = "";
        boolean success = false;
        try {
            machineInfoAccepter.cancelBind(ids);
            success = true;
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
     * 餐厅与机器绑定
     *
     * @param jsonStr
     * @return
     */
    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/bindRes.do", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public JSONObject bindRes(String jsonStr) {
        logger.info("请求参数：" + jsonStr);

        JSONObject json = new JSONObject();
        MachineInfo machineInfo = (MachineInfo) JSON.parseObject(jsonStr, MachineInfo.class);
        String message = "";
        boolean success = false;
        try {
            if (machineInfo == null || machineInfo.getId() == null || machineInfo.getRestaurantId() == null ||
                    StringUtils.isBlank(machineInfo.getRestaurantName())) {
                throw new EminException("参数异常");
            }
            machineInfoAccepter.bindMachine(machineInfo);
            success = true;
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
