package com.emin.igwmp.skm.facade.accepters.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.emin.base.service.Condition;
import com.emin.igwmp.ms.domain.MachineChannel;
import com.emin.igwmp.ms.domain.MachineInfo;
import com.emin.igwmp.ms.facade.accepters.MachineInfoAccepter;
import com.emin.igwmp.ords.domain.Order;
import com.emin.igwmp.ords.domain.OrderItem;
import com.emin.igwmp.ords.facade.accepters.OrderAccepter;
import com.emin.igwmp.prds.domain.Product;
import com.emin.igwmp.prds.facade.accepters.ProductAccepter;
import com.emin.igwmp.skm.core.msmanage.assemble.AssembleMesageOrder;
import com.emin.igwmp.skm.core.msmanage.entity.bean.RowsBean;
import com.emin.igwmp.skm.exception.ErrorCode;
import com.emin.igwmp.skm.facade.accepters.OrderNoticeAccepter;
import com.emin.igwmp.skm.facade.callers.vo.OrderVO;
import com.emin.igwmp.skm.facade.callers.vo.RowID;
import com.emin.igwmp.skm.facade.callers.vo.bean.OrderBean;
import com.emin.igwmp.skm.util.EmptyUtils;
import com.emin.igwmp.skm.util.LogUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/4/19.
 */
@Service(version = "0.0.1")
@Component("orderNoticeAccepter")
public class OrderNoticeAccepterImpl implements OrderNoticeAccepter{

    @Reference(version="0.0.1")
    private MachineInfoAccepter machineInfoAccepter;

    @Reference(version="0.0.1")
    private OrderAccepter orderAccepter;

    @Reference(version="0.0.1")
    private ProductAccepter productAccepter;//产品信息

    @Override
    public JSONObject OrderDeliver(String ipcCode, String deliver) {
        LogUtils.D("接收到的订单:" + deliver + "  设备: " + ipcCode);
        RowsBean row = new RowsBean();
        row.setRowId(RowID.ORDER_PUSH);
        if(EmptyUtils.isEmpty(ipcCode) || EmptyUtils.isEmpty(deliver)){
            return ErrorCode.ResultFail(ErrorCode.PARAM_ERR);
        }
        OrderVO vo = new OrderVO();
        OrderBean bean = new OrderBean();
        List<Condition> conditions = new ArrayList<Condition>();
        List<MachineInfo> result = null;


//        row.setRow(deliver);
        try {
            vo.setSuccess(true);
            bean.setOrderId(deliver);//订单号
            //查询订单得到订单对应产品
            Order order =  orderAccepter.findOrderByNumber(deliver);
            Set<OrderItem> items = order.getItems();
            OrderItem item = items.iterator().next();
            bean.setValue(item.getCount());//出酒量
            Long productId = item.getProductId();

            //查询产品
            Product product = productAccepter.getProductById(productId);
            bean.setName(product.getName());//产品名
            bean.setIpcCode(ipcCode);//设备号
            bean.setProductId(productId);

            //机器码查询机器上的产品
            conditions.add(new Condition("ipcCode", Condition.ConditionOperator.EQ, Condition.ConditionType.CHARACTER,ipcCode));
            result = machineInfoAccepter.queryMachineInfoForList(conditions);
            if (EmptyUtils.isEmpty(result) || EmptyUtils.isEmpty(result.get(0).getChannelList())) {
                return ErrorCode.ResultFail(ErrorCode.NOT_EXIST);
            }

            for (MachineChannel channel: result.get(0).getChannelList()){
                if(channel.getLiquorInfoId() == product.getId()){
                    bean.setChannel(channel.getChannelNo());//通道号
                    if((channel.getAllowance() - channel.getAlarmValue()) < item.getCount()){
                        return ErrorCode.ResultFail(ErrorCode.PARAM_UNDERFLOW);//机器酒量不足
                    }
                    vo.setRows(bean);
                    row.setRow(JSON.toJSONString(vo));
                    new AssembleMesageOrder().Assemble(ipcCode, row);
                    return ErrorCode.ResultFail(ErrorCode.SUCCESS);
                }
            }
            return ErrorCode.ResultFail(ErrorCode.NOT_EXIST);
        } catch (Exception e) {
            e.printStackTrace();
            return ErrorCode.ResultFail(ErrorCode.NOT_EXIST);
        }

    }
}
