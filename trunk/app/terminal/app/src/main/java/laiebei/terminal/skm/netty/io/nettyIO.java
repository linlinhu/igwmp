package laiebei.terminal.skm.netty.io;


import io.netty.channel.Channel;
import laiebei.terminal.common.utilcode.EmptyUtils;
import laiebei.terminal.exceptions.ExceptionCode;
import laiebei.terminal.exceptions.RunException;
import laiebei.terminal.skm.netty.Client;
import laiebei.terminal.skm.netty.handler.Impl.ClientHeartHandlerImpl;
import timber.log.Timber;

/**
 * Created by Administrator on 2017/4/7.
 */

public class nettyIO {

    public static  void write( String message) throws RunException{
        if(EmptyUtils.isEmpty(message)){
            throw new RunException(ExceptionCode.T_PARAMETER_ERROR,"Netty IO Write");
        }

        Channel ioChannel = Client.INSTANCE.getClientChennel();
        if(ioChannel == null){
            throw new RunException(ExceptionCode.INFO_ERROR,"");
        }
        if(ioChannel.isActive() && ioChannel.isOpen() && ioChannel.isRegistered() && ioChannel.isWritable()){
            Timber.d("向服务器发送信息：" + message);
            ioChannel.writeAndFlush(message).addListener(new ClientHeartHandlerImpl(message));
        }else{
            throw new RunException(ExceptionCode.INFO_ERROR,"");
        }

    }

}
