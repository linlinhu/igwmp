package laiebei.terminal.skm.msmanage.distribute;


import laiebei.terminal.skm.msmanage.entity.bean.RowsBean;
import timber.log.Timber;

/**
 * Created by Administrator on 2017/3/24.
 */
public class DistributeProduct extends DistributeFactory {
    @Override
    void ModuleCaller(String ipcId,RowsBean row) {
        //TODO:调用产品模块
        Timber.d("调用产品管理模块");
    }
}
