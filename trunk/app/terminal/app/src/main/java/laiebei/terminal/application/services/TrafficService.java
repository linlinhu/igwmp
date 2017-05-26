package laiebei.terminal.application.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import laiebei.terminal.common.utilcode.NetworkUtils;
import laiebei.terminal.skm.netty.Client;
import timber.log.Timber;

/**
 * 版权:贵州仁塔科技有限公司
 * 功能描述:
 * 创建者:Created by Administrator-aleyds on 2017/4/22 17:52.
 * 对外接口:
 */

public class TrafficService extends Service {
	private static final long NETWORK_TIMES = 1*1000;//实时监测网络情况

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		registerReceiver();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}



	/**************************定时检测网络情况********************************/
	private Handler TrafficHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

		}
	};

	private Runnable TrafficRunnable = new Runnable() {
		@Override
		public void run() {
			TrafficHandler.sendEmptyMessage(0);
		}
	};


	private void startTrafficTimer(){
		TrafficHandler.postDelayed(TrafficRunnable,NETWORK_TIMES);
	}

	private void stopTrafficTimer(){
		TrafficHandler.removeCallbacks(TrafficRunnable);
	}

	private void restartTrafficTimer(){
		startTrafficTimer();
		stopTrafficTimer();
	}

	private BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if(action.equalsIgnoreCase(ConnectivityManager.CONNECTIVITY_ACTION)){
				boolean isConnect = NetworkUtils.isConnected(context);
				if(isConnect){
					Timber.i("网络状态变化: 网络链接上"  );
					try {
						Client.INSTANCE.getReconnectHandler().UpdateNet(isConnect);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					Timber.w("络状态变化: 网络断开");

				}
			}

		}
	};

	private void registerReceiver(){
		IntentFilter filter = new IntentFilter();
		filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(receiver,filter);
	}

}
