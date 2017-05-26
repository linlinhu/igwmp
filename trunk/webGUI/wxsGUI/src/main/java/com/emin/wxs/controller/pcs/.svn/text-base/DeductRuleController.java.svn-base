package com.emin.wxs.controller.pcs;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.emin.base.dao.PagedResult;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.igwmp.pcs.domain.ProductPrice;
import com.emin.wxs.facade.pcs.WxsToDeductRuleCaller;
import com.emin.wxs.vo.pcs.DeductRuleVO;
import com.emin.wxs.vo.pcs.ProductPriceVO;
import com.emin.wxs.vo.pcs.ResultFail;
import com.emin.wxs.vo.pcs.ResultSuccess;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.emin.wxs.controller.WxsBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/28.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/rule")
public class DeductRuleController extends WxsBaseController {
    private Logger logger = LoggerFactory.getLogger(DeductRuleController.class);

    @Resource(name = "wxsToDeductRuleCaller")
    private WxsToDeductRuleCaller ruleCaller;

    @RequestMapping(value="/page.do",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public JSONObject loadDeductRule(String keyword){
        List<Condition> conditions = new ArrayList<Condition>();

        if(StringUtils.isNotBlank(keyword)){
            try {
                keyword = new String(keyword.getBytes("ISO-8859-1"),"utf-8");
            } catch (UnsupportedEncodingException e) {
                ResultFail fail = new ResultFail();
                fail.setSuccess(false);
                fail.setMessage(e.getLocalizedMessage());
            }
            logger.info("规则查询关键字：" + keyword );
            conditions.add(new Condition(ProductPrice.PROP_NAME, Condition.ConditionOperator.LIKE, Condition.ConditionType.CHARACTER, "%"+keyword+"%"));
        }

        PagedResult<DeductRuleVO> result = null;
        try {
            logger.info("开始查询");
            if(conditions == null || conditions.isEmpty()){
                result = ruleCaller.loadPagedDeductRuleByCondition(getPageRequestData(), null);
            }else{
                result = ruleCaller.loadPagedDeductRuleByCondition(getPageRequestData(), conditions);
            }
        } catch (EminException e) {
            ResultFail fail = new ResultFail();
            fail.setSuccess(false);
            fail.setMessage(e.getLocalizedMessage());
            return  (JSONObject) JSON.toJSON(fail);
        }catch (Exception e) {
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
    public JSONObject saveDeductRule(@RequestBody DeductRuleVO deductRuleVO){
        ResultFail fail = new ResultFail();
        try {
            ruleCaller.saveOrUpdateDeductRule(deductRuleVO);
            fail.setSuccess(true);
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
    public JSONObject deleteDeductRule(@RequestParam(required=true)Long id){
        ResultFail fail = new ResultFail();
        try {
            ruleCaller.deleteDeductRule(id);
            fail.setSuccess(true);
        } catch (EminException e) {
            fail.setSuccess(false);
            fail.setMessage(e.getLocalizedMessage());
        } catch (Exception e) {
            fail.setSuccess(false);
            fail.setMessage(e.getMessage());
        }

        return (JSONObject) JSON.toJSON(fail);
    }
}
