package laiebei.terminal.skm.msmanage.convert;


import com.alibaba.fastjson.JSON;

import io.netty.channel.ChannelHandlerContext;
import laiebei.terminal.common.utilcode.EmptyUtils;
import laiebei.terminal.exceptions.ExceptionCode;
import laiebei.terminal.exceptions.RunException;
import laiebei.terminal.skm.msmanage.entity.ChiefEntity;

/**
 * Created by Administrator on 2017/3/24.
 */
public class MessageConvert {


    public static ChiefEntity MessgeToVO(String message) throws RunException {
        if(EmptyUtils.isEmpty(message)){
            throw new RunException(ExceptionCode.T_PARAMETER_ERROR,"Messge Convert To VO");
        }
        ChiefEntity entity = JSON.parseObject(message,ChiefEntity.class);
        if(EmptyUtils.isEmpty(entity)){
            throw new RunException(ExceptionCode.INFO_ERROR,"Messge Convert To VO");
        }
        return entity;
    }

    public static String VOToMessage(ChiefEntity entity)throws RunException {
        if(EmptyUtils.isEmpty(entity)){
            throw new RunException(ExceptionCode.T_PARAMETER_ERROR,"Messge Convert To Message");
        }
        String message = JSON.toJSONString(entity);
        if(EmptyUtils.isEmpty(message)){
            throw new RunException(ExceptionCode.INFO_ERROR,"Messge Convert To Message");
        }
        return message;
    }



    public static void Decode(ChannelHandlerContext ctx, String message) throws RunException {
        if(message == null || ctx == null){
            throw new RunException(ExceptionCode.T_PARAMETER_ERROR , "Message Docede");
        }
        ChiefEntity entity = JSON.parseObject(message, ChiefEntity.class);
        if(entity == null){
            throw new RunException(ExceptionCode.INFO_ERROR,"Message Docede");
        }

    }

    public static String Encode(ChiefEntity entity){
        if(entity == null){
            throw  new RunException(ExceptionCode.T_PARAMETER_ERROR, "Message Encode");
        }

        return JSON.toJSONString(entity);
    }
}
