/**
 * 
 */
package com.emin.igwmp.fs.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author jim
 *
 */
@Entity
@DiscriminatorValue("1")
public class NormalFile extends EminFile{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5202044430027592442L;

	
	private String fileUrl;

	@Column(name="fileurl")
	public String getFileUrl() {
		return fileUrl;
	}


	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	
	
	

	
	
}
