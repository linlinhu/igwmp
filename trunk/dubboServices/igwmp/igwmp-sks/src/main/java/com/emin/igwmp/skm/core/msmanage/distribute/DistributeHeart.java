package com.emin.igwmp.skm.core.msmanage.distribute;

import com.alibaba.fastjson.JSONObject;
import com.emin.igwmp.skm.core.msmanage.entity.bean.RowsBean;
import com.emin.igwmp.skm.facade.callers.impl.SkToMachineCallerImpl;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/3/26.
 */
@Service("distributeHeart")
public class DistributeHeart extends DistributeFactory {
    @Override
    JSONObject ServiceCaller(String ipcId,RowsBean row) {
        return null;
    }

    @Override
    void Response(String ipcCode, JSONObject json, int rowId) {

    }

}
