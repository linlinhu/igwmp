package com.emin.igwmp.rstm.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.emin.base.dao.PreFilter;
import com.emin.base.dao.PreFilters;
import com.emin.base.domain.UndeleteableEntity;
import com.emin.base.service.Condition;
import com.emin.base.service.Conditions;
import com.emin.base.service.UndeleteableServiceImpl;
import com.emin.base.service.Condition.ConditionOperator;
import com.emin.igwmp.rstm.service.RstmBaseService;

@Service("rstmBaseService")
public class RstmBaseServiceImpl<T extends UndeleteableEntity> extends UndeleteableServiceImpl<T> implements RstmBaseService<T>{
    
	@Override
	public PreFilter transferConditionsToPreFilter(List<Condition> conditions) {
		PreFilter conditionFilter = null;
		PreFilter likeFilter = null;
		if (conditions != null) {
			for (Condition condition : conditions) {
				//分离模糊匹配和其他匹配
				if(condition.getOperator() == ConditionOperator.LIKE)
				{				
					int size = condition.getValues().length;
					Object[] likeValues = condition.getValues();
					if(size>0){
						for(Object likeValue:likeValues){
							likeFilter = PreFilters.or(likeFilter, PreFilters.like(condition.getPropertyName(), likeValue+""));
						}
					}				
				}
				else{
					conditionFilter = PreFilters.and(conditionFilter, Conditions.convertToPropertyFilter(condition));
				}	
			}
		}
		PreFilter finalFilter = PreFilters.and(likeFilter, conditionFilter);
		return finalFilter;
	}
}
