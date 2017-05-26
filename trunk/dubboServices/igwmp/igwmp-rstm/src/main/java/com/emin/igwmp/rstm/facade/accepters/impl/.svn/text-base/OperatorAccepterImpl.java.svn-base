package com.emin.igwmp.rstm.facade.accepters.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Service;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.igwmp.rstm.domain.RestaurantAdminInfo;
import com.emin.igwmp.rstm.domain.RestaurantPublicInfo;
import com.emin.igwmp.rstm.facade.accepters.OperatorAccepter;
import com.emin.igwmp.rstm.service.RestaurantAdminService;
import com.emin.igwmp.rstm.service.RstmBaseService;


/**
 * 调用运营者相关信息
 * @author zhaoqt
 *
 */
@Service(version="0.0.1")
@Component("operatorAccepter")
public class OperatorAccepterImpl implements OperatorAccepter{

	@Resource
	RestaurantAdminService restaurantAdminService;
	@Resource
	RstmBaseService<RestaurantPublicInfo> rstmBaseService;
	
	/**
     * 根据条件分页查询服务员列表
     * @param pageRequest
     * @param conditions
     * @return
     */
	public PagedResult<RestaurantAdminInfo> queryOperatorByCondition(PageRequest pageRequest , List<Condition> conditions) {
		return restaurantAdminService.loadPagedRestaurantAdminInfoByCondition(pageRequest, conditions);
	}

	/**
	 * 保存运营者信息
	 * @param RestaurantAdminInfo
	 */
	public void saveOperatorInfo(RestaurantAdminInfo restaurantAdminInfo) {
		restaurantAdminService.saveOrUpdate(restaurantAdminInfo);
	}
	
	 /**
     * 根据ID删除服务员信息表
     *
     * @param id
     */
    public void removeOperatorInfo(Long id) {
    	restaurantAdminService.deleteById(id);
	}

	@Override
	public RestaurantAdminInfo queryOperatorById(Long id) {
		
		return restaurantAdminService.findById(id);
	}

	@Override
	public List<RestaurantAdminInfo> queryRestaurantAdminForListByCondition(
			List<Condition> conditions) {
		return restaurantAdminService.findByPreFilter(rstmBaseService.transferConditionsToPreFilter(conditions));
	}

	@Override
	public RestaurantAdminInfo queryRestaurantAdminObjByCondition(
			List<Condition> conditions) {
	
		return restaurantAdminService.findUniqueByPreFilter(rstmBaseService.transferConditionsToPreFilter(conditions));
	}

}
