package laiebei.terminal.skm.msmanage.distribute;


import laiebei.terminal.application.LaiebeiApp;
import laiebei.terminal.application.services.CabinetService;
import laiebei.terminal.skm.msmanage.entity.bean.RowsBean;
import laiebei.terminal.skm.msmanage.vo.request.RowID;
import laiebei.terminal.skm.netty.Client;
import timber.log.Timber;

/**
 * Created by Administrator on 2017/3/24.
 */
public class DistributeOrder extends DistributeFactory {
    @Override
    void ModuleCaller(String ipcId,RowsBean row) {
        //TODO:调用订单模块
        Timber.d("调用订单管理模块");
        if(row.getRowId() == RowID.ORDER_PUSH){
            LaiebeiApp.sendBroadCoast(CabinetService.ACTION_CABINET_ORDER,row.getRow());
        }
        Client.INSTANCE.getOnResponseListener().OnResult(row.getRow());
    }
}
