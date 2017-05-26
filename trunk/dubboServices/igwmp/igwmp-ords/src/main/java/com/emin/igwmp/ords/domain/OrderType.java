package com.emin.igwmp.ords.domain;

public enum OrderType {

	/**
	 * 普通订单
	 */
	NORMAL("普通订单"),
	
	/**
	 * 补偿订单
	 */
	COMPENSATE("补偿订单"),
	
	/**
	 * 转赠订单
	 */
	GIVEN("转赠订单");
	
	private String name;
	
	private OrderType(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
