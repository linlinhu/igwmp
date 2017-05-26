package com.sks.client.handler;

import com.alibaba.fastjson.JSON;
import com.emin.igwmp.skm.core.msmanage.entity.ChiefEntity;
import com.emin.igwmp.skm.core.msmanage.entity.bean.HandlerBean;
import com.emin.igwmp.skm.core.msmanage.entity.bean.IdentBean;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.Date;


public class TcpClientHandler extends SimpleChannelInboundHandler<String> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg)
			throws Exception {
		
	    System.out.println("channelRead0 client接收到服务器返回的消息:"+msg);
	}
	
	 @Override
	    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
	            throws Exception {
	        if (evt instanceof IdleStateEvent) {
	            IdleStateEvent event = (IdleStateEvent) evt;
	            if (event.state().equals(IdleState.READER_IDLE)) {
//	                System.out.println("READER_IDLE");
	                // 超时关闭channel
	               //ctx.channel().close();
	            } else if (event.state().equals(IdleState.WRITER_IDLE)) {
//	                System.out.println("WRITER_IDLE");
	                // 超时关闭channel
	                //ctx.channel().close();
	            } else if (event.state().equals(IdleState.ALL_IDLE)) {
//	                System.out.println("ALL_IDLE");
	                // 发送心跳

					ChiefEntity entity = new ChiefEntity();
					IdentBean Ident = new IdentBean();
					Ident.setType(1);
					Ident.setDevice("233243543");
					HandlerBean handler = new HandlerBean();
					handler.setType(0);
					handler.setVersion("2222");
					handler.setLenght(0);
					handler.setTime(new Date().getTime());

					entity.setIdent(Ident);
					entity.setHandler(handler);
					ctx.channel().writeAndFlush(JSON.toJSONString(entity));

	            }
	        }
	        super.userEventTriggered(ctx, evt); 
	    } 
}