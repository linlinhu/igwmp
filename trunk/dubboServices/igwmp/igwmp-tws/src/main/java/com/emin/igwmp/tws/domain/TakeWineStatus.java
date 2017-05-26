package com.emin.igwmp.tws.domain;

public enum TakeWineStatus {

	CREATE("创建"),
	SUCCESS("取酒完成"),
	EXCEPTION("取酒异常");
	
	private String name;
	
	private TakeWineStatus(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
