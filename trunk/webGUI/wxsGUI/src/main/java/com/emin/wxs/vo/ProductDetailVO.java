package com.emin.wxs.vo;

import com.emin.igwmp.prds.domain.ProductDetail;

public class ProductDetailVO {
		private Long id;
		private String description;//产品描述
		
		private String capacity; //产品容量
		
		private String unit;// 单位 （斤）
		
		private String spec;// 备注 
		
		private int status; //状态
		
		private Long productId;

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getUnit() {
			return unit;
		}

		public void setUnit(String unit) {
			this.unit = unit;
		}
		
//		public ProductDetailVO productDetailToVO(ProductDetail productDetail){
//			if(productDetail!=null){
//				ProductDetailVO vo = new ProductDetailVO();
//				vo.setId(productDetail.getId());
//				vo.setDescription(productDetail.getDescription());
//				vo.setCapacity(productDetail.getCapacity());
//				vo.setSpec(productDetail.getSpec());
//				vo.setStatus(productDetail.getStatus());
//				vo.setUnit(productDetail.getUnit());
//				vo.setProductId(productDetail.getProductId());
//				return vo;
//			}
//			return null;
//		}
		public ProductDetail convertToProduct(ProductDetail productDetail){
			ProductDetail p = productDetail;
			if(p==null){
				 p = new ProductDetail();
			}
			p.setId(this.id);
			p.setCapacity(this.capacity);
			p.setDescription(this.description);
			p.setSpec(this.spec);
			p.setProductId(this.productId);
			p.setStatus(this.status);
			p.setUnit(this.unit);
			return p;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getSpec() {
			return spec;
		}

		public void setSpec(String spec) {
			this.spec = spec;
		}

		public String getCapacity() {
			return capacity;
		}

		public void setCapacity(String capacity) {
			this.capacity = capacity;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public Long getProductId() {
			return productId;
		}

		public void setProductId(Long productId) {
			this.productId = productId;
		}

		public static ProductDetailVO productToVO(ProductDetail pd) {
			if(null==pd) return null;
			ProductDetailVO vo =new ProductDetailVO();
			vo.setId(pd.getId());
			vo.setCapacity(pd.getCapacity());
			vo.setDescription(pd.getDescription());
			vo.setSpec(pd.getSpec());
			vo.setUnit(pd.getUnit());
			return vo;
		}

	

	
		
	}