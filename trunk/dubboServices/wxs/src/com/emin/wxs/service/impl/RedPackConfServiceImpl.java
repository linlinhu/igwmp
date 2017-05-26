package com.emin.wxs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emin.base.dao.PreFilters;
import com.emin.base.service.CRUDServiceImpl;
import com.emin.wxs.domain.RedPackActivityRecord;
import com.emin.wxs.domain.RedPackConf;
import com.emin.wxs.service.RedPackActivityRecordService;
import com.emin.wxs.service.RedPackConfService;

import net.sf.json.JSONObject;
@Service("redPackConfService")
public class RedPackConfServiceImpl extends CRUDServiceImpl<RedPackConf> implements RedPackConfService {
	
	
	
	@Autowired
	@Qualifier("redPackActivityRecordService")
	private RedPackActivityRecordService redPackActivityRecordService;
	@Override
	public RedPackConf loadRedPackConf(Long woaId){
		return this.findUniqueByPreFilter(PreFilters.eq(RedPackConf.PROP_WOA_ID, woaId));
	}
	
	@Override
	@Transactional
	public void updateConf(RedPackConf redPackConf){
		this.update(redPackConf);
		String code = redPackConf.getActCode();
		RedPackActivityRecord ar = redPackActivityRecordService.findUniqueByPreFilter(PreFilters.eq("code", code),PreFilters.eq("woaId", redPackConf.getWoaId()));
		if(ar!=null){
			if(!redPackConf.getEnable()){
				ar.setEndTime(System.currentTimeMillis());
			}else{
				this.getEntityDao().updateByHQL("update RedPackActivityRecord set endTime = ?  where endTime is null and woaid = ?",System.currentTimeMillis(),redPackConf.getWoaId());
				ar.setEndTime(null);
			}
			ar.setMaxCount(redPackConf.getMaxRedPack());
		}else{
			if(redPackConf.getEnable()){
				this.getEntityDao().updateByHQL("update RedPackActivityRecord set endTime = ?  where endTime is null and woaid = ?",System.currentTimeMillis(),redPackConf.getWoaId());
				ar = new RedPackActivityRecord();
				ar.setCode(code);
				ar.setMaxCount(redPackConf.getMaxRedPack());
				ar.setStartTime(System.currentTimeMillis());
			}
		}
		redPackActivityRecordService.saveOrUpdate(ar);
	}
	
}
