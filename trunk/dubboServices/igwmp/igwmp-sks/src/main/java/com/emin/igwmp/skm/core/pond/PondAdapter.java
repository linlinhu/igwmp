package com.emin.igwmp.skm.core.pond;

import com.emin.igwmp.skm.exception.ExceptionCode;
import com.emin.igwmp.skm.exception.RunException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/23.
 */
public class PondAdapter {

    private Map<String,PondEntity> PondHash = new HashMap<String , PondEntity>();

    public static final PondAdapter INSTANCE = new PondAdapter();

    private PondAdapter() { }


    public void addPond(PondEntity entity) throws RunException{
        if (entity == null ||  PondHash == null) {
            throw new RunException(ExceptionCode.T_PARAMTERS_INVALID,"Add Pond");
        }
        if(PondHash.containsKey(entity.getDevice())){
            throw new RunException(ExceptionCode.M_DATA_REPEAT, "Add Pond");
        }
        entity.setCreatTime(new Date().getTime());
        PondHash.put(entity.getDevice(),entity);
    }

    public void removePond(String deviceId)throws RunException{
        if (deviceId == null ||  PondHash == null) {
            throw new RunException(ExceptionCode.T_PARAMTERS_INVALID,"Remove Pond");
        }
        PondHash.remove(deviceId);
    }

    public PondEntity queryPond(String deviceId)throws RunException {
        if (deviceId == null ||  PondHash == null) {
            throw new RunException(ExceptionCode.T_PARAMTERS_INVALID,"Remove Pond");
        }
        PondEntity entity = PondHash.get(deviceId);
        if(entity == null){
            throw new RunException(ExceptionCode.M_DATA_REPEAT, "Query Pond");
        }
        return entity;
    }
}
