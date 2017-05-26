package com.emin.igwmp.skm.facade.callers.vo;

/**
 * 版权:贵州仁塔科技有限公司
 * 功能描述:
 * 创建者:Created by Administrator-aleyds on 2017/4/18 22:03.
 * 对外接口:
 */

public class RowID {
	//设备管理
	public static final int MACHINE_RELATION = 0;
	public static final int MACHINE_WINES = 1;
	public static final int MACHINE_CONVERT_URL = 2;
	//订单
	public static final int ORDER_PAY = 0;//支付
	public static final int ORDER_REPORT = 1;//上报出酒结果
	public static final int ORDER_TAKE_CODE = 2;//取酒验证
	public static final int ORDER_PUSH = 3;//订单推送
	public static final int ORDER_REPLACE = 4;//服务员代打酒列表
}
