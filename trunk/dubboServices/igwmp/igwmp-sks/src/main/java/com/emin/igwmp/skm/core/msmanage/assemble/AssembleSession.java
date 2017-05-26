package com.emin.igwmp.skm.core.msmanage.assemble;

import com.emin.igwmp.skm.core.msmanage.entity.ChiefEntity;
import com.emin.igwmp.skm.core.msmanage.entity.bean.*;
import com.emin.igwmp.skm.core.msmanage.validate.SessionGenerator;
import com.emin.igwmp.skm.core.pond.PondAdapter;
import com.emin.igwmp.skm.core.pond.PondEntity;
import com.emin.igwmp.skm.util.PlatformUtils;

import java.util.Date;

/**
 * Created by Administrator on 2017/3/26.
 */
public class AssembleSession extends AssembleFactory {
    @Override
    public IdentBean AssembleIndent(String ipcId) {

        IdentBean ident = new IdentBean();
        ident.setType(1);
        if(ipcId != null && !ipcId.equalsIgnoreCase("")){
            ident.setDevice(ipcId);
        }
        return ident;
    }

    @Override
    public HandlerBean AssembleHandler(String ipcId, int bodyLen) {
        HandlerBean handler = new HandlerBean();
        handler.setTime(new Date().getTime());
        handler.setType(0);
        handler.setVersion(PlatformUtils.getPlatformVer());
        handler.setSesscion(SessionGenerator.getSesssion());
        handler.setLenght(bodyLen);
        return handler;
    }

    @Override
    public BodyBean AssembleBody(RowsBean rows) {
        return null;
    }

    @Override
    public FootBean AssembleFoot() {
        return null;
    }

    @Override
    public void Call(String ipcId, ChiefEntity entity) {

    }
}
