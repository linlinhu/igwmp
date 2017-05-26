package laiebei.terminal.trade.wine.facade.accepter;

import com.alibaba.fastjson.JSONObject;

import laiebei.terminal.trade.wine.listener.OnCabinetListener;

/**
 * Created by Administrator on 2017/4/20.
 */

public interface CabinetAccepter {


    /**
     * 控制机柜出酒
     * @param orderId 交易序号
     * @param channel 出酒通道号
     * @param value 出酒量
     * @param listener 返回监听
     * */
    public JSONObject vendout(String orderId, Long productId,int channel, int value, OnCabinetListener listener);
}
