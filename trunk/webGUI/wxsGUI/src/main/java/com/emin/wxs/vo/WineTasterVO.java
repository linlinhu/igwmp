package com.emin.wxs.vo;

import com.emin.igwmp.prds.domain.WineTaster;



public class WineTasterVO {

//	private Long id;
	
	private String description;
		
	private String name;//类型香型
	
	private int status;//度数
	
	private String imgUrl;//图片地址
 
	public static WineTasterVO productToVO(WineTaster wineTaster){
		if(wineTaster!=null){
			WineTasterVO vo = new WineTasterVO();
			vo.setDescription(wineTaster.getDescription());
			vo.setName(wineTaster.getName());
			vo.setStatus(wineTaster.getStatus());
			vo.setImgUrl(wineTaster.getUrl());
			return vo;
		}
		return null;
	}
	public WineTaster convertToProduct(WineTaster wineTaster){
		WineTaster p = wineTaster;
		if(p==null){
			 p = new WineTaster();
		}
		p.setDescription(this.description);
		p.setName(this.name);
		p.setStatus(this.status);
		p.setUrl(this.imgUrl);
		return p;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

}
