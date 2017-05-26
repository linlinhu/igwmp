package laiebei.terminal.application;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;

import com.laiebei.terminal.BuildConfig;

import java.io.File;

import io.dcloud.application.DCloudApplication;
import laiebei.terminal.application.services.CabinetService;
import laiebei.terminal.application.services.TrafficService;
import laiebei.terminal.common.cache.disk.RomLruCache;
import laiebei.terminal.common.utilcode.AppUtils;
import laiebei.terminal.common.utilcode.DeviceUtils;
import laiebei.terminal.common.utilcode.EmptyUtils;
import laiebei.terminal.dbm.DaoLite;
import laiebei.terminal.mainten.logs.LogcatEngine;
import laiebei.terminal.skm.netty.Client;
import laiebei.terminal.trade.wine.Liquid;
import timber.log.Timber;

/**
 * 版权:贵州仁塔科技有限公司
 * 功能描述:
 * 创建者:Created by Administrator-aleyds on 2017/3/17 14:29.
 * 对外接口:
 */

public class LaiebeiApp extends DCloudApplication {

	private static Context appContext;

	public static Context getAppContext(){
		return appContext;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		if (BuildConfig.DEBUG) {
			Timber.plant(new Timber.DebugTree());
		}
		this.appContext = this;
		//开启日志记录引擎
		LogcatEngine.startLogcat(this);
		LogcatEngine.INSTANCE.addImportTag(this.getClass().getSimpleName());

		ApkStartLog();

		//缓存配置
		RomLruCache.init(this);

		//Socket 客户端连接
		Client.INSTANCE.startClient();
		//酒柜端底层通信
		Liquid.INSTANCE.whith(this).startLiquid();
		//数据库初始化
		DaoLite.INSTANCE.InitDao(this);
		startServices();

	}


	@Override
	public void onTerminate() {
		super.onTerminate();
	}

	private void ApkStartLog(){
		Timber.i("来e杯客户端程序启动");
		Timber.i("#############################################");
		Timber.i("监控版本:");
		Timber.i("终端版本号:" + AppUtils.getAppVersionName(this) + "." + AppUtils.getAppVersionCode(this));
		Timber.i("设备序列号:" + DeviceUtils.getSerialID());
		Timber.i("android系统ID:" + DeviceUtils.getAndroidID(this));
		Timber.i("文件路径:" + Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + Config.APP_DIR);
	}

	private void startServices(){
		//酒柜service
		Intent intentCabinet = new Intent(this, CabinetService.class);
		startService(intentCabinet);

		//网络监听
		Intent intentTraffic = new Intent(this, TrafficService.class);
		startService(intentTraffic);
	}

	public static void sendBroadCoast(String action, String param){
		Intent intent = new Intent();
		intent.setAction(action);
		if(EmptyUtils.isNotEmpty(param)){
			intent.putExtra(CabinetService.PARAM_KEY,param);
		}
		getAppContext().sendBroadcast(intent);
	}
}
