package com.emin.igwmp.pcs.core;
 
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InitRun {
	
	
	
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("spring-servlet.xml");		
		c.start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
				}				
			}
		}).start();
//		c.close();
	} 
}
