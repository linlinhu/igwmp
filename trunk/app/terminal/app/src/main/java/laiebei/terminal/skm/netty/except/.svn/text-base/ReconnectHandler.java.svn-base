package laiebei.terminal.skm.netty.except;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLException;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoop;
import laiebei.terminal.application.LaiebeiApp;
import laiebei.terminal.common.cache.KeyCode;
import laiebei.terminal.common.cache.ram.RamLruCache;
import laiebei.terminal.common.utilcode.NetworkUtils;
import laiebei.terminal.skm.netty.Client;
import timber.log.Timber;

/**
 * 版权:贵州仁塔科技有限公司
 * 功能描述:
 * 创建者:Created by Administrator-aleyds on 2017/4/24 16:41.
 * 对外接口:
 */

public class ReconnectHandler extends Thread{
	private final static int REQUEST_RECONNECT = 0;

	private Handler ReconnectHandler;

	//接收重连请求
	public void ReceiveReconnectRequest(ChannelHandlerContext ctx){
		//网络链接状态，立即响应请求
		Timber.w("接收到重连请求");
		if(NetworkUtils.isConnected(LaiebeiApp.getAppContext())){
			sendReconnectMessage(REQUEST_RECONNECT,ctx);
		}else{
			//网络不通则先存入缓存
			Timber.w("当前网络不通，重连请求先进入缓存");
			RamLruCache.INSTANCE.saveCache(KeyCode.CLIENT_RECONNECT,ctx);
		}
	}

	public void UpdateNet(boolean isConnect){
		if(isConnect){
			ChannelHandlerContext ctx = (ChannelHandlerContext) RamLruCache.INSTANCE.getCache(KeyCode.CLIENT_RECONNECT);
			if(ctx != null){
				RamLruCache.INSTANCE.remove(KeyCode.CLIENT_RECONNECT);
				sendReconnectMessage(REQUEST_RECONNECT,ctx);
			}
		}
	}

	private void sendReconnectMessage(int type, Object obj){
		Message msg = ReconnectHandler.obtainMessage();
		if(msg == null){
			msg = new Message();
		}
		msg.what = type;
		msg.obj = obj;
		ReconnectHandler.sendMessage(msg);
	}

	@Override
	public void run() {
		super.run();
		Looper.prepare();
		ReconnectHandler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				switch (msg.what){
					case REQUEST_RECONNECT:
						startReconnectAction((ChannelHandlerContext) msg.obj);
						break;
					default:
						break;
				}
			}
		};
		Looper.loop();
	}

	private void startReconnectAction(ChannelHandlerContext ctx){
		final EventLoop eventLoop = ctx.channel().eventLoop();
		Timber.d("开始执行网络重连任务");
		eventLoop.schedule(new Runnable() {
			@Override
			public void run() {
				try {
					Client.INSTANCE.creatClient();
				} catch (SSLException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, 1L, TimeUnit.SECONDS);
	}
}
