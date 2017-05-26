package com.emin.igwmp.skm.core.netty.stream;

import com.alibaba.fastjson.JSON;
import com.emin.igwmp.skm.core.msmanage.entity.ChiefEntity;
import com.emin.igwmp.skm.core.pond.PondAdapter;
import com.emin.igwmp.skm.core.pond.PondEntity;
import com.emin.igwmp.skm.exception.ExceptionCode;
import com.emin.igwmp.skm.exception.RunException;
import com.emin.igwmp.skm.util.LogUtils;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by Administrator on 2017/3/22.
 */
public class nettyIO {

    public static void SocketStreamRead(String message)throws RunException {
        if(message == null){
            throw new RunException(ExceptionCode.T_PARAMTERS_INVALID,"Socket Read");
        }
        ChiefEntity entity = JSON.parseObject(message , ChiefEntity.class);

    }

    public static void Write(String clientId, String message)throws RunException{
        if(clientId == null || message == null){
            throw new RunException(ExceptionCode.T_PARAMTERS_INVALID,"Socket Write");
        }
        PondEntity pentity = PondAdapter.INSTANCE.queryPond(clientId);
        if(pentity == null){
            throw new RunException(ExceptionCode.M_DATA_REPEAT,"Socket Write");
        }
        ChannelHandlerContext clx = pentity.getChannel();
        if (clx == null || !clx.channel().isActive()) {
            throw new RunException(ExceptionCode.M_DATA_REPEAT,"clx Socket Write");
        }
        LogUtils.D("向客户端发送信息：" + message);
        clx.channel().writeAndFlush(message);
    }
}
