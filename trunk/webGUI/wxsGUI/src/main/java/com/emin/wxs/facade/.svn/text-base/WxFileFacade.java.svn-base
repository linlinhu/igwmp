package com.emin.wxs.facade;

import com.emin.wxs.domain.WxFile;

import net.sf.json.JSONArray;

public interface WxFileFacade {

	/**
	 * 保存文件
	 * @param file
	 */
	void saveFile(WxFile file);

	/**
	 * 删除文件
	 * @param id
	 */
	void deleteFile(Long id);
	/**
	 * 加载文件列表
	 * @param woaId
	 * @return
	 */
	JSONArray loadFiles(Long woaId);

	WxFile getFile(Long id);

}
