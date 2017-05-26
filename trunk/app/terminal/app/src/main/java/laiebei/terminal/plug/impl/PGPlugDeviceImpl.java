package laiebei.terminal.plug.impl;

import com.alibaba.fastjson.JSON;

import org.json.JSONArray;

import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.DHInterface.StandardFeature;
import io.dcloud.common.util.JSUtil;
import laiebei.terminal.application.LaiebeiApp;
import laiebei.terminal.application.services.CabinetService;
import laiebei.terminal.common.cache.KeyCode;
import laiebei.terminal.common.cache.disk.RomLruCache;
import laiebei.terminal.common.cache.ram.RamLruCache;
import laiebei.terminal.common.utilcode.AppUtils;
import laiebei.terminal.common.utilcode.DeviceUtils;
import laiebei.terminal.common.utilcode.TrafficUtils;
import laiebei.terminal.exceptions.ErrorCode;
import laiebei.terminal.plug.PGPlugDevice;
import laiebei.terminal.skm.accepter.impl.MasterAccepterImpl;
import laiebei.terminal.skm.listener.OnResponseListener;
import laiebei.terminal.skm.msmanage.vo.response.OrderVO;
import laiebei.terminal.skm.msmanage.vo.response.bean.OrderBean;

/**
 * 版权:贵州仁塔科技有限公司
 * 功能描述:
 * 创建者:Created by Administrator-aleyds on 2017/4/4 10:32.
 * 对外接口:
 */

public class PGPlugDeviceImpl extends StandardFeature implements PGPlugDevice {

	@Override
	public void getPackageInfo(IWebview pWebview, JSONArray array) {
		String CallBackID = array.optString(0);
		JSONArray resultJson = new JSONArray();
		resultJson.put(AppUtils.getAppPackageName(pWebview.getContext()));
		resultJson.put(AppUtils.getAppVersionName(pWebview.getContext()));
		resultJson.put(AppUtils.getAppVersionCode(pWebview.getContext()));
		JSUtil.execCallback(pWebview, CallBackID,resultJson , JSUtil.OK, false);
	}

	@Override
	public void getIpcCode(IWebview pWebview, JSONArray array) {
		String CallBackID = array.optString(0);
		JSONArray resultJson = new JSONArray();
		resultJson.put(DeviceUtils.getSerialID());
		JSUtil.execCallback(pWebview, CallBackID,resultJson , JSUtil.OK, false);
	}


	@Override
	public void getTrafficInfo(IWebview pWebview, JSONArray array) {
		String CallBackID = array.optString(0);
		JSONArray resultJson = new JSONArray();
		resultJson.put(TrafficUtils.getTotalRxFlow());
		resultJson.put(TrafficUtils.getTotalTxFlow());
		JSUtil.execCallback(pWebview, CallBackID,resultJson , JSUtil.OK, false);
	}

	@Override
	public void vendout(final IWebview pWebview, JSONArray array) {
		final String CallBackID = array.optString(0);
		String orderId = array.optString(1);
		int channel = array.optInt(2);
		int value = array.optInt(3);
		OrderVO vo = new OrderVO();
		OrderBean bean = new OrderBean();
		vo.setSuccess(true);
		bean.setOrderId(orderId);
		bean.setChannel(channel);
		bean.setValue(value);
		bean.setName("测试");
		bean.setProductId(0l);
		vo.setRows(bean);
		LaiebeiApp.sendBroadCoast(CabinetService.ACTION_CABINET_ORDER, JSON.toJSONString(vo));
		JSONArray resultJson = new JSONArray();
		resultJson.put(JSON.toJSONString(ErrorCode.ResultFail(0)));
		JSUtil.execCallback(pWebview, CallBackID,resultJson , JSUtil.OK, false);
	}

	@Override
	public void getQrcode(final IWebview pWebview, JSONArray array) {
		final String CallBackID = array.optString(0);
		String publicUrl = array.optString(1);
		new MasterAccepterImpl().getQrcode(DeviceUtils.getSerialID(), publicUrl, new OnResponseListener() {
			@Override
			public void OnResult(String row) {
				JSONArray resultJson = new JSONArray();
				resultJson.put(row);
				JSUtil.execCallback(pWebview, CallBackID,resultJson , JSUtil.OK, false);
			}

			@Override
			public void OnFail(String error) {
				JSONArray resultJson = new JSONArray();
				resultJson.put(error);
				JSUtil.execCallback(pWebview, CallBackID,resultJson , JSUtil.OK, false);
			}
		});

	}

	@Override
	public void clearCache(final IWebview pWebview, JSONArray array) {
		String CallBackID = array.optString(0);
		JSONArray resultJson = new JSONArray();
		RamLruCache.INSTANCE.clear();
		RomLruCache.INSTANCE.resetCache();
		resultJson.put(true);
		JSUtil.execCallback(pWebview, CallBackID,resultJson , JSUtil.OK, false);
	}

	@Override
	public void deleteCache(IWebview pWebview, JSONArray array) {
		String CallBackID = array.optString(0);
		int moduleId = array.optInt(1);
		int rowId = array.optInt(2);
		JSONArray resultJson = new JSONArray();
		String deleteKey = "";
		switch (moduleId){
			case 0:
				deleteKey = KeyCode.MACHINE_KEY + rowId;
				break;
			default:
				break;
		}
		RamLruCache.INSTANCE.remove(deleteKey);
		RomLruCache.INSTANCE.removeCache(deleteKey);
		resultJson.put(true);
		JSUtil.execCallback(pWebview, CallBackID,resultJson , JSUtil.OK, false);
	}
}
