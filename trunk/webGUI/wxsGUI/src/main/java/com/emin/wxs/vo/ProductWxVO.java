package com.emin.wxs.vo;

import java.io.Serializable;
import java.math.BigDecimal;



public class ProductWxVO implements Serializable{


	private static final long serialVersionUID = -8307810613456464404L;
	
	private String name;
	private BigDecimal realPrice;//实际价格
	private BigDecimal discountedPrice;//优惠后的价格
	private String desc;//描述
	private Long categoryId;//分类ID 
	
	private Long wineryId;//酒厂ID 
	
	private String flavorTypes;//香型	
	
	private Double degree;//度数
	
	private String number;//编号
	
	private String expertName;//专家姓名	
	
	private String expertDescription;//专家描述
	
	private String expertUrl;//专家地址
	  
	private Long createTime;//创建时间
	
	private Long lastModifyTime;//最后更新时间
	
	
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Long getWineryId() {
		return wineryId;
	}
	public void setWineryId(Long wineryId) {
		this.wineryId = wineryId;
	}
	
	public String getFlavorTypes() {
		return flavorTypes;
	}
	public void setFlavorTypes(String flavorTypes) {
		this.flavorTypes = flavorTypes;
	}
	public Double getDegree() {
		return degree;
	}
	public void setDegree(Double degree) {
		this.degree = degree;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public Long getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getDiscountedPrice() {
		return discountedPrice;
	}
	public void setDiscountedPrice(BigDecimal discountedPrice) {
		this.discountedPrice = discountedPrice;
	}
	public BigDecimal getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getExpertName() {
		return expertName;
	}
	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}
	public String getExpertDescription() {
		return expertDescription;
	}
	public void setExpertDescription(String expertDescription) {
		this.expertDescription = expertDescription;
	}
	public String getExpertUrl() {
		return expertUrl;
	}
	public void setExpertUrl(String expertUrl) {
		this.expertUrl = expertUrl;
	}

}
