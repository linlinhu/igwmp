package com.emin.wxs.vo;

import com.emin.igwmp.prds.domain.Images;


public class ProductImgVO {

//	private Long id;
	private	String imgUrl;//产品图片地址
	private	String name;//产品图片名字
	private Long relationId;//关联id （暂时先加上）
	private String description;//图片描述（暂时先加上）
	
	
	public static ProductImgVO productToVO(Images productImage){
		if(productImage!=null){
			ProductImgVO vo = new ProductImgVO();
			vo.setImgUrl(productImage.getUrl());
			vo.setName(productImage.getName());
			vo.setRelationId(productImage.getRelationId());
			vo.setDescription(productImage.getDescription());
			return vo;
		}
		return null;
	}
	public Images convertToProduct(Images productImage){
		Images p = productImage;
		if(p==null){
			 p = new Images();
		}
		p.setName(this.name);
		p.setDescription(this.description);
		p.setUrl(this.imgUrl);
		p.setRelationId(this.relationId);

		return p;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getRelationId() {
		return relationId;
	}
	public void setRelationId(Long relationId) {
		this.relationId = relationId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
