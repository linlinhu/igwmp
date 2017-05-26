package laiebei.terminal.skm.msmanage.distribute;

import com.alibaba.fastjson.JSON;

import laiebei.terminal.common.utilcode.DeviceUtils;
import laiebei.terminal.common.utilcode.EmptyUtils;
import laiebei.terminal.dbm.DaoLite;
import laiebei.terminal.exceptions.ExceptionCode;
import laiebei.terminal.exceptions.RunException;
import laiebei.terminal.skm.msmanage.assemble.AssembleHeart;
import laiebei.terminal.skm.msmanage.convert.MessageConvert;
import laiebei.terminal.skm.msmanage.entity.ChiefEntity;
import laiebei.terminal.skm.msmanage.validate.ValidateEng;
import laiebei.terminal.skm.netty.Client;
import laiebei.terminal.skm.netty.io.nettyIO;
import timber.log.Timber;

/**
 * Created by Administrator on 2017/4/7.
 */

public class Distribute {
    public static void  MessageDistribute(String message) throws RunException{
        if(EmptyUtils.isEmpty(message)){
            throw new RunException(ExceptionCode.T_PARAMETER_ERROR, "message Distribute");
        }
        final ChiefEntity entity = MessageConvert.MessgeToVO(message);
        if(EmptyUtils.isEmpty(entity)){
            throw new RunException(ExceptionCode.INFO_ERROR,"Mesage Distribute entity");
        }
        if(entity.getIdent().getType() == 0){ // 心跳数据直接回复服务器
            nettyIO.write(MessageConvert.VOToMessage(new AssembleHeart().Assemble(0,null)));
        }else{
            if(entity.getHandler().getType() == 0){//请求到session直接保存数据库
                DaoLite.INSTANCE.saveOrUpdateSession(entity.getHandler().getSesscion());
            }else if(entity.getHandler().getType() == 2){
                Timber.d("Netty业务交互数据:" + message);
                if(entity.getBody().getCode() == 0){
                    if(ValidateEng.validate(entity.getIdent().getDevice(),entity.getHandler())){
                        if(entity.getBody().getType() == 0){
                            new DistributeMachine().Distribute(entity);
                        }else if(entity.getBody().getType() == 1){
                            new DistributeProduct().Distribute(entity);
                        }else if(entity.getBody().getType() == 2){
                            new DistributeOrder().Distribute(entity);
                        }
                    }
                }else {
                    //TODO:服务器返回错误处理
                    Timber.e("服务器返回错误:Code:" + entity.getBody().getCode());
                    Client.INSTANCE.getOnResponseListener().OnFail(JSON.toJSONString(entity.getBody()));
                }
            }
        }
    }
}
