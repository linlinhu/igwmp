package laiebei.terminal.skm.netty.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

/**
 * 版权:贵州仁塔科技有限公司
 * 功能描述:
 * 创建者:Created by Administrator-aleyds on 2017/3/26 14:05.
 * 对外接口:
 */

public class ClientChannelInitializer extends ChannelInitializer<SocketChannel>{
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
		pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
		pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
		pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
		pipeline.addLast("idleStateHandler", new IdleStateHandler(5, 5, 10)); //心跳监测 读超时为20s，写超时为20s 全部空闲时间30s
		pipeline.addLast("handler", new ClinetHandler());
	}


}
