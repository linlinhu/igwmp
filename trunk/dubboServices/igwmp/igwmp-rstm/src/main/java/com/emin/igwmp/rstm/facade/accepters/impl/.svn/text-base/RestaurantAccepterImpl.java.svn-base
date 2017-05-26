package com.emin.igwmp.rstm.facade.accepters.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Service;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult; 
import com.emin.base.service.Condition; 
import com.emin.igwmp.rstm.domain.RestaurantPublicInfo;
import com.emin.igwmp.rstm.facade.accepters.RestaurantAccepter;
import com.emin.igwmp.rstm.service.RestaurantPublicInfoService;
import com.emin.igwmp.rstm.service.RstmBaseService;


/**
 * 调用饭店相关信息
 * @author zhaoqt
 *
 */
@Service(version="0.0.1")
@Component("restaurantAccepter")
public class RestaurantAccepterImpl implements RestaurantAccepter{

	@Resource
	RestaurantPublicInfoService restaurantPublicInfoService;
	@Resource
	RstmBaseService<RestaurantPublicInfo> rstmBaseService;
//    /**
//     * 根据关键字分页查询饭店列表
//     * @param pageRequest
//     * @param match
//     * @return
//     */
//	public PagedResult<RestaurantPublicInfo> queryRestaurantPublicInfoByMatch(PageRequest pageRequest , String... match);
	
	/**
     * 根据条件分页查询饭店列表
     * @param pageRequest
     * @param conditions
     * @return
     */
	public PagedResult<RestaurantPublicInfo> queryRestaurantPublicInfoByCondition(PageRequest pageRequest , List<Condition> conditions) {
		return restaurantPublicInfoService.findPagedRestaurantPublicInfoByCondition(pageRequest, conditions);
	}

	/**
	 * 保存饭店信息
	 * @param id
	 */
	public Long saveRestaurantInfo(RestaurantPublicInfo restaurantPublicInfo) {
		restaurantPublicInfoService.saveOrUpdate(restaurantPublicInfo);
		return restaurantPublicInfo.getId();
	}
	
	 /**
     * 根据ID删除饭店基本信息表
     *
     * @param id
     */
    public void removeRestaurantInfo(Long id) {
    	restaurantPublicInfoService.deleteById(id);
	}

	@Override
	public RestaurantPublicInfo queryRestaurantPublicInfoById(Long id) {
		
		return restaurantPublicInfoService.findById(id);
	}

	@Override
	public List<RestaurantPublicInfo> queryRestaurantPublicInfoForListByCondition(
			List<Condition> conditions) {

		return 	restaurantPublicInfoService.findByPreFilter(rstmBaseService.transferConditionsToPreFilter(conditions));
	}

	@Override
	public RestaurantPublicInfo queryRestaurantPublicInfoObjByCondition(
			List<Condition> conditions) {
		
		return restaurantPublicInfoService.findUniqueByPreFilter(rstmBaseService.transferConditionsToPreFilter(conditions));
	}

}
