package com.emin.igwmp.skm.core.msmanage.distribute;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.emin.igwmp.skm.core.msmanage.assemble.AssembleMesageMachine;
import com.emin.igwmp.skm.core.msmanage.entity.bean.RowsBean;
import com.emin.igwmp.skm.facade.callers.impl.SkToMachineCallerImpl;
import com.emin.igwmp.skm.facade.callers.vo.RowID;
import com.emin.igwmp.skm.util.LogUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/3/24.
 */
@Service("distributeMachine")
public class DistributeMachine extends DistributeFactory {

    @Resource( name = "skToMachineCaller")
    SkToMachineCallerImpl skToMachineCaller;


    @Override
    JSONObject ServiceCaller(String ipcId, RowsBean row) {
        if (row.getRowId() == RowID.MACHINE_RELATION){
//            LogUtils.D("获取设备关联信息");
            JSONObject json = JSON.parseObject(row.getRow());
            String ipcCode = json.getString("ipcCode");
            JSONObject resultJson = skToMachineCaller.queryRelation(ipcCode);
            return resultJson;
        }else if(row.getRowId() == RowID.MACHINE_WINES){
//            LogUtils.D("获取设备配置酒品列表");
            JSONObject json = JSON.parseObject(row.getRow());
            String ipcCode = json.getString("ipcCode");
            JSONObject resultJson = skToMachineCaller.queryWines(ipcCode);
            return resultJson;
        }else if(row.getRowId() == RowID.MACHINE_CONVERT_URL){
            JSONObject json = JSON.parseObject(row.getRow());
            String url = json.getString("url");
            String ipcCode = json.getString("ipcCode");
            JSONObject resultJson = skToMachineCaller.convertQrcode(ipcCode,url);
            return resultJson;
        }
        return new JSONObject();
    }

    @Override
    void Response(String ipcCode,JSONObject json, int rowId) {
            RowsBean row = new RowsBean();
            row.setRowId(rowId);
            row.setRow(JSON.toJSONString(json));
            new AssembleMesageMachine().Assemble(ipcCode,row);
    }
}
