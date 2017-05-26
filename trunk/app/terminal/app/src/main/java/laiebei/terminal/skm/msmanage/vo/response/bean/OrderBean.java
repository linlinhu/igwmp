package laiebei.terminal.skm.msmanage.vo.response.bean;

/**
 * Created by Administrator on 2017/4/21.
 */
public class OrderBean {
    /**
     * orderId :
     * channel : 2
     * value : 50
     */

    private String ipcCode;
    private String orderId;
    private int channel;
    private int value;
    private String name;
    private Long productId;

    public String getIpcCode() {
        return ipcCode;
    }

    public void setIpcCode(String ipcCode) {
        this.ipcCode = ipcCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
