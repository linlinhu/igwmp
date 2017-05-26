package laiebei.terminal.plug.vo.bean;

/**
 * Created by Administrator on 2017/4/17.
 */
public class PayBean {
    /**
     * type : 0
     * pay : 12.6
     * payQr :
     */

    private int type;
    private double pay;
    private String payQr;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    public String getPayQr() {
        return payQr;
    }

    public void setPayQr(String payQr) {
        this.payQr = payQr;
    }
}
