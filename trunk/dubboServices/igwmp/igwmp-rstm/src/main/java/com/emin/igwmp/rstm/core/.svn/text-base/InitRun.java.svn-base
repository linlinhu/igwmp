package com.emin.igwmp.rstm.core;
 
import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InitRun {
	private static final Logger LOGGER = Logger.getLogger(InitRun.class);
	
	public static void main(String[] args) throws Exception {
//		PropertyConfigurator.configure( "properties/log4j.properties" );
		ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("spring-servlet.xml");		
		c.start();		
		//createRestaurantInfo(c);
//		updateRestaurantInfo(c);
		//findPagedRestaurantPublicInfoByMatch(c);
		//findPagedRestaurantPublicInfoByCondition(c);
		new Thread(new Runnable() {			
			@Override
			public void run() {
				while (true) {
				}				
			}
		}).start();
	}
}
