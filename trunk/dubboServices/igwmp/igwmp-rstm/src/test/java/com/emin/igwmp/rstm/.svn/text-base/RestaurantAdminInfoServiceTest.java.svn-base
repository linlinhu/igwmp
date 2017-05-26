package com.emin.igwmp.rstm;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
 
import com.emin.igwmp.rstm.domain.RestaurantAdminInfo;
import com.emin.igwmp.rstm.service.RestaurantAdminService;

public class RestaurantAdminInfoServiceTest extends BaseJunit4Test{

	@Resource
	RestaurantAdminService restaurantAdminService;
	
	    @Test
	   	@Rollback(false)
		public void createRestaurantInfo(){
		   RestaurantAdminInfo restaurantAdminInfo = new RestaurantAdminInfo();  
		   restaurantAdminInfo.setCellphone("2323321122334");
		   restaurantAdminInfo.setRoleType(3);
		   restaurantAdminInfo.setName("王二");
		   restaurantAdminInfo.setWechatId("MCK33443XT54334");
	        
	         //添加一条数据库信息
		   restaurantAdminService.saveOrUpdate(restaurantAdminInfo);
	        System.out.println("饭店服务员信息数据库添加成功");
	       //获取添加成功的记录信息
		}
	    
	    @Test
	   	@Rollback(false)
		public void findPagedAdminInfoByMatch(){
			 
//			PageRequest pageRequest = new PageRequest(0,5);
//			PagedResult<RestaurantAdminInfo> pr = restaurantAdminService.loadPagedServantByMatch(pageRequest, null);
//			if(pr!=null){
//				System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(pr.getResultList()));
//			}
			System.out.println("根据关键字查询饭店服务员数据成功");	
			
		}
}
