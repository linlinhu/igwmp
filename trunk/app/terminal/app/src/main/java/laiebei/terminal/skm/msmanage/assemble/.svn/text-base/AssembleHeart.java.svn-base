package laiebei.terminal.skm.msmanage.assemble;


import laiebei.terminal.skm.msmanage.entity.ChiefEntity;
import laiebei.terminal.skm.msmanage.entity.bean.BodyBean;
import laiebei.terminal.skm.msmanage.entity.bean.FootBean;
import laiebei.terminal.skm.msmanage.entity.bean.HandlerBean;
import laiebei.terminal.skm.msmanage.entity.bean.IdentBean;
import laiebei.terminal.skm.msmanage.entity.bean.RowsBean;

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
