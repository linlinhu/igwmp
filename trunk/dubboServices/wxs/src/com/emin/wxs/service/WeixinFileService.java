package com.emin.wxs.service;

import java.util.List;

import com.emin.base.service.CRUDService;
import com.emin.wxs.domain.WxFile;

public interface WeixinFileService extends CRUDService<WxFile>{

	public void saveFile(WxFile file);

	public void deleteFile(Long id);

	public List<WxFile> loadFiles(Long woaId);

}
