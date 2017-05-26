package laiebei.terminal.skm.accepter.impl;

import com.alibaba.fastjson.JSON;

import laiebei.terminal.common.cache.KeyCode;
import laiebei.terminal.common.cache.disk.RomLruCache;
import laiebei.terminal.common.cache.ram.RamLruCache;
import laiebei.terminal.common.utilcode.EmptyUtils;
import laiebei.terminal.exceptions.ErrorCode;
import laiebei.terminal.exceptions.RunException;
import laiebei.terminal.skm.accepter.MasterAccepter;
import laiebei.terminal.skm.listener.OnResponseListener;
import laiebei.terminal.skm.msmanage.assemble.AssembleMesageMachine;
import laiebei.terminal.skm.msmanage.assemble.AssembleMesageOrder;
import laiebei.terminal.skm.msmanage.entity.bean.RowsBean;
import laiebei.terminal.skm.msmanage.vo.request.ReqPay;
import laiebei.terminal.skm.msmanage.vo.request.ReqQrcode;
import laiebei.terminal.skm.msmanage.vo.request.ReqRelation;
import laiebei.terminal.skm.msmanage.vo.request.ReqReplaces;
import laiebei.terminal.skm.msmanage.vo.request.ReqReport;
import laiebei.terminal.skm.msmanage.vo.request.ReqTakeCode;
import laiebei.terminal.skm.msmanage.vo.request.ReqWines;
import laiebei.terminal.skm.msmanage.vo.request.RowID;
import laiebei.terminal.skm.netty.Client;

/**
 * Created by Administrator on 2017/4/17.
 */

public class MasterAccepterImpl  implements MasterAccepter {

    @Override
    public void RelationInfo(String ipcCode, OnResponseListener listener) throws RunException{
        String cacheRow =  RomLruCache.INSTANCE.getForDisk(KeyCode.MACHINE_KEY + RowID.MACHINE_RELATION);
        if(EmptyUtils.isEmpty(cacheRow)){
            AssembleMesageMachine machine = new AssembleMesageMachine();
            RowsBean row = new RowsBean();
            ReqRelation req = new ReqRelation();
            req.setIpcCode(ipcCode);
            row.setRowId(RowID.MACHINE_RELATION);
            row.setRow(JSON.toJSONString(req));
            machine.Assemble(0,row);
            Client.INSTANCE.setOnResponseListener(listener);
        }else{
            listener.OnResult(cacheRow);//使用缓存数据
        }
    }

    @Override
    public void WinesInfo(String ipcCode,OnResponseListener listener){
        String cacheRow = (String) RamLruCache.INSTANCE.getCache(KeyCode.MACHINE_KEY + RowID.MACHINE_WINES);
        if(EmptyUtils.isEmpty(cacheRow)){
            AssembleMesageMachine machine = new AssembleMesageMachine();
            RowsBean row = new RowsBean();
            ReqWines req = new ReqWines();
            req.setIpcCode(ipcCode);
            row.setRowId(RowID.MACHINE_WINES);
            row.setRow(JSON.toJSONString(req));
            Client.INSTANCE.setOnResponseListener(listener);
            try{
                machine.Assemble(0,row);
            }catch (Exception e){
                e.printStackTrace();
                listener.OnFail(ErrorCode.ResultFailString(ErrorCode.CLIENT_BREAK));
            }
        }else{
            listener.OnResult(cacheRow);
        }

    }

    @Override
    public void PayMent(String ipcCode, Long productId, Long value, double price, int payType,OnResponseListener listener) {
        ReqPay pay = new ReqPay();
        RowsBean row = new RowsBean();
        pay.setIpcCode(ipcCode);
        pay.setProductId(productId);
        pay.setValue(value);
        pay.setPrice(price);
        pay.setPayType(payType);
        row.setRowId(RowID.ORDER_PAY);
        row.setRow(JSON.toJSONString(pay));
        new AssembleMesageOrder().Assemble(0,row);
        Client.INSTANCE.setOnResponseListener(listener);
    }

    @Override
    public void TakeCodeValidate(String ipcCode, Long productId, String code, OnResponseListener listener) {
        ReqTakeCode takeCode = new ReqTakeCode();
        RowsBean row = new RowsBean();
        takeCode.setIpcCode(ipcCode);
        takeCode.setProductId(productId);
        takeCode.setCode(code);
        row.setRowId(RowID.ORDER_TAKE_CODE);
        row.setRow(JSON.toJSONString(takeCode));
        new AssembleMesageOrder().Assemble(0,row);
        Client.INSTANCE.setOnResponseListener(listener);
    }


    @Override
    public void ReportOrder(String ipcCode, String orderId,  int channel,
                            int value, boolean isSuccess,OnResponseListener listener) throws RunException{
        RowsBean row = new RowsBean();
        ReqReport report = new ReqReport();
        report.setIpcCode(ipcCode);
        report.setTime(System.currentTimeMillis());
        report.setOrderId(orderId);
        report.setChannel(channel);
        report.setValue(value);
        report.setSuccess(isSuccess);
        row.setRowId(RowID.ORDER_REPORT);
        row.setRow(JSON.toJSONString(report));
        Client.INSTANCE.setOnResponseListener(listener);
        new AssembleMesageOrder().Assemble(0,row);
    }

    @Override
    public void getReplaces(String ipcCode, String phone, OnResponseListener listener) {
        RowsBean row = new RowsBean();
        ReqReplaces replaces = new ReqReplaces();
        replaces.setIpcCode(ipcCode);
        replaces.setPhone(phone);

        row.setRowId(RowID.ORDER_REPLACE);
        row.setRow(JSON.toJSONString(replaces));
        Client.INSTANCE.setOnResponseListener(listener);
        new AssembleMesageOrder().Assemble(0,row);
    }

    @Override
    public void getQrcode(String ipcCode, String publicUtl, OnResponseListener listener) {
        String cacheRow =  RomLruCache.INSTANCE.getForDisk(KeyCode.MACHINE_KEY + RowID.MACHINE_CONVERT_URL);
        if(EmptyUtils.isEmpty(cacheRow)){
            RowsBean row = new RowsBean();
            ReqQrcode qrcode = new ReqQrcode();
            qrcode.setIpcCode(ipcCode);
            qrcode.setUrl(publicUtl);
            row.setRowId(RowID.MACHINE_CONVERT_URL);
            row.setRow(JSON.toJSONString(qrcode));
            Client.INSTANCE.setOnResponseListener(listener);
            new AssembleMesageMachine().Assemble(0,row);
        }else{
            listener.OnResult(cacheRow);
        }

    }


}
