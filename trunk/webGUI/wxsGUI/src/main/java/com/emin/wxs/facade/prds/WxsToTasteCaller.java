package com.emin.wxs.facade.prds;

import java.util.List; 
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.igwmp.prds.domain.Taste;
 
/**
 *	品酒
 * 
 **/
public interface WxsToTasteCaller {
	
	public void saveOrUpdateTaste(Taste taste);
	/**
	 * 获取酒评列表
	 * @param req
	 * @param conditions
	 * @return
	 */
	 
	PagedResult<Taste> loadPagedTastesByCondition(PageRequest req, List<Condition> conditions);
	
	/**
	 * 
	 * 删除
	 */
	void deleteTaste(Long id);
}



