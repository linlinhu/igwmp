package com.emin.wxs.facade.trading.callers;

import java.util.List;

import com.emin.base.exception.EminException;
import com.emin.igwmp.ords.domain.OrderStatus;
import com.emin.wxs.vo.trading.OrderItemVO;
import com.emin.wxs.vo.trading.OrderVO;

import net.sf.json.JSONObject;

/**
 * 
* @ClassName: WxsToOrderCaller
* @Description: 订单关联业务调用接口
* @author Jim.lee
* @date 2017年4月11日 上午11:36:24
*
 */
public interface WxsToOrderCaller {

	
	/**
	* @Title: createOrderByFansUnionId
	* @Description: 调用订单服务创建订单并返回订单号 (订单在redis中) 微信粉丝的UnionID方式
	* @param orderVO
	* @return String
	 * @throws Exception 
	 */
	String createOrderByFansUnionId(String unionId, List<OrderItemVO> itemVO) throws EminException,Exception;

	/**
	* @Title: createOrderByFansOpenId
	* @Description: 调用订单服务创建订单并返回订单号 (订单在redis中) 微信粉丝的OpenID方式
	* @param orderVO
	* @return String
	 * @throws Exception 
	 */
	String createOrderByFansOpenId(String openId, List<OrderItemVO> itemVO) throws EminException,Exception;

	/**
	 * 
	* @Title: loadPersonalOrder
	* @Description: 根据状态查询个人订单
	* @param openId
	* @param orderStatus
	* @return List<OrderVO>
	 */
	List<OrderVO> loadPersonalOrder(String openId, OrderStatus orderStatus);

	/**
	 *
	* @Title: findOrderById
	* @Description:  根据ID查询订单
	* @param orderId
	* @return OrderVO
	 */
	OrderVO findOrderById(Long orderId);

	/**
	 * 
	* @Title: findOrderByNumber
	* @Description: 根据订单号查询订单
	* @param orderNumber
	* @return OrderVO
	 */
	OrderVO findOrderByNumber(String orderNumber);
	
	/**
	 * 
	 * @Title: giveOrder
	 * @Description:赠送订单 有效时间12小时
	 * @param orderNumber void
	 */
	void giveOrder(String orderNumber);

	/**
	 * 
	* @Title: getGivingOrder
	* @Description: 赠送订单领取
	* @param orderNumber 领取的订单号
	* @param receiverId 领取方ID
	* @param receiverInfo 领取方信息
	 */
	void getGivingOrder(String orderNumber, Long receiverId, JSONObject receiverInfo);

	/**
	 * 
	* @Title: cancelGiveOrder
	* @Description: 取消赠送或超时后自动回收赠送订单
	* @param orderNumber void
	 */
	void cancelGiveOrder(String orderNumber);
	String getGivingOrderUrl(Long woaId,String url);
}
