package laiebei.terminal.common.utilcode;

import android.content.Context;
import android.net.TrafficStats;

/**
 * 版权:贵州仁塔科技有限公司
 * 功能描述:
 * 创建者:Created by Administrator-aleyds on 2017/4/4 13:26.
 * 对外接口:
 */

public class TrafficUtils {
	private TrafficUtils() {
		throw new UnsupportedOperationException("u can't instantiate me...");
	}

	public static String getTotalRxFlow(){
		return  String.valueOf(ConvertUtils.byte2Size(TrafficStats.getTotalRxBytes(),ConstUtils.MemoryUnit.KB));
	}

	public static String getTotalTxFlow(){
		return String.valueOf(ConvertUtils.byte2Size(TrafficStats.getTotalTxBytes(),ConstUtils.MemoryUnit.KB));
	}

	public static long getTotalPackets(){
		return TrafficStats.getTotalRxPackets();
	}

	public static String getMobileFlow(){
		return  String.valueOf(ConvertUtils.byte2Size(TrafficStats.getMobileRxBytes(),ConstUtils.MemoryUnit.KB));
	}

	public static String getMobileTxFlow(){
		return String.valueOf(ConvertUtils.byte2Size(TrafficStats.getMobileTxBytes(),ConstUtils.MemoryUnit.KB));
	}

	public static long getMobilePackets(){
		return TrafficStats.getMobileRxPackets();
	}

	public static String getAppRxFlow(Context context){
		int uuid = AppUtils.getAppUuid(context);
		return String.valueOf(ConvertUtils.byte2Size(TrafficStats.getUidRxBytes(uuid),ConstUtils.MemoryUnit.KB));
	}

	public static String getAppTxFlow(Context context){
		int uuid = AppUtils.getAppUuid(context);
		return String.valueOf(ConvertUtils.byte2Size(TrafficStats.getUidTxBytes(uuid),ConstUtils.MemoryUnit.KB));
	}
}
