package com.emin.igwmp.ords.core;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

public class InitRun {
	private static ObjectMapper mapper = new ObjectMapper();
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
