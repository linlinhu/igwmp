/**
 * 
 */
package com.emin.igwmp.fs.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.emin.base.domain.CompanyEntity;

/**
 * @author jim
 *
 */
@Table(schema="public",name="eminfile")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  
@DiscriminatorColumn(name = "filetype")  
public class EminFile extends CompanyEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7736518484556966866L;

	public static final String PROP_IS_TEMPORARY = "isTemporary";
	
	private Long fileSize;//文件大小
	
	private String suffix;//后缀
	
	private Long uploadTime;//上传时间
	
	private Integer fileType;//文件类型 1：路径存储 2:流存储
	
	private Boolean isTemporary;
	@Id
	@Override
	@SequenceGenerator(name = "eminfile_id_seq", sequenceName = "public.eminfile_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eminfile_id_seq")
	public Long getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}
	
	@Override
	@Column(name="company_id")
	public Long getCompanyId() {
		// TODO Auto-generated method stub
		return super.getCompanyId();
	}
	@Column(name="filesize")
	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	@Column(name="suffix")
	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	@Column(name="updatetime")
	public Long getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Long uploadTime) {
		this.uploadTime = uploadTime;
	}
	@Column(name="filetype",insertable=false,updatable=false)
	public Integer getFileType() {
		return fileType;
	}

	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}

	@Column(name="istemp")
	public Boolean getIsTemporary() {
		return isTemporary;
	}

	public void setIsTemporary(Boolean isTemporary) {
		this.isTemporary = isTemporary;
	}

	
	
}
