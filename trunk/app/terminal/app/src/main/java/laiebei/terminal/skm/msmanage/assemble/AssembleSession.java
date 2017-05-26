package laiebei.terminal.skm.msmanage.assemble;


import java.util.Date;

import laiebei.terminal.application.LaiebeiApp;
import laiebei.terminal.common.utilcode.AppUtils;
import laiebei.terminal.skm.msmanage.entity.ChiefEntity;
import laiebei.terminal.skm.msmanage.entity.bean.BodyBean;
import laiebei.terminal.skm.msmanage.entity.bean.FootBean;
import laiebei.terminal.skm.msmanage.entity.bean.HandlerBean;
import laiebei.terminal.skm.msmanage.entity.bean.IdentBean;
import laiebei.terminal.skm.msmanage.entity.bean.RowsBean;

/**
 * Created by Administrator on 2017/3/26.
 */
public class AssembleSession extends AssembleFactory {
    @Override
    public IdentBean AssembleIndent(String ipcId) {

        IdentBean ident = new IdentBean();
        ident.setType(2);
        if(ipcId != null && !ipcId.equalsIgnoreCase("")){
            ident.setDevice(ipcId);
        }
        return ident;
    }

    @Override
    public HandlerBean AssembleHandler(String ipcId, int bodyLen) {
        HandlerBean handler = new HandlerBean();
        handler.setTime(System.currentTimeMillis());
        handler.setType(0);//获取session数据
        handler.setVersion(AppUtils.getAppAbsoluteVersion(LaiebeiApp.getInstance()));
        handler.setLenght(bodyLen);
        return handler;
    }

    @Override
    public BodyBean AssembleBody(int resultCode,RowsBean rows) {
        return null;
    }

    @Override
    public FootBean AssembleFoot() {
        return null;
    }

    @Override
    public void RunCall(ChiefEntity entity) {

    }
}
