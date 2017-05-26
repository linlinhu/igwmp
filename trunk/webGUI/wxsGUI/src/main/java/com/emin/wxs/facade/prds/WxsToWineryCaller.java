package com.emin.wxs.facade.prds;

import java.util.List; 
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.igwmp.prds.domain.Winery;

/**
 *  酒厂
 */
public interface WxsToWineryCaller {
	
	public void saveOrUpdateWinery(Winery winery);
	/**
	 * 获取酒厂列表
	 * @param req
	 * @param conditions
	 * @return
	 */
	 
	public PagedResult<Winery> loadPagedPCWinerysByCondition(PageRequest req,List<Condition> conditions);
	
	void deleteWinery(Long id);
}



