/**
 * 
 */
package com.emin.igwmp.fs.facade.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;
import com.emin.base.dao.PreFilters;
import com.emin.base.service.CRUDServiceImpl;
import com.emin.base.util.CommonsUtil;
import com.emin.igwmp.fs.domain.EminFile;
import com.emin.igwmp.fs.domain.NormalFile;
import com.emin.igwmp.fs.domain.SecretFile;
import com.emin.igwmp.fs.facade.service.EminFileService; 

/**
 * @author jim
 *
 */
@Service("eminFileService")
public class EminFileServiceImpl<F extends EminFile> extends CRUDServiceImpl<F> implements EminFileService<F>{

	
	public Long saveFile(byte[] fileContent, String fileName, Long companyId) {
		
		return null;
	}
	
	public byte[] getFileById(Long id){
		return null;
	}
	@Override
	public void useFiles(Long[] fileIds){
		this.getEntityDao().updateByHQL("update EminFile set isTemporary = false where id in ("+CommonsUtil.longArrToString(fileIds)+")");
	}
	@Override
	public void cleanTemporaryFiles(){
		List<F> files = this.findByPreFilter(PreFilters.eq(EminFile.PROP_IS_TEMPORARY, true));
		for (F f : files) {
			if(f instanceof SecretFile){
				this.getEntityDao().delete(f);
			}else{
				NormalFile nf = (NormalFile)f;
				File rawFile = new File(nf.getFileUrl());
				if(rawFile.exists()){
					if(rawFile.delete()){
						this.getEntityDao().delete(f);
					}
				}else{
					this.getEntityDao().delete(f);
				}
			}
		}
	}
}
