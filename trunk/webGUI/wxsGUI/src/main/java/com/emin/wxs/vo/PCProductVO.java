package com.emin.wxs.vo;

import com.emin.igwmp.prds.domain.Category;
import com.emin.igwmp.prds.domain.Product;
import com.emin.igwmp.prds.domain.ProductDetail;
import com.emin.igwmp.prds.domain.Taste;
import com.emin.igwmp.prds.domain.WineTaster;
import com.emin.igwmp.prds.domain.Winery;
import com.emin.igwmp.prds.domain.Images;
import com.emin.platform.domain.Person;

import net.sf.json.JSONObject;

public class PCProductVO {
	private Long id;
	private Long productDetailId;

	private String name;// 名称
	private Long senceId;// 使用场景id
	private String senceName;// 使用场景
	private Double degree;// 度数
	private String type;// 香型
	private Long masterId;// 调酒大师Id
	private String masterName;// 调酒大师
	private String wineryName;// 酒厂
	private String remark;// 备注
	private Long wineryId;//酒厂Id
	private String number;//酒厂Id
	private String productListImgs;//	产品图片地址
	private String productDetailImgs;//	产品图片地址
	private String mobileImgs;//	产品图片地址
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductListImgs() {
		return productListImgs;
	}

	public void setProductListImgs(String productListImgs) {
		this.productListImgs = productListImgs;
	}

	public String getProductDetailImgs() {
		return productDetailImgs;
	}

	public void setProductDetailImgs(String productDetailImgs) {
		this.productDetailImgs = productDetailImgs;
	}

	public String getMobileImgs() {
		return mobileImgs;
	}

	public void setMobileImgs(String mobileImgs) {
		this.mobileImgs = mobileImgs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSenceId() {
		return senceId;
	}

	public void setSenceId(Long senceId) {
		this.senceId = senceId;
	}

	public String getSenceName() {
		return senceName;
	}

	public void setSenceName(String senceName) {
		this.senceName = senceName;
	}

	public Double getDegree() {
		return degree;
	}

	public void setDegree(Double degree) {
		this.degree = degree;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getMasterId() {
		return masterId;
	}

	public void setMasterId(Long masterId) {
		this.masterId = masterId;
	}

	public String getMasterName() {
		return masterName;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}

	public String getWineryName() {
		return wineryName;
	}

	public void setWineryName(String wineryName) {
		this.wineryName = wineryName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	

	public Long getProductDetailId() {
		return productDetailId;
	}

	public void setProductDetailId(Long productDetailId) {
		this.productDetailId = productDetailId;
	}

	public static PCProductVO productToVO(Product product) {
		if (null == product)
			return null;
		PCProductVO vo = new PCProductVO();
		vo.setId(product.getId());
		vo.setNumber(product.getNumber());
		Winery winery =product.getWinery();
		
		if(null!=winery){
			vo.setWineryName(winery.getName());		
			vo.setWineryId(winery.getId());			
		}
		
		/*if(null!=images){
			vo.setWineryName(winery.getName());		
			vo.setWineryId(winery.getId());			
		}*/
		vo.setDegree(product.getDegree());
		vo.setName(product.getName());
		vo.setType(product.getFlavorTypes());
		ProductDetail pDetail = product.getProductDetail();
		if(null!=pDetail){
			vo.setRemark(pDetail.getDescription());	
			vo.setProductDetailId(pDetail.getId());
		}
		Category category = product.getCategory();
		if(null!=category){
			vo.setSenceId(category.getId());
			vo.setSenceName(category.getName());
		}
		//Taste taste = getTaste(product.getId());
		return vo;
	}
	

	public Product convertToProduct(Product product){
		Product p = product;
		if(p==null){
			 p = new Product();
		}
		
		p.setId(this.id);
		p.setDegree(this.degree);
		p.setName(this.name);
		p.setFlavorTypes(this.type);
		p.setWineryId(this.wineryId);
		p.setCategoryId(this.senceId);
		return p;
	}

	public Long getWineryId() {
		return wineryId;
	}

	public void setWineryId(Long wineryId) {
		this.wineryId = wineryId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String string) {
		this.number = string;
	}
 

}
