package com.emin.wxs.controller.pcs;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.emin.base.dao.PagedResult;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition; 
import com.emin.igwmp.pcs.domain.ProductPrice;
import com.emin.wxs.controller.WxsBaseController;
import com.emin.wxs.facade.pcs.WxsToProductPriceCaller;
import com.emin.wxs.vo.pcs.ProductPriceVO;
import com.emin.wxs.vo.pcs.ResultFail;
import com.emin.wxs.vo.pcs.ResultSuccess;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/25.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/price")
public class ProductPriceController extends WxsBaseController {

    private Logger logger = LoggerFactory.getLogger(ProductPriceController.class);
    @Resource(name = "wxsToProductPriceCaller")
    private WxsToProductPriceCaller priceCaller;

//    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value="/page.do",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public JSONObject loadProductPrice(String keyword, @RequestParam(required=false) Long runType){
        List<Condition> conditions = new ArrayList<Condition>();
        logger.info("查询产品价格：" + keyword + "  RunType:" + runType);

        if(StringUtils.isNotBlank(keyword)){
//            try {
//                keyword = new String(keyword.getBytes("ISO-8859-1"),"utf-8");
//            } catch (UnsupportedEncodingException e) {
//                ResultFail fail = new ResultFail();
//                fail.setSuccess(false);
//                fail.setMessage(e.getLocalizedMessage());
//                return  (JSONObject) JSON.toJSON(fail);
//            }
            logger.info("查询关键字 keyword：" + keyword);
            conditions.add(new Condition(ProductPrice.PROP_NAME, Condition.ConditionOperator.LIKE, Condition.ConditionType.CHARACTER, "%"+ keyword + "%"));
        }
        if(runType != null && !runType.equals(-1)){
            logger.info("查询关键字 runType：" + runType);
            conditions.add(new Condition("runType", Condition.ConditionOperator.EQ, Condition.ConditionType.CHARACTER, runType));
        }
        
        PagedResult<ProductPriceVO> result = null;
        
        try {
            logger.info("开始查询");
            if(conditions == null || conditions.isEmpty()){
                result = priceCaller.loadPagedProductPriceByCondition(getPageRequestData(), null);
            }else{
                result = priceCaller.loadPagedProductPriceByCondition(getPageRequestData(), conditions);
            }

        } catch (EminException e) {
            ResultFail fail = new ResultFail();
            fail.setSuccess(false);
            fail.setMessage(e.getLocalizedMessage());
            logger.info("查询错误");
            return  (JSONObject) JSON.toJSON(fail);
        }catch (Exception e) {
            e.printStackTrace();
            ResultFail fail = new ResultFail();
            fail.setSuccess(false);
            fail.setMessage(e.getLocalizedMessage());
            return  (JSONObject) JSON.toJSON(fail);
        }
        ResultSuccess success = new ResultSuccess();
        success.setSuccess(true);
        success.setTotal(result.getTotalCount());
        success.setRows(result.getResultList());
        logger.info("查询成功：" + JSON.toJSONString(success));

        return (JSONObject) JSON.toJSON(success);
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value="/saveOrUpdate.do",method={RequestMethod.POST,RequestMethod.PUT})
    @ResponseBody
    public JSONObject saveProductPrice(String jsonStr){
        net.sf.json.JSONObject jsonObject=net.sf.json.JSONObject.fromObject(jsonStr);
        ProductPriceVO productPriceVO = (ProductPriceVO)net.sf.json.JSONObject.toBean(jsonObject, ProductPriceVO.class);

        logger.info("保存价格数据:" + jsonStr);
        ResultFail fail = new ResultFail();
        try {
            priceCaller.saveOrUpdateProductPrice(productPriceVO);
            fail.setSuccess(true);
            fail.setMessage("");
        } catch (EminException e) {
            fail.setSuccess(false);
            fail.setMessage(e.getLocalizedMessage());
        } catch (Exception e) {
            fail.setSuccess(false);
            fail.setMessage(e.getMessage());
        }
        return (JSONObject) JSON.toJSON(fail);
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @RequestMapping(value = "/delete.do",method={RequestMethod.POST,RequestMethod.DELETE})
    @ResponseBody
    public JSONObject deleteProductPrice(@RequestParam(required=true)Long id){
        ResultFail fail = new ResultFail();
        try {
            priceCaller.deleteProductPrice(id);
            fail.setSuccess(true);
            fail.setMessage("");
        } catch (EminException e) {
            fail.setSuccess(false);
            fail.setMessage(e.getLocalizedMessage());
            return (JSONObject) JSON.toJSON(fail);
        } catch (Exception e) {
            fail.setSuccess(false);
            fail.setMessage(e.getMessage());
            return (JSONObject) JSON.toJSON(fail);
        }

        return (JSONObject) JSON.toJSON(fail);
    }

}
