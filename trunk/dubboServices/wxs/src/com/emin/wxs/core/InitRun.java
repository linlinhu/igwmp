package com.emin.wxs.core;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.emin.wxs.domain.WxOfficialAccount;
import com.emin.wxs.service.FansService;
import com.emin.wxs.service.WxOfficialAccountService;

public class InitRun {
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("spring-servlet.xml");		
		c.start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				while(true){
					
				}
				
			}
		}).start();
	
		
	}
}
