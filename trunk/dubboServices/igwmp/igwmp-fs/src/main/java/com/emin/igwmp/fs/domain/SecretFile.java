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
@DiscriminatorValue("2")
public class SecretFile extends EminFile{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6958963237030589522L;
	
	private byte[] fileContent;

	@Column(name="filecontent")
	public byte[] getFileContent() {
		return fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}
	
	
}
