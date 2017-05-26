package com.emin.igwmp.skm.core.msmanage.distribute;


import com.alibaba.fastjson.JSONObject;
import com.emin.igwmp.skm.core.msmanage.entity.ChiefEntity;
import com.emin.igwmp.skm.core.msmanage.entity.bean.BodyBean;
import com.emin.igwmp.skm.core.msmanage.entity.bean.RowsBean;
import com.emin.igwmp.skm.exception.ExceptionCode;
import com.emin.igwmp.skm.exception.RunException;

import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 */
public abstract class DistributeFactory {
    abstract JSONObject ServiceCaller(String ipcId, RowsBean row);
    abstract void Response(String ipcCode,JSONObject json,int rowId);

    public void Distribute(ChiefEntity entity) throws RunException {
        if(entity == null){
            throw new RunException(ExceptionCode.C_PARAMTERS_INVALID,"Distribute Value");
        }
        BodyBean body = entity.getBody();
        String ipcCode = entity.getIdent().getDevice();
        if(body != null){
            List<RowsBean> rowList = body.getRows();
            if(rowList == null || rowList.isEmpty()){
                Response(ipcCode,ServiceCaller(ipcCode,null),0);
            }else{
                for (RowsBean row : rowList ) {
                    if(row != null){
                        Response(ipcCode,ServiceCaller(ipcCode,row),row.getRowId());
                    }
                }
            }
        }else{
            Response(ipcCode,ServiceCaller(ipcCode,null),0);
        }
    }
}
