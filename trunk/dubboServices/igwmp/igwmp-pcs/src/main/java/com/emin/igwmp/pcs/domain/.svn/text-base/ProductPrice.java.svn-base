package com.emin.igwmp.pcs.domain;

import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/3/20.
 */
@Table(schema = "pcs", name = "product_price")
@Entity
public class ProductPrice extends BaseEntity implements UndeleteableEntity {

    private static final long serialVersionUID = 4618706197323695160L;

    //可查询属性
    public static final String PROP_NAME = "productName";

    public static final String PROP_PRDUCT_ID = "productId";

    public static final String PROP_AREA_ID = "areaId";


    /**
     * 记录状态  0 有效  1无效 可扩展
     * */
    private int status;

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

    /**
     * 使用场景 由产品表提供   运营  测试 活动
     * */
    private Long runType;

    /**
     * 区域ID  标识该价格的作用区域  不同区域做区别化运营,关联区域表
     * */
    private Long areaId;

    /**
     * 产品ID 标识该价格关联产品
     * */
    private Long productId;

    /**
     * 产品名称  从产品表中冗余 方便查看相关
     * */
    private String productName;

    /**
     * 创建时间
     * */
    private Long createTime;

    /**
     * 最后一次修改时间
     * */
    private Long lastModifyTime;

    @Id
    @SequenceGenerator(name = "product_price_id_seq", sequenceName = "pcs.product_price_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_price_id_seq")
    public Long getId() {
        return super.getId();
    }


    @Column(name = "status")
    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public void setStatus(int status) {
        this.status = status;
    }

    @Column(name = "purch_price")
    public double getPurchPrice() {
        return purchPrice;
    }

    public void setPurchPrice(double purchPrice) {
        this.purchPrice = purchPrice;
    }

    @Column(name = "orginal_price")
    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    @Column(name = "selling_price")
    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    @Column(name = "runType")
    public Long getRunType() {
        return runType;
    }

    public void setRunType(Long runType) {
        this.runType = runType;
    }

    @Column(name = "area_id")
    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    @Column(name = "product_id")
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Column(name = "product_name")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    @Override
    @Column(name = "create_time")
    public Long getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Long aLong) {
        createTime = aLong;
    }

    @Override
    @Column(name = "lastmodify_time")
    public Long getLastModifyTime() {
        return lastModifyTime;
    }

    @Override
    public void setLastModifyTime(Long aLong) {
        lastModifyTime = aLong;
    }


}
