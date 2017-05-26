package com.emin.wxs.controller.admin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emin.base.exception.EminException;
import com.emin.base.util.CommonsUtil;
import com.emin.wxs.controller.WxsBaseController;
import com.emin.wxs.facade.WxsToPermissionCaller;
import com.emin.wxs.vo.OperationVO;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("/permission")
public class PermissionController extends WxsBaseController{
	
	private Logger logger = LoggerFactory.getLogger(PermissionController.class);
	
	@Autowired
	@Qualifier("wxsToPermissionCaller")
	private WxsToPermissionCaller wxsToPermissionCaller;
	
	/**
	 * 保存组织权限
	 * url:permission/save.do
	 * @param orgId
	 * @param operationIds 英文逗号分隔的操作权限ID字符串
	 * @return
	 */
	@RequestMapping("/save.do")
	@ResponseBody
	public JSONObject savePermission(@RequestParam Long orgId,@RequestParam String operationIds){
		JSONObject json = new JSONObject();
		boolean success = false;
		String message = "";
		try {
			wxsToPermissionCaller.savePermission(orgId, CommonsUtil.stringToLongArr(operationIds));
			success = true;
		} catch (EminException e) {
			logger.error(e.getLocalizedMessage(),e);
			message = e.getLocalizedMessage();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			message = e.getMessage();
		}
		json.put("success", success);
		json.put("message", message);
		return json;
	}
	
	/**
	 * 加载所有权限用于授权
	 * url:permission/save.do
	 * @return List<OperationVO>
	 * ExtJS GridPanel 使用 GroupingStore、GroupingView 根据menuName进行分组呈现
	 */
	@RequestMapping("/all.do")
	@ResponseBody
	public List<OperationVO> loadAll(){
		return wxsToPermissionCaller.loadAllOperations();
	}
	/**
	 * 加载组织已有的权限  用于修改前的呈现
	 * @param orgId
	 * @return
	 */
	@RequestMapping("/orgOperations.do")
	@ResponseBody
	public Long[] loadOrgOperations(Long orgId){
		return wxsToPermissionCaller.loadOperationIdsForOrg(orgId);
	}
	
}
