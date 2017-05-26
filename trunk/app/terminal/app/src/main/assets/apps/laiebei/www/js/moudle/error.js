
    var SUCCESS = 0;
    var PARAM_ERR = -1;//传入参数错误
    var NOT_EXIST = -2;//数据不存在
    var PARAM_OVERFLOW = -3;//参数上溢
    var PARAM_UNDERFLOW = -4;//参数下溢
    var RULE_BREAK = -5;//不符合规则
    var SERVICE_CALL = -6;//服务调用错误
    var VERIFY_ERR = -7;//校验错误
    var TIMEOUT = -8;//超时错误
    
    var NOT_NETWORK = -100;//没有打开网络，或网络硬件故障
    var CLIENT_BREAK = -101;//socket服务断线
    var CLIENT_TIMEOUT = -102;//超时
    var CLIENT_COMMUNION = -103;//通讯错误
    
     /**酒柜返回错误码**/
    var CABINET_UNKNUW = -110;//未知错误
    var CABINET_CONFIG_ERR = -111;//机器配置错误
    var CABINET_MOUDLE_ERR = -112;//出酒模式错误
    var CABINET_WAY_ERR = -113;//通道越界
    var CABINET_VALUE_ERR = -114;//出酒量越界
    var CABINET_MACHINE_ERR = -115;//机器故障
    var CABINET_SOLD_OUT = -116;//机器售空
    var CABINET_CUP_OUT = -117;//杯子提前拿出
    var CABINET_TIMEOUT = -118;//超时没有放杯
    var CABINET_REPEAT = -119;//订单重复
    var CABINET_OVERFLOW = -120;//酒量越界
    var CABINET_NOT_DEVICE = -121;//没有出酒设备


    //服务错误调用类型
    var PRODUCT_SERVER = -1000;//产品服务调用错误
    var PRICE_SERVER = -1001;//价格服务调用错误
    var MACHINE_SERVER = -1002;//设备服务调用错误
    var ORDER_SERVER = -1003;//订单服务调用错误
    var PAY_SERVER = -1004;//支付服务调用错误
    var TAKE_SERVER = -1005;//取酒验证服务调用错误
    var WXC_SERVER = -1006;//地址转换服务调用错误
    var SAVE_REPORT = -1007;//上传订单保存余量错误

    //数据不存在的类型
    var MACHINE_NOT_CHANNEL = -1100;//机器没有任何配置酒品
    var TAKE_CODE_ERROR = -1101;//取酒码错误
    var MACHINE_NO_CONFIG = -1102;//机器没有配置对应酒品
    var CHANNEL_WINE_NO_ENOUGH = -1103;//酒量不足
    var WAITER_NOT_EXIST = -1104;//服务员没有注册
    var WAITER_NOT_LIST = -1105;//服务员没有取酒码