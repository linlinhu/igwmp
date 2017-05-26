package com.emin.wxs.vo.pcs;

import com.alibaba.fastjson.JSON;
import com.emin.base.exception.EminException;
import com.emin.igwmp.pcs.domain.DeductRule;
import com.emin.igwmp.pcs.domain.ProductPrice;
import com.emin.igwmp.prds.exception.ExceptionCode;

/**
 * Created by Administrator on 2017/3/27.
 */
public class Convert {

    public static Object VOConvertPOMutual(Object convert, Class<?> convertAftClassz) throws EminException{
        if(convert == null){
            throw new EminException(ExceptionCode.C_PARAMTERS_INVALID,"VOConvertPOMutual param");
        }
        String ConvertPre = JSON.toJSONString(convert);
        return JSON.parseObject(ConvertPre,convertAftClassz);
    }

    public static ProductPriceVO ProductPriceToVO(ProductPrice po)throws EminException{
        if(po == null){
            throw new EminException(ExceptionCode.C_PARAMTERS_INVALID,"VOConvertPOMutual param");
        }
        return (ProductPriceVO) VOConvertPOMutual(po,ProductPriceVO.class);
    }

    public static ProductPrice ConvertProductPrice(ProductPriceVO vo)throws EminException{
        if(vo == null){
            throw new EminException(ExceptionCode.C_PARAMTERS_INVALID,"VOConvertPOMutual param");
        }
        ProductPrice  price = (ProductPrice) VOConvertPOMutual(vo,ProductPrice.class);
        return price;
    }


    public static DeductRuleVO DeductRuleToVO(DeductRule po)throws EminException{
        if(po == null){
            throw new EminException(ExceptionCode.C_PARAMTERS_INVALID," param");
        }
        return (DeductRuleVO) VOConvertPOMutual(po,DeductRuleVO.class);
    }

    public static DeductRule ConvertDeductRule(DeductRuleVO vo)throws EminException{
        if(vo == null){
            throw new EminException(ExceptionCode.C_PARAMTERS_INVALID," param");
        }
        return (DeductRule) VOConvertPOMutual(vo,DeductRule.class);
    }
}
