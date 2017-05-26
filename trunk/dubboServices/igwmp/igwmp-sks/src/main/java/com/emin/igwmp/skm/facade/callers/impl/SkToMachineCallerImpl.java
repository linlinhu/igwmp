package com.emin.igwmp.skm.facade.callers.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.emin.base.dao.PagedResult;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.igwmp.ms.domain.MachineChannel;
import com.emin.igwmp.ms.domain.MachineInfo;
import com.emin.igwmp.ms.facade.accepters.MachineInfoAccepter;
import com.emin.igwmp.pcs.domain.ProductPrice;
import com.emin.igwmp.pcs.facade.accepter.ProductPriceAccepter;
import com.emin.igwmp.prds.domain.Images;
import com.emin.igwmp.prds.domain.Product;
import com.emin.igwmp.prds.facade.accepters.ImageAccepter;
import com.emin.igwmp.prds.facade.accepters.ProductAccepter;
import com.emin.igwmp.prds.facade.accepters.WineryAccepter;
import com.emin.igwmp.rp.facade.accepters.ProductReportAccepter;
import com.emin.igwmp.skm.exception.ErrorCode;
import com.emin.igwmp.skm.facade.callers.SkToMachineCaller;
import com.emin.igwmp.skm.facade.callers.vo.FaildVO;
import com.emin.igwmp.skm.facade.callers.vo.RelationVO;
import com.emin.igwmp.skm.facade.callers.vo.WinesVO;
import com.emin.igwmp.skm.facade.callers.vo.bean.RelationBean;
import com.emin.igwmp.skm.facade.callers.vo.bean.WinesBean;
//import com.emin.igwmp.skm.util.EmptyUtils;
//import com.emin.igwmp.skm.util.LegalUtils;
import com.emin.igwmp.skm.facade.callers.vo.bean.wxUrlBean;
import com.emin.igwmp.skm.facade.callers.vo.wxUrlVO;
import com.emin.igwmp.skm.util.EmptyUtils;
import com.emin.igwmp.skm.util.LegalUtils;
import com.emin.wxs.service.WeixinToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
 */
@Service("skToMachineCaller")
public class SkToMachineCallerImpl implements SkToMachineCaller{

    @Reference(version="0.0.1")
    private MachineInfoAccepter machineInfoAccepter;

    @Reference(version="0.0.1")
    private ProductAccepter productAccepter;//产品信息

    @Reference(version="0.0.1")
    private ProductReportAccepter reportAccepter;//产品信息

    @Reference(version="0.0.1")
    private ProductPriceAccepter productPriceAccepter;//产品价格信息

    @Reference(version="0.0.1")
    private WineryAccepter wineryAccepter;//酒厂信息

    @Reference(version = "0.0.1")
    private ImageAccepter imageAccepter;//图片信息

    @Reference(version="0.0.1")
    WeixinToolService weixinToolService;


    @Override
    public JSONObject queryRelation(String ipcCode) {
        List<Condition> conditions = new ArrayList<Condition>();
        List<MachineInfo> result = null;
       if(!StringUtils.isBlank(ipcCode)){
           conditions.add(new Condition("ipcCode", Condition.ConditionOperator.EQ, Condition.ConditionType.CHARACTER,ipcCode));
           try {
               result = machineInfoAccepter.queryMachineInfoForList(conditions);
           } catch (Exception e) {
               e.printStackTrace();
               return ErrorCode.ResultFail(ErrorCode.SERVICE_CALL);
           }
           if (result == null || result.isEmpty()) {
               return ErrorCode.ResultFail(ErrorCode.NOT_EXIST);
           }else if(result.size() > 1){
               return ErrorCode.ResultFail(ErrorCode.RULE_BREAK);
           }else{
               RelationVO vo = new RelationVO();
               RelationBean bean = new RelationBean();
               bean.setName(result.get(0).getRestaurantName());
               bean.setCreatTime(result.get(0).getCreateTime());
               bean.setUpdateTime(result.get(0).getLastModifyTime());
               vo.setSuccess(true);
               vo.setRows(bean);
               return (JSONObject) JSON.toJSON(vo);
           }
       }else{
           return ErrorCode.ResultFail(ErrorCode.PARAM_ERR);
       }
    }

