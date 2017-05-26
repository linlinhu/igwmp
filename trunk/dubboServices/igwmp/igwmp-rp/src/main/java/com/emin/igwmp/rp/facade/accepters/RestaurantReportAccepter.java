package com.emin.igwmp.rp.facade.accepters;

import com.emin.base.exception.EminException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface RestaurantReportAccepter {
	
	/**
	 * 饭店销售排行榜
	 * @param beginDate 开始时间
	 * @param endDate 结束时间  
	 * @param regionId 区域 可以不传或者传 0
	 * @return
	 * @throws EminException
	 */
	public JSONArray findSaleRandRanking(Long beginDate,Long endDate,Long regionId) throws EminException;

	/**
	 * 酒品销售统计 
	 * @param beginDate 开始时间
	 * @param endDate 结束时间
	 * @param restaurantId 饭店id
	 * @return [{amount:80,wineName:'习酒'},{amount:80,wineName:'习酒'}]
	 * @throws EminException
	 */
	public JSONArray findWineSaleStatistics(Long beginDate,Long endDate,Long restaurantId) throws EminException;

	/**
	 * 获取饭店信息
	 * @param beginDate 开始时间
	 * @param endDate 结束时间
	 * @param restaurantId 饭店id
	 * @throws EminException
	 * @return {amount:12,restaurantId:12,address:'就是这个地方',name:'饭店名称',latitude:15.25,longitude:222.55}
	 */
	public JSONObject findRestaurantInfo(Long beginDate,Long endDate,Long restaurantId)throws EminException;

}
