package com.emin.wxs.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.emin.base.exception.EminException;
import com.emin.base.util.CookieUtil;
import com.emin.base.util.DesUtil;
import com.emin.wxs.controller.WxsBaseController;
import com.emin.wxs.domain.Menu;
import com.emin.wxs.facade.WxsToPermissionCaller;
import com.emin.wxs.facade.WxsToPersonCaller;
import com.emin.wxs.util.WxsThreadLocalUtil;
import com.emin.wxs.vo.PersonVO;

import net.sf.json.JSONObject;

@Controller
public class CommonController extends WxsBaseController{
	
	private Logger logger = LoggerFactory.getLogger(CommonController.class);
	@Autowired
	@Qualifier("wxsToPermissionCaller")
	private WxsToPermissionCaller wxsToPermissionCaller;
	
	@Autowired
	@Qualifier("wxsToPersonCaller")
	private WxsToPersonCaller wxsToPersonCaller;
	
	@RequestMapping("/index.htm")
	public ModelAndView index(){
		ModelAndView view = new ModelAndView("index");
		String menuCodes = wxsToPermissionCaller.getAuthorizedMenuForPerson();
		List<Menu> menus = wxsToPermissionCaller.getAuthorizedMenuLstForPerson();
		view.addObject("codes", menuCodes);
		view.addObject("menus",menus);
		PersonVO vo = wxsToPersonCaller.findById(WxsThreadLocalUtil.getPersonId());
		view.addObject("person", vo);
		view.addObject("basePath", getBasePath());
		//PersonVO vo = wxsToPersonCaller.findById(1l);
		//view.addObject("person", vo);
		return view;
	}
	@RequestMapping("/common/loadModule.do")
	public void loadModule(String moduleName){
		Map<String,Object> data = new HashMap<>();
		WxsThreadLocalUtil.setPersonId(1l);
		String codes = wxsToPermissionCaller.getMenuOperationCodesForPerson(moduleName);
		data.put("codes", codes);
		printFtl(moduleName, data);		
	}
	/**
	 * 登录接口
	 * url:login.do
	 * @param cellNumber 手机号
	 * @param password 密码 （JS实现MD5加密）
	 * @return
	 */
	@RequestMapping("/login.do")
	@ResponseBody
	public JSONObject login(String cellNumber,String password){
		JSONObject json = new JSONObject();
		String message = "";
		boolean success = false;
		try{
			PersonVO vo = wxsToPersonCaller.login(cellNumber, password);
			
			if(vo!=null){
				String loginInfo = JSONObject.fromObject(vo).toString();
				CookieUtil.addCookie(getResponse(), "loginInfo", DesUtil.encrypt(loginInfo), -1);
				success = true;
			}
		}catch(EminException e){
			message = e.getLocalizedMessage();
			logger.error(e.getLocalizedMessage(),e);
		}catch (Exception e) {
			message = "服务器繁忙,请稍后再试";
			logger.error(e.getMessage(),e);
		}
		json.put("success", success);
		json.put("message", message);
		return json;
	}
	 
	@RequestMapping("/logout.do")
	@ResponseBody
	public void logOut(){
		JSONObject json = new JSONObject();
		boolean success = false;
		String message = "";
		try {
			getRequest().getSession().invalidate();
			success = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			message = "注销失败";
		}
		json.accumulate("success", success);
		json.accumulate("message", message);
		printJson(json);
		
	}
	
	/**
	 * 修改用户密码
	 * @param password 旧密码
	 * @param newPassword 新密码
	 * @return JSONObject {success:true/false,message:"错误提示信息"}
	 */
	@RequestMapping("/modifyPassword.do")
	@ResponseBody
	public JSONObject modifyPassword(String password,String newPassword){
		JSONObject json = new JSONObject();
		String message = "";
		boolean success = false;
		try{
			wxsToPersonCaller.modifyPassword(password, newPassword);
		}catch(EminException e){
			message = e.getLocalizedMessage();
			logger.error(e.getLocalizedMessage(),e);
		}catch (Exception e) {
			message = "服务器繁忙,请稍后再试";
			logger.error(e.getMessage(),e);
		}
		json.put("success", success);
		json.put("message", message);
		return json;
	}
}
