package laiebei.terminal.dbm.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/4/20.
 */
@Entity
public class VentoutOrder {
    @Id
    private Long id;
    @NotNull
    private String orderId;//订单ID
    private Long productId;//产品ID
    private Long creatTime;
    private int channel;//出酒通道
    private int value;//出酒量
    private int status;//状态
    private int resultCode;//返回码
    private boolean isReport;//是否已经成功上传

    @Generated(hash = 1462711420)
    public VentoutOrder(Long id, @NotNull String orderId, Long productId,
            Long creatTime, int channel, int value, int status, int resultCode,
            boolean isReport) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.creatTime = creatTime;
        this.channel = channel;
        this.value = value;
        this.status = status;
        this.resultCode = resultCode;
        this.isReport = isReport;
    }

    @Generated(hash = 505500338)
    public VentoutOrder() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Long creatTime) {
        this.creatTime = creatTime;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public boolean isReport() {
        return isReport;
    }

    public void setReport(boolean report) {
        isReport = report;
    }

    public boolean getIsReport() {
        return this.isReport;
    }

    public void setIsReport(boolean isReport) {
        this.isReport = isReport;
    }

    public Long getProductId() {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
