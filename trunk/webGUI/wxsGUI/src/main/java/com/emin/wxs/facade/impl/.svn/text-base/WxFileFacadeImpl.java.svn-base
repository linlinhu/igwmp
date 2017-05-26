package com.emin.wxs.facade.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.wxs.domain.WxFile;
import com.emin.wxs.facade.WxFileFacade;
import com.emin.wxs.service.WeixinFileService;

import net.sf.json.JSONArray;
@Component("wxFileFacade")
public class WxFileFacadeImpl implements WxFileFacade{

	@Reference(version="0.0.1")
	private WeixinFileService weixinFileService;
	
	@Override
	public void saveFile(WxFile file){
		weixinFileService.saveFile(file);
	}
	@Override
	public void deleteFile(Long id){
		weixinFileService.deleteFile(id);
	}
	@Override
	public JSONArray loadFiles(Long woaId){
		List<WxFile> files = weixinFileService.loadFiles(woaId);
		return JSONArray.fromObject(files);
	}
	@Override
	public WxFile getFile(Long id){
		WxFile file = weixinFileService.findById(id);
		return file;
	}
}
