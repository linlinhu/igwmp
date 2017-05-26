package com.emin.igwmp.skm.core.msmanage.assemble;

import com.emin.igwmp.skm.core.msmanage.decode.Convert;
import com.emin.igwmp.skm.core.msmanage.entity.ChiefEntity;
import com.emin.igwmp.skm.core.msmanage.entity.bean.*;
import com.emin.igwmp.skm.core.msmanage.validate.ValidateEng;
import com.emin.igwmp.skm.core.netty.stream.nettyIO;
import com.emin.igwmp.skm.core.pond.PondAdapter;
import com.emin.igwmp.skm.core.pond.PondEntity;
import com.emin.igwmp.skm.exception.ExceptionCode;
import com.emin.igwmp.skm.exception.RunException;
import com.emin.igwmp.skm.util.PlatformUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 */
public abstract class AssembleMessageFactory extends AssembleFactory {
    @Override
    public IdentBean AssembleIndent(String ipcId) throws RunException{
        IdentBean ident = new IdentBean();
        ident.setType(1);
        ident.setDevice(ipcId);
        return ident;
    }

    @Override
    public HandlerBean AssembleHandler(String ipcId, int bodyLen)throws RunException {
        PondEntity pond =  PondAdapter.INSTANCE.queryPond(ipcId);
        Long time = new Date().getTime();
        HandlerBean handler = new HandlerBean();
        if(pond == null){
            throw new RunException(ExceptionCode.INFO_ERROR,"");
        }
        handler.setTime(time);
        handler.setType(2);
        handler.setVersion(PlatformUtils.getPlatformVer());
        handler.setSesscion(ValidateEng.generate(pond,time));
        handler.setLenght(bodyLen);
        return handler;
    }

    public abstract int getType();
    public abstract int resultCode();

    @Override
    public BodyBean AssembleBody(RowsBean rows) throws RunException{
        BodyBean body = new BodyBean();
        if(rows != null){
            List<RowsBean> lists = new ArrayList<>();
            lists.add(rows);
            body.setRows(lists);
        }
        body.setCode(resultCode());
        body.setType(getType());
        return body;
    }

    @Override
    public FootBean AssembleFoot() throws RunException{
        FootBean foot = new FootBean();
        foot.setDescribe("");
        return foot;
    }

    @Override
    public void Call(String ipcId,ChiefEntity entity) throws RunException{
        nettyIO.Write(ipcId, Convert.Encode(entity));
    }
}
