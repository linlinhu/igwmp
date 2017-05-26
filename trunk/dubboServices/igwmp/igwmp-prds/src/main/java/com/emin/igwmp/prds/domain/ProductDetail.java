package com.emin.igwmp.prds.domain;
 
import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn; 
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table; 
import com.emin.base.domain.BaseEntity; 
import com.emin.base.domain.UndeleteableEntity; 
 
@Table(schema="prds",name="product_detail")
@Entity
public class ProductDetail extends BaseEntity implements UndeleteableEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2565824942884608430L;
	 

	public static final String PROP_PRODUCT_ID = "productId";
	
	private Long productId;
	
	private String unit;//单位
	
	private String spec;//规格
	
	private String capacity;//容量
	
	private String description;//描述
	  
	private Product product;
		
	private Long createTime;//创建时间
	
	private Long lastModifyTime;//最后更新时间
	
	private int status; 
	
	@Id
	@Override
	@SequenceGenerator(name = "product_detail_id_seq", sequenceName = "prds.product_detail_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_detail_id_seq")
	public Long getId() {
		 
		return super.getId();
	}
	 
	@Override
	@Column(name="create_time")
	public Long getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Override
	@Column(name="lastmodify_time")
	public Long getLastModifyTime() {
		return lastModifyTime;
	}

	@Override
	public void setLastModifyTime(Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	@Override
	@Column(name="status")
	public int getStatus() {
		return status;
	}

	@Override
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Column(name="product_id")
	public Long getProductId() {
		
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	@Column(name="unit")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Column(name="spec")
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
	@Column(name="capacity")
	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToOne
	@JoinColumn(name="product_id",insertable=false,updatable=false)
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	 
}
