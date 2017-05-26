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
package com.emin.igwmp.skm.core.netty.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.socksx.SocksPortUnificationServerHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("SocksServerInitializer")
public final class SocksServerInitializer extends ChannelInitializer<SocketChannel> {

    @Resource(name = "socksServerHandler")
    private SocksServerHandler socksServerHandler;

    @Override
    public void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
        pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
        // AMF3 编码或者解码
        //pipeline.addLast("decoder", new DecoderAMF3());
        //pipeline.addLast("encoder", new EncoderAMF3());

        // 序列化对象object编码和解码 【用于Java Object】
        //pipeline.addLast("objectEncoder", new ObjectEncoder());
        //pipeline.addLast("objectDecoder", new ObjectDecoder(ClassResolvers.cacheDisabled(null)));

        // 以UTF-8格式编码和解码 数据 【用于字符串】
        pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
        pipeline.addLast("ping", new IdleStateHandler(5, 5, 10));
        // //心跳监测 读超时为20s，写超时为20s 全部空闲时间30s
        pipeline.addLast("handler",socksServerHandler);
//        ch.pipeline().addLast(
//                new LoggingHandler(LogLevel.DEBUG),
//                new SocksPortUnificationServerHandler(),
    }
}
