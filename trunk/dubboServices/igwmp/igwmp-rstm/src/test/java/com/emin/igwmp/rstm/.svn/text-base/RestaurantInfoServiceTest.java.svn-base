package com.emin.igwmp.rstm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
 
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.alibaba.fastjson.JSONObject;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.base.service.Condition;
import com.emin.base.service.Condition.ConditionOperator;
import com.emin.base.service.Condition.ConditionType;
import com.emin.igwmp.rstm.domain.RestaurantAdminInfo;
import com.emin.igwmp.rstm.domain.RestaurantPrivateInfo;
import com.emin.igwmp.rstm.domain.RestaurantPublicInfo;
import com.emin.igwmp.rstm.service.RestaurantAdminService;
import com.emin.igwmp.rstm.service.RestaurantPrivateInfoService;
import com.emin.igwmp.rstm.service.RestaurantPublicInfoService;


public class RestaurantInfoServiceTest extends BaseJunit4Test{
	
//	private static final Logger LOGGER = Logger.getLogger(RestaurantInfoServiceTest.class);
	
    @Resource
	private RestaurantAdminService advertisementInfoService;
    
    @Resource
	private RestaurantPublicInfoService restaurantPublicInfoService;
    
    @Resource
    private RestaurantPrivateInfoService restaurantPrivateInfoService;
   
    @Test
   	@Rollback(false)
	public void createRestaurantInfo(){
		RestaurantPublicInfo restaurantPublicInfo = new RestaurantPublicInfo();  
    	restaurantPublicInfo.setAddress("世纪金源购物中心");
    	restaurantPublicInfo.setCuisine(1);
    	restaurantPublicInfo.setExplaination("树厨");
    	restaurantPublicInfo.setLatitude(10.334324324234);
    	restaurantPublicInfo.setLongitude(123.423324234);
    	restaurantPublicInfo.setUsingScene(1);
    	restaurantPublicInfo.setPhone("13823242423");
      	restaurantPublicInfo.setName("树厨");
      	
      	RestaurantPrivateInfo restaurantPrivateInfo = new RestaurantPrivateInfo();
      	restaurantPrivateInfo.setCorperationName("XX餐饮有限公司");
      	restaurantPrivateInfo.setLegalRepresentative("钟路");
      	restaurantPrivateInfo.setBusinessRgstNum("123123123");
      	restaurantPrivateInfo.setCorperationAccountName("XX餐饮有限公司账户");
      	restaurantPrivateInfo.setCorperationAccountBank("建行");
      	restaurantPrivateInfo.setCorperationAccount("2356743224567334");
//new Array
//    	restaurantPrivateInfo.setRestaurantSvcPics() {
//    		this.restaurantSvcPics = restaurantSvcPics;
//    	}
      	restaurantPrivateInfo.setContractNum("NY0031");
        restaurantPrivateInfo.setContractStartTime(new Date().getTime()); 
        restaurantPrivateInfo.setContractEndTime(new Date().getTime()); 
       // restaurantPublicInfo.setRestaurantPrivateInfo(restaurantPrivateInfo);
        
//        RestaurantSvcPic restaurantSvcPic =  new RestaurantSvcPic();       
//        List<RestaurantSvcPic> restaurantSvcPics = new ArrayList<RestaurantSvcPic>();//饭店各种图片
//        restaurantSvcPics.add(restaurantSvcPic);
//        restaurantPublicInfo.setRestaurantSvcPics(restaurantSvcPics);
        
        RestaurantAdminInfo restaurantAdminInfo = new RestaurantAdminInfo();
        restaurantAdminInfo.setCellphone("13888888888");
        restaurantAdminInfo.setRoleType(1);
        restaurantAdminInfo.setName("钟路");
        
        RestaurantAdminInfo restaurantServantInfo = new RestaurantAdminInfo();
        restaurantAdminInfo.setCellphone("138344688888");
        restaurantAdminInfo.setRoleType(3);
        restaurantAdminInfo.setName("钟路他妹");
        
        List<RestaurantAdminInfo> admins = new ArrayList<RestaurantAdminInfo>();
        admins.add(restaurantAdminInfo);
        admins.add(restaurantServantInfo);
//        restaurantPublicInfo.setAdmins(admins);
        
         //添加一条数据库信息
        restaurantPublicInfoService.saveOrUpdate(restaurantPublicInfo);
        System.out.println("饭店公共信息数据库添加成功");
       //获取添加成功的记录信息
	}
	
