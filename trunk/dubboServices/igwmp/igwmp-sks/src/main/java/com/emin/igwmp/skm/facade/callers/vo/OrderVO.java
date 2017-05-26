package com.emin.igwmp.skm.facade.callers.vo;

import com.emin.igwmp.skm.facade.callers.vo.bean.OrderBean;

/**
 * Created by Administrator on 2017/4/21.
 */
public class OrderVO {

    /**
     * success : true
     * rows : {"orderId":"","channel":2,"value":50}
     */

    private boolean success;
    private OrderBean rows;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public OrderBean getRows() {
        return rows;
    }

    public void setRows(OrderBean rows) {
        this.rows = rows;
    }

}
