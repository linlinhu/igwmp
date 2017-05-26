package com.emin.igwmp.skm.core.msmanage.assemble;

import com.alibaba.fastjson.JSON;
import com.emin.igwmp.skm.core.msmanage.entity.ChiefEntity;
import com.emin.igwmp.skm.core.msmanage.entity.bean.*;
import com.emin.igwmp.skm.exception.RunException;

/**
 * Created by Administrator on 2017/3/24.
 */
public abstract  class AssembleFactory {
    public abstract IdentBean AssembleIndent(String ipcId)throws RunException;
    public abstract HandlerBean AssembleHandler(String ipcId, int bodyLen)throws RunException;
    public abstract BodyBean AssembleBody(RowsBean rows)throws RunException;
    public abstract FootBean AssembleFoot()throws RunException;

    public abstract void Call(String ipcId,ChiefEntity entity) throws RunException;

    public ChiefEntity  Assemble(String ipcId, RowsBean rows)throws RunException{
        ChiefEntity entity = new ChiefEntity();
        entity.setIdent(AssembleIndent(ipcId));
        BodyBean body = AssembleBody(rows);
        String bodyJson = "";
        if(body != null){
            bodyJson = JSON.toJSONString(body);
        }
        entity.setHandler(AssembleHandler(ipcId,bodyJson.length()));
        entity.setBody(body);
        entity.setFoot(AssembleFoot());
//        if(writeSud){
//            nettyIO.Write(ipcId, Convert.Encode(entity));
//        }
        Call(ipcId,entity);
        return entity;
    }
}
