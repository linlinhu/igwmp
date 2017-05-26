package com.emin.wxs.facade.fs.impl;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.igwmp.fs.facade.accepters.EminFileAccepter;
import com.emin.wxs.facade.fs.WxsToFileCaller;
@Component("wxsToFileCaller")
public class WxsToFileCallerImpl implements WxsToFileCaller {
	 
	@Reference(version="0.0.1")
	private EminFileAccepter eminFileAccepter;
	
	@Override
	public Long uploadFile(byte[] fileContent, String fileName) {
		
		Long id = eminFileAccepter.saveFile(fileContent, fileName, 0L);
		eminFileAccepter.useFiles(new Long[]{id});
		return id;
	}

	@Override
	public byte[] getFileById(Long id) {

		return eminFileAccepter.getFileById(id);
	}

	 

}
