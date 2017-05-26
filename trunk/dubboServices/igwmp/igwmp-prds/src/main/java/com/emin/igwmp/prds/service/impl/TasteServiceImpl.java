package com.emin.igwmp.prds.service.impl;

 
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.dao.PreFilter;
import com.emin.base.service.Condition;
import com.emin.base.service.Conditions;
import com.emin.base.service.UndeleteableServiceImpl;
import com.emin.igwmp.prds.domain.Taste;
import com.emin.igwmp.prds.service.TasteService;
@Service("tasteService")
public class TasteServiceImpl extends UndeleteableServiceImpl<Taste> implements TasteService {

	@Override
	public PagedResult<Taste> loadPagedTastesByCondition(PageRequest pageRequest, List<Condition> conditions) {
		List<PreFilter> filterList = new ArrayList<PreFilter>();
		filterList.add(getStatusFilter());
		if (conditions != null) {
			for (Condition condition : conditions) {

				PreFilter filter = Conditions.convertToPropertyFilter(condition);
				filterList.add(filter);
			}
		}
		PreFilter[] filters = new PreFilter[filterList.size()];
		filters = filterList.toArray(filters);
		return this.getPage(pageRequest, filters);
	}

	@Override
	public List<Taste> findTastes(List<Condition> conditions) {
		List<PreFilter> filterList = new ArrayList<PreFilter>();
		filterList.add(getStatusFilter());
		if (conditions != null) {
			for (Condition condition : conditions) {

				PreFilter filter = Conditions.convertToPropertyFilter(condition);
				filterList.add(filter);
			}
		}
		PreFilter[] filters = new PreFilter[filterList.size()];
		filters = filterList.toArray(filters);
		return this.findByPreFilter(filters);
	}
 
}
