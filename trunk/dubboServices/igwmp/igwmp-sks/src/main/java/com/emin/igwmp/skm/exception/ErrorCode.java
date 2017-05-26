package com.emin.igwmp.skm.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.emin.igwmp.skm.facade.callers.vo.FaildVO;

/**
 * Created by Administrator on 2017/4/17.
 */
public class ErrorCode {
    public static final int SUCCESS = 0;
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
    public static final int WXC_SERVER = -14;//地址转换服务调用错误
    public static final int SAVE_REPORT = -15;//上传订单保存余量错误

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
}
