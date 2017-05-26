package com.emin.platform.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity;

@Entity
@Table(schema="platform",name="image")
public class Image extends BaseEntity implements UndeleteableEntity{
	private static final long serialVersionUID = -8543689507127656807L;
	private String url;
	private int status;
	private Long createTime;
	private Long lastModifyTime;
	@Id
	@Override
	@SequenceGenerator(name = "image_id_seq", sequenceName = "platform.image_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_id_seq")
	public Long getId() {
		return super.getId();
	}
	@Column(name="url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name="status")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Column(name="createtime")
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	@Column(name="lastmodifytime")
	public Long getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	
}
