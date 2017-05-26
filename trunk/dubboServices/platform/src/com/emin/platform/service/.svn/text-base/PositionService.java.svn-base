package com.emin.platform.service;


import java.util.List;



import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.base.service.UndeleteableService;
import com.emin.platform.domain.Position;
public interface PositionService extends UndeleteableService<Position>{
	List<Position> loadByCompanyId(Long companyId);

	List<Position> getAllPositions();

	public void saveOrUpdatePosition(Position position);


	public PagedResult<Position> loadPositionByCondition(
			PageRequest pageRequestData, List<Condition> conditions) ;

	public void deletePosition(Position position);


	public void deleteByCompanyId(Long companyId);

	void deletePosition(Long id, Long companyId);

	/**
	 * 批量删除职位
	 * @author Johnny.xiang
	 * @Since  Sep 1, 2015
	 * @param ids
	 * @param companyId
	 */
	void deletePositions(String ids, Long companyId);

}


