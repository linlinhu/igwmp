package com.emin.wxs.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.dao.PreFilter;
import com.emin.base.service.Condition;
import com.emin.base.service.Conditions;
import com.emin.base.service.UndeleteableServiceImpl;
import com.emin.wxs.domain.RedPackRecord;
import com.emin.wxs.service.RedPackRecordService;
@Service("redPackRecordService")
public class RedPackRecordServiceImpl extends UndeleteableServiceImpl<RedPackRecord> implements RedPackRecordService {

	@Override
	public PagedResult<RedPackRecord> loadRecordsByPage(PageRequest pageRequest,List<Condition> conditions){
		List<PreFilter> filters = new ArrayList<PreFilter>();
		if(conditions!=null && conditions.size()>0){
			for (Condition condition : conditions) {
				filters.add(Conditions.convertToPropertyFilter(condition));
			}
		}
		return this.getPage(pageRequest, filters.toArray(new PreFilter[filters.size()]));
	}
}
