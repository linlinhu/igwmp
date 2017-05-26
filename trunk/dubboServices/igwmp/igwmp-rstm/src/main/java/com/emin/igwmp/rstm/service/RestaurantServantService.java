package com.emin.igwmp.rstm.service;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.base.service.UndeleteableService;
import com.emin.igwmp.rstm.domain.RestaurantAdminInfo;
import com.emin.igwmp.rstm.domain.RestaurantPublicInfo;
import com.emin.igwmp.rstm.domain.RestaurantServantInfo;

/**
 * 服务员管理类
 * @author zhaoqt
 *
 */
public interface RestaurantServantService extends UndeleteableService<RestaurantServantInfo>{
	
	/**
     * 分页加载管理者列表
     * @param pageRequest
     * @param match
     * @return
     */
	public PagedResult<RestaurantServantInfo> loadPagedRestaurantServantInfoByCondition(PageRequest pageRequest , List<Condition> conditions);
}
