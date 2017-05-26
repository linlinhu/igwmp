package com.emin.wxs.vo;

import com.emin.igwmp.prds.domain.Product;
import com.emin.igwmp.prds.domain.ProductDetail;
import com.emin.igwmp.pcs.domain.ProductPrice;

public class ProductVO {

	private Long id;	

	private Long productDetailId;
	private String productName;//	产品名
	private String productDesc;//	产品描述
	private Double discountedPrice;//	产品优惠后价格
	private Double realPrice;//	产品市场价格
	private Double saleVolume;//	已经卖出多少斤（销量）
	private String productImgUrl;//	产品图片地址
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
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
	public Double getSaleVolume() {
		return saleVolume;
	}
	public void setSaleVolume(Double saleVolume) {
		this.saleVolume = saleVolume;
	}
	public String getProductImgUrl() {
		return productImgUrl;
	}
	public void setProductImgUrl(String productImgUrl) {
		this.productImgUrl = productImgUrl;
	}

	public Long getProductDetailId() {
		return productDetailId;
	}
	public void setProductDetailId(Long productDetailId) {
		this.productDetailId = productDetailId;
	}
	public static ProductVO productToVO(Product product) {
		ProductVO vo =new ProductVO();
		vo.setId(product.getId());
		vo.setProductName(product.getName());
		ProductDetail pd = product.getProductDetail();
		
		if(pd!=null){
			vo.setProductDesc(pd.getDescription());		
			vo.setProductDetailId(pd.getId());		
		}
		return vo;
	}
	
	
 
}
