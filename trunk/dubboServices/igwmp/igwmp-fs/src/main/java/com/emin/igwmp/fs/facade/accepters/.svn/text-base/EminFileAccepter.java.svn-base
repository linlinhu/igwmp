/**
 * 
 */
package com.emin.igwmp.fs.facade.accepters;

 
 
public interface EminFileAccepter{

	/**
	 * @param file
	 * @param fileName
	 * @param companyId
	 * @return
	 */
	Long saveFile(byte[] fileContent, String fileName, Long companyId);
	
	public byte[] getFileById(Long id);

	/**
	 * 文件（图片）与实体关联后须调用此方法 不然文件会被定时清除
	 * @param fileIds
	 */
	void useFiles(Long[] fileIds);

	/**
	 * 清除零时文件
	 */
	void cleanTemporaryFiles();
}
