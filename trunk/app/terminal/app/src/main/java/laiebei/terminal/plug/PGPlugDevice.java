package laiebei.terminal.plug;

import org.json.JSONArray;

import io.dcloud.common.DHInterface.IWebview;

/**
 * 版权:贵州仁塔科技有限公司
 * 功能描述:
 * 创建者:Created by Administrator-aleyds on 2017/4/3 22:14.
 * 对外接口:
 */

public interface PGPlugDevice {
	public void getPackageInfo(IWebview pWebview, JSONArray array);
	public void getIpcCode(IWebview pWebview, JSONArray array);
	public void getTrafficInfo(IWebview pWebview, JSONArray array);
	public void vendout(IWebview pWebview, JSONArray array);
	public void getQrcode(IWebview pWebview, JSONArray array);
	public void clearCache(IWebview pWebview, JSONArray array);
	public void deleteCache(IWebview pWebview, JSONArray array);

}
