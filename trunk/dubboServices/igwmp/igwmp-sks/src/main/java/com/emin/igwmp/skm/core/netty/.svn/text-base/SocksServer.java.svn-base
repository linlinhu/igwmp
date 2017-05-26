/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.emin.igwmp.skm.core.netty;

import com.emin.igwmp.skm.core.netty.group.BossGroup;
import com.emin.igwmp.skm.core.netty.group.WorkerGroup;
import com.emin.igwmp.skm.core.netty.handler.SocksServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoop;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import javax.annotation.Resource;
import javax.net.ssl.SSLException;
import java.util.concurrent.TimeUnit;

public final class SocksServer {

    private static final int PORT = Integer.parseInt(System.getProperty("port", "1085"));

    @Resource(name = "SocksServerBootstrap")
    private ServerBootstrap mServerBootstrap;

    @Resource(name="bossGroup")
    private BossGroup bossGroup;

    @Resource(name="workerGroup")
    private WorkerGroup workerGroup;

    @Resource(name = "SocksServerInitializer")
    private SocksServerInitializer mSocksServerInitializer;

    private ChannelFuture mChannelFuture;

    /**
     * 启动socket服务
     * */
    public void startServer(){
        mServerBootstrap.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(mSocksServerInitializer);

        try {
//            mServerBootstrap.bind(PORT).sync().channel().closeFuture().sync();
            mChannelFuture = mServerBootstrap.bind(PORT).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (!future.isSuccess()) {
                        final EventLoop loop = future.channel().eventLoop();
                        loop.schedule(new Runnable() {
                            @Override
                            public void run() {
                                    startServer();
                            }

                        }, 1L, TimeUnit.SECONDS);

                    }
                }
            }).sync();
            mChannelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
            stopServer();
        }

    }

    /**
     * 注销socket服务
     * */
    public void stopServer(){
        if(mChannelFuture != null){
            try {
                mChannelFuture.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

}
