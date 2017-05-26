package com.emin.igwmp.skm.facade.callers;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
 */
public interface SkToMachineCaller {

    /**
     *获取设备关联信息
     * @ipcCode :机柜工控机编码
     * @return 返回设备关联信息json字符串
     * */
    public JSONObject queryRelation(String ipcCode);

    /**
     * 根据机器工控机编码获取机器通道信息
     * @ipcCode:机柜工控机编码
     * */
    public JSONObject queryWines(String ipcCode);

    /**
     * 转换带参二维码
     * @param url 普通微信地址加上参数
     * */
    public JSONObject convertQrcode(String ipcCode,String url);


}
