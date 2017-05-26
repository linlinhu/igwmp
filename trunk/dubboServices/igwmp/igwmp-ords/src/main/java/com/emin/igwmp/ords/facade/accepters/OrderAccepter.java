package com.emin.igwmp.ords.facade.accepters;

import java.util.List;

import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.exception.EminException;
import com.emin.base.service.Condition;
import com.emin.igwmp.ords.domain.Order;

import net.sf.json.JSONObject;

public interface OrderAccepter {

	/**
	 * 创建订单并返回订单号 此时订单被未写入数据库，存放在Redis
	 * @param order
	 * @return
	 */
	String createOrder(Order order) throws EminException;
	
	/**
	 * 酒柜订单并返回订单号 此时订单被未写入数据库，存放在Redis
	 * @param order
	 * @return
	 */
	String createOrderForMachine(Order order) throws EminException;

	/**
	 * 根据条件查询订单列表（不分页）
	 * @param conditions
	 * @return {@link List}
	 */
	List<Order> loadOrderByCondition(List<Condition> conditions);

	/**
	 * 根据条件查询订单列表（分页）
	 * @param pageRequest
	 * @param conditions
	 * @return {@link PagedResult}
	 */
	PagedResult<Order> loadOrderByCondition(PageRequest pageRequest, List<Condition> conditions);

	/**
	 * 根据ID查询Order (PS: Redis中的Order 没有ID)
	 * @param id
	 * @return {@link Order}
	 */
	Order findOrderById(Long id);

	/**
	 * 根据订单编号查询订单 
	 * 先从数据库查询 若 数据库中不存在则从Redis中获取
	 * @param orderNumber
	 * @return {@link Order}
	 */
	Order findOrderByNumber(String orderNumber);

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

	/**
	 * 
	* @Title: makeUpForLockedOrder
	* @Description: 针对下发未及时上报的订单（一般是由于断电或设备故障）手动补单的接口，仅当订单状态为LOCKED时有效
	* @param orderNumber 订单号
	* @param compensationAmount 补偿量（ml）不能低于20ml
	* @return String
	 */
	String makeUpForLockedOrder(String orderNumber, Integer compensationAmount);

	

}
