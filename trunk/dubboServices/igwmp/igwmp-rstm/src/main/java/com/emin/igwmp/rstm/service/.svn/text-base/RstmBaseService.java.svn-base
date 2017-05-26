package com.emin.igwmp.rstm.service;

import java.util.List;

import com.emin.base.dao.PreFilter;
import com.emin.base.domain.UndeleteableEntity;
import com.emin.base.service.Condition;
import com.emin.base.service.UndeleteableService;

public interface RstmBaseService<T extends UndeleteableEntity> extends UndeleteableService<T> {
   PreFilter transferConditionsToPreFilter(List<Condition> conditions);
}
