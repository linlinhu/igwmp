package laiebei.terminal.skm.msmanage.distribute;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import laiebei.terminal.common.cache.KeyCode;
import laiebei.terminal.common.cache.disk.RomLruCache;
import laiebei.terminal.common.cache.ram.RamLruCache;
import laiebei.terminal.skm.msmanage.entity.bean.RowsBean;
import laiebei.terminal.skm.netty.Client;
import timber.log.Timber;

/**
 * Created by Administrator on 2017/3/24.
 */
public class DistributeMachine extends DistributeFactory {
    @Override
    void ModuleCaller(String ipcId,RowsBean row) {
        //TODO:调用设备模块
        Timber.d("调用设备管理模块");
        JSONObject obj = JSON.parseObject(row.getRow());
        if(obj.getBoolean("success")){
            RamLruCache.INSTANCE.saveCache((KeyCode.MACHINE_KEY + row.getRowId()) ,row.getRow());//保存内存缓存
            RomLruCache.INSTANCE.saveForDisk((KeyCode.MACHINE_KEY + row.getRowId()) ,row.getRow());
        }
        Client.INSTANCE.getOnResponseListener().OnResult(row.getRow());
    }
}
