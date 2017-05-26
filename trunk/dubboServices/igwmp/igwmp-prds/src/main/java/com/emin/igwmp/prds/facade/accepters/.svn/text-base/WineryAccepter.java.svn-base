package com.emin.igwmp.prds.facade.accepters;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition; 
import com.emin.igwmp.prds.domain.Winery;

public interface WineryAccepter {
	
	public PagedResult<Winery> loadPagedWinerysByCondition(PageRequest pageRequest, List<Condition> conditions)throws EminException;

	public List<Winery> findTastes(List<Condition> conditions)throws EminException;
	
	public Winery getWinery(Long id)throws EminException;
	
	public void saveOrUpdate(Winery winery)throws EminException;
	
	public void deleteWinery(Long id)throws EminException;
	
	public void deleteWinery(Winery winery) throws EminException;
}
