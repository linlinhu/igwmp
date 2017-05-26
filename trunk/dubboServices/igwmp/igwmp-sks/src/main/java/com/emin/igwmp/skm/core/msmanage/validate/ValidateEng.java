package com.emin.igwmp.skm.core.msmanage.validate;

import com.emin.igwmp.skm.core.msmanage.entity.bean.HandlerBean;
import com.emin.igwmp.skm.core.pond.PondAdapter;
import com.emin.igwmp.skm.core.pond.PondEntity;
import com.emin.igwmp.skm.exception.ExceptionCode;
import com.emin.igwmp.skm.exception.RunException;
import com.emin.igwmp.skm.util.EmptyUtils;

/**
 * Created by Administrator on 2017/3/23.
 */
public class ValidateEng {

    public static String generate(PondEntity pond,Long time){
        String[] st = {pond.getSession(),String.valueOf(time)};
        return SignProcessor.createSignStr(st);
    }

    public static  boolean validate(String ipcId,HandlerBean mHandler)throws RunException {
        if(EmptyUtils.isEmpty(ipcId) || EmptyUtils.isEmpty(mHandler)){
            throw new RunException(ExceptionCode.T_PARAMTERS_INVALID,"");
        }

        PondEntity pond =  PondAdapter.INSTANCE.queryPond(ipcId);
        if(pond == null){
            throw new RunException(ExceptionCode.M_DATA_REPEAT,"ValidateEng ");
        }
        if(EmptyUtils.isEmpty(mHandler.getSesscion())){
            throw new RunException(ExceptionCode.T_PARAMTERS_INVALID,"");
        }
        String[] st = {pond.getSession(),String.valueOf(mHandler.getTime())};
        String validate = SignProcessor.createSignStr(st);
        if(validate.equalsIgnoreCase(mHandler.getSesscion())){
            return true;
        }
        return false;
    }
}
