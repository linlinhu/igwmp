package com.emin.igwmp.rp.facade.accepters.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.emin.base.exception.EminException;
import com.emin.igwmp.rp.facade.accepters.ServantReportAccepter;
import com.emin.igwmp.rp.facade.service.ReportService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Component("servantReportAccepter")
@Service(version="0.0.1")
public class ServantReportAccepterImpl implements ServantReportAccepter {

	@Autowired
	ReportService reportService;
	
	@Override
	public JSONArray findSaleStatistics(Long bossId, Long beginDate, Long endDate) throws EminException {
		JSONArray array =new JSONArray();
		String findServantSql ="SELECT servant_id servantId,restaurant_id FROM \"public\".ref_restaurant_servant WHERE restaurant_id = ? ";
		List<Map<String, Object>> servantList= reportService.findDataListBySql(findServantSql, new Object[]{bossId});
		if(null==servantList||servantList.size()<=0) return array;
		String servants = "";
		for (Map<String, Object> os : servantList) {
			servants+=os.get("servantId")+",";
		}
		servants+=",";
		servants = servants.replace(",,","");

		String sql = " SELECT MAX(vendee_info->>'headimgurl') url,vendee_id,SUM(vendout_capacity) capacity,servant_name 'name' FROM rstm.servant_venout_record rr WHERE rr.servant_id IN ("
				
				+servants+") and rr.create_time BETWEEN ? and  ? GROUP BY vendee_id,servant_name" ; 
		
		List<Map<String, Object>> dataList= reportService.findDataListBySql(sql, new Object[]{beginDate,endDate});
		for (Map<String, Object> map : dataList) {
			JSONObject json = new JSONObject();
			json.put("url",map.get("url"));
			json.put("amout",map.get("capacity"));
			json.put("name",map.get("name"));
			array.add(json);
		}	 
		return array;
	}

	@Override
	public JSONArray findTakeWineRanking() throws EminException {
		JSONArray array =new JSONArray();
		List<String> urlList = new ArrayList<>();		
		urlList.add("http://images.haiwainet.cn/2017/0327/20170327091634512.jpg");
		urlList.add("http://images.haiwainet.cn/2017/0327/20170327112634865.jpg");
		urlList.add("http://images.haiwainet.cn/2017/0328/20170328081849372.jpg");
		urlList.add("http://images.haiwainet.cn/2017/0304/20170304071654904.jpg");
		int index = new Random().nextInt(100);
		index+=50;
		for (String url : urlList) {
			JSONObject json = new JSONObject();
			index-=5; 
			json.put("amout", index);
			json.put("name", "服务员名称"+index);
			json.put("url", url);
			json.put("restaurantName", "饭店名称"+index);
			json.put("integral", index/10);
			array.add(json);
		}
		return array;
	}

	@Override
	public JSONArray findTakeWineList(Long fansId, Long beginDate, Long endDate) throws EminException {
		JSONArray array =new JSONArray();
		List<String> urlList = new ArrayList<>();
		urlList.add("http://images.haiwainet.cn/2017/0327/20170327112634865.jpg");
		urlList.add("http://images.haiwainet.cn/2017/0304/20170304071654904.jpg");
		urlList.add("http://images.haiwainet.cn/2017/0328/20170328081849372.jpg");
		urlList.add("http://images.haiwainet.cn/2017/0327/20170327091634512.jpg");
		int index = new Random().nextInt(100);
		index+=50;
		for (String url : urlList) {
			JSONObject json = new JSONObject();
			// [{:1490950052276,:'习酒',:20,:120000,:500}]
			index-=5; 
			json.put("time", System.currentTimeMillis()-new Random().nextInt(10000));
			json.put("wineName", "测试"+index);
			json.put("quantity", index);
			json.put("amount", index*50);
			json.put("integral", index/10);
			json.put("url", url);
			array.add(json);
		}		
		return array;
	}

}
