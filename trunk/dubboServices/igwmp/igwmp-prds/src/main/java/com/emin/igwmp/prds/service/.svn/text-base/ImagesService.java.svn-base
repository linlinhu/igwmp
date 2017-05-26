package com.emin.igwmp.prds.service;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.base.service.UndeleteableService;
import com.emin.igwmp.prds.domain.Images;

public interface ImagesService extends UndeleteableService<Images> {

	public void deleteImages(Long productId)throws EminException;

	public List<Images> findImages(Long productId, Integer type)throws EminException;

	public PagedResult<Images> loadPagedImagesByCondition(PageRequest pageRequest, List<Condition> conditions);

	public List<Images> findImages(List<Condition> conditions);

}
