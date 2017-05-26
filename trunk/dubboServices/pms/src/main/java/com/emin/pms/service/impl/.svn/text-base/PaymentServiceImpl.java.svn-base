package com.emin.pms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emin.base.dao.PreFilters;
import com.emin.base.exception.EminException;
import com.emin.base.log.BussLog;
import com.emin.base.service.CRUDServiceImpl;
import com.emin.pms.domain.PayerType;
import com.emin.pms.domain.PaymentChannel;
import com.emin.pms.domain.PaymentLog;
import com.emin.pms.domain.PaymentStatus;
import com.emin.pms.domain.PaymentStrategy;
import com.emin.pms.exception.PmsExceptionCode;
import com.emin.pms.redis.RedisDao;
import com.emin.pms.service.PaymentService;
import com.emin.pms.util.EminPropertyPlaceholderConfigurer;
import com.pingplusplus.exception.PingppException;
import com.pingplusplus.model.Charge;
@Service("paymentService")
public class PaymentServiceImpl extends CRUDServiceImpl<PaymentLog> implements PaymentService{

	@Autowired
	private RedisDao redisDao;
	
	
	@BussLog(description="创建支付")
	private String createPayment(String orderNumber,
			Long channelId,
			Integer amount,
			PayerType payerType,
			Long payerId,
			PaymentStrategy paymentStrategy,
			Map<String,Object> otherParam
			){
		this.beforeCreatePayment(orderNumber, channelId, amount, payerType, payerId, paymentStrategy, otherParam);
		PaymentChannel channel = findChannelById(channelId);
		if(channel==null || channel.getStatus()==-1){
			throw new EminException(PmsExceptionCode.CHANNEL_INVALID);
		}
		if(channel.getChanelCode().equals("wx_pub") && !otherParam.containsKey("openId")){
			throw new EminException(PmsExceptionCode.WXPUB_MUST_BE_HAS_OPENID);
		}
		if(channel.getChanelCode().equals("wx_pub_qr") && !otherParam.containsKey("productId")){
			throw new EminException(PmsExceptionCode.WXPUB_QR_MUST_BE_HAS_PRODUCTID);
		}
		Long sysTime = System.currentTimeMillis();
		Charge charge = null;
        Map<String, Object> chargeMap = new HashMap<String, Object>();
        chargeMap.put("amount", amount);//订单总金额, 人民币单位：分（如订单总金额为 1 元，此处请填 100）
        chargeMap.put("currency", "cny");
        chargeMap.put("subject", otherParam.get("subject"));
        chargeMap.put("body",otherParam.get("body"));
        chargeMap.put("order_no", orderNumber);// 推荐使用 8-20 位，要求数字或字母，不允许其他字符
        chargeMap.put("channel", channel.getChanelCode());// 支付使用的第三方支付渠道取值，请参考：https://www.pingxx.com/api#api-c-new
        chargeMap.put("client_ip",otherParam.get("clientIP")); // 发起支付请求客户端的 IP 地址，格式为 IPV4
        Integer expire = Integer.valueOf(EminPropertyPlaceholderConfigurer.getContextProperty("pingpp.defaultExpireSecond"));
        TimeUnit tu = TimeUnit.SECONDS;
        if(otherParam.containsKey("expire")){
        	expire = Integer.valueOf(otherParam.get("expire").toString());
        	
        	if(otherParam.containsKey("expireTimeUnit")){
        		tu = (TimeUnit) otherParam.get("expireTimeUnit");
        	}
        }
        Long expireTime = tu.toMillis(expire);
        if(expireTime>3600*1000){
        	throw new EminException(PmsExceptionCode.PAY_EXPIRE_TOME_TOO_LONG);
        }
        Map<String, String> app = new HashMap<String, String>();
        app.put("id", EminPropertyPlaceholderConfigurer.getContextProperty("pingpp.appId"));
        chargeMap.put("app", app);

        Map<String, Object> extra = new HashMap<String, Object>();
        if(channel.getChanelCode().equals("wx_pub")){
        	 extra.put("open_id", otherParam.get("openId"));// 用户在商户微信公众号下的唯一标识，获取方式可参考 WxPubOAuthExample.java
        }else if(channel.getChanelCode().equals("wx_pub_qr")){
        	 extra.put("product_id", otherParam.get("productId"));// 用户在商户微信公众号下的唯一标识，获取方式可参考 WxPubOAuthExample.java
        }
      
        chargeMap.put("extra", extra);
        try {
            //发起交易请求
        	charge = Charge.create(chargeMap);
        	
        	//保存支付LOG
        	logger.info(charge.toString());
        	PaymentLog paymentLog = new PaymentLog();
        	paymentLog.setAmount(amount);
        	paymentLog.setChannelId(channelId);
        	paymentLog.setOrderNumber(orderNumber);
        	paymentLog.setCreateTime(sysTime);
        	paymentLog.setPayerInfo(otherParam.get("payerInfo").toString());
        	paymentLog.setPayerId(payerId);
        	paymentLog.setPayerType(payerType);            
            paymentLog.setPaymentNumber(charge.getId().replace("_", "-"));		
            paymentLog.setPaymentStatus(PaymentStatus.CREATED);
            paymentLog.setPaymentStrategy(paymentStrategy);
            paymentLog.setExpireTime(sysTime+expireTime);
            this.save(paymentLog);
            //将支付log存入redis
            redisDao.put("Payment", "PAYMENT_"+paymentLog.getPaymentNumber(),paymentLog.getOrderNumber());
            //设置此交易的过期时间
            redisDao.exipre("PAYMENT_"+paymentLog.getPaymentNumber(),Long.parseLong(expire.toString()),tu);
            //通知交易中心有新的支付
            redisDao.sendMessage("payment", paymentLog.getPaymentNumber());
            // 传到客户端请先转成字符串 .toString(), 调该方法，会自动转成正确的 JSON 字符串
            String chargeString = charge.toString();
            return chargeString;
        } catch (PingppException e) {
            e.printStackTrace();
        }
        return null;
	}
	
