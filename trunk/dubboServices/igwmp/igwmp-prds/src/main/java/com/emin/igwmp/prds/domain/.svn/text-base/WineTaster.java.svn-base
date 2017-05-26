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
 *  品酒师表
 */
@Table(schema="prds",name="wine_taster")
@Entity
public class WineTaster extends BaseEntity implements UndeleteableEntity{


	/**
	 * 
	 */
	private static final long serialVersionUID = 2145930219420290344L;

	public static final String PROP_NAME="name";
	 
	private String name;
	
	private String honor;//个人荣誉描述
	
	
	private String url;//头像
	
	private Long createTime;//创建时间
	
	private Long lastModifyTime;//最后更新时间
	
	private int status;
	
	private String description;	


	@Id
	@Override
	@SequenceGenerator(name = "wine_taster_id_seq", sequenceName = "prds.wine_taster_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wine_taster_id_seq")
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
	@Column(name="honor")
	public String getHonor() {
		return honor;
	}
 
	public void setHonor(String honor) {
		this.honor = honor;
	}
	 
}
