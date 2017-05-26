package com.emin.igwmp.skm.core.msmanage.distribute;

import com.alibaba.fastjson.JSONObject;
import com.emin.igwmp.skm.core.msmanage.entity.bean.RowsBean;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/3/24.
 */
@Service("distributeProduct")
public class DistributeProduct extends DistributeFactory {
    @Override
    JSONObject ServiceCaller(String ipcId, RowsBean row) {
        return null;
    }

    @Override
    void Response(String ipcCode, JSONObject json, int rowId) {

    }
}
