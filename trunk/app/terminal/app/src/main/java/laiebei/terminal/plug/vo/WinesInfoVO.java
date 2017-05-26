package laiebei.terminal.plug.vo;

import java.util.List;

import laiebei.terminal.plug.vo.bean.WinesBean;

/**
 * Created by Administrator on 2017/4/17.
 */

public class WinesInfoVO {

    /**
     * success : true
     * "total":6
     * rows : [{"id":123456,"enable":true,"name":"习酒特曲","flavor":"酱香型","degress":54,"originalPrice":24.5,"sellingPrice":12.6,"remainder":500,"describe":"远离世俗，远离轻薄，远离娇柔的狐媚","product":"贵州省习水县","res":[{"type":0,"url":"图片地址"}]}]
     */

    private boolean success;
    private int total;
    private List<WinesBean> rows;

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

    public List<WinesBean> getRows() {
        return rows;
    }

    public void setRows(List<WinesBean> rows) {
        this.rows = rows;
    }

}
