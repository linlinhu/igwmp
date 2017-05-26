package com.emin.igwmp.prds.facade.accepters.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.igwmp.prds.domain.Winery;
import com.emin.igwmp.prds.facade.accepters.WineryAccepter;
import com.emin.igwmp.prds.service.WineryService; 
@Component("wineryAccepter")
@Service(version="0.0.1")
public class WineryAccepterImpl implements WineryAccepter {


	@Autowired
	@Qualifier("wineryService")
	private WineryService wineryService;
	
	@Override
	public PagedResult<Winery> loadPagedWinerysByCondition(PageRequest pageRequest, List<Condition> conditions)
			throws EminException {

		return wineryService.loadPagedWinerysByCondition(pageRequest, conditions);
	}

	@Override
	public List<Winery> findTastes(List<Condition> conditions) throws EminException {
		 
		return wineryService.findTastes(conditions);
	}

	@Override
	public Winery getWinery(Long id) throws EminException {
		 
		return wineryService.findById(id);
	}

	@Override
	public void saveOrUpdate(Winery winery) throws EminException {
		if(null==winery.getId()||winery.getId()<=0){
			winery.setCreateTime(System.currentTimeMillis());
			winery.setId(null);
		}
		winery.setLastModifyTime(System.currentTimeMillis());
		winery.setStatus(Winery.STATUS_VALID); 
		wineryService.saveOrUpdate(winery);		
	}

	@Override
	public void deleteWinery(Long id) throws EminException {
		wineryService.deleteById(id);		
	}

	@Override
	public void deleteWinery(Winery winery) throws EminException {
		wineryService.delete(winery);		
	}

}
