/**
 * 
 */
package com.emin.igwmp.fs.facade.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.emin.base.exception.EminException;
import com.emin.igwmp.fs.domain.SecretFile;
import com.emin.igwmp.fs.exception.FileExceptionCode;
import com.emin.igwmp.fs.facade.service.SecretFileService;
import com.emin.igwmp.fs.util.FileUtil;
 

/**
 * @author jim
 *
 */
@Service("secretFileService")
public class SecretFileServiceImpl extends EminFileServiceImpl<SecretFile> implements SecretFileService{
	@Override
	public Long saveFile(byte[] fileContent,String fileName,Long companyId){		
		if(fileContent==null ||fileContent.length==0 || StringUtils.isBlank(fileName) || companyId==null){
			throw new EminException(FileExceptionCode.F_PARAMTERS_INVALID);
		}
		
		String suffix = FileUtil.getExtension(fileName);
		
			
		SecretFile nf = new SecretFile();
		nf.setFileSize((long)fileContent.length);
		nf.setFileContent(fileContent);
		nf.setSuffix(suffix);
		nf.setIsTemporary(true);
		nf.setUploadTime(System.currentTimeMillis());
		nf.setCompanyId(companyId);
		this.save(nf);
		return nf.getId();
		
		
	}
	@Override
	public byte[] getFileById(Long id) {
		return this.findById(id).getFileContent();
	}
}
