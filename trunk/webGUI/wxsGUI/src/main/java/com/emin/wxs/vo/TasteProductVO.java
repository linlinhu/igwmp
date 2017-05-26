package com.emin.wxs.vo;
 
import com.emin.igwmp.prds.domain.Product; 

public class TasteProductVO {
	private Long id;
	private String productName;// 名称
	private Long masterId;// 调酒大师ID
	private Long productId;// 产品ID
	private String masterName;// 调酒大师
	private String description;// 评价
 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
 

	public String getMasterName() {
		return masterName;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}
 
	public static TasteProductVO productToVO(Product product) {
		if (null == product)
			return null;
		TasteProductVO vo = new TasteProductVO();
		//vo.setId(product.getId());
		vo.setProductId(product.getId());
		vo.setProductName(product.getName());
		//Taste taste = getTaste(product.getId());
		return vo;
	}
	

	/*public Product convertToProduct(Product product){
		Product p = product;
		if(p==null){
			 p = new Product();
		}
		
		p.setId(this.id);
		p.setDegree(this.degree);
		p.setName(this.name);
		p.setFlavorTypes(this.type);
		p.setWineryId(this.companyID);
		p.setCategoryId(this.senceID);
		return p;
	}
 
 */

	public Long getMasterId() {
		return masterId;
	}

	public void setMasterId(Long masterId) {
		this.masterId = masterId;
	}
 

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
 

}
