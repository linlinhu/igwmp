package com.emin.igwmp.pcs.bean;

/**
 * Created by Administrator on 2017/3/21.
 */
public class ProductPriceBean {
    private int status;
    private double purchPrice;
    private double originalPrice;
    private double sellingPrice;
    private Long scene;
    private Long productId;
    private String productName;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getPurchPrice() {
        return purchPrice;
    }

    public void setPurchPrice(double purchPrice) {
        this.purchPrice = purchPrice;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Long getScene() {
        return scene;
    }

    public void setScene(Long scene) {
        this.scene = scene;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
