package laiebei.terminal.trade.wine.validate;

import laiebei.terminal.common.utilcode.EmptyUtils;
import laiebei.terminal.dbm.DaoLite;
import laiebei.terminal.dbm.domain.VentoutOrder;
import laiebei.terminal.exceptions.ExceptionCode;
import laiebei.terminal.exceptions.RunException;

/**
 * Created by Administrator on 2017/4/20.
 */

public class ValidateCabinet {
    public static  boolean validate(String orderId,Long producuId,int channel, int value)throws RunException{
        if(EmptyUtils.isEmpty(orderId)){
            throw new RunException(ExceptionCode.T_PARAMETER_ERROR,"");
        }
        VentoutOrder order = DaoLite.INSTANCE.queryOrder(orderId);
        if(EmptyUtils.isEmpty(order)){
            DaoLite.INSTANCE.saveOrUpdateOrder(orderId,producuId,channel,value,0,false);
            return true; //数据库没有保存过该订单，说明订单没有执行过
        }

        if(order.getChannel() == channel && order.getValue() != value){
            DaoLite.INSTANCE.saveOrUpdateOrder(orderId,producuId,channel,value,0,false);
            return true;//统一个订单，通道相同，出酒值不同，可以允许出酒
        }

        return true;
    }
}
