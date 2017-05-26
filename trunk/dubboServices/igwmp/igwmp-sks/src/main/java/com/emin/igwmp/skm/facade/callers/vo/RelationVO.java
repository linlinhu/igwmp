package com.emin.igwmp.skm.facade.callers.vo;

import com.emin.igwmp.skm.facade.callers.vo.bean.RelationBean;

/**
 * Created by Administrator on 2017/4/17.
 */
public class RelationVO {


    /**
     * success : true
     * rows : {"name":"","creatTime":12334455,"updateTime":12334455}
     */

    private boolean success;
    private RelationBean rows;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public RelationBean getRows() {
        return rows;
    }

    public void setRows(RelationBean rows) {
        this.rows = rows;
    }

}
