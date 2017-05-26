package com.emin.wxs.facade.fs;

public interface WxsToFileCaller {
	/**
	 * 添加文件 返回文件路径
	 * @return
	 */
	public Long uploadFile(byte[] fileContent, String fileName);
	
	
	public byte[] getFileById(Long id);

 
}
