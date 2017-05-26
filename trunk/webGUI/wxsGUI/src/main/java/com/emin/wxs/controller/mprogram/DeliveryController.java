/**
 * 
 */
package com.emin.wxs.controller.mprogram;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.base.service.Condition.ConditionOperator;
import com.emin.base.service.Condition.ConditionType;
import com.emin.wxs.controller.WxsBaseController;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author jim
 *
 */
@Controller
@RequestMapping("/receive")
public class DeliveryController extends WxsBaseController{
	
	/*@Reference(version="0.0.1")
	private LogisticsService logisticsService;
	*/
	@RequestMapping("/submit.mp")
	@ResponseBody
	public JSONObject submit(String jsonData){
		JSONObject json = new JSONObject();
		boolean success = false;
		String message = "";
		try{
			JSONObject data = JSONObject.fromObject(jsonData);
			/*BillMain bm = new BillMain();
			bm.setCompanyId(1l);
			bm.setSenderId(0l);			
			bm.setDriverId(data.getLong("driverId"));
			bm.setLogisticsCompanyId(data.getLong("lcompanyId"));
			bm.setTruckId(data.getLong("truckId"));
			bm.setCustomerId(1l);
			bm.setUserId(1l);
			bm.setStatus(1);
			bm.setBillTypeId(BillType.PROP_BILL_TYPE_IN);
			bm.setUserName(data.getString("person"));
			bm.setSendTime(System.currentTimeMillis());
			bm.setSendAddress("地址");
			List<BillInBoxCode> codes = new ArrayList<>();
			JSONArray array = data.getJSONArray("receiveItems");
			for(int i=0;i<array.size();i++){
				JSONObject item = array.getJSONObject(i);
				BillInBoxCode code = new BillInBoxCode();
				code.setBoxCode(item.getString("code"));
				codes.add(code);
				
			}
			logisticsService.saveInBillMain(bm, codes);*/
			success = true;
		}catch(Exception e){
			e.printStackTrace();
			message  = "保存收货单失败!";
		}
		json.put("success", success);
		json.put("message", message);
		return json;
	}
	@RequestMapping("/receiveList.mp")
	@ResponseBody
	public JSONArray receiveList(){
		/*DateFormatSymbols dfs = new DateFormatSymbols();
		dfs.setShortWeekdays(new String[]{"","周日","周一","周二","周三","周四","周五","周六"});
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd EE", dfs);
		List<Condition> conditions = new ArrayList<>();
		conditions.add(new Condition("billTypeId", ConditionOperator.EQ, ConditionType.OTHER, BillType.PROP_BILL_TYPE_IN));
		PagedResult<BillMain> result = logisticsService.findBillMainByPage(getPageRequestData("1", "1000"), conditions);
		JSONArray array = JSONArray.fromObject(result.getResultList());
		for(int i=0;i<array.size();i++){
			Long time = array.getJSONObject(i).getLong("sendTime");
			array.getJSONObject(i).put("sendTime", sdf.format(new Date(time)));
		}
		return array;*/
		return null;
	}
	public static void main(String[] args) {
		DateFormatSymbols dfs = new DateFormatSymbols();
		dfs.setShortWeekdays(new String[]{"","周日","周一","周二","周三","周四","周五","周六"});
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd EE", dfs);
		System.out.println(sdf.format(new Date()));
	}
}
