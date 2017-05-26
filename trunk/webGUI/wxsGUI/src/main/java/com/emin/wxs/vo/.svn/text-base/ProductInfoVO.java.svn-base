package com.emin.wxs.vo;

import com.emin.igwmp.prds.domain.Product;
import com.emin.igwmp.prds.domain.ProductDetail;

public class ProductInfoVO {
		 
	private Long id;
	private Long productDetailId;
	private String productDesc;//	产品图片详情
	private String productType;//	产品类型（浓香型）
	
	private String productImgUrl;//	产品图片地址
	private Double productDegree;//	酒精度（53度）
	private String expertImgUrl;//	大师图片地址
	private String expertProfession;//	大师介绍职称
	private String expertName;//	大师介绍姓名
	
	private String productName;//	产品名称
	private Double discountedPrice;//	产品优惠价格
	private Double realPrice;//	产品门市价格
	private String productAddress;//	产品产地
	private String productCapacity;//	产品容量
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductImgUrl() {
		return productImgUrl;
	}
	public void setProductImgUrl(String productImgUrl) {
		this.productImgUrl = productImgUrl;
	}
	public Double getProductDegree() {
		return productDegree;
	}
	public void setProductDegree(Double productDegree) {
		this.productDegree = productDegree;
	}
	public String getExpertImgUrl() {
		return expertImgUrl;
	}
	public void setExpertImgUrl(String expertImgUrl) {
		this.expertImgUrl = expertImgUrl;
	}
	public String getExpertProfession() {
		return expertProfession;
	}
	public void setExpertProfession(String expertProfession) {
		this.expertProfession = expertProfession;
	}
	public String getExpertName() {
		return expertName;
	}
	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getDiscountedPrice() {
		return discountedPrice;
	}
	public void setDiscountedPrice(Double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}
	public Double getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(Double realPrice) {
		this.realPrice = realPrice;
	}
	public String getProductAddress() {
		return productAddress;
	}
	public void setProductAddress(String productAddress) {
		this.productAddress = productAddress;
	}
	public String getProductCapacity() {
		return productCapacity;
	}
	public void setProductCapacity(String productCapacity) {
		this.productCapacity = productCapacity;
	}

	public Long getProductDetailId() {
		return productDetailId;
	}
	public void setProductDetailId(Long productDetailId) {
		this.productDetailId = productDetailId;
	}
	public static ProductInfoVO productToVO(Product product) {
		ProductInfoVO vo = new ProductInfoVO();
		vo.setId(product.getId());
		vo.setProductName(product.getName());
		vo.setProductType(product.getFlavorTypes());
		vo.setProductDegree(product.getDegree());
		
		ProductDetail pd = product.getProductDetail();
		if(null!=pd){
			vo.setProductDesc(pd.getDescription());
			vo.setProductCapacity(pd.getCapacity());	
			vo.setProductDetailId(pd.getId());
		} 
		return vo;
	}

}