package laiebei.terminal.skm.msmanage.vo.request;

/**
 * 版权:贵州仁塔科技有限公司
 * 功能描述:
 * 创建者:Created by Administrator-aleyds on 2017/4/18 22:03.
 * 对外接口:
 */

public class RowID {
	//设备管理
	public static final int MACHINE_RELATION = 0;//设备关联信息
	public static final int MACHINE_WINES = 1;//酒品配置列表
	public static final int MACHINE_CONVERT_URL = 2;//机器关联二维码
	//订单
	public static final int ORDER_PAY = 0;//支付
	public static final int ORDER_REPORT = 1;//交付
	public static final int ORDER_TAKE_CODE = 2;//取酒验证
	public static final int ORDER_PUSH = 3;//订单推送
	public static final int ORDER_REPLACE = 4;//服务员代打酒列表
}
