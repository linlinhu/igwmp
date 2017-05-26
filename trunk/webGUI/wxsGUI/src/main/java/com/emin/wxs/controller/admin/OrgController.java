package com.emin.wxs.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emin.base.dao.PagedResult;
import com.emin.base.exception.EminException;
import com.emin.base.util.CommonsUtil;
import com.emin.wxs.controller.WxsBaseController;
import com.emin.wxs.facade.WxsToOrganizeCaller;
import com.emin.wxs.vo.OrganizeVO;
import com.emin.wxs.vo.PersonVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/org")
public class OrgController extends WxsBaseController{

	private Logger logger = LoggerFactory.getLogger(OrgController.class);
	
	@Autowired
	@Qualifier("wxsToOrganizeCaller")
	private WxsToOrganizeCaller wxsToOrganizeCaller;
	
	/**
	 * 保存组织信息
	 * @param OrganizeVO vo
	 * URL:org/save.do
	 * @return
	 */
	@RequestMapping(value = "/save.do",method={RequestMethod.POST,RequestMethod.PUT})
	@ResponseBody
	public JSONObject saveOrg(OrganizeVO vo){
		JSONObject json = new JSONObject();
		String message = "";
		boolean success = false;
		try {
			wxsToOrganizeCaller.saveOrganize(vo);
			success = true;
		} catch (EminException e) {
			logger.error(e.getLocalizedMessage(),e);
			message = e.getLocalizedMessage();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			message = "服务器繁忙，请稍后再试";
		}
		json.put("success", success);
		json.put("message", message);
		return json;
	}
	
	/**
	 * 加载组织树
	 * @param pid 父级组织ID 顶级组织传0
	 * URL: org/tree.do
	 * 使用Extjs TreePanel加载即可
	 */
	@RequestMapping("/tree.do")
	@ResponseBody
	public JSONArray loadOrgTree(@RequestParam(required=true)Long pid){
		return wxsToOrganizeCaller.loadOrgTree(pid);
	}
	
	/**
	 * 删除组织
	 * URL: org/delete.do
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete.do")
	@ResponseBody
	public JSONObject deleteOrg(@RequestParam(required=true)Long id){
		JSONObject json = new JSONObject();
		String message = "";
		boolean success = false;
		try {
			wxsToOrganizeCaller.deleteOrg(id);
			success = true;
		} catch (EminException e) {
			logger.error(e.getLocalizedMessage(),e);
			message = e.getLocalizedMessage();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			message = "服务器繁忙，请稍后再试";
		}
		json.put("success", success);
		json.put("message", message);
		return json;
	}
	
	/**
	 * 为员工分配组织
	 * URL: org/assign.do
	 * @param orgId
	 * @param personIds 用英文逗号分隔的ID字符串
	 * @return
	 */
	@RequestMapping("/assign.do")
	@ResponseBody
	public JSONObject addPersonToOrg(@RequestParam(required=true)Long orgId,@RequestParam(required=true)String personIds){
		JSONObject json = new JSONObject();
		String message = "";
		boolean success = false;
		try {
			wxsToOrganizeCaller.addPersonToOrg(orgId, CommonsUtil.stringToLongArr(personIds));
			success = true;
		} catch (EminException e) {
			logger.error(e.getLocalizedMessage(),e);
			message = e.getLocalizedMessage();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			message = "服务器繁忙，请稍后再试";
		}
		json.put("success", success);
		json.put("message", message);
		return json;
	}
	
	/**
	 * 将员工从组织中移除
	 * URL: org/remove.do
	 * @param orgId
	 * @param personIds 用英文逗号分隔的ID字符串
	 * @return
	 */
	@RequestMapping("/remove.do")
	@ResponseBody
	public JSONObject removePersonToOrg(@RequestParam(required=true)Long orgId,@RequestParam(required=true)String personIds){
		JSONObject json = new JSONObject();
		String message = "";
		boolean success = false;
		try {
			wxsToOrganizeCaller.removePersonFromOrg(orgId, CommonsUtil.stringToLongArr(personIds));
			success = true;
		} catch (EminException e) {
			logger.error(e.getLocalizedMessage(),e);
			message = e.getLocalizedMessage();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			message = "服务器繁忙，请稍后再试";
		}
		json.put("success", success);
		json.put("message", message);
		return json;
	}
	
	/**
	 * 将员工从组织中移除
	 * URL: org/reAssign.do
	 * @param oldOrgId 原组织ID
	 * @param newOrgId 新组织ID
	 * @param personIds 用英文逗号分隔的ID字符串
	 * @return
	 */
	@RequestMapping("/reAssign.do")
	@ResponseBody
	public JSONObject changePersonOrg(@RequestParam(required=true)Long oldOrgId,@RequestParam(required=true)Long newOrgId,@RequestParam(required=true)String personIds){
		JSONObject json = new JSONObject();
		String message = "";
		boolean success = false;
		try {
			wxsToOrganizeCaller.changePersonOrg(oldOrgId, newOrgId, CommonsUtil.stringToLongArr(personIds));
			success = true;
		} catch (EminException e) {
			logger.error(e.getLocalizedMessage(),e);
			message = e.getLocalizedMessage();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			message = "服务器繁忙，请稍后再试";
		}
		json.put("success", success);
		json.put("message", message);
		return json;
	}
	
	/**
	 * 分页加载组织成员
	 * URL:org/member.do
	 * @param orgId
	 * @param match 关键字 可模糊匹配手机、姓名
	 * @return
	 */
	@RequestMapping("/member.do")
	@ResponseBody
	public JSONObject loadOrgMember(@RequestParam(required=true)Long orgId,@RequestParam(required=false)String match){
		PagedResult<PersonVO> page = wxsToOrganizeCaller.loadOrgMembers(getPageRequestData(), orgId, match);
		JSONObject json = new JSONObject();
		json.put("total", page.getTotalCount());
		json.put("rows", page.getResultList());

		return json;
	}
	
	/**
	 * 分页加载尚未分配过组织的员工
	 * @return
	 */
	@RequestMapping("/unallocated.do")
	@ResponseBody
	public JSONObject loadUnallocatedPersons(){
		PagedResult<PersonVO> page = wxsToOrganizeCaller.loadUnallocatedPersons(getPageRequestData());
		JSONObject json = new JSONObject();
		json.put("total", page.getTotalCount());
		json.put("rows", page.getResultList());
		return json;
	}
	
}
