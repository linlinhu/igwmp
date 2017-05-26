package com.emin.tdc.core;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.emin.tdc.redis.RedisDao;

public class InitRun {
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("spring-servlet.xml");		
		c.start();
		RedisDao redisDao = (RedisDao) c.getBean("redisDao");
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					
				}
				
			}
		}).start();
		
	}
}
