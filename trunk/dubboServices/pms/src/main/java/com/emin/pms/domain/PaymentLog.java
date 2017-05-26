package com.emin.pms.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.emin.base.domain.BaseEntity;
@Entity
@Table(schema="pms",name="payment_log")
public class PaymentLog extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 103687028383229891L;
	
	
	public static final String PROP_ORDER_NUMBER = "orderNumber";
	
	public static final String PROP_PAYMENT_NUMBER = "paymentNumber";

	private String orderNumber;//订单号
	
	private String paymentNumber;//支付流水号
	
	private Long channelId;//支付通道ID
	
	private PaymentChannel channel; //支付通道
	
	private Integer amount;//支付金额 单位：分
	
	private PaymentStrategy paymentStrategy;//支付策略 (暂时只有全额支付) FULL 后期做支付策略管理
	
	private Long createTime;//支付发起时间
	
	private Long payedTime;//实际支付时间
	
	private Long expireTime;//支付过期时间 各种支付平台要求不一致 按照最多1小时处理
	
	private PaymentStatus paymentStatus;//支付状态

	private Long payerId;//支付方ID
	
	private PayerType payerType;//支付方类型
	
	private String payerInfo;// JSON String
	
	@Id
	@Override
	@SequenceGenerator(name = "payment_log_id_seq", sequenceName = "pms.payment_log_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_log_id_seq")
	public Long getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}

	@Column(name="order_number",nullable=false)
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Column(name="payment_number",nullable=false)
	public String getPaymentNumber() {
		return paymentNumber;
	}

	public void setPaymentNumber(String paymentNumber) {
		this.paymentNumber = paymentNumber;
	}

	@Column(name="channel_id",nullable=false)
	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}
	
	@ManyToOne
	@JoinColumn(name="channel_id",insertable=false,updatable=false)
	public PaymentChannel getChannel() {
		return channel;
	}

	public void setChannel(PaymentChannel channel) {
		this.channel = channel;
	}
	
	@Column(name="amount",nullable=false)
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Column(name="payment_strategy",nullable=false)
	@Enumerated(EnumType.STRING)
	public PaymentStrategy getPaymentStrategy() {
		return paymentStrategy;
	}

	public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
		this.paymentStrategy = paymentStrategy;
	}

	@Column(name="create_time",nullable=false)
	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	
	@Column(name="payed_time")
	public Long getPayedTime() {
		return payedTime;
	}

	public void setPayedTime(Long payedTime) {
		this.payedTime = payedTime;
	}

	@Column(name="payment_status",nullable=false)
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Column(name="payer_id",nullable=false)
	public Long getPayerId() {
		return payerId;
	}

	public void setPayerId(Long payerId) {
		this.payerId = payerId;
	}

	@Column(name="payer_type",nullable=false)
	@Enumerated(EnumType.STRING)
	public PayerType getPayerType() {
		return payerType;
	}

	public void setPayerType(PayerType payerType) {
		this.payerType = payerType;
	}

	@Column(name="payer_info",columnDefinition="TEXT")
	public String getPayerInfo() {
		return payerInfo;
	}

	public void setPayerInfo(String payerInfo) {
		this.payerInfo = payerInfo;
	}

	@Column(name="expireTime")
	public Long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
	}
	
	
	
}
