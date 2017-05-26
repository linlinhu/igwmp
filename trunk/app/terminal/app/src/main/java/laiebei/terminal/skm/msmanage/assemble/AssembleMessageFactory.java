package laiebei.terminal.skm.msmanage.assemble;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import laiebei.terminal.application.LaiebeiApp;
import laiebei.terminal.common.utilcode.AppUtils;
import laiebei.terminal.common.utilcode.DeviceUtils;
import laiebei.terminal.common.utilcode.EmptyUtils;
import laiebei.terminal.exceptions.RunException;
import laiebei.terminal.skm.msmanage.convert.MessageConvert;
import laiebei.terminal.skm.msmanage.entity.ChiefEntity;
import laiebei.terminal.skm.msmanage.entity.bean.BodyBean;
import laiebei.terminal.skm.msmanage.entity.bean.FootBean;
import laiebei.terminal.skm.msmanage.entity.bean.HandlerBean;
import laiebei.terminal.skm.msmanage.entity.bean.IdentBean;
import laiebei.terminal.skm.msmanage.entity.bean.RowsBean;
import laiebei.terminal.skm.msmanage.validate.ValidateEng;
import laiebei.terminal.skm.netty.io.nettyIO;

/**
 * Created by Administrator on 2017/3/24.
 */
public abstract class AssembleMessageFactory extends AssembleFactory {
    @Override
    public IdentBean AssembleIndent(String ipcId) {
        IdentBean ident = new IdentBean();
        ident.setType(1);
        ident.setDevice(ipcId);
        return ident;
    }

    @Override
    public HandlerBean AssembleHandler(String ipcId, int bodyLen) {
        HandlerBean handler = new HandlerBean();
        Long time = System.currentTimeMillis();
        handler.setTime(time);
        handler.setType(1);
        handler.setVersion(AppUtils.getAppAbsoluteVersion(LaiebeiApp.getInstance()));
        handler.setSesscion(ValidateEng.generate(time));
        handler.setLenght(bodyLen);
        return handler;
    }

//    public abstract BodyBean AssembleBodyService(RowsBean rows);

    public abstract int getType();

    @Override
    public BodyBean AssembleBody(int resultCode,RowsBean rows) {
        BodyBean body = new BodyBean();
        body.setCode(resultCode);
        body.setType(getType());
        List<RowsBean> Listrows = new ArrayList<RowsBean>();
        Listrows.add(rows);
        if(EmptyUtils.isNotEmpty(Listrows)){
            body.setRows(Listrows);
        }
        return body;
    }

    @Override
    public FootBean AssembleFoot() {
        FootBean foot = new FootBean();
        return foot;
    }

    @Override
    public void RunCall(ChiefEntity entity) throws RunException{
        nettyIO.write(MessageConvert.VOToMessage(entity));
    }
}
