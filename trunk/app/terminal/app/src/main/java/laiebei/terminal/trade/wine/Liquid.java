package laiebei.terminal.trade.wine;

import android.content.Context;

import java.io.IOException;

import laiebei.terminal.exceptions.ExceptionCode;
import laiebei.terminal.exceptions.RunException;
import laiebei.terminal.trade.wine.listener.OnCabinetListener;
import lib.laiebei.com.cabinetlibrary.Cabinet;
import lib.laiebei.com.cabinetlibrary.listener.DeviceInfoListener;
import lib.laiebei.com.cabinetlibrary.listener.DeviceStatusListener;
import lib.laiebei.com.cabinetlibrary.listener.MoudleErrorListener;
import lib.laiebei.com.cabinetlibrary.listener.WineDoorListener;

/**
 * 版权:贵州仁塔科技有限公司
 * 功能描述:
 * 创建者:Created by Administrator-aleyds on 2017/3/17 15:06.
 * 对外接口:
 */

public class Liquid implements DeviceStatusListener,DeviceInfoListener,WineDoorListener,MoudleErrorListener {
	public static Liquid INSTANCE = new Liquid();
	private static Context mContext;
	private OnCabinetListener listener;

	private Liquid(){}

	public  Liquid whith(Context context){
		mContext = context;
		return this;
	}

	public void startLiquid(){
		try {
			Cabinet.load(mContext);
			Cabinet.getCabinetDevice().CabinetInit(this,this,this,this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Cabinet getCabinet()throws RunException{
		Cabinet cabinet = Cabinet.getCabinetDevice();
		if(cabinet == null){
			throw new RunException(ExceptionCode.INFO_ERROR,"");
		}
		return cabinet;
	}


	@Override
	public void DeviceWineNum(byte b) {

	}

	@Override
	public void DeviceTime(String s) {

	}

	@Override
	public void DeviceSense(byte b, byte[] bytes) {

	}

	@Override
	public void DevicePump(byte b, int[] ints) {

	}

	@Override
	public void DeviceVersion(String s) {

	}

	@Override
	public void DeviceSaleSt(byte b, byte[] bytes) {

	}

	@Override
	public void DeviceWarningSt(byte b, String s) {

	}

	@Override
	public void DeviceDoorSt(byte b) {

	}

	@Override
	public void DeviceCupSt(byte b) {

	}

	@Override
	public void DeviceHumanSt(byte b) {

	}

	@Override
	public void DeviceWaterWay(byte b) {

	}

	@Override
	public void onError(int i, String s) {

	}

	@Override
	public void ResultStatus(byte b) {

	}
}
