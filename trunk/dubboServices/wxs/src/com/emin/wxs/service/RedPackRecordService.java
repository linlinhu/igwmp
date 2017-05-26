package com.emin.wxs.service;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.base.service.UndeleteableService;
import com.emin.wxs.domain.RedPackRecord;

public interface RedPackRecordService extends UndeleteableService<RedPackRecord>{

	PagedResult<RedPackRecord> loadRecordsByPage(PageRequest pageRequest, List<Condition> conditions);

}
