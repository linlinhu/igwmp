package com.emin.igwmp.rstm.service;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.base.service.UndeleteableService;
import com.emin.igwmp.rstm.domain.ServantVenoutRecord;

public interface ServantVenoutRecordService extends UndeleteableService<ServantVenoutRecord>{
	
	/**
     * 分页加载服务员出酒记录列表
     * @param pageRequest
     * @param match
     * @return
     */
	public PagedResult<ServantVenoutRecord> loadPagedServantVenoutRecordByCondition(PageRequest pageRequest , List<Condition> conditions);
}
