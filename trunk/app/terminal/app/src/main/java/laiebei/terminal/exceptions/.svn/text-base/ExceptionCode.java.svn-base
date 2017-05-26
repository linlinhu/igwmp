package laiebei.terminal.exceptions;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator on 2017/4/1.
 */

public class ExceptionCode {
    public static final String T_PARAMETER_ERROR = "paramer_err";
    public static final String T_NECESSARY_OBJ_ERR = "Necessary_err";
    public static final String INFO_ERROR = "info_err";

    public static JSONObject getSuccess(int code){
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
