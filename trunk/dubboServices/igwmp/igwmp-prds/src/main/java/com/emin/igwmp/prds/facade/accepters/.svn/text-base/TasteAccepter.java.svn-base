package com.emin.igwmp.prds.facade.accepters;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.igwmp.prds.domain.Taste;

public interface TasteAccepter {

	public PagedResult<Taste> loadPagedTastesByCondition(PageRequest pageRequest, List<Condition> conditions)throws EminException;

	public List<Taste> findTastes(List<Condition> conditions)throws EminException;
	
	public Taste getTaste(Long id)throws EminException;
	
	public void saveOrUpdate(Taste taste)throws EminException;
	
	public void deleteTaste(Long id)throws EminException;
	
	public void deleteTaste(Taste taste) throws EminException;
	
}