	@Override
	public String createPayment(String orderNumber,
			String channelCode,
			Integer amount,
			PayerType payerType,
			Long payerId,
			PaymentStrategy paymentStrategy,
			Map<String,Object> otherParam
			){
		PaymentChannel channel = this.getEntityDao().findUniqueByPreFilter(PaymentChannel.class, PreFilters.eq(PaymentChannel.PROP_CHANNEL_CODE,channelCode));
		Long channelId = channel ==null?null:channel.getId();
		logger.info("channelId===="+channelId);
		return createPayment(orderNumber, channelId, amount, payerType, payerId, paymentStrategy, otherParam);
	}
	
	private void beforeCreatePayment(String orderNumber,
			Long channelId,
			Integer amount,
			PayerType payerType,
			Long payerId,
			PaymentStrategy paymentStrategy,
			Map<String,Object> otherParam
			){
		if(channelId==null
				||amount==null
				||payerType==null
				||payerId==null
				||otherParam==null
				||!otherParam.containsKey("clientIP")){
			throw new EminException(PmsExceptionCode.PARAMTER_INVALID);
		}
		if(paymentStrategy==null){
			paymentStrategy = PaymentStrategy.FULL;
		}
		
		
	}
	@Override
	public List<PaymentLog> findPaymentLogByOrderNumber(String orderNumber){
		return this.findByPreFilter(PreFilters.eq(PaymentLog.PROP_ORDER_NUMBER, orderNumber));
	}
	@Override
	@BussLog(description="根据交易号查询交易记录")
	public PaymentLog findPaymentLogByPaymentNumber(String paymentNumber){
		return this.findUniqueByPreFilter(PreFilters.eq(PaymentLog.PROP_PAYMENT_NUMBER, paymentNumber));
	}
	
	@Override
	public List<PaymentChannel> loadChannels(){
		return this.getEntityDao().findByPreFilter(PaymentChannel.class, PreFilters.ge(PaymentChannel.PROP_STATUS, 0));
	}
	private PaymentChannel findChannelById(Long id){
		return this.getEntityDao().findById(PaymentChannel.class, id);
	}
	public static void main(String[] args) {
		TimeUnit tu = TimeUnit.HOURS;
		System.out.println(tu.toMillis(1));
	}
}
