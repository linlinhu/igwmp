package com.emin.igwmp.prds.domain;
 
import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id; 
import javax.persistence.SequenceGenerator;
import javax.persistence.Table; 
import com.emin.base.domain.BaseEntity; 
import com.emin.base.domain.UndeleteableEntity; 
 
@Table(schema="prds",name="images")
@Entity
public class Images  extends BaseEntity implements UndeleteableEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2565824942884608430L;

	public static final String PROP_NAME="name";
	/**
	 * 作用域 1.产品列表2.产品详情3.终端列表4.终端详情5.大师品鉴图
	 */
	public static final String PROP_SCOPE="scope";
	/**
	 *  1.产品列表
	 */
	public static final Integer PROP_SCOPE_PRODUCT_LIST=1;
	/**
	 *  2.产品详情
	 */
	public static final Integer PROP_SCOPE_PRODUCT_DETAIL=2;
	/**
	 * 3.终端列表
	 */
	public static final Integer PROP_SCOPE_TERMINAL_LIST=3;
	/**
	 * 4.终端详情
	 */
	public static final Integer PROP_SCOPE_TERMINAL_DETAIL=4;
	/**
	 * 5.大师品鉴图
	 */
	public static final Integer PROP_SCOPE_WINE_TASTER=5;
	
	/**
	 * 关系id
	 */
	public static final String PROP_RELATION_ID="relationId";
	 
	private String name; 

	private String description;	
	
	private String url;	
	
	private Integer scope;//作用域 1.产品列表2.产品详情3.终端列表4.终端详情5.大师品鉴图
	
	private Long  relationId;//关系id

	private Long createTime;//创建时间
	
	private Long lastModifyTime;//最后更新时间
	
	private int status;
	 
 
	@Id
	@Override
	@SequenceGenerator(name = "images_id_seq", sequenceName = "prds.images_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "images_id_seq")
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
	
	@Column(name="url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name="scope")
	public Integer getScope() {
		return scope;
	}
	public void setScope(Integer scope) {
		this.scope = scope;
	}
	@Column(name="relation_id")
	public Long getRelationId() {
		return relationId;
	}
	public void setRelationId(Long relationId) {
		this.relationId = relationId;
	}
	 
}
