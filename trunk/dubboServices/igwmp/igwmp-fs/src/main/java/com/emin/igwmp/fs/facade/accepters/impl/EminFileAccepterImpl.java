package com.emin.igwmp.fs.facade.accepters.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.emin.igwmp.fs.facade.accepters.EminFileAccepter; 
import com.emin.igwmp.fs.facade.service.NormalFileService;
@Component("eminFileAccepter")
@Service(version="0.0.1")
public class EminFileAccepterImpl implements EminFileAccepter {
	
	
	@Autowired
	@Qualifier("normalFileService")
	private NormalFileService normalFileService;
	
	@Override
	public Long saveFile(byte[] fileContent, String fileName, Long companyId) {
		 
		return normalFileService.saveFile(fileContent, fileName, companyId);
	}

	@Override
	public byte[] getFileById(Long id) {

		return normalFileService.getFileById(id);
	}

	@Override
	public void useFiles(Long[] fileIds) {
		normalFileService.useFiles(fileIds);
	}

	@Override
	public void cleanTemporaryFiles() {
		normalFileService.cleanTemporaryFiles();
	}

}
