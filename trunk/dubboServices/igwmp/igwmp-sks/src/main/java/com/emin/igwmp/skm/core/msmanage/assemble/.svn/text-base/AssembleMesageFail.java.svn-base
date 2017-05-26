package com.emin.igwmp.skm.core.msmanage.assemble;

/**
 * Created by Administrator on 2017/4/18.
 */
public class AssembleMesageFail extends AssembleMessageFactory {

    public int errorCode;

    public AssembleMesageFail(int errorCode){
        this.errorCode = errorCode;
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public int resultCode() {
        return this.errorCode;
    }
}
