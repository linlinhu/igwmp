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
/**
 * 酒厂 
 */
@Table(schema="prds",name="winery")
@Entity
public class Winery  extends BaseEntity implements UndeleteableEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2565824942884608430L;
	
	public static final String PROP_NAME="name";
	 
	private String name; 
	
	private String description;	

	private Long createTime;//创建时间
	
	private Long lastModifyTime;//最后更新时间
	
	private int status;
	 
	 
	
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
	 
}
