package laiebei.terminal.plug;

import org.json.JSONArray;

import io.dcloud.common.DHInterface.IWebview;

/**
 * 版权:贵州仁塔科技有限公司
 * 功能描述:
 * 创建者:Created by Administrator-aleyds on 2017/4/12 22:32.
 * 对外接口:
 */

public interface PGPlugMaster {
	/**
	 * 获得机器关联信息
	 * @param pWebview
	 * @param array  callbackID
	 * */
	public void Relation(IWebview pWebview, JSONArray array);

	/**
	 * 获得机器配置酒品信息
	 * @param pWebview
	 * @param array
	 * */
	public void Wines(IWebview pWebview, JSONArray array);


	/**
	 * 品酒
	 * */
	public void Tasting(IWebview pWebview, JSONArray array);

	/**
	 * 买酒
	 * */
	public void Buy(IWebview pWebview, JSONArray array);

	/**
	 * 取酒
	 * */
	public void Take(IWebview pWebview, JSONArray array);

	/**
	 * 代打酒列表
	 * */
	public void Replaces(IWebview pWebview, JSONArray array);


}
