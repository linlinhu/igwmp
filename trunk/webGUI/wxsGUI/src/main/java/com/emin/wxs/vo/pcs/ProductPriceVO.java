package com.emin.wxs.vo.pcs;

/**
 * Created by Administrator on 2017/3/27.
 */
public class ProductPriceVO {

    private Long Id;
    /**
     * 产品ID 标识该价格关联产品
     * */
    private Long productId;

    /**
     * 产品名称  从产品表中冗余 方便查看相关
     * */
    private String productName;
    /**
     * 使用场景 由产品表提供   运营  测试 活动
     * */
    private Long runType;
    /**
     * 区域ID  标识该价格的作用区域  不同区域做区别化运营,关联区域表
     * */
    private Long areaId;
    /**
     * 酒品进价  采购价
     * */
    private double purchPrice;

    /**
     * 酒品原价  酒品售卖原价  对标酒为市场售卖价非对标酒为自定义售价
     * 为0时则无原价
     * */
    private double originalPrice;

    /**
     * 实际价格  来e杯实际运营售价，由实际酒品品质决定
     * */
    private double sellingPrice;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public Long getRunType() {
        return runType;
    }

    public void setRunType(Long runType) {
        this.runType = runType;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
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


}
