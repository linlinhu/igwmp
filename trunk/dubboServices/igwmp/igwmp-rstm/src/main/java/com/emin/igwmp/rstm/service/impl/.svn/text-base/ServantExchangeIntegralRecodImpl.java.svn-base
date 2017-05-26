package com.emin.igwmp.rstm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.dao.PreFilter;
import com.emin.base.dao.PreFilters;
import com.emin.base.service.Condition;
import com.emin.base.service.Conditions;
import com.emin.base.service.UndeleteableServiceImpl;
import com.emin.base.service.Condition.ConditionOperator;
import com.emin.igwmp.rstm.domain.ServantExchangeIntegralRecord;
import com.emin.igwmp.rstm.service.ServantExchangeIntegralRecodService;

@Service("servantExchangeIntegralRecod")
public class ServantExchangeIntegralRecodImpl extends RstmBaseServiceImpl<ServantExchangeIntegralRecord>
		implements ServantExchangeIntegralRecodService {

	@Override
	public PagedResult<ServantExchangeIntegralRecord> loadPagedExchangeIntegralRecodByCondition(
			PageRequest pageRequest, List<Condition> conditions) {
		return this.getPage(pageRequest,transferConditionsToPreFilter(conditions), getStatusFilter());
	}

}
