package com.emin.wxs.facade.prds;

import java.util.List; 
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.igwmp.prds.domain.WineTaster;
 
/**
 *	品酒大师
 * 
 **/
public interface WxsToTasterCaller {
	
	public void saveOrUpdateTaster(WineTaster taster);
	/**
	 * 获取酒评列表
	 * @param req
	 * @param conditions
	 * @return
	 */
	 
	PagedResult<WineTaster> loadPagedTastersByCondition(PageRequest req, List<Condition> conditions);
	
	/**
	 * 
	 * 删除
	 */
	void deleteTaster(Long id);
}



