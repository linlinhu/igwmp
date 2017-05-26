package com.emin.igwmp.skm.facade.accepters.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.emin.base.service.Condition;
import com.emin.igwmp.ms.domain.MachineChannel;
import com.emin.igwmp.rstm.domain.ServantVenoutRecord;
import com.emin.igwmp.rstm.facade.accepters.ServantVenoutRecordAccepter;
import com.emin.igwmp.skm.core.msmanage.assemble.AssembleMesageOrder;
import com.emin.igwmp.skm.core.msmanage.entity.bean.RowsBean;
import com.emin.igwmp.skm.exception.ErrorCode;
import com.emin.igwmp.skm.facade.accepters.UserNoticeAccepter;
import com.emin.igwmp.skm.facade.callers.vo.ReplacesVO;
import com.emin.igwmp.skm.facade.callers.vo.ReplacessVO;
import com.emin.igwmp.skm.facade.callers.vo.RowID;
import com.emin.igwmp.skm.facade.callers.vo.TakeCodesVO;
import com.emin.igwmp.skm.facade.callers.vo.bean.ReplaceBean;
import com.emin.igwmp.skm.service.MachineService;
import com.emin.igwmp.skm.util.EmptyUtils;
import com.emin.igwmp.skm.util.LogUtils;
import com.emin.igwmp.tws.domain.TakeWineRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/25.
 */
@Service(version = "0.0.1")
@Component("userNoticeAccepter")
public class UserNoticeAccepterImpl implements UserNoticeAccepter {

    @Reference(version="0.0.1")
    ServantVenoutRecordAccepter servantVenoutRecordAccepter;

    @Autowired
    MachineService machineService;

    @Override
    public void ProxyPorter(String phone, String ipcCode) throws Exception{
        LogUtils.D("机柜服务员登录通知: phone: " + phone +  "  ipcCode:" + ipcCode);
        if(EmptyUtils.isEmpty(phone) || EmptyUtils.isEmpty(ipcCode)){
            throw new Exception();
        }
        RowsBean row = new RowsBean();
        row.setRowId(RowID.ORDER_REPLACE);
        ReplacessVO reVo = new ReplacessVO();
        List<ReplaceBean> rows = new ArrayList<>();
        reVo.setSuccess(true);

        List<Condition> conditions = new ArrayList<Condition>();
        conditions.add(new Condition("restaurantServantInfo.cellphone", Condition.ConditionOperator.EQ, Condition.ConditionType.CHARACTER,phone));
        conditions.add(new Condition("vendoutStatus", Condition.ConditionOperator.EQ, Condition.ConditionType.OTHER,0));
        List<ServantVenoutRecord> lists = servantVenoutRecordAccepter.queryServantVenoutRecordForListByCondition(conditions);
        if(EmptyUtils.isEmpty(lists)){
            throw  new Exception();
        }
        reVo.setTotal(lists.size());
        for(ServantVenoutRecord record : lists){
            ReplaceBean bean = new ReplaceBean();
            bean.setCode(record.getTakeCode());
            MachineChannel channel = machineService.getChannelForProduct(ipcCode,record.getProductId());
            if( (channel == null) || (channel.getAllowance() - channel.getAlarmValue() < record.getVendoutCapacity())){
                bean.setEnable(false);
            }else{
                bean.setEnable(true);
            }
            bean.setTable(String.valueOf(record.getTableNum()));
            bean.setName(record.getWineName());
            bean.setQuantity(record.getVendoutCapacity()/50);
            rows.add(bean);
        }
        reVo.setRows(rows);
        row.setRow(JSON.toJSONString(reVo));
        new AssembleMesageOrder().Assemble(ipcCode, row);
    }
}
