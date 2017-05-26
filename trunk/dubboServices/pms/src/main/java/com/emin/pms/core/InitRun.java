package com.emin.pms.core;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.emin.pms.util.EminPropertyPlaceholderConfigurer;
import com.pingplusplus.Pingpp;

public class InitRun {
	private static SecureRandom random = new SecureRandom();
	//private static ObjectMapper mapper = new ObjectMapper();
	public static void main(String[] args) throws Exception {
		
		ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("spring-servlet.xml");		
		c.start();
		Pingpp.apiKey = EminPropertyPlaceholderConfigurer.getContextProperty("pingpp.apiKey");
		
		//Pingpp.privateKeyPath = ClassLoader.getSystemResource(EminPropertyPlaceholderConfigurer.getContextProperty("pingpp.privateKeyFilePath")).getFile();
		Pingpp.privateKey = EminPropertyPlaceholderConfigurer.getContextProperty("pingpp.privateKey");
       // System.out.println(charge.toString());
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					
				}
				
			}
		}).start();
		/*System.in.read();
		c.close();*/
		
	}
	public static String randomString(int length) {
        String str = new BigInteger(130, random).toString(32);
        return str.substring(0, length);
    }

    public static int currentTimeSeconds() {
        return (int)(System.currentTimeMillis() / 1000);
    }
}
