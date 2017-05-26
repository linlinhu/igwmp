package laiebei.terminal.exceptions;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator on 2017/4/17.
 */
public class ErrorCode {

    public static final int SUCCESS = 0;
    /**服务器调用错误类型**/
    public static final int PARAM_ERR = -1;//传入参数错误
    public static final int NOT_EXIST = -2;//数据不存在
    public static final int PARAM_OVERFLOW = -3;//参数上溢
    public static final int PARAM_UNDERFLOW = -4;//参数下溢
    public static final int RULE_BREAK = -5;//不符合规则
    public static final int SERVICE_CALL = -6;//服务调用错误
    public static final int VERIFY_ERR = -7;//校验错误
    public static final int PRODUCT_SERVER = -8;//产品服务调用错误
    public static final int PRICE_SERVER = -9;//价格服务调用错误
    public static final int MACHINE_SERVER = -10;//设备服务调用错误
    public static final int ORDER_SERVER = -11;//订单服务调用错误
    public static final int PAY_SERVER = -12;//支付服务调用错误
    public static final int TAKE_SERVER = -13;//取酒验证服务调用错误

    /**android调用错误类型**/
    public static final int NOT_NETWORK = -100;//没有打开网络，或网络硬件故障
    public static final int CLIENT_BREAK = -101;//socket服务断线

    public static JSONObject ResultFail(int code){
        FaildVO vo = new FaildVO();
        if(code == 0){
            vo.setSuccess(true);
        }else{
            vo.setSuccess(false);
        }
        vo.setCode(code);
        return (JSONObject) JSON.toJSON(vo);
    }

    public static String ResultFailString(int code){
        return JSON.toJSONString(ResultFail(code));
    }
}
