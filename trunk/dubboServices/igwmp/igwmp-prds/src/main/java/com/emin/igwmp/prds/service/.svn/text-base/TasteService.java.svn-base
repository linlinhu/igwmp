package com.emin.igwmp.prds.service;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.base.service.UndeleteableService;
import com.emin.igwmp.prds.domain.Taste; 

public interface TasteService extends UndeleteableService<Taste> {
	PagedResult<Taste> loadPagedTastesByCondition(PageRequest pageRequest, List<Condition> conditions);

	List<Taste> findTastes(List<Condition> conditions);
}
