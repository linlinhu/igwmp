package com.emin.igwmp.prds.domain;
 
import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn; 
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne; 
import javax.persistence.SequenceGenerator;
import javax.persistence.Table; 
import com.emin.base.domain.BaseEntity; 
import com.emin.base.domain.UndeleteableEntity; 
 
@Table(schema="prds",name="product")
@Entity
public class Product extends BaseEntity implements UndeleteableEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2565824942884608430L;
	
	public static final String PROP_NAME="name";
	
	public static final String PROP_NUMBER="number";

	public static final String PROP_CATEGORY_ID = "categoryId";

	public static final String PROP_WINERY_ID = "wineryId";

	public static final String PROP_CATEGORY_NAME = "category."+Category.PROP_NAME;
 	
	private String name;

	private Long categoryId;//分类ID
	
	private Long wineryId;//酒厂ID
	
	private Category category; //类型
	
	private Winery winery; //酒厂
	
	private String flavorTypes;//香型	
	
	private Double degree;//度数
	
	private String number;//编号
	  
	private Long createTime;//创建时间
	
	private Long lastModifyTime;//最后更新时间
	
	private int status;
	
	private ProductDetail productDetail;
	 
	
	@Id
	@Override
	@SequenceGenerator(name = "product_id_seq", sequenceName = "prds.product_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
	public Long getId() {
		 
		return super.getId();
	}
	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
	
	
	@Column(name="degree")
	public Double getDegree() {
		if(degree.isNaN())return 0D;
		return degree;
	}

	public void setDegree(Double degree) {
		this.degree = degree;
	} 
	 
	@Column(name="category_id")
	public Long getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	@ManyToOne
	@JoinColumn(name="category_id",insertable=false,updatable=false)
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	

	@ManyToOne
	@JoinColumn(name="winery_id",insertable=false,updatable=false)
	public Winery getWinery() {
		return winery;
	}
	public void setWinery(Winery winery) {
		this.winery = winery;
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
	@Column(name="number")
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	  
	@Column(name="winery_id")
	public Long getWineryId() {
		return wineryId;
	}
	public void setWineryId(Long wineryId) {
		this.wineryId = wineryId;
	}
	
	 
	@Column(name="flavor_types")
	public String getFlavorTypes() {
		return flavorTypes;
	}
	public void setFlavorTypes(String flavorTypes) {
		this.flavorTypes = flavorTypes;
	}
	
 
	@OneToOne(mappedBy="product") 
	public ProductDetail getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}
	 
}
