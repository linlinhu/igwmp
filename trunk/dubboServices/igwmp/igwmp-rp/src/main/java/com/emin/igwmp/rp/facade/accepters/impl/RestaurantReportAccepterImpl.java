package com.emin.igwmp.rp.facade.accepters.impl;
 
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Service;
import com.emin.base.exception.EminException;
import com.emin.igwmp.rp.facade.accepters.RestaurantReportAccepter;
import com.emin.igwmp.rp.facade.service.ReportService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Component("restaurantReportAccepter")
@Service(version="0.0.2")
public class RestaurantReportAccepterImpl implements RestaurantReportAccepter {

	@Autowired
	ReportService reportService;
	
	@Override
	public JSONArray findSaleRandRanking(Long beginDate, Long endDate, Long regionId) throws EminException {

		JSONArray array =new JSONArray();
		
		String sql = "	SELECT re.address address,rd.capacity capacity,re.\"name\" 'name',pic.\"path\" url FROM  "+
		" (   "+
		" 	SELECT SUM(rd.vendout_capacity) capacity,rd.rest_id FROM rstm.servant_venout_record rd WHERE rd.create_time BETWEEN ? and  ? GROUP BY rd.rest_id "+  
		" ) rd 			"+
		" LEFT JOIN rstm.restaurant_public_info re ON rd.rest_id=re.\"id\"  "+
		" LEFT JOIN rstm.restaurant_svc_pic  pic ON  rd.rest_id = pic.master_id  "+
		" ORDER BY rd.capacity DESC ";
		
		List<Map<String, Object>> list = reportService.findDataListBySql(sql, new Object[]{beginDate,endDate});
		if(null==list||list.size()<=0)return null;
		for (Map<String, Object> map : list) {
			JSONObject item = new JSONObject();
			item.put("url", map.get("url"));
			item.put("amout", map.get("capacity"));
			item.put("name",map.get("name") );
			item.put("address", map.get("address") );
			array.add(item);
		} 
		return array;
	}

	@Override
	public JSONArray findWineSaleStatistics(Long beginDate, Long endDate, Long restaurantId) throws EminException {

		JSONArray array =new JSONArray();
		
		String sql = "	SELECT rs.capacity capacity,rs.wine_name 'name',rr.address,rr.\"name\" FROM rstm.restaurant_public_info  rr LEFT JOIN  "+
						"( 	"+
						"		SELECT SUM(vendout_capacity) capacity,wine_name,rest_id FROM rstm.servant_venout_record WHERE create_time BETWEEN ? AND ? GROUP BY wine_name,rest_id  "+
						"	) rs ON rr.\"id\"=rs.rest_id "+
						"	WHERE rr.\"id\" = ?  ORDER BY rs.capacity DESC ";
		
		List<Map<String, Object>> list = reportService.findDataListBySql(sql, new Object[]{beginDate,endDate,restaurantId});
		if(null==list||list.size()<=0)return null;
		for (Map<String, Object> map : list) {
			JSONObject item = new JSONObject(); 
			item.put("amount", map.get("capacity"));
			item.put("name",  map.get("name")); 
			array.add(item);
		}
 
		return array;
	}

	@Override
	public JSONObject findRestaurantInfo(Long beginDate, Long endDate, Long restaurantId) throws EminException {
		
		String sql = "	SELECT rr.\"id\" 'id',rs.capacity  capacity,rr.address address,rr.\"name\" 'name',rr.latitude latitude,rr.longitude longitude FROM rstm.restaurant_public_info  rr LEFT JOIN  "+
					"	(		"+
					"		SELECT SUM(vendout_capacity) capacity,rest_id FROM rstm.servant_venout_record WHERE create_time BETWEEN ? AND ? GROUP BY rest_id	"+
					"	) rs ON rr.\"id\"=rs.rest_id	"+
					"	WHERE rr.\"id\" = ? ORDER BY rs.capacity DESC";
		
		List<Map<String, Object>> list = reportService.findDataListBySql(sql, new Object[]{beginDate,endDate,restaurantId});
		if(null==list||list.size()<=0)return null;
		Map<String, Object> map = list.get(0);
		JSONObject item = new JSONObject(); 
		item.put("id",map.get("id")); 
		item.put("amount", map.get("capacity"));
		item.put("address", map.get("address")); 
		item.put("name",map.get("name")); 
		item.put("latitude", map.get("latitude")); 
		item.put("longitude", map.get("longitude"));  
		 
		return item;
	}

}
