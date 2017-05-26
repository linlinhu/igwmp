package com.emin.igwmp.skm.facade.accepters;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator on 2017/4/18.
 */
public interface MachineNoticeAccepter {
    /**
     * 设备修改通知
     * */
    public void MachineUpdateNotice(String ipcCode)throws  Exception;
}
