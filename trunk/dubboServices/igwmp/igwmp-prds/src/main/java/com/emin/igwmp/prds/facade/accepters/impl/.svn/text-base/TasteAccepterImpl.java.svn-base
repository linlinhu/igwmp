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
import com.emin.igwmp.prds.domain.Taste;
import com.emin.igwmp.prds.facade.accepters.TasteAccepter;
import com.emin.igwmp.prds.service.TasteService;
import com.emin.igwmp.prds.service.WineTasterService; 
@Component("tasteAccepter")
@Service(version="0.0.1")
public class TasteAccepterImpl implements TasteAccepter {

	
	@Autowired
	@Qualifier("tasteService")
	private TasteService tasteService;
	
	
	@Autowired
	@Qualifier("wineTasterService")
	private WineTasterService wineTasterService;
	
	@Override
	public PagedResult<Taste> loadPagedTastesByCondition(PageRequest pageRequest, List<Condition> conditions)
			throws EminException {

		return tasteService.loadPagedTastesByCondition(pageRequest, conditions);
	}

	@Override
	public List<Taste> findTastes(List<Condition> conditions) throws EminException {
		 
		return tasteService.findTastes(conditions);
	}

	@Override
	public Taste getTaste(Long id) throws EminException {
		 
		return tasteService.findById(id);
	}

	@Override
	public void saveOrUpdate(Taste taste) throws EminException {
		if(null==taste.getId()||taste.getId()<=0){
			taste.setCreateTime(System.currentTimeMillis());
			taste.setId(null);
		}
		taste.setLastModifyTime(System.currentTimeMillis());
		taste.setStatus(Taste.STATUS_VALID);
		tasteService.saveOrUpdate(taste);
		
	}

	@Override
	public void deleteTaste(Long id) throws EminException {
		tasteService.deleteById(id);
		
	}

	@Override
	public void deleteTaste(Taste taste) throws EminException {
		tasteService.delete(taste);
		
	}

}
