package com.emin.igwmp.rp.facade.accepters;

 
import com.emin.base.exception.EminException;

import net.sf.json.JSONArray;

public interface ServantReportAccepter {
	/**
	 * 服务员销售统计
	 * @param bossId
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @throws EminException
	 */
	public JSONArray findSaleStatistics(Long bossId,Long beginDate,Long endDate)throws EminException;

	/**
	 * 服务员取酒排行榜
	 * @return [{name:'服务员名称',url:'图片路径',restaurantName:'饭店名称',amount:50,integral:500}]
	 * @throws EminException
	 */
	public JSONArray findTakeWineRanking()throws EminException;
	
	/**
	 * 服务员取酒列表
	 * @param fansId 粉丝id
	 * @param beginDate 查询开始时间
	 * @param endDate 结束时间
	 * @return [{time:1490950052276,wineName:'习酒',quantity:20,amount:120000,integral:500}]
	 * @throws EminException
	 */
	public JSONArray findTakeWineList(Long fansId,Long beginDate,Long endDate)throws EminException;
}
