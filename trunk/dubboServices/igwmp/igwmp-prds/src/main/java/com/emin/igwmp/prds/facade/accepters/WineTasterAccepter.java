package com.emin.igwmp.prds.facade.accepters;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.igwmp.prds.domain.WineTaster; 

public interface WineTasterAccepter {
	public PagedResult<WineTaster> loadPagedWineTastersByCondition(PageRequest pageRequest, List<Condition> conditions)throws EminException;

	public List<WineTaster> findWineTaster(List<Condition> conditions)throws EminException;
	
	public WineTaster getWineTaster(Long id)throws EminException;
	
	public void saveOrUpdate(WineTaster wineTaster)throws EminException;
	
	public void deleteWineTaster(Long id)throws EminException;
	
	public void deleteWineTaster(WineTaster wineTaster) throws EminException;
 
}
