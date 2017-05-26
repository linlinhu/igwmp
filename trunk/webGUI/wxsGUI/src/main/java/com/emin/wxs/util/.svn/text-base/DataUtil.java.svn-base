/**
 * 
 */
package com.emin.wxs.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONObject;

/**
 * @author jim
 *
 */
public class DataUtil {

	private static Map<String,JSONObject> data = new HashMap<>();
	static{
		JSONObject item = new JSONObject();
		item.put("name", "绵柔尖庄·红钻");
		item.put("imgurl", "http://img0.imgtn.bdimg.com/it/u=995628207,227815485&fm=21&gp=0.jpg");
		item.put("type",2);
		item.put("unit", "瓶");
		data.put("10001", item);
		JSONObject item2 = new JSONObject();
		item2.put("name", "绵柔尖庄·红钻");
		item2.put("imgurl", "http://img3.imgtn.bdimg.com/it/u=2831316570,3721727059&fm=23&gp=0.jpg");
		item2.put("type",1);
		item2.put("unit", "箱");
		data.put("10002", item2);
	}
	public static JSONObject getItem(String code){
		if(StringUtils.isBlank(code)){
			return null;
		}
		String c = code.substring(1, 6);
		System.out.println(c);
		return data.get(c);
	}
	
}
