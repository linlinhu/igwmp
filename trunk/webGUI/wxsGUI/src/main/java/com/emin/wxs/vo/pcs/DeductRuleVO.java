package com.emin.wxs.vo.pcs;

/**
 * Created by Administrator on 2017/3/27.
 */
public class DeductRuleVO {

    private Long Id;
    /**
     *  区域ID  运营规则绑定的作用域
     * */
    private Long areaId;

    /**
     * 提成规则  对运营角色提成规则 百分比
     * */
    private double deductRatio;

    /**
     * 产品Id 标识该规则关联产品  不同产品运用不同规则
     * */
    private Long productId;

    /**
     * 产品名称 从产品表中冗余 方便查看相关
     * */
    private String productName;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }


    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public double getDeductRatio() {
        return deductRatio;
    }

    public void setDeductRatio(double deductRatio) {
        this.deductRatio = deductRatio;
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
