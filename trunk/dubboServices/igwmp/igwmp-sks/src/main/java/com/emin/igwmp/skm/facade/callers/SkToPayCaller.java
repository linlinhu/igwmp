package com.emin.igwmp.skm.facade.callers;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator on 2017/4/18.
 */
public interface SkToPayCaller {

    public JSONObject createPay(String ipcCode, String payInfo);
}
