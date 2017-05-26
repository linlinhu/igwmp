package laiebei.terminal.skm.msmanage.validate;


import laiebei.terminal.common.utilcode.DeviceUtils;
import laiebei.terminal.common.utilcode.EmptyUtils;
import laiebei.terminal.dbm.DaoLite;
import laiebei.terminal.dbm.domain.Session;
import laiebei.terminal.exceptions.ExceptionCode;
import laiebei.terminal.exceptions.RunException;
import laiebei.terminal.skm.msmanage.entity.bean.HandlerBean;

/**
 * Created by Administrator on 2017/3/23.
 */
public class ValidateEng {

    public static boolean validate(String ipcCcde,HandlerBean handler)throws RunException {
        if(EmptyUtils.isEmpty(handler)){
            throw new RunException(ExceptionCode.T_PARAMETER_ERROR,"");
        }
        Session localSession = DaoLite.INSTANCE.querySession(DeviceUtils.getSerialID());
        if(EmptyUtils.isEmpty(localSession)){
            return false;
        }
        String[] st = {localSession.getSession(),String.valueOf(handler.getTime())};
        String validate = SignProcessor.createSignStr(st);
        if(validate.equalsIgnoreCase(handler.getSesscion()) && DeviceUtils.getSerialID().equalsIgnoreCase(ipcCcde)){
            return true;
        }
        return false;
    }

    public static String generate(Long time){
        Session localSession = DaoLite.INSTANCE.querySession(DeviceUtils.getSerialID());
        if(EmptyUtils.isEmpty(localSession)){
            return null;
        }
        String[] st = {localSession.getSession(),String.valueOf(time)};
        return SignProcessor.createSignStr(st);
    }
}
