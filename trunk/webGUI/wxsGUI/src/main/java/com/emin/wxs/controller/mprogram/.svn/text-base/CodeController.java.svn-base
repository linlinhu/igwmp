/**
 * 
 */
package com.emin.wxs.controller.mprogram;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emin.wxs.controller.WxsBaseController;
import com.emin.wxs.util.DataUtil;

import net.sf.json.JSONObject;

/**
 * @author jim
 *
 */
@Controller
@RequestMapping("/code")
public class CodeController extends WxsBaseController {

	@RequestMapping("/parse.mp")
	@ResponseBody
	public JSONObject parse(String code){
		JSONObject item = DataUtil.getItem(code);
		if(item==null){
			item = new JSONObject();
			item.put("type", 3);
			item.put("name", code);
		}
		item.put("code", code);
		return item;
	}
}
