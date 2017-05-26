package com.emin.platform.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.dao.PreFilter;
import com.emin.base.dao.PreFilters;
import com.emin.base.dao.SortKey;
import com.emin.base.exception.BaseExCode;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.base.service.Conditions;
import com.emin.base.service.UndeleteableServiceImpl;
import com.emin.base.util.CommonsUtil;
import com.emin.platform.domain.Company;
import com.emin.platform.domain.Person;
import com.emin.platform.domain.Position;
import com.emin.platform.service.PositionService;

@Service("positionService")
public class PositionServiceImpl extends UndeleteableServiceImpl<Position> implements
		PositionService {

	@Override
	public List<Position> getAllPositions() {
		return this.findAll(new SortKey[] { SortKey.DEFAULT });
	}

	@Override
	public void deletePosition(Position position) {
		this.getEntityDao().execSQL(
				"delete from platform.percompany_position where position_id=" + position.getId());
		this.delete(position);

	}

	public List<Position> loadByCompanyId(Long companyId) {
		return this.findByPreFilter(PreFilters.eq(Position.PROP_COMPANY_ID, companyId));

	}

	@Override
	public void saveOrUpdatePosition(Position position) {
		this.beforeSaveOrUpdate(position);
		super.saveOrUpdate(position);

	}

	/**
	 * 增加或更新之前对一个组织的校验
	 * 
	 * @author rocky
	 * @param position
	 * @return
	 */

	private void beforeSaveOrUpdate(Position position) {
		if (null == position.getName() || null == position.getCompanyId()
				|| null == position.getType()) {
			throw new EminException(BaseExCode.BIZ_ILLEGAL_PARAMETER);
		}
		// 判断name重复
		Position p = findByName(position.getName(), position.getId(), position.getCompanyId());
		if (p != null && p.getStatus() == Position.STATUS_VALID) {
			throw new EminException(BaseExCode.BIZ_NAME_EXISTS, position.getName());
		}
	}

	/**
	 * 按照查询条件查询职位
	 * 
	 * @author rocky
	 * @param name
	 * @param id
	 * @param companyId
	 * @return position
	 */
	public Position findByName(String name, Long id, Long companyId) {
		PreFilter nameFilter = PreFilters.eq(Position.PROP_NAME, name);
		PreFilter companyIdFilter = PreFilters.eq(Position.PROP_COMPANY_ID, companyId);

		if (null == id) {
			return this.findUniqueByPreFilter(nameFilter, companyIdFilter);
		} else {
			PreFilter notIdFilter = PreFilters.notEq(Position.PROP_ID, id);
			return this.findUniqueByPreFilter(nameFilter, notIdFilter, companyIdFilter);
		}

	}

	@Override
	public PagedResult<Position> loadPositionByCondition(PageRequest pageRequestData,
			List<Condition> conditions) {
		List<PreFilter> filters = new ArrayList<PreFilter>(conditions.size());
		if (null != conditions && conditions.size() > 0) {
			for (Condition condition : conditions) {
				filters.add(Conditions.convertToPropertyFilter(condition));
			}
		}
		filters.add(PreFilters.eq(Position.PROP_STATUS, Position.STATUS_VALID));
		pageRequestData.setOrderBy(new SortKey[] { SortKey.DEFAULT });
		PreFilter[] preFilters = new PreFilter[filters.size()];
		preFilters = filters.toArray(preFilters);
		return this.getPage(pageRequestData, preFilters);
	}

	@Transactional
	@Override
	public void deleteByCompanyId(Long companyId) {
		List<Position> list = loadByCompanyId(companyId);
		for (Position position : list) {
			this.delete(position);
		}

	}

	@Override
	public void deletePosition(Long positionId, Long companyId) {
		/*PreFilter companyFilter = PreFilters.eq(PerCompany.PROP_COMPANY_ID, companyId);
		PreFilter statusFilter = PreFilters.eq(PerCompany.PROP_STATUS, PerCompany.STATUS_VALID);
		PreFilter companyStatusFilter = PreFilters.eq(PerCompany.PROP_COMPANY_STATUS,
				Company.STATUS_VALID);
		PreFilter personStatusFilter = PreFilters.eq(PerCompany.PROP_PERSON_STATUS,
				Person.STATUS_VALID);
		List<PerCompany> list = this.getEntityDao().findByPreFilter(PerCompany.class,
				companyFilter, statusFilter, companyFilter, personStatusFilter,
				companyStatusFilter, PreFilters.eq(PerCompany.PROP_POSITION_ID, positionId));
		if (null != list && list.size() > 0) {
			throw new EminException(BaseExCode.BIZ_CANNOT_DELETE_NODE_HAS_EFFECTIVE_NODE);
		}

		for (PerCompany perCompany : list) {
			perCompany.getPositions().clear();
			perCompany.setPositions(null);
			this.getEntityDao().update(perCompany);
		}
		this.deleteById(positionId);*/
	}

	@Override
	public void deletePositions(String ids, Long companyId) {
		if (!CommonsUtil.isNotEmpty(ids) || !CommonsUtil.isNotEmpty(companyId)) {
			throw new EminException(BaseExCode.BIZ_ILLEGAL_PARAMETER);
		}
		Long[] positionIds = CommonsUtil.stringToLongArr(ids);
		for (int index = 0; index < positionIds.length; index++) {
			Long positionId = positionIds[index];
			this.deletePosition(positionId, companyId);
		}
	}

}
