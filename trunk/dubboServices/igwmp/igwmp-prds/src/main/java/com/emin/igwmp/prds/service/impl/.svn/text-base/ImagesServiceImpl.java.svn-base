package com.emin.igwmp.prds.service.impl;

  
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.dao.PreFilter;
import com.emin.base.dao.PreFilters;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.base.service.Conditions;
import com.emin.base.service.UndeleteableServiceImpl;
import com.emin.igwmp.prds.domain.Images;
import com.emin.igwmp.prds.service.ImagesService;
@Service("imagesService")
public class ImagesServiceImpl extends UndeleteableServiceImpl<Images> implements ImagesService {

	@Override
	public void deleteImages(Long productId) throws EminException {

		List<Images> list= this.findByPreFilter(PreFilters.eq(Images.PROP_RELATION_ID, productId));
		for (Images images : list) {
			this.delete(images);
		}
	}

	@Override
	public List<Images> findImages(Long productId, Integer type) throws EminException {
		PreFilter relFilter= PreFilters.eq(Images.PROP_RELATION_ID, productId);
		PreFilter typeFilter=  null;
		if(type>0){
			 typeFilter=  PreFilters.eq(Images.PROP_SCOPE, type);
		}
		PreFilter statusFilter = getStatusFilter();
		return this.findByPreFilter(relFilter,typeFilter,statusFilter);
	}

	@Override
	public PagedResult<Images> loadPagedImagesByCondition(PageRequest pageRequest, List<Condition> conditions) {
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
	public List<Images> findImages(List<Condition> conditions) {
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
