package com.emin.igwmp.ords.domain;

public enum OrderStatus {
	
	CREATE("创建"),
	
	TOBETAKE("待取酒"),
	
	TOOK("已取酒"),
	
	EXCEPTION("取酒异常"),
	
	FAKE_EXCEPTION("取酒异常"),//实际出酒和应出酒差量小于最小出酒粒度
	
	LOCKED("被锁定");
	
	private String name;
	
	private OrderStatus(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
