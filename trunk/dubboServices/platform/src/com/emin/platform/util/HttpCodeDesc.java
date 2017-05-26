package com.emin.platform.util;

public enum HttpCodeDesc {
	
	_404(404,"未找到服务"),
	_500(500,"服务处理异常");
	
	private Integer status;
	private String desc;
	private HttpCodeDesc(Integer status,String desc){
		this.desc = desc;
		this.status = status;
	}
	public static HttpCodeDesc get(Integer status){
		for(HttpCodeDesc desc : HttpCodeDesc.values()){
			if(desc.getStatus().equals(status)){
				return desc;
			}
		}
		return null;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
