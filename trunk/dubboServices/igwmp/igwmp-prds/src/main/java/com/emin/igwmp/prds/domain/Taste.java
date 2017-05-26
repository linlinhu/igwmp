package com.emin.igwmp.prds.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity;
/**
 * 品鉴评价
 * 
 */
@Table(schema="prds",name="taste")
@Entity
public class Taste extends BaseEntity implements UndeleteableEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2565824942884608430L;

	public static final String PROP_NAME="name";
	
	public static final String PROP_WINE_TASTER_ID="wineTasterId";
	/**
	 * 产品id 品鉴是一定需要针对产品的
	 */
	public static final String PROP_PRODUCT_ID  = "productId";
	 
	private String name; 
	
	private Long wineTasterId;
	
	private String description;	

	private Long createTime;//创建时间
	
	private Long lastModifyTime;//最后更新时间
	
	
	private Long productId;
	
	private int status;
	
	private WineTaster wineTaster;
	 
	 
	
	@Id
	@Override
	@SequenceGenerator(name = "winery_id_seq", sequenceName = "prds.winery_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "winery_id_seq")
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
	
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="wine_taster_id")
	public Long getWineTasterId() {
		return wineTasterId;
	}
	public void setWineTasterId(Long wineTasterId) {
		this.wineTasterId = wineTasterId;
	}
	
	@ManyToOne
	@JoinColumn(name="wine_taster_id",insertable=false,updatable=false)
	public WineTaster getWineTaster() {
		return wineTaster;
	}
	public void setWineTaster(WineTaster wineTaster) {
		this.wineTaster = wineTaster;
	}
	
	@Column(name="product_id")
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
}
