package com.emin.igwmp.skm.core;
 
import com.emin.igwmp.skm.util.LogUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class InitRun {

	public static void main(String[] args)  {
		LogUtils.setLevel();
		ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("spring-sks.xml");
		c.start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
				}				
			}
		}).start();

	}
}
