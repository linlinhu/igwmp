package laiebei.terminal.skm.msmanage.assemble;

import com.alibaba.fastjson.JSON;

import laiebei.terminal.common.utilcode.AppUtils;
import laiebei.terminal.common.utilcode.DeviceUtils;
import laiebei.terminal.exceptions.RunException;
import laiebei.terminal.skm.msmanage.entity.ChiefEntity;
import laiebei.terminal.skm.msmanage.entity.bean.BodyBean;
import laiebei.terminal.skm.msmanage.entity.bean.FootBean;
import laiebei.terminal.skm.msmanage.entity.bean.HandlerBean;
import laiebei.terminal.skm.msmanage.entity.bean.IdentBean;
import laiebei.terminal.skm.msmanage.entity.bean.RowsBean;

/**
 * Created by Administrator on 2017/3/24.
 */
public abstract  class AssembleFactory {
    public abstract IdentBean AssembleIndent(String ipcId);
    public abstract HandlerBean AssembleHandler(String ipcId, int bodyLen);
    public abstract BodyBean AssembleBody(int resultCode,RowsBean rows);
    public abstract FootBean AssembleFoot();
    public abstract void RunCall(ChiefEntity entity);

    public ChiefEntity Assemble(int resultCode,RowsBean rows)throws RunException{
        ChiefEntity entity = new ChiefEntity();
        entity.setIdent(AssembleIndent(DeviceUtils.getSerialID()));
        BodyBean body = AssembleBody(resultCode,rows);
        String bodyJson = "";
        if(body != null){
            bodyJson = JSON.toJSONString(body);
        }
        entity.setHandler(AssembleHandler(DeviceUtils.getSerialID(),bodyJson.length()));
        entity.setBody(body);
        entity.setFoot(AssembleFoot());
        RunCall(entity);
        return entity;
    }
}
