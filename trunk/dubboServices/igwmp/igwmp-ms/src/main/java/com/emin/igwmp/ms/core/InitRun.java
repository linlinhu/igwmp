package com.emin.igwmp.ms.core;

 
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InitRun {

 
    public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("spring-servlet.xml");		
		c.start(); 
//		System.in.read();
		new Thread(new Runnable() {			
			@Override
			public void run() {
				while (true) {
				}	
			}
		}).start();
    }

}