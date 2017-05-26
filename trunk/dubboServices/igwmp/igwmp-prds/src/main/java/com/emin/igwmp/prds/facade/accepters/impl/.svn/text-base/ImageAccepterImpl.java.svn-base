package com.emin.igwmp.prds.facade.accepters.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.dao.PreFilter;
import com.emin.base.dao.PreFilters;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.igwmp.prds.domain.Images; 
import com.emin.igwmp.prds.facade.accepters.ImageAccepter;
import com.emin.igwmp.prds.service.ImagesService; 
@Component("imageAccepter")
@Service(version="0.0.1")
public class ImageAccepterImpl implements ImageAccepter {

	@Autowired
	@Qualifier("imagesService")
	private ImagesService imagesService;
	
	@Override
	public PagedResult<Images> loadPagedImagesByCondition(PageRequest pageRequest, List<Condition> conditions)
			throws EminException {
		 
		return imagesService.loadPagedImagesByCondition(pageRequest,conditions);
	}

	@Override
	public List<Images> findImages(List<Condition> conditions) throws EminException {
	 
		return imagesService.findImages(conditions);
	}

	@Override
	public Images getImages(Long id) throws EminException {
		 
		return imagesService.findUniqueByPreFilter(PreFilters.eq(Images.PROP_ID, id));
	}

	@Override
	public void saveOrUpdate(Images images) throws EminException {
		imagesService.saveOrUpdate(images);
		
	}

	@Override
	public void deleteImages(Long id) throws EminException {
		imagesService.deleteById(id);
		
	}

	@Override
	public void deleteImages(Images images) throws EminException {
		imagesService.delete(images);
		
	}

	@Override
	public List<Images> findImages(Long productId, Integer type) throws EminException {
		PreFilter relationFilter = PreFilters.eq(Images.PROP_RELATION_ID, productId);
		PreFilter typeFilter = PreFilters.eq(Images.PROP_SCOPE, type);
		PreFilter statusFilter  = imagesService.getStatusFilter();
		return imagesService.findByPreFilter(relationFilter,typeFilter,statusFilter);
	}

	
	 

}
