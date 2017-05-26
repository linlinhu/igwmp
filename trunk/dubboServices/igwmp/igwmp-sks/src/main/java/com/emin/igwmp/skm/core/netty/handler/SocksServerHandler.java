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

import com.alibaba.fastjson.JSON;
import com.emin.igwmp.skm.core.msmanage.assemble.AssembleHeart;
import com.emin.igwmp.skm.core.msmanage.decode.Convert;
import com.emin.igwmp.skm.core.msmanage.entity.ChiefEntity;
import com.emin.igwmp.skm.service.DistributeService;
import com.emin.igwmp.skm.util.LogUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@ChannelHandler.Sharable
@Service("socksServerHandler")
public final class SocksServerHandler extends SimpleChannelInboundHandler<String> {

    @Resource(name="distributeService")
    DistributeService distributeService;

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        distributeService.Distribute(ctx,JSON.parseObject(msg,ChiefEntity.class));
        if (!ctx.isRemoved()) {
            return;
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        LogUtils.I("客户端退出:" + ctx.channel().remoteAddress() + "  :" + ctx.name());
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        try {
            LogUtils.I("客户端进入:" + ctx.channel().remoteAddress() + "  :" + ctx.name());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state().equals(IdleState.READER_IDLE)) {
//
            } else if (event.state().equals(IdleState.WRITER_IDLE)) {
//
            } else if (event.state().equals(IdleState.ALL_IDLE)) {
                // 发送心跳
                String heart = Convert.Encode(new AssembleHeart().Assemble("",null));
                ctx.channel().writeAndFlush(heart);
            }
        }
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable throwable) {

        throwable.printStackTrace();
        SocksServerUtils.closeOnFlush(ctx.channel());
    }
}
