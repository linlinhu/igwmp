package laiebei.terminal.trade.wine.facade.accepter.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import laiebei.terminal.exceptions.ErrorCode;
import laiebei.terminal.trade.wine.Liquid;
import laiebei.terminal.trade.wine.facade.accepter.CabinetAccepter;
import laiebei.terminal.trade.wine.listener.OnCabinetListener;
import laiebei.terminal.trade.wine.validate.ValidateCabinet;
import laiebei.terminal.trade.wine.vo.StatusVO;
import laiebei.terminal.trade.wine.vo.bean.StatusBean;
import lib.laiebei.com.cabinetlibrary.listener.VendoutReportListener;
import lib.laiebei.com.cabinetlibrary.listener.VendoutStatusListener;

/**
 * Created by Administrator on 2017/4/20.
 */

public class CabinetAccepterImpl implements CabinetAccepter {


    @Override
    public JSONObject vendout(String orderId, Long productId, int channel, int value, final OnCabinetListener listener) {

        if(!ValidateCabinet.validate(orderId,productId,channel,value)){
            return ErrorCode.ResultFail(ErrorCode.VERIFY_ERR);
        }
        if(value > 2000 || channel > 6){
            return ErrorCode.ResultFail(ErrorCode.PARAM_OVERFLOW);
        }
        if(value < 20 || channel < 0){
            return ErrorCode.ResultFail(ErrorCode.PARAM_UNDERFLOW);
        }
        final StatusVO vo  = new StatusVO();
        final StatusBean bean = new StatusBean();
        bean.setOrderId(orderId);
        bean.setProductId(productId);
        try{
            Liquid.INSTANCE.getCabinet().CabinetVendout(1111, 0x2, 1, channel, value, new VendoutStatusListener() {

                @Override
                public void OnNothing(byte channel, int value, int actualValue) {
                    bean.setStatus(0);
                    vo.setSuccess(true);
                    bean.setChannel(channel);
                    bean.setValue(value);
                    bean.setActutalValue(actualValue);
                    bean.setResultCode(0);
                    vo.setRows(bean);
                    listener.OnResult(JSON.toJSONString(vo));
                }

                @Override
                public void OnReady(byte channel, int value, int actualValue) {
                    bean.setStatus(1);
                    vo.setSuccess(true);
                    bean.setChannel(channel);
                    bean.setValue(value);
                    bean.setActutalValue(actualValue);
                    bean.setResultCode(0);
                    vo.setRows(bean);
                    listener.OnResult(JSON.toJSONString(vo));
                }

                @Override
                public void OnRunning(byte channel, int value, int actualValue) {
                    bean.setStatus(2);
                    vo.setSuccess(true);
                    bean.setChannel(channel);
                    bean.setValue(value);
                    bean.setActutalValue(actualValue);
                    bean.setResultCode(0);
                    vo.setRows(bean);
                    listener.OnResult(JSON.toJSONString(vo));
                }

                @Override
                public void OnComplete(byte channel, int value, int actualValue) {

                }

                @Override
                public void OnFaild(byte errorCode, byte channel, int value, int actualValue) {
                }
            }, new VendoutReportListener() {

                @Override
                public void OnReport(int serial, String time, byte type, byte channel, int value, int actualValue) {
                    bean.setStatus(3);
                    vo.setSuccess(true);
                    bean.setChannel(channel);
                    bean.setValue(value);
                    bean.setActutalValue(actualValue);
                    bean.setResultCode(0);
                    vo.setRows(bean);
                    listener.OnResult(JSON.toJSONString(vo));
                }

                @Override
                public void OnFaild(byte errorCode, byte channel, int value, int actualValue) {
                    bean.setStatus(4);
                    bean.setChannel(channel);
                    bean.setValue(value);
                    vo.setSuccess(true);
                    bean.setActutalValue(actualValue);
                    bean.setResultCode(errorCode);
                    vo.setRows(bean);
                    listener.OnResult(JSON.toJSONString(vo));
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            return ErrorCode.ResultFail(ErrorCode.SERVICE_CALL);
        }
        return ErrorCode.ResultFail(ErrorCode.SUCCESS);
    }
}
