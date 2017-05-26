package laiebei.terminal.skm.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import laiebei.terminal.skm.msmanage.assemble.AssembleSession;
import laiebei.terminal.skm.msmanage.convert.MessageConvert;
import laiebei.terminal.skm.msmanage.distribute.Distribute;
import laiebei.terminal.skm.msmanage.entity.ChiefEntity;
import laiebei.terminal.skm.netty.Client;
import laiebei.terminal.skm.netty.io.nettyIO;
import timber.log.Timber;

/**
 * 版权:贵州仁塔科技有限公司
 * 功能描述:
 * 创建者:Created by Administrator-aleyds on 2017/3/17 14:04.
 * 对外接口:
 */

public class ClinetHandler extends ChannelInboundHandlerAdapter {


	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		super.channelReadComplete(ctx);
//		Timber.d("数据接收完成" );
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object message) throws Exception {
		super.channelRead(ctx, message);
//		Timber.d("接收到服务器数据" + message);
		Distribute.MessageDistribute((String) message);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		Timber.d("Socket 连接进入  请求通信session");
		ChiefEntity entity = new AssembleSession().Assemble(0,null);
		nettyIO.write(MessageConvert.VOToMessage(entity));
//		Client.INSTANCE.getRepeatHandler().start();//传输中连接断开，数据重发
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		Timber.d("Socket 运行时连接退出");
		Client.INSTANCE.getReconnectHandler().ReceiveReconnectRequest(ctx);
//		final EventLoop eventLoop = ctx.channel().eventLoop();
//		eventLoop.schedule(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					Client.INSTANCE.creatClient();
//				} catch (SSLException e) {
//					e.printStackTrace();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		}, 1L, TimeUnit.SECONDS);
		super.channelInactive(ctx);
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		super.userEventTriggered(ctx, evt);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
		Timber.e("客户端连接发生错误:" );
		cause.printStackTrace();
	}
}
