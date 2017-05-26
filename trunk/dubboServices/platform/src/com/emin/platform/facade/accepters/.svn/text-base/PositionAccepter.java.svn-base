package com.emin.platform.facade.accepters;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.platform.domain.Position;

public interface PositionAccepter {

	/**
	 * 分页加载职位（角色）
	 * @param PageRequest pageRequest
	 * @param List<Condition> conditions
	 * @return PagedResult<Position>
	 */
	PagedResult<Position> loadPositionsByPage(PageRequest pageRequest, List<Condition> conditions);

	/**
	 * 保存职位（角色）信息
	 * @param position
	 */
	void saveOrUpdatePosition(Position position);

}
