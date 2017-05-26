package com.emin.wxs.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;

import com.emin.base.dao.PreFilter;
import com.emin.base.dao.PreFilters;
import com.emin.base.exception.BaseExCode;
import com.emin.base.exception.EminException;
import com.emin.base.service.CRUDServiceImpl;
import com.emin.base.util.CommonsUtil;
import com.emin.wxs.domain.WxFile;
import com.emin.wxs.service.WeixinFileService;
@Service("weixinFileService")
public class WeixinFileServiceImpl extends CRUDServiceImpl<WxFile> implements WeixinFileService{
	@Override
	public void saveFile(WxFile file){
		this.beforSave(file);
		super.save(file);
	}
	private void beforSave(WxFile file){
		if(!CommonsUtil.isNotEmpty(file.getName())				
				||file.getSize()==null				
				||file.getType()==null
				||file.getPath()==null
				||file.getWoaId()==null
				){
			throw new EminException(BaseExCode.BIZ_ILLEGAL_PARAMETER);
		}
		file.setCreateTime(System.currentTimeMillis());
		file.setLastModifyTime(System.currentTimeMillis());
		file.setStatus(1);
	}
	@Override
	public void deleteFile(Long id){
		WxFile wf = this.findById(id);
		File f = new File(wf.getPath());
		super.deleteById(id);
		f.delete();
	}
	@Override
	public List<WxFile> loadFiles(Long woaId){
		return this.findByPreFilter(PreFilters.eq(WxFile.PROP_WOAID, woaId));
	}
}
