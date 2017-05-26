package com.emin.wxs.vo;

import com.emin.igwmp.prds.domain.Images;

public class ImageVO {

	private Long id;
	
	private String url;
	/**
	 * 1.产品列表2.产品详情3.终端列表4.终端详情5.大师品鉴图
	 */
	private Integer type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public static ImageVO personToVO(Images image){
		if(null==image)return null;
		ImageVO vo = new ImageVO();
		vo.setId(image.getId());
		vo.setType(image.getScope());
		vo.setUrl(image.getUrl());
		return vo;
	}
	 
	
	
}
