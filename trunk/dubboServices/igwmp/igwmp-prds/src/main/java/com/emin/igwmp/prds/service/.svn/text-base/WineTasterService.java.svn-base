package com.emin.igwmp.prds.service;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.base.service.UndeleteableService;
import com.emin.igwmp.prds.domain.WineTaster;

public interface WineTasterService extends UndeleteableService<WineTaster> {

	PagedResult<WineTaster> loadPagedWineTastersByCondition(PageRequest pageRequest, List<Condition> conditions);

	List<WineTaster> findWineTasters(List<Condition> conditions);

}
