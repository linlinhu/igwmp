package com.emin.wxs.vo.waiter;

import com.emin.igwmp.rstm.domain.ServantVenoutRecord;

public class WaiterServiceRecordVO {
	private Long Id;

    /**
     * 服务员姓名
     * */
    private String name;
    /**
     * 出酒时间
     * */
    private long wineOutTime;
    /**
     * 获得的积分
     * */
    private int gainIntegral;
    /**
     * 订单编号
     * */
    private String orderNo;
    /**
     * 出酒量
     * */
    private int outCapacity;
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
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getWineOutTime() {
		return wineOutTime;
	}
	public void setWineOutTime(long wineOutTime) {
		this.wineOutTime = wineOutTime;
	}
	public int getGainIntegral() {
		return gainIntegral;
	}
	public void setGainIntegral(int gainIntegral) {
		this.gainIntegral = gainIntegral;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public int getOutCapacity() {
		return outCapacity;
	}
	public void setOutCapacity(int outCapacity) {
		this.outCapacity = outCapacity;
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
    
	public static WaiterServiceRecordVO convertPoToVo(ServantVenoutRecord po,int cascadeDepth){
		WaiterServiceRecordVO vo = new WaiterServiceRecordVO();
		if(po ==null){
			return null;
		}	
		vo.setId(po.getId()) ;
	
		vo.setWineOutTime(po.getCreateTime());

		vo.setOrderNo(po.getOrderNo());
		
		vo.setOutCapacity(po.getVendoutCapacity());	

		vo.setWaiterName(po.getServantName()) ;

		vo.setWineName(po.getWineName()) ;
		
		vo.setRestaurantName(po.getRestName()) ;

		vo.setGainIntegral(po.getGainIntegral());
		
		return vo;			
	}
	
	public static  ServantVenoutRecord convertVoToPo(ServantVenoutRecord dbPo,WaiterServiceRecordVO vo,int cascadeDepth){
		ServantVenoutRecord po = null;
		if(vo == null) return null;
		if(dbPo != null){
			po = dbPo;
		}
		else{
			po = new ServantVenoutRecord();
			po.setCreateTime(System.currentTimeMillis());
		}		
			
	    po.setId(vo.getId());		
		po.setLastModifyTime(System.currentTimeMillis());
		po.setStatus(1);
		po.setOrderNo(vo.getOrderNo());
		
		po.setVendoutCapacity(vo.getOutCapacity());	

		po.setServantName(vo.getWaiterName()) ;

		po.setWineName(vo.getWineName()) ;
		
		po.setRestName(vo.getRestaurantName()) ;

		po.setGainIntegral(vo.getGainIntegral());
		
		return po;
	}
    
}
