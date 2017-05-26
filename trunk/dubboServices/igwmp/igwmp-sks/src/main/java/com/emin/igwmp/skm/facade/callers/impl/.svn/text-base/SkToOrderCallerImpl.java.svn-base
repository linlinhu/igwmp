package com.emin.igwmp.skm.facade.callers.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.emin.base.dao.PagedResult;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.igwmp.ms.domain.MachineChannel;
import com.emin.igwmp.ords.facade.accepters.OrderAccepter;
import com.emin.igwmp.rstm.domain.ServantVenoutRecord;
import com.emin.igwmp.rstm.facade.accepters.ServantVenoutRecordAccepter;
import com.emin.igwmp.skm.exception.ErrorCode;
import com.emin.igwmp.skm.facade.accepters.OrderNoticeAccepter;
import com.emin.igwmp.skm.facade.callers.SkToOrderCaller;
import com.emin.igwmp.skm.facade.callers.vo.ReplacesVO;
import com.emin.igwmp.skm.facade.callers.vo.ReplacessVO;
import com.emin.igwmp.skm.facade.callers.vo.ReportsVO;
import com.emin.igwmp.skm.facade.callers.vo.TakeCodesVO;
import com.emin.igwmp.skm.facade.callers.vo.bean.ReplaceBean;
import com.emin.igwmp.skm.service.MachineService;
import com.emin.igwmp.skm.util.EmptyUtils;
import com.emin.igwmp.skm.util.LogUtils;
import com.emin.igwmp.tws.domain.TakeWineRecord;
import com.emin.igwmp.tws.facade.accepters.TakeWineRecordAccepter;
import com.emin.tdc.domain.TakeWineAction;
import com.emin.tdc.facade.accepters.TradingCenterAccepter;
import com.emin.wxs.service.FansService;
import com.emin.wxs.service.WeixinToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
 */
@Service("skToOrderCaller")
public class SkToOrderCallerImpl implements SkToOrderCaller {

    @Reference(version="0.0.1")
    TradingCenterAccepter  tradingCenterAccepter;

    @Reference(version="0.0.1")
    private OrderAccepter orderAccepter;

    @Reference(version="0.0.1")
    TakeWineRecordAccepter takeWineRecordAccepter;

    @Autowired
    OrderNoticeAccepter orderNoticeAccepter;

    @Reference(version="0.0.1")
    ServantVenoutRecordAccepter servantVenoutRecordAccepter;

    @Reference(version="0.0.1")
    FansService fansService;

    @Autowired
    MachineService machineService;




    @Override
    public JSONObject ReportOrder(String orderInfo) {
        LogUtils.D("上传订单信息:" + orderInfo);
        ReportsVO vo = JSON.parseObject(orderInfo,ReportsVO.class);
        vo.getIpcCode();
        TakeWineAction action = (vo.isSuccess()) ? TakeWineAction.SUCCESS : TakeWineAction.EXCEPTION;
        JSONObject reportInfo =  new JSONObject();
        reportInfo.put("channel",vo.getChannel());
        reportInfo.put("ipcCode",vo.getIpcCode());

        try {
            //通道减量
            MachineChannel channel = machineService.getChannelForProduct(vo.getIpcCode(),vo.getProductId());
            if(channel != null){
                channel.setAllowance(channel.getAllowance() - vo.getValue());
                if(!machineService.updateChannel(vo.getIpcCode(),channel)){
                    return ErrorCode.ResultFail(ErrorCode.SAVE_REPORT);
                }
            }

            //通知交易中心完成订单
            tradingCenterAccepter.takeWineComplete(vo.getOrderId(),vo.getValue(),JSON.toJSONString(reportInfo),action);

            return ErrorCode.ResultFail(ErrorCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return ErrorCode.ResultFail(ErrorCode.SERVICE_CALL);
        }
    }

    @Override
    public JSONObject TakeCodeValidate(String codeInfo) {
        LogUtils.D("上传取酒码验证:" + codeInfo);
        TakeCodesVO vo = JSON.parseObject(codeInfo, TakeCodesVO.class);
        try {
            TakeWineRecord record = takeWineRecordAccepter.lockTakeCode(vo.getCode());
            return orderNoticeAccepter.OrderDeliver(vo.getIpcCode(),record.getOrderNumber());
        } catch (Exception e) {
            e.printStackTrace();
            return ErrorCode.ResultFail(ErrorCode.TAKE_SERVER);
        }
    }

    @Override
    public JSONObject getReplaces(String replacesInfo) {

        LogUtils.D("代取酒列表获取:" + replacesInfo);
        ReplacesVO vo = JSON.parseObject(replacesInfo, ReplacesVO.class);
        ReplacessVO reVo = new ReplacessVO();
        List<ReplaceBean> rows = new ArrayList<>();
        reVo.setSuccess(true);

        try {
            List<Condition> conditions = new ArrayList<Condition>();
            conditions.add(new Condition("restaurantServantInfo.cellphone", Condition.ConditionOperator.EQ, Condition.ConditionType.CHARACTER,vo.getPhone()));
            conditions.add(new Condition("vendoutStatus", Condition.ConditionOperator.EQ, Condition.ConditionType.OTHER,0));
            List<ServantVenoutRecord> lists = servantVenoutRecordAccepter.queryServantVenoutRecordForListByCondition(conditions);
            if(EmptyUtils.isEmpty(lists)){
                return ErrorCode.ResultFail(ErrorCode.NOT_EXIST);
            }

            reVo.setTotal(lists.size());
            for(ServantVenoutRecord record : lists){
                ReplaceBean bean = new ReplaceBean();
                bean.setCode(record.getTakeCode());
                MachineChannel channel = machineService.getChannelForProduct(vo.getIpcCode(),record.getProductId());
                if( (channel == null) || (channel.getAllowance() - channel.getAlarmValue() < record.getVendoutCapacity())){
                    bean.setEnable(false);
                    bean.setName(record.getWineName());
                }else{
                    bean.setEnable(true);
                    bean.setName(channel.getLiquorName());
                }
                bean.setTable(String.valueOf(record.getTableNum()));
                bean.setQuantity(record.getVendoutCapacity()/50);
                rows.add(bean);
            }
            Collections.sort(rows, new Comparator<ReplaceBean>() {
                @Override
                public int compare(ReplaceBean o1, ReplaceBean o2) {
                    if(o1.isEnable()){
                        return -1;
                    }else{
                        return 1;
                    }
                }
            });
            reVo.setRows(rows);
            return (JSONObject) JSON.toJSON(reVo);

        } catch (Exception e) {
            e.printStackTrace();
            return ErrorCode.ResultFail(ErrorCode.TAKE_SERVER);
        }
    }


}
