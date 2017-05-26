package com.emin.igwmp.skm.facade.callers.vo;

import com.emin.igwmp.skm.facade.callers.vo.bean.wxUrlBean;

/**
 * Created by Administrator on 2017/4/24.
 */
public class wxUrlVO {


    /**
     * success : true
     * rows : {"url":""}
     */

    private boolean success;
    private wxUrlBean rows;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public wxUrlBean getRows() {
        return rows;
    }

    public void setRows(wxUrlBean rows) {
        this.rows = rows;
    }

}
