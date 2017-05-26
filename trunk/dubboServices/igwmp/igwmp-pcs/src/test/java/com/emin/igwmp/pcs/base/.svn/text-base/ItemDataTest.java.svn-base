package com.emin.igwmp.pcs.base;

import com.emin.igwmp.pcs.domain.DeductRule;
import com.emin.igwmp.pcs.domain.ProductPrice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by Administrator on 2017/3/21.
 */
public class ItemDataTest {

    public static void DisplayEntity(Class<?> jclass ,ProductPrice price){
         Logger logger = LoggerFactory.getLogger(jclass);
        if(price != null){
            logger.info("***************显示产品价格数据库************************" );
            logger.info("状态:" + price.getStatus());
            logger.info("产品ID:" + price.getProductId());
            logger.info("产品名:" + price.getProductName());
            logger.info("创建时间:" + price.getCreateTime());
            logger.info("最后一次修改时间:" + price.getLastModifyTime());
            logger.info("区域ID:" + price.getAreaId());
            logger.info("价格作用类型:" + price.getRunType());
            logger.info("产品进价:" + price.getPurchPrice());
            logger.info("产品原价:" + price.getOriginalPrice() );
            logger.info("产品售卖价格:" + price.getSellingPrice());
            logger.info("*************************************************************" );

        }
    }

    //获取价格测试项
    public static ProductPrice getProductPrice(){
        ProductPrice price = new ProductPrice();
        price.setCreateTime(new Date().getTime());//测试项设置创建时间
        price.setLastModifyTime(new Date().getTime());//测试项设置最后一次更新时间
        price.setPurchPrice(12.7);//测试项设置进价
        price.setOriginalPrice(30.12);//测试项设置原价
        price.setSellingPrice(15.6);//测试项设置售卖价格
        price.setAreaId(67L);//测试项设置区域ID
        price.setProductId(345677L);//测试项设置产品ID
        price.setProductName("习酒e品金樽");//测试项设置产品名称
        price.setRunType(0L);//测试项设置使用场景为运营
        price.setStatus(0);//测试项设置记录状态为有效
        return price;
    }

    public static void DisplayEntity(Class<?> jclass ,DeductRule rule){
        Logger logger = LoggerFactory.getLogger(jclass);
        if(rule != null){
            logger.info("***************显示运营规则数据库************************" );
            logger.info("状态:" + rule.getStatus());
            logger.info("产品ID:" + rule.getProductId());
            logger.info("产品名:" + rule.getProductName());
            logger.info("创建时间:" + rule.getCreateTime());
            logger.info("最后一次修改时间:" + rule.getLastModifyTime());
            logger.info("区域ID:" + rule.getAreaId());
            logger.info("规则分成比例:" + rule.getDeductRatio());
            logger.info("*************************************************************" );

        }
    }

    //获取规则测试项
    public static DeductRule getDeductRule(){
        DeductRule rule = new DeductRule();
        rule.setCreateTime(new Date().getTime());
        rule.setLastModifyTime(new Date().getTime());
        rule.setProductId(345677L);
        rule.setProductName("习酒e品金樽");
        rule.setStatus(0);
        rule.setAreaId(67L);
        rule.setDeductRatio(0.01);
        return rule;
    }
}
