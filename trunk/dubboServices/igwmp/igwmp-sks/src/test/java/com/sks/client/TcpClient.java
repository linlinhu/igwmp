package com.sks.client;

import com.emin.igwmp.skm.core.msmanage.entity.ChiefEntity;
import com.sks.client.handler.TcpClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;


public class TcpClient {
	public static String HOST = "127.0.0.1";
	public static int PORT = 1080;
	public static int MAX_RECONNECT_TIMEOUT_COUNT=3;//最大重连数
	public int curTimeoutCount = 0;//当前重连数
	public EventLoopGroup group = null;
	public Bootstrap bootstrap = getBootstrap();
	public Channel channel = getChannel(HOST,PORT);
	private static TcpClient tcpClient= new TcpClient();

	/**
	 * 初始化Bootstrap
	 * @return
	 */
	public Bootstrap getBootstrap()  {

		group = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(group).channel(NioSocketChannel.class);
		b.handler(new ChannelInitializer<Channel>() {
			@Override
			protected void initChannel(Channel ch) throws Exception {
				ChannelPipeline pipeline = ch.pipeline();
				pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
				pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
				pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
				pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
				pipeline.addLast("idleStateHandler", new IdleStateHandler(5, 5, 10)); //心跳监测 读超时为20s，写超时为20s 全部空闲时间30s
				pipeline.addLast("handler", new TcpClientHandler());
			}
		});
		b.option(ChannelOption.SO_KEEPALIVE, true);
		return b;
	}
	
	public static TcpClient getInstance(){
		return tcpClient;
	}

	public Channel getChannel(String host,int port){
		Channel channel = null;
		try {
			channel = bootstrap.connect(host, port).sync().channel();
		} catch (Exception e) {
		    System.out.println(String.format("连接Server(IP[%s],PORT[%s])失败", host,port));
		    e.printStackTrace();
			return null;
		}
		return channel;
	}

	public void sendMsg(ChiefEntity entity){
		try {
			while(channel==null){
				if(curTimeoutCount>=MAX_RECONNECT_TIMEOUT_COUNT){
					System.out.println("已到达最大重连数，请下次再尝试....");
					curTimeoutCount=0;
					break;
				}
				curTimeoutCount++;
				reconnect(channel);		
			}
			if(channel!=null){
				channel.writeAndFlush(entity).sync();
			}
		} catch (Exception e) {
			e.printStackTrace();
		    group.shutdownGracefully();
		}
	}
	
	public Channel reconnect(Channel currentChannel){
		if(currentChannel==null || !currentChannel.isActive()){
			System.out.println("重新连接....");
			return getChannel(HOST,PORT);
		}
		return null;
	}

    public static void main(String[] args) throws Exception {
//		    BufferedReader sin = new BufferedReader(new InputStreamReader(
//			    System.in));
//		    String readline;

//		    readline = sin.readLine(); // 从系统标准输入读入一字符串
//
//		    while (!readline.equals("bye")) {
//
//			// 刷新输出流，使Server马上收到该字符串
//			System.out.println("Client:" + readline);
//			TcpClient.sendMsg("{\"userId\": \"10000631\",\"sendUserId\": \"10000632\", \"msg\": \"" + readline + "\"}");
//			//TcpClient.sendMsg("{\"userId\": \"10000631\",\"sendUserId\": \"10000632\", \"msg\": \"" + readline + "\"}");
//
//			// 从Server读入一字符串，并打印到标准输出上
//			readline = sin.readLine(); // 从系统标准输入读入一字符串
//
//		    } // 继续循环
			int clientCount = 0;
			int maxCount = 1;
			TcpClient[] clients = new TcpClient[maxCount];
//			while(clientCount<=maxCount-1){
//				clientCount ++;
//				TcpClient tcpClient = new TcpClient();
//				clients[clientCount-1] = tcpClient;
//				ChiefEntity entity = new ChiefEntity();
//				entity.getIdent().setType(1);
//				entity.getIdent().setDevice("ddddddd");
//				tcpClient.sendMsg(entity);
//				Thread.sleep(10);
//			}
//			while(true){
//				Random random = new Random();
//				int clientIndex = random.nextInt(maxCount);				
//				clients[clientIndex].sendMsg("{\"userId\": \""+clientIndex+"\", \"msg\": \"" + "随机发送信息"+clientIndex + "\"}");
//				Thread.sleep(10);
//			}
		    
    }
}
