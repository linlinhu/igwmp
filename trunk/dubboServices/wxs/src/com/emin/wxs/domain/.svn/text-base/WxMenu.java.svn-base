package com.emin.wxs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.emin.base.domain.BaseEntity;
import com.emin.base.domain.UndeleteableEntity;

@Entity
@Table(schema="public",name="wxmenu")
public class WxMenu extends BaseEntity implements UndeleteableEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7135334193451760633L;
	public static final String PROP_PID = "pid";
	public static final String PROP_SORT = "sort";
	public static final int BTNTYPE_PARENT = 1;//一级菜单
	public static final int BTNTYPE_SUB = 2;//二级菜单
	/**
	 * <strong>跳转基本网页授权URL</strong>：用户点击snsapi_base类型按钮后，微信客户端将会打开开发者
	 * 在按钮中填写的网页URL，可与网页授权获取用户基本信息接口结合，获得用户基本信息。
	 * */
	public static final String ACTIVETYPE_SNSAPIBASE = "snsapi_base";
	/**
	 * <strong>跳转用户信息网页授权URL</strong>：用户点击snsapi_base类型按钮后，微信客户端将会打开开发者
	 * 在按钮中填写的网页URL，可与网页授权获取用户基本信息接口结合，获得用户基本信息。
	 * */
	public static final String ACTIVETYPE_SNSAPIUSERINFO = "snsapi_userinfo";
	/**
	 * <strong>点击推事件</strong>：用户点击click类型按钮后，微信服务器会通过消息
	 * 接口推送消息类型为event的结构给开发者（参考消息接口指南），
	 * 并且带上按钮中开发者填写的key值，开发者可以通过自定义的key值与用户进行交互；
	 * */
	public static final String ACTIVETYPE_CLICK = "click";
	/**
	 * <strong>跳转URL</strong>：用户点击view类型按钮后，微信客户端将会打开开发者
	 * 在按钮中填写的网页URL，可与网页授权获取用户基本信息接口结合，获得用户基本信息。
	 * */
	public static final String ACTIVETYPE_VIEW = "view";
	/**
	 * <strong>扫码推事件</strong>：用户点击按钮后，微信客户端将调起扫一扫工具，
	 * 完成扫码操作后显示扫描结果（如果是URL，将进入URL），且会将扫码的结果传给开发者，开发者可以下发消息。
	 * <br><br>注：5.4以上版本支持
	 * */
	public static final String ACTIVETYPE_SCANPUSH = "scancode_push";
	/**
	 * <strong>扫码推事件且弹出“消息接收中”提示框</strong>：用户点击按钮后，微信客户端将调起扫一扫工具，
	 * 完成扫码操作后，将扫码的结果传给开发者，同时收起扫一扫工具，然后弹出“消息接收中”提示框，
	 * 随后可能会收到开发者下发的消息。
	 * <br><br>注：5.4以上版本支持
	 * */
	public static final String ACTIVETYPE_SCANWAIT = "scancode_waitmsg";
	/**
	 * <strong>弹出系统拍照发图</strong>：用户点击按钮后，微信客户端将调起系统相机，
	 * 完成拍照操作后，会将拍摄的相片发送给开发者，并推送事件给开发者，同时收起系统相机，
	 * 随后可能会收到开发者下发的消息。
	 * <br><br>注：5.4以上版本支持
	 * */
	public static final String ACTIVETYPE_PICPHOTO = "pic_sysphoto";
	/**
	 * <strong>弹出拍照或者相册发图</strong>：用户点击按钮后，微信客户端
	 * 将弹出选择器供用户选择“拍照”或者“从手机相册选择”。用户选择后即走其他两种流程。
	 * <br><br>注：5.4以上版本支持
	 * */
	public static final String ACTIVETYPE_PICALBUM = "pic_photo_or_album";
	/**
	 * <strong>弹出微信相册发图器</strong>：用户点击按钮后，微信客户端将调起微信相册，
	 * 完成选择操作后，将选择的相片发送给开发者的服务器，并推送事件给开发者，
	 * 同时收起相册，随后可能会收到开发者下发的消息。
	 * <br><br>注：5.4以上版本支持
	 * */
	public static final String ACTIVETYPE_PICWEIXIN = "pic_weixin";
	/**
	 * <strong>弹出地理位置选择器</strong>：用户点击按钮后，微信客户端将调起地理位置选择工具，
	 * 完成选择操作后，将选择的地理位置发送给开发者的服务器，同时收起位置选择工具，随后可能会收到开发者下发的消息。
	 * <br><br>注：5.4以上版本支持
	 * */
	public static final String ACTIVETYPE_LOCATION = "location_select";
	public static final String PROP_ACTIVETYPE = "activetype";
	public static final String PROP_WOAID="woaId";
	private Long pid;//上级菜单
	private String name;//菜单名，不超过16个字节，子菜单不超过40个字节
	private Integer btntype;//菜单类型
	private String activetype;//响应动作类型
	private String activekey;//如果类型是VIEW，则url不超过256字节，否则长度不超过128字节
	private Boolean audit;//是否已审核
	private String remark;
	private int status;
	private Long createTime;
	private Long lastModifyTime;
	private Integer sort;
	private Long woaId;//所属公众号ID
	@Id
	@Override
	@SequenceGenerator(name = "wxmenu_id_seq", sequenceName = "public.wxmenu_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wxmenu_id_seq")
	public Long getId() {
		return super.getId();
	}
	@Column(name="pid")
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="btntype")
	public Integer getBtntype() {
		return btntype;
	}
	public void setBtntype(Integer btntype) {
		this.btntype = btntype;
	}
	@Column(name="activetype")
	public String getActivetype() {
		return activetype;
	}
	public void setActivetype(String activetype) {
		this.activetype = activetype;
	}
	@Column(name="activekey")
	public String getActivekey() {
		return activekey;
	}
	public void setActivekey(String activekey) {
		this.activekey = activekey;
	}
	@Column(name="audit")
	public Boolean getAudit() {
		return audit;
	}
	public void setAudit(Boolean audit) {
		this.audit = audit;
	}
	@Column(name="remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="status")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Column(name="createtime")
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	@Column(name="lastmodifytime")
	public Long getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	@Column(name="sort")
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	@Column(name="woaid")
	public Long getWoaId() {
		return woaId;
	}
	public void setWoaId(Long woaId) {
		this.woaId = woaId;
	}

}
