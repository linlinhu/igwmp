package com.emin.igwmp.skm.facade.callers.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.emin.igwmp.ords.domain.Order;
import com.emin.igwmp.ords.domain.OrderItem;
import com.emin.igwmp.ords.facade.accepters.OrderAccepter;
import com.emin.igwmp.prds.domain.Product;
import com.emin.igwmp.prds.facade.accepters.ProductAccepter;
import com.emin.igwmp.skm.exception.ErrorCode;
import com.emin.igwmp.skm.facade.callers.SkToPayCaller;
import com.emin.igwmp.skm.facade.callers.vo.PaysVO;
import com.emin.igwmp.skm.facade.callers.vo.PaymentVO;
import com.emin.igwmp.skm.facade.callers.vo.bean.PaymentBean;
import com.emin.igwmp.skm.util.EmptyUtils;
import com.emin.igwmp.skm.util.LogUtils;
import com.emin.igwmp.skm.util.MonyUtils;
import com.emin.igwmp.skm.util.NetworkUtils;
import com.emin.pms.domain.PayerType;
import com.emin.pms.facade.accepters.PaymentAccepter;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/4/18.
 */
@Service("skToPayCaller")
public class SkToPayCallerImpl implements SkToPayCaller {

    @Reference(version="0.0.1")
    private PaymentAccepter paymentAccepter;

    @Reference(version = "0.0.1")
    OrderAccepter orderAccepter;

    @Reference(version="0.0.1")
    private ProductAccepter productAccepter;//产品信息

    @Override
    public JSONObject createPay(String ipcCode, String payInfo) {
        LogUtils.D("创建支付信息:"  + ipcCode +  "  payInfo:" + payInfo);

        PaysVO pay = JSON.parseObject(payInfo, PaysVO.class);

        Set<OrderItem> items = new HashSet<>();
        OrderItem item = new OrderItem();
        item.setProductId(pay.getProductId());
        Product product = productAccepter.getProductById(pay.getProductId());
        if(EmptyUtils.isEmpty(product)){
            return  ErrorCode.ResultFail(ErrorCode.NOT_EXIST);
        }
        net.sf.json.JSONObject proInfo = new net.sf.json.JSONObject();
        proInfo.put("name", product.getName());
        proInfo.put("payType",pay.getPayType());
        item.setProductInfo(proInfo);
        if(pay.getPayType() == 1){//试饮
            item.setAmount(pay.getPrice());//总价
            item.setPrice(pay.getPrice());
            item.setCount(pay.getValue());//数量ml
        }else{
            if(EmptyUtils.isEmpty(pay.getValue()) || pay.getValue() == 0){
                //通过总价创建订单
                item.setAmount(pay.getPrice());//总价
            }else{
                //通过单价个购买量创建订单
                item.setPrice(pay.getPrice());//单价
                item.setCount(pay.getValue()/50);//数量ml
            }
        }
        items.add(item);
        Order order = new Order();
        order.setItems(items);
        String orderNumber = null;
        Order creatOrder;
        try {
            orderNumber = orderAccepter.createOrderForMachine(order);
            creatOrder = orderAccepter.findOrderByNumber(orderNumber);

        } catch (Exception e) {
            e.printStackTrace();
            return  ErrorCode.ResultFail(ErrorCode.ORDER_SERVER);
        }
        Map<String ,Object> otherInfo = new HashMap<>();

        otherInfo.put("subject", "欢迎使用来e杯");//付款显示标题
        otherInfo.put("body","来e杯");
        otherInfo.put("productId", pay.getIpcCode());//付款方标识
        otherInfo.put("clientIP", NetworkUtils.getLocalIP());//

        JSONObject payerInfo = new JSONObject();
        payerInfo.put("name",ipcCode);
        otherInfo.put("payerInfo",payerInfo.toString());
        int creatMony = (int) (creatOrder.getTotalMoney()*100);//以分计算支付价格
        try {
            String charge = paymentAccepter.createPayment(orderNumber,"wx_pub_qr",creatMony, PayerType.WEIXINFANS,-1l,otherInfo);

            JSONObject json = JSON.parseObject(charge);
            String url = json.getJSONObject("credential").getString("wx_pub_qr");
            PaymentVO vo  = new PaymentVO();
            PaymentBean bean = new PaymentBean();
            bean.setType(0);
            bean.setPayQr(url);
            bean.setPay(MonyUtils.format(creatOrder.getTotalMoney()));
            vo.setSuccess(true);
            vo.setRows(bean);
            return (JSONObject) JSON.toJSON(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return  ErrorCode.ResultFail(ErrorCode.PAY_SERVER);
        }

    }
}
