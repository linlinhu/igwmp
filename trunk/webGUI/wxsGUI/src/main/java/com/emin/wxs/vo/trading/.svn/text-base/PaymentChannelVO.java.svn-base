package com.emin.wxs.vo.trading;

import java.util.ArrayList;
import java.util.List;

import com.emin.pms.domain.PaymentChannel;

public class PaymentChannelVO {

	private Long id;
	
	private String code;
	
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PaymentChannelVO(Long id, String code, String name) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
	}
	
	public static PaymentChannelVO paymentChannelToVO(PaymentChannel paymentChannel){
		if(paymentChannel==null){
			return null;
		}
		return new PaymentChannelVO(paymentChannel.getId(), paymentChannel.getChanelCode(), paymentChannel.getName());
	}
	public static List<PaymentChannelVO> paymentChannelToVO(List<PaymentChannel> paymentChannel){
		List<PaymentChannelVO> vos = new ArrayList<>();
		if(paymentChannel==null || paymentChannel.size()==0){
			return vos ;
		}
		for(PaymentChannel channel : paymentChannel){
			vos.add(paymentChannelToVO(channel));
		}
		return vos;
	}
}
