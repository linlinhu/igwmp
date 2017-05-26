package com.emin.igwmp.skm.core.msmanage.distribute;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.emin.igwmp.skm.core.msmanage.assemble.AssembleMesageMachine;
import com.emin.igwmp.skm.core.msmanage.assemble.AssembleMesageOrder;
import com.emin.igwmp.skm.core.msmanage.entity.bean.RowsBean;
import com.emin.igwmp.skm.exception.ErrorCode;
import com.emin.igwmp.skm.facade.callers.SkToOrderCaller;
import com.emin.igwmp.skm.facade.callers.SkToPayCaller;
import com.emin.igwmp.skm.facade.callers.impl.SkToMachineCallerImpl;
import com.emin.igwmp.skm.facade.callers.impl.SkToPayCallerImpl;
import com.emin.igwmp.skm.facade.callers.vo.RowID;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/3/24.
 */
@Service("distributeOrder")
public class DistributeOrder extends DistributeFactory {

    @Resource( name = "skToPayCaller")
    SkToPayCaller skToPayCaller;

    @Resource(name = "skToOrderCaller")
    SkToOrderCaller skToOrderCaller;

    @Override
    JSONObject ServiceCaller(String ipcId, RowsBean row) {
        if(row.getRowId() == RowID.ORDER_PAY){
            return skToPayCaller.createPay(ipcId,row.getRow());
        }else if(row.getRowId() == RowID.ORDER_REPORT){
            return skToOrderCaller.ReportOrder(row.getRow());
        }else if(row.getRowId() == RowID.ORDER_TAKE_CODE){
            return skToOrderCaller.TakeCodeValidate(row.getRow());
        }else if (row.getRowId() == RowID.ORDER_REPLACE){
            return skToOrderCaller.getReplaces(row.getRow());
        }
        return ErrorCode.ResultFail(ErrorCode.PARAM_OVERFLOW);
    }

    @Override
    void Response(String ipcCode, JSONObject json, int rowId) {
        RowsBean row = new RowsBean();
        row.setRowId(rowId);
        row.setRow(JSON.toJSONString(json));
        new AssembleMesageOrder().Assemble(ipcCode,row);
    }
}
