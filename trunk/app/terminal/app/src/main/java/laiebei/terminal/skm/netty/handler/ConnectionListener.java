package laiebei.terminal.skm.netty.handler;

import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoop;
import laiebei.terminal.skm.netty.Client;
import timber.log.Timber;

/**
 * Created by Administrator on 2017/4/1.
 * 客户端启动监控，启动无法链接则进行重连
 */

public class ConnectionListener implements ChannelFutureListener {


    @Override
    public void operationComplete(ChannelFuture future) throws Exception {
        if (!future.isSuccess()) {
                Timber.w("客户端进行重连");
                final EventLoop loop = future.channel().eventLoop();
                loop.schedule(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Client.INSTANCE.stopClient();
                            Client.INSTANCE.startClient();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

                }, 1L, TimeUnit.SECONDS);

        }
    }
}
