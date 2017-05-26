/**
 * 
 */
package com.emin.igwmp.fs.facade.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.emin.base.exception.EminException;
import com.emin.base.util.EminEnvUtil;
import com.emin.igwmp.fs.domain.NormalFile;
import com.emin.igwmp.fs.exception.FileExceptionCode;
import com.emin.igwmp.fs.facade.service.NormalFileService;
import com.emin.igwmp.fs.util.FileUtil; 

/**
 * @author jim
 *
 */
@Service("normalFileService")
public class NormalFileServiceImpl extends EminFileServiceImpl<NormalFile> implements NormalFileService{
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	@Override
	public Long saveFile(byte[] fileContent,String fileName,Long companyId){		
		if(fileContent==null || fileContent.length==0 || StringUtils.isBlank(fileName) || companyId==null){
			throw new EminException(FileExceptionCode.F_PARAMTERS_INVALID);
		}
		StringBuffer path = new StringBuffer();
		path.append(System.getenv(EminEnvUtil.EMIN_HOME)).append(File.separator)
		.append(companyId).append(File.separator).append(sdf.format(new Date()));
		File folder = new File(path.toString());
		if(!folder.exists()){
			folder.mkdirs();
		}
		String suffix = FileUtil.getExtension(fileName);
		path.append(File.separator).append(System.currentTimeMillis()).append(suffix);
		File destFile = new File(path.toString());
		File file = new File(FileUtil.getFile(fileContent, fileName));
		if(!file.renameTo(destFile)){
			throw new EminException(FileExceptionCode.F_SAVE_ERROR);
		}
		else{
			NormalFile nf = new NormalFile();
			nf.setFileSize(destFile.length());
			nf.setFileUrl(destFile.getAbsolutePath());
			nf.setSuffix(suffix);
			nf.setIsTemporary(true);
			nf.setUploadTime(System.currentTimeMillis());
			nf.setCompanyId(companyId);
			this.save(nf);
			return nf.getId();
		}
		
	}
	@Override
	public byte[] getFileById(Long id) {
		NormalFile nf = this.findById(id);
		File f = new File(nf.getFileUrl());
		return FileUtil.getBytes(f);
	}
	
	/*public static void main(String[] args) {
		File f = new File("D:/workbook.xls");
		NormalFileServiceImpl nfs = new NormalFileServiceImpl();
		nfs.saveFile(f, "workbook.xls", 1l);
	}*/
}
