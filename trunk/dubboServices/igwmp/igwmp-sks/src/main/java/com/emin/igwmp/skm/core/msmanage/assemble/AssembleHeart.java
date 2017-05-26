package com.emin.igwmp.skm.core.msmanage.assemble;

import com.emin.igwmp.skm.core.msmanage.entity.ChiefEntity;
import com.emin.igwmp.skm.core.msmanage.entity.bean.*;

/**
 * Created by Administrator on 2017/3/24.
 */
public class AssembleHeart extends AssembleFactory {
    @Override
    public IdentBean AssembleIndent(String ipcId) {
        IdentBean ident = new IdentBean();
        ident.setType(0);
        if(ipcId != null && !ipcId.equalsIgnoreCase("")){
            ident.setDevice(ipcId);
        }
        return ident;
    }

    @Override
    public HandlerBean AssembleHandler(String ipcId, int bodyLen) {
        return null;
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
