package com.emin.igwmp.skm.core.msmanage.entity;


import com.emin.igwmp.skm.core.msmanage.entity.bean.BodyBean;
import com.emin.igwmp.skm.core.msmanage.entity.bean.FootBean;
import com.emin.igwmp.skm.core.msmanage.entity.bean.HandlerBean;
import com.emin.igwmp.skm.core.msmanage.entity.bean.IdentBean;

/**
 * 版权:贵州仁塔科技有限公司
 * 功能描述:
 * 创建者:Created by Administrator-aleyds on 2017/3/10 14:50.
 * 对外接口:
 */

public class ChiefEntity {

	/**
	 * type : 0 心跳包 1通讯包
	 * device : 机器识别码
	 */

	private IdentBean ident;
	/**
	 * time : 指令上传时间戳
	 * type : 通讯类型  0获取session数据  1基本通讯
	 * version : 版本号
	 * sesscion : 服务器校验码  type为0时为空 基本通讯时作为校验码
	 * bodyType : text/json/html/xml 指示body的数据形式
	 * bodyLenght : 指示body数据长度
	 */

	private HandlerBean handler;
	/**
	 * code : 返回类型  成功/失败类型
	 * type : 通讯类型  机器绑定  酒列表 广告等 0:设备关联信息 1:品酒配置 2:产品基本信息  3:产品运营信息  4:设备产品列表 5:升级描述 6:资源配置
	 * messages : [{"messageId":"消息ID指示同种通讯类型下的不同类别","message":"具体消息实体"}]
	 * describe : body信息描述
	 */

	private BodyBean body;
	/**
	 * describe :
	 */

	private FootBean foot;

	public IdentBean getIdent() {
		return ident;
	}

	public void setIdent(IdentBean ident) {
		this.ident = ident;
	}

	public HandlerBean getHandler() {
		return handler;
	}

	public void setHandler(HandlerBean handler) {
		this.handler = handler;
	}

	public BodyBean getBody() {
		return body;
	}

	public void setBody(BodyBean body) {
		this.body = body;
	}

	public FootBean getFoot() {
		return foot;
	}

	public void setFoot(FootBean foot) {
		this.foot = foot;
	}

}
