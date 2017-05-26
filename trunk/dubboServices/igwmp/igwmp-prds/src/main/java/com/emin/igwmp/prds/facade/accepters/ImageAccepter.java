package com.emin.igwmp.prds.facade.accepters;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition; 
import com.emin.igwmp.prds.domain.Images;

public interface ImageAccepter {
	
	public PagedResult<Images> loadPagedImagesByCondition(PageRequest pageRequest, List<Condition> conditions)throws EminException;

	public List<Images> findImages(List<Condition> conditions)throws EminException;
	
	public Images getImages(Long id)throws EminException;
	
	public void saveOrUpdate(Images images)throws EminException;
	
	public void deleteImages(Long id)throws EminException;
	
	public void deleteImages(Images images) throws EminException;
 

	public List<Images> findImages(Long productId, Integer type)throws EminException;
}
