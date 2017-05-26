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
import com.emin.igwmp.prds.domain.WineTaster;
import com.emin.igwmp.prds.domain.Winery;
import com.emin.igwmp.prds.facade.accepters.WineTasterAccepter;
import com.emin.igwmp.prds.service.WineTasterService; 
@Component("wineTasterAccepter")
@Service(version="0.0.1")
public class WineTasterAccepterImpl implements WineTasterAccepter {

	@Autowired
	@Qualifier("wineTasterService")
	private WineTasterService wineTasterService;
	
	
	@Override
	public PagedResult<WineTaster> loadPagedWineTastersByCondition(PageRequest pageRequest, List<Condition> conditions)
			throws EminException { 
		return wineTasterService.loadPagedWineTastersByCondition(pageRequest,conditions);
	}

	@Override
	public List<WineTaster> findWineTaster(List<Condition> conditions) throws EminException {
		 
		return wineTasterService.findWineTasters(conditions);
	}

	@Override
	public WineTaster getWineTaster(Long id) throws EminException {
		 
		return wineTasterService.findById(id);
	}

	@Override
	public void saveOrUpdate(WineTaster wineTaster) throws EminException {
		if(null==wineTaster.getId()||wineTaster.getId()<=0){
			wineTaster.setCreateTime(System.currentTimeMillis());
			wineTaster.setId(null);
		}
		wineTaster.setLastModifyTime(System.currentTimeMillis());
		wineTaster.setStatus(Winery.STATUS_VALID);  
		wineTasterService.saveOrUpdate(wineTaster);
		
	}

	@Override
	public void deleteWineTaster(Long id) throws EminException {
		wineTasterService.deleteById(id); 		
	}

	@Override
	public void deleteWineTaster(WineTaster wineTaster) throws EminException {
		wineTasterService.delete(wineTaster);
		
	}

}
