package com.emin.platform.facade.accepters.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.platform.domain.Position;
import com.emin.platform.facade.accepters.PositionAccepter;
import com.emin.platform.service.PositionService;
@Component("positionAccepter")
@Service(version="0.0.1")
public class PositionAccepterImpl implements PositionAccepter{

	@Autowired
	@Qualifier("positionService")
	private PositionService positionService;
	
	@Override
	public void saveOrUpdatePosition(Position position){
		positionService.saveOrUpdatePosition(position);
	}
	
	@Override
	public PagedResult<Position> loadPositionsByPage(PageRequest pageRequest,List<Condition> conditions){
		return positionService.loadPositionByCondition(pageRequest, conditions);
	}
	
	
}
