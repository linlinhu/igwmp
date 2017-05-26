package laiebei.terminal.skm.netty;


import javax.net.ssl.SSLException;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.nio.NioSocketChannel;
import laiebei.terminal.exceptions.ExceptionCode;
import laiebei.terminal.exceptions.RunException;
import laiebei.terminal.skm.listener.OnResponseListener;
import laiebei.terminal.skm.netty.except.ReconnectHandler;
import laiebei.terminal.skm.netty.handler.ClientBootstrap;
import laiebei.terminal.skm.netty.handler.ClientChannelInitializer;
import laiebei.terminal.skm.netty.handler.ConnectionListener;
import laiebei.terminal.skm.netty.handler.GroupHandler;
import laiebei.terminal.skm.netty.repeat.RepeatHandler;
import timber.log.Timber;

/**
 * 版权:贵州仁塔科技有限公司
 * 功能描述:
 * 创建者:Created by Administrator-aleyds on 2017/3/17 13:51.
 * 对外接口:
 */

public class Client {

	public static final Client INSTANCE = new Client();
	private ChannelFuture mChannelFuture;
	private ClientBootstrap mClientBootstrap;
	private RepeatHandler repeatHandler = new RepeatHandler();
	private OnResponseListener messageListener;
	private ReconnectHandler reconnectHandler;

	private Client(){}

	public void startClient(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					creatClient();
				} catch (SSLException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		//开启重连任务
		reconnectHandler = new ReconnectHandler();
		reconnectHandler.start();
	}

	public void stopClient() throws InterruptedException {
		if(mChannelFuture != null){
			mChannelFuture.channel().closeFuture().sync();
		}
	}

	public void creatClient()throws SSLException, InterruptedException{
		GroupHandler group = new GroupHandler();
		mClientBootstrap= new ClientBootstrap();
		mClientBootstrap.group(group)
				.channel(NioSocketChannel.class)
				.option(ChannelOption.TCP_NODELAY, true)
				.handler(new ClientChannelInitializer());
		ConnectClient();
	}

	public Channel getClientChennel()throws RunException{
		if(mChannelFuture== null || mChannelFuture.channel() == null){
			throw new RunException(ExceptionCode.T_PARAMETER_ERROR,"Client Get Channel");
		}
		return mChannelFuture.channel();
	}

	private void ConnectClient() throws RunException {
		try {
			mChannelFuture = mClientBootstrap.connect(NeConfig.HOST, NeConfig.PORT).
					addListener(new ConnectionListener()).sync();
		} catch (Exception e) {//无法启动客户端不进行kill APK
			Timber.e("启动服务器连接失败:" + NeConfig.HOST + ":" + NeConfig.PORT);
			e.printStackTrace();
		}
	}

	public RepeatHandler getRepeatHandler(){
		return repeatHandler;
	}

	public void setOnResponseListener(OnResponseListener listener){
		this.messageListener = listener;
	}

	public OnResponseListener getOnResponseListener() throws RunException{
		if(messageListener == null){
			throw new RunException(ExceptionCode.T_PARAMETER_ERROR,"");
		}
		return messageListener;
	}

	public ReconnectHandler getReconnectHandler()throws  Exception{
		if( reconnectHandler == null){
			throw new Exception();
		}
		return reconnectHandler;
	}

}
