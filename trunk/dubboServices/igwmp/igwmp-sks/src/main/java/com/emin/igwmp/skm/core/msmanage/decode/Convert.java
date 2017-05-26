package com.emin.igwmp.skm.core.msmanage.decode;

import com.alibaba.fastjson.JSON;
import com.emin.igwmp.skm.core.msmanage.entity.ChiefEntity;
import com.emin.igwmp.skm.exception.ExceptionCode;
import com.emin.igwmp.skm.exception.RunException;
import com.emin.igwmp.skm.service.impl.DistributeServiceImpl;
import io.netty.channel.ChannelHandlerContext;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/3/24.
 */
public class Convert {

    public static void Decode(ChannelHandlerContext ctx,String message) throws RunException {
        if(message == null || ctx == null){
            throw new RunException(ExceptionCode.C_PARAMTERS_INVALID , "Message Docede");
        }
        ChiefEntity entity = JSON.parseObject(message, ChiefEntity.class);
        if(entity == null){
            throw new RunException(ExceptionCode.INFO_ERROR,"Message Docede");
        }
        new DistributeServiceImpl().Distribute(ctx,entity);
    }

    public static String Encode(ChiefEntity entity){
        if(entity == null){
            throw  new RunException(ExceptionCode.C_PARAMTERS_INVALID, "Message Encode");
        }

        return JSON.toJSONString(entity);
    }
}