    @Test
   	@Rollback(false)
	public void updateRestaurantInfo(){
		RestaurantPublicInfo restaurantPublicInfo = new RestaurantPublicInfo();  
    	restaurantPublicInfo.setId(new Long(15));
		restaurantPublicInfo.setAddress("大十字");
    	restaurantPublicInfo.setCuisine(1);
    	restaurantPublicInfo.setExplaination("树厨");
    	restaurantPublicInfo.setLatitude(10.334324324234);
    	restaurantPublicInfo.setLongitude(123.423324234);
    	restaurantPublicInfo.setUsingScene(1);
    	restaurantPublicInfo.setPhone("13823242423");
      	restaurantPublicInfo.setName("树厨");
      	
      	RestaurantPrivateInfo restaurantPrivateInfo = new RestaurantPrivateInfo();
      	restaurantPrivateInfo.setCorperationName("XX餐饮有限公司");
      	restaurantPrivateInfo.setLegalRepresentative("钟魁");
//        restaurantPublicInfo.setRestaurantPrivateInfo(restaurantPrivateInfo);
        
        
//        RestaurantSvcPic restaurantSvcPic =  new RestaurantSvcPic();       
//        List<RestaurantSvcPic> restaurantSvcPics = new ArrayList<RestaurantSvcPic>();//饭店各种图片
//        restaurantSvcPics.add(restaurantSvcPic);
//        restaurantPublicInfo.setRestaurantSvcPics(restaurantSvcPics);
        
        RestaurantAdminInfo restaurantAdminInfo = new RestaurantAdminInfo();
        restaurantAdminInfo.setCellphone("138823424558");
        restaurantAdminInfo.setRoleType(1);
        restaurantAdminInfo.setName("钟魁");
        List<RestaurantAdminInfo> admins = new ArrayList<RestaurantAdminInfo>();
        admins.add(restaurantAdminInfo);
//        restaurantPublicInfo.setAdmins(admins);
        
         //更新一条数据库信息
        restaurantPublicInfoService.saveOrUpdate(restaurantPublicInfo);
       System.out.println("饭店公共信息数据库更新成功");
   
	}
	
    @Test
   	@Rollback(false)
	public void findPagedRestaurantPublicInfoByMatch(){
		 
		PageRequest pageRequest = new PageRequest(0,5);
		String match1 = "树";
		String match2  = "钟";
		PagedResult<RestaurantPublicInfo> pr = restaurantPublicInfoService.findPagedRestaurantPublicInfoByMatch(pageRequest,match1,match2);
		if(pr!=null){
			System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(pr.getResultList()));
		}
		System.out.println("根据关键字查询饭店公共信息数据成功");	
		
	}
	
    @Test
   	@Rollback(false)
	public void findPagedRestaurantPublicInfoByCondition(){
		PageRequest pageRequest = new PageRequest(0,5);
		Condition condition = new Condition("usingScene", ConditionOperator.EQ, ConditionType.CHARACTER, 1);
		List<Condition> conditions = new ArrayList<Condition>();
		conditions.add(condition);
		PagedResult<RestaurantPublicInfo> pr = restaurantPublicInfoService.findPagedRestaurantPublicInfoByCondition(pageRequest,conditions);
		if(pr!=null){
			System.out.println(JSONObject.toJSONString(pr.getResultList()));		
		}	
		System.out.println("根据条件查询饭店公共信息数据成功");	
	}
    
    @Test
   	@Rollback(false)
	public void createRestaurantPrivateInfo(){
      	
      	RestaurantPrivateInfo restaurantPrivateInfo = new RestaurantPrivateInfo();
      	restaurantPrivateInfo.setCorperationName("XX餐饮有限公司");
      	restaurantPrivateInfo.setLegalRepresentative("钟路");
      	restaurantPrivateInfo.setBusinessRgstNum("123123123");
      	restaurantPrivateInfo.setCorperationAccountName("XX餐饮有限公司账户");
      	restaurantPrivateInfo.setCorperationAccountBank("建行");
      	restaurantPrivateInfo.setCorperationAccount("2356743224567334");
//new Array
//    	restaurantPrivateInfo.setRestaurantSvcPics() {
//    		this.restaurantSvcPics = restaurantSvcPics;
//    	}
      	restaurantPrivateInfo.setContractNum("NY0031");
        restaurantPrivateInfo.setContractStartTime(new Date().getTime()); 
        restaurantPrivateInfo.setContractEndTime(new Date().getTime()); 
        
        restaurantPrivateInfoService.saveOrUpdate(restaurantPrivateInfo);
//		if(pr!=null){
//			System.out.println(JSONObject.toJSONString(pr.getResultList()));		
//		}	
		System.out.println("保存饭店私有信息数据成功");	
	}
}
