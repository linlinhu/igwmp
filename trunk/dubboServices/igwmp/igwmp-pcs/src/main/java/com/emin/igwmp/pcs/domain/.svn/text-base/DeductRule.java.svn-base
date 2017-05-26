package com.emin.igwmp.pcs.domain;

import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/3/20.
 * 运营提成规则
 */

@Table(schema = "pcs", name = "deduct_rule")
@Entity
public class DeductRule extends BaseEntity implements UndeleteableEntity {

    private static final long serialVersionUID = 5618706197323695160L;

    //可查询属性
    public static final String PROP_NAME = "productName";

    public static final String PROP_PRDUCT_ID = "productId";

    public static final String PROP_AREA_ID = "areaId";
    /**
     * 记录状态  0 有效  1无效 可扩展
     * */
    private int status;

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

    /**
     * 创建时间
     * */
    private Long createTime;

    /**
     * 最后一次修改时间
     * */
    private Long lastModifyTime;


    @Id
    @SequenceGenerator(name = "deduct_rule_id_seq", sequenceName = "pcs.deduct_rule_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deduct_rule_id_seq")
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

    @Column(name = "area_id")
    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    @Column(name = "deduct_ratio")
    public double getDeductRatio() {
        return deductRatio;
    }

    public void setDeductRatio(double deductRatio) {
        this.deductRatio = deductRatio;
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
    public Long getLastModifyTime() {
        return lastModifyTime;
    }

    @Override
    @Column(name = "lastmodify_time")
    public void setLastModifyTime(Long aLong) {
        lastModifyTime = aLong;
    }
}
