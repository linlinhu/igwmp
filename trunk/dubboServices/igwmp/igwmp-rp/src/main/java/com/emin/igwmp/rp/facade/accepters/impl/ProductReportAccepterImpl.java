package com.emin.igwmp.rp.facade.accepters.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.emin.base.exception.EminException;
import com.emin.igwmp.rp.facade.accepters.ProductReportAccepter;
import com.emin.igwmp.rp.facade.service.ReportService;

import net.sf.json.JSONObject;
@Service(version="0.0.1")
@Component("productReportAccepter")
public class ProductReportAccepterImpl implements ProductReportAccepter {

	@Autowired
	ReportService reportService;
	@Override
	public JSONObject getProcductSaleAmount(Long productId) throws EminException {
		JSONObject json = new JSONObject();
		json.put("productId", productId);
		json.put("saleAmount", 0);
		json.put("outAmount", 0);
		String saleSql = "SELECT SUM(\"count\") quantity,product_id FROM ords.order_item WHERE product_id=? GROUP BY product_id ";
		List<Map<String, Object>> saleList = reportService.findDataListBySql(saleSql, new Object[]{productId});
		if(null!=saleList&&saleList.size()>0){
			json.put("saleAmount", saleList.get(0).get("quantity"));
		}
		String outSql = " SELECT SUM(tr.\"actual_take_ml\") amount,oi.product_id FROM tws.take_wine_record tr "+
						" LEFT JOIN ords.\"order\" oo ON tr.order_number = oo.order_number"+
						" LEFT JOIN ords.order_item oi ON oi.order_id = oo.\"id\" "+
						" WHERE oi.product_id = ? "+
						" GROUP BY oi.product_id";
		List<Map<String, Object>> outList = reportService.findDataListBySql(outSql, new Object[]{productId});
		if(null!=outList&&outList.size()>0){
			json.put("outAmount", outList.get(0).get("amount"));
		}
		return json;
	}

}
