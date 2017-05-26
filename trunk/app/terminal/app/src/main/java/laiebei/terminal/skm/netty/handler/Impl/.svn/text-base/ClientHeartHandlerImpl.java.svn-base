package laiebei.terminal.skm.netty.handler.Impl;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import timber.log.Timber;

/**
 * Created by Administrator on 2017/4/6.
 */

public class ClientHeartHandlerImpl  implements ChannelFutureListener {

    private String SendMessage;

    public ClientHeartHandlerImpl(String message){
        SendMessage = message;
    }

    @Override
    public void operationComplete(ChannelFuture future) throws Exception {
        if(!future.isSuccess()){
            Timber.w("发送数据过程失败端进行重连");
//            future.channel().pipeline().fireChannelInactive();

//            final EventLoop loop = future.channel().eventLoop();
//            loop.schedule(new Runnable() {
//                @Override
//                public void run() {
//                    try {
////                        Client.INSTANCE.getRepeatHandler().add(SendMessage);
////                        Client.INSTANCE.creatClient();
//                        SocketAddress report =
//                        future.channel().connect(NeConfig.HOST, NeConfig.PORT)
//                    } catch (SSLException e) {
//                        e.printStackTrace();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }, 1L, TimeUnit.SECONDS);
        }
    }
}
