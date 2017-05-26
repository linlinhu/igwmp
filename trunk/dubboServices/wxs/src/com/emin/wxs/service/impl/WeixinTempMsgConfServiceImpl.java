package com.emin.wxs.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.emin.base.dao.PreFilters;
import com.emin.base.exception.BaseExCode;
import com.emin.base.exception.EminException;
import com.emin.base.service.UndeleteableServiceImpl;
import com.emin.base.util.CommonsUtil;
import com.emin.wxs.domain.WeixinTempMsgConf;
import com.emin.wxs.domain.WeixinTempMsgConfItem;
import com.emin.wxs.service.WeixinTempMsgConfService;
@Service("weixinTempMsgConfService")
public class WeixinTempMsgConfServiceImpl extends UndeleteableServiceImpl<WeixinTempMsgConf> implements WeixinTempMsgConfService{
	
	@Override	
	public void saveTempMsgConf(WeixinTempMsgConf conf){
		this.beforeSaveTempMsgConf(conf);
		if(conf.getId()!=null){
			this.getEntityDao().deleteByPrefilters(WeixinTempMsgConfItem.class, PreFilters.eq(WeixinTempMsgConfItem.PROP_CONFID, conf.getId()));
		}
		super.saveOrUpdate(conf);
		
		
	}
	private void beforeSaveTempMsgConf(WeixinTempMsgConf conf){
		if(conf.getName()==null
				||conf.getItems()==null
				||conf.getItems().size()==0
				||conf.getTemplateId()==null
				){
			throw new EminException(BaseExCode.BIZ_ILLEGAL_PARAMETER);
		}
		if(!CommonsUtil.isNotEmpty(conf.getTopcolor())){
			conf.setTopcolor("#FFFFFF");
		}
		conf.setStatus(WeixinTempMsgConf.STATUS_VALID);
	}
	@Override
	public List<WeixinTempMsgConf> loadConf(Long woaId){
		return this.findByPreFilter(getStatusFilter(),PreFilters.eq("woaId", woaId));
	}
}
