/**
 * 
 */
package com.emin.wxs.controller.mprogram;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emin.wxs.controller.WxsBaseController;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author jim
 *
 */
@Controller
@RequestMapping("/basic")
public class BasicController extends WxsBaseController{

	/*@Reference(version="0.0.1")
	private LogisticsCompanyService logisticsCompanyService;*/
	
	@RequestMapping("/valid.mp")
	@ResponseBody
	public JSONObject validUser(@RequestParam String openid){
		JSONObject json = new JSONObject();
		System.out.println(openid);
		json.put("success", true);
		json.put("message", "你不是操作员");
		return json;
	}
	@RequestMapping("/data.mp")
	@ResponseBody
	public JSONObject loadAllData(){
		JSONObject data = new JSONObject();
		/*List<LogisticsCompany> companies = logisticsCompanyService.findLogisticsCompany(1l);
		JSONArray cs = JSONArray.fromObject(companies);
		data.put("lcompanies", cs);
		if(companies.size()>0){
			List<Truck> trucks = logisticsCompanyService.findTruckList(companies.get(0).getId());
			JSONArray ts = JSONArray.fromObject(trucks);
			data.put("trucks", ts);
			if(trucks.size()>0){
				List<TruckDriver> drivers = logisticsCompanyService.findDriverByTruckId(trucks.get(0).getId());
				JSONArray ds = JSONArray.fromObject(drivers);
				data.put("drivers", ds);
			}else{
				data.put("drivers", new JSONArray());
			}
		}else{
			data.put("trucks", new JSONArray());
			data.put("drivers", new JSONArray());
		}*/
		return data;
	}
	@RequestMapping("/truck.mp")
	@ResponseBody
	public JSONArray loadTruck(Long lcompanyId){
		//List<Truck> trucks = logisticsCompanyService.findTruckList(lcompanyId);
		//JSONArray ts = JSONArray.fromObject(trucks);
		return null;
	}
	@RequestMapping("/driver.mp")
	@ResponseBody
	public JSONArray loadDriver(Long truckId){
		/*List<TruckDriver> drivers = logisticsCompanyService.findDriverByTruckId(truckId);
		JSONArray ds = JSONArray.fromObject(drivers);
		return ds;*/
		return null;
	}
}
