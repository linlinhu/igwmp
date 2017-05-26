package com.emin.igwmp.prds.service;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.base.service.UndeleteableService; 
import com.emin.igwmp.prds.domain.Winery; 

public interface WineryService extends UndeleteableService<Winery>{
	PagedResult<Winery> loadPagedWinerysByCondition(PageRequest pageRequest, List<Condition> conditions);

	List<Winery> findTastes(List<Condition> conditions);

}
