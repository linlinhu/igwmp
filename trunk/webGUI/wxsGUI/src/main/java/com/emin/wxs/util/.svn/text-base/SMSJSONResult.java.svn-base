package com.emin.wxs.util;

import com.alibaba.fastjson.JSONObject;

/**
 * <p>Title: JSONResult</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2014</p>
 *
 */
public class SMSJSONResult {
	//如果为 0 数据正确   如果数据错误 从 -2开始  -1为预留 签名错误
    private int resultCode;

    private Object resultMsg;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Object getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(Object resultMsg) {
        this.resultMsg = resultMsg;
    }


    public JSONObject toJSON() {
        JSONObject result = new JSONObject();
        result.put("resultCode", resultCode);
        result.put("resultMsg", resultMsg);
        return  result;
    }

    public static SMSJSONResult getInstance(int resultCode, String resultMsg) {
        SMSJSONResult jsonResult = new SMSJSONResult();
        jsonResult.setResultCode(resultCode);
        jsonResult.setResultMsg(resultMsg);
        return jsonResult;
    }
}
