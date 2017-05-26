package com.emin.igwmp.skm.facade.callers.vo;

import com.emin.igwmp.skm.facade.callers.vo.bean.ReplaceBean;

import java.util.List;


/**
 * Created by Administrator on 2017/4/17.
 */

public class ReplacessVO {

    /**
     * success : true
     * "total":6
     * rows : [{"id":11111,"name":"习酒大曲","quantity":5,"table":"5号桌","code":123456}]
     */

    private boolean success;
    private int total;
    private List<ReplaceBean> rows;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ReplaceBean> getRows() {
        return rows;
    }

    public void setRows(List<ReplaceBean> rows) {
        this.rows = rows;
    }

}
