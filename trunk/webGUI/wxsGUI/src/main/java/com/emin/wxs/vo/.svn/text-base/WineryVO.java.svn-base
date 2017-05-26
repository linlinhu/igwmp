package com.emin.wxs.vo;

import com.emin.igwmp.prds.domain.Winery;



public class WineryVO {

//	private Long id;
	
	private String description;//酒厂描述
		
	private String name;//酒厂名字
	
	private int status;//酒厂状态
 
	public static WineryVO productToVO(Winery winery){
		if(winery!=null){
			WineryVO vo = new WineryVO();
			vo.setName(winery.getName());
			vo.setDescription(winery.getDescription());
			vo.setStatus(winery.getStatus());

			return vo;
		}
		return null;
	}
	public Winery convertToProduct(Winery winery){
		Winery p = winery;
		if(p==null){
			 p = new Winery();
		}
		p.setName(this.name);
		p.setDescription(this.name);
		p.setStatus(this.status);	
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

}
