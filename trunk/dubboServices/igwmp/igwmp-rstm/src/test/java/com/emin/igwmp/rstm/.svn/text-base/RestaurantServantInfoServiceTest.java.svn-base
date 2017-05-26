package com.emin.igwmp.rstm;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.emin.igwmp.rstm.domain.RestaurantServantInfo;
import com.emin.igwmp.rstm.domain.ServantExchangeIntegralRecord;
import com.emin.igwmp.rstm.domain.ServantVenoutRecord;
import com.emin.igwmp.rstm.service.RestaurantServantService;
import com.emin.igwmp.rstm.service.ServantExchangeIntegralRecodService;
import com.emin.igwmp.rstm.service.ServantVenoutRecordService; 

public class RestaurantServantInfoServiceTest extends BaseJunit4Test{

	@Resource
	RestaurantServantService restaurantServantInfoService;
	@Resource
	ServantExchangeIntegralRecodService servantExchangeIntegralRecodService;
	@Resource
	ServantVenoutRecordService servantVenoutRecordService;
	    @Test
	   	@Rollback(false)
		public void createRestaurantServantInfo(){
	    	RestaurantServantInfo restaurantServantInfo = new RestaurantServantInfo();
	    	restaurantServantInfo.setCellphone("23456675");
	    	restaurantServantInfo.setName("石榴姐");
	    	restaurantServantInfo.setIntegral(3344);
	    	restaurantServantInfo.setHistoryIntegral(334452);
	    	restaurantServantInfo.setExchangeIntegral(342);
	        
	         //添加一条数据库信息
	    	restaurantServantInfoService.saveOrUpdate(restaurantServantInfo);
	        System.out.println("饭店服务员信息数据库添加成功");
	       //获取添加成功的记录信息
	       List<RestaurantServantInfo> servants = restaurantServantInfoService.findAll(null);
	       RestaurantServantInfo  currentServant=null;
	       if(servants!=null&&servants.size()>0){
	    	   currentServant = servants.get(0);
	       }
	       if(currentServant!=null){
	    	   ServantVenoutRecord servantVenoutRecord =  new ServantVenoutRecord();
	   		
	    	   servantVenoutRecord.setCreateTime(System.currentTimeMillis());
	
	    	   servantVenoutRecord.setOrderNo("2343234");
		   		
	    	   servantVenoutRecord.setVendoutCapacity(new Random().nextInt(400));	
	
	    	   servantVenoutRecord.setServantName(servants.get(0).getName()) ;
	
	    	   servantVenoutRecord.setWineName("习酒") ;
		   		
	    	   servantVenoutRecord.setRestName("彭厨") ;
	
	    	   servantVenoutRecord.setGainIntegral(new Random().nextInt(600));
	    	   
	    	   servantVenoutRecord.setStatus(1);
	    	   
	    	   servantVenoutRecord.setRestaurantServantInfo(servants.get(0));
	    	   
	    	   ServantExchangeIntegralRecord servantExchangeIntegralRecord =  new ServantExchangeIntegralRecord();
	    	
	    	   servantExchangeIntegralRecord.setExchangeTime(System.currentTimeMillis());

	    	   servantExchangeIntegralRecord.setOrderNo("44422344575");
	    		
	    	   servantExchangeIntegralRecord.setOrderStatus(2);

	    	   servantExchangeIntegralRecord.setServantName(servants.get(0).getName()); 
	    	
	    	   servantExchangeIntegralRecord.setExpendIntegral(3333333);

	    	   servantExchangeIntegralRecord.setWineName("来e杯大师酒");
	    		
	    	   servantExchangeIntegralRecord.setRestaurantName("彭厨");
	    	   
	    	   servantExchangeIntegralRecord.setRestaurantServantInfo(servants.get(0));
	    	   
	    	   servantExchangeIntegralRecord.setStatus(1);
	    	   
	    	   servantExchangeIntegralRecodService.saveOrUpdate(servantExchangeIntegralRecord);
	    	   
	    	   servantVenoutRecordService.saveOrUpdate(servantVenoutRecord);
	       }
		}
	    
}
