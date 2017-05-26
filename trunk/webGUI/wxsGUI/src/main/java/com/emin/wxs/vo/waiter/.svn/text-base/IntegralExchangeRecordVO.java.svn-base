package com.emin.wxs.vo.waiter;

import com.emin.igwmp.rstm.domain.ServantExchangeIntegralRecord;

public class IntegralExchangeRecordVO {
	private Long id;	
	/**
     * 兑换时间
     * */
    private long expendIntegralTime;	
    /**
     * 兑换时间
     * */
    private int expendIntegral;	
    /**
     * 兑换订单编号
     * */
    private String orderNo;	
    /**
     * 订单状态
     * */
    private int orderStatus;	
    /**
     * 服务员姓名
     * */
    private String waiterName;	
    /**
     * 餐厅名称
     * */
    private String restaurantName;	
    /**
     * 酒品名称
     * */
    private String wineName;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public long getExpendIntegralTime() {
		return expendIntegralTime;
	}
	public void setExpendIntegralTime(long expendIntegralTime) {
		this.expendIntegralTime = expendIntegralTime;
	}
	public int getExpendIntegral() {
		return expendIntegral;
	}
	public void setExpendIntegral(int expendIntegral) {
		this.expendIntegral = expendIntegral;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getWaiterName() {
		return waiterName;
	}
	public void setWaiterName(String waiterName) {
		this.waiterName = waiterName;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getWineName() {
		return wineName;
	}
	public void setWineName(String wineName) {
		this.wineName = wineName;
	}	
    
	public static IntegralExchangeRecordVO convertPoToVo(ServantExchangeIntegralRecord servantExchangeIntegralRecord,int cascadeDepth){
		if(servantExchangeIntegralRecord == null){
			return null;
		}
		IntegralExchangeRecordVO vo = new IntegralExchangeRecordVO();
		vo.setId(servantExchangeIntegralRecord.getId());	

		vo.setExpendIntegralTime(servantExchangeIntegralRecord.getExchangeTime());

		vo.setOrderNo(servantExchangeIntegralRecord.getOrderNo());
		
		vo.setOrderStatus(servantExchangeIntegralRecord.getOrderStatus());

		vo.setWaiterName(servantExchangeIntegralRecord.getServantName()); 
	
		vo.setExpendIntegral(servantExchangeIntegralRecord.getExpendIntegral());

		vo.setWineName(servantExchangeIntegralRecord.getWineName());
		
		vo.setRestaurantName(servantExchangeIntegralRecord.getRestaurantName());
		
		return vo;
	}
	
	public static ServantExchangeIntegralRecord convertVoToPo(ServantExchangeIntegralRecord dbPo,IntegralExchangeRecordVO vo,int cascadeDepth){
		ServantExchangeIntegralRecord po = null;
		if(vo == null) return null;
		if(dbPo != null){
			po = dbPo;
		}
		else{
			po = new ServantExchangeIntegralRecord();
			po.setCreateTime(System.currentTimeMillis());
		}		
		
	    po.setId(vo.getId());		
		po.setLastModifyTime(System.currentTimeMillis());
		po.setStatus(1);	

		po.setExchangeTime(vo.getExpendIntegralTime());

		po.setOrderNo(vo.getOrderNo());
		
		po.setOrderStatus(vo.getOrderStatus());

		po.setServantName(vo.getWaiterName()); 
	
		po.setExpendIntegral(vo.getExpendIntegral());

		po.setWineName(vo.getWineName());
		
		po.setRestaurantName(vo.getRestaurantName());
		return po;
	}
    
}