    @Override
    public JSONObject queryWines(String ipcCode) {
        List<Condition> conditions = new ArrayList<Condition>();
        List<MachineInfo> result = null;
        WinesVO vo = new WinesVO();
        List<WinesBean> wines = new ArrayList<>();
        if(StringUtils.isBlank(ipcCode)){
            return ErrorCode.ResultFail(ErrorCode.PARAM_ERR);
        }
        conditions.add(new Condition("ipcCode", Condition.ConditionOperator.EQ, Condition.ConditionType.CHARACTER,ipcCode));
        try {
            result = machineInfoAccepter.queryMachineInfoForList(conditions);
        } catch (Exception e) {
            e.printStackTrace();
            return ErrorCode.ResultFail(ErrorCode.MACHINE_SERVER);
        }
        if (EmptyUtils.isEmpty(result) || EmptyUtils.isEmpty(result.get(0).getChannelList())) {
            return ErrorCode.ResultFail(ErrorCode.NOT_EXIST);
        }
        for (MachineChannel channel: result.get(0).getChannelList()) {
            try {
                Product product = productAccepter.getProductById(channel.getLiquorInfoId());
                if(EmptyUtils.isEmpty(product)){
                    return ErrorCode.ResultFail(ErrorCode.NOT_EXIST);
                }
                List<Images> ListImages = imageAccepter.findImages(product.getId(),1);//获得缩略列表图
                List<Images> DetailedImages = imageAccepter.findImages(product.getId(),2);//获取产品详情图
                try {
                    ProductPrice pp = productPriceAccepter.getProductPrice(product.getId(), 0L);
                    if(EmptyUtils.isEmpty(pp)){
                        return ErrorCode.ResultFail(ErrorCode.NOT_EXIST);
                    }
                    WinesBean bean = new WinesBean();
                    bean.setId(product.getId());
                    bean.setName(product.getName());
                    bean.setFlavor(product.getFlavorTypes());
                    bean.setDegress(product.getDegree());
                    bean.setRemainder(channel.getAllowance());
                    bean.setOriginalPrice(pp.getOriginalPrice());
                    bean.setSellingPrice(pp.getSellingPrice());
                    net.sf.json.JSONObject jsonObject = reportAccepter.getProcductSaleAmount(product.getId());
                    bean.setSales((long) jsonObject.getInt("saleAmount"));
                    bean.setProduct(product.getWinery().getName());
                    bean.setDescribe(product.getProductDetail().getDescription());
                    bean.setEnable(LegalUtils.isSelling(pp.getSellingPrice(),channel.getAllowance(),channel.getAlarmValue()));
                    if(EmptyUtils.isNotEmpty(ListImages)){
                        bean.setImgList(ListImages.get(0).getUrl());
                    }
                    if(EmptyUtils.isNotEmpty(DetailedImages)){
                        bean.setImgDetails(DetailedImages.get(0).getUrl());
                    }
                    wines.add(bean);
                } catch (Exception e) {
                    e.printStackTrace();
                    return ErrorCode.ResultFail(ErrorCode.PRICE_SERVER);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return ErrorCode.ResultFail(ErrorCode.PRODUCT_SERVER);
            }
        }
        vo.setSuccess(true);
        vo.setTotal(wines.size());
        vo.setRows(wines);
        return (JSONObject) JSON.toJSON(vo);
    }

    @Override
    public JSONObject convertQrcode(String ipcCode,String url) {
        if(EmptyUtils.isEmpty(url)){
            return ErrorCode.ResultFail(ErrorCode.PARAM_ERR);
        }
        try {
            String newUrl = url + "?ipcCode=" + ipcCode;
            String result = weixinToolService.convertUrlToOauthUrl(1l,newUrl);
            wxUrlVO vo = new wxUrlVO();
            wxUrlBean bean = new wxUrlBean();
            vo.setSuccess(true);
            bean.setUrl(result);
            vo.setRows(bean);
            return (JSONObject) JSON.toJSON(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return ErrorCode.ResultFail(ErrorCode.WXC_SERVER);
        }
    }

}
