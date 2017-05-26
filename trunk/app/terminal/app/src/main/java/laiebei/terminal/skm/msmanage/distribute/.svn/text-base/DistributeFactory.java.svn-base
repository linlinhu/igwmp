package laiebei.terminal.skm.msmanage.distribute;


import java.util.List;

import laiebei.terminal.exceptions.ExceptionCode;
import laiebei.terminal.exceptions.RunException;
import laiebei.terminal.skm.msmanage.entity.ChiefEntity;
import laiebei.terminal.skm.msmanage.entity.bean.BodyBean;
import laiebei.terminal.skm.msmanage.entity.bean.RowsBean;

/**
 * Created by Administrator on 2017/3/24.
 */
public abstract class DistributeFactory {
    abstract void ModuleCaller(String ipcId,RowsBean row);

    public void Distribute(ChiefEntity entity) throws RunException {
        if(entity == null){
            throw new RunException(ExceptionCode.T_PARAMETER_ERROR,"Distribute Value");
        }
        BodyBean body = entity.getBody();
        if(body != null){
            List<RowsBean> rowList = body.getRows();
            if(rowList == null || rowList.isEmpty()){
                ModuleCaller(entity.getIdent().getDevice(),null);
            }else{
                for (RowsBean row : rowList ) {
                    if(row != null){
                        ModuleCaller(entity.getIdent().getDevice(),row);
                    }
                }
            }
        }else{
            ModuleCaller(entity.getIdent().getDevice(),null);
        }
    }
}
