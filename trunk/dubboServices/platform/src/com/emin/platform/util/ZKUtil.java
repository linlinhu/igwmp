package com.emin.platform.util;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.emin.platform.util.dubbo.Provider;
import com.emin.platform.util.dubbo.ProviderService;



public class ZKUtil {
	private static Watcher watcher = new Watcher() {
		
		@Override
		public void process(WatchedEvent event) {
			System.out.println("process : " + event.getType());
		}
	};
	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("spring-servlet.xml");
		
		c.start();
		ProviderService providerService = (ProviderService) c.getBean("providerService");
		Set<String> applications = new TreeSet<String>();
        List<String> providerApplications = providerService.findApplications();
        if (providerApplications != null && providerApplications.size() > 0) {
            applications.addAll(providerApplications);
        }	
        System.out.println("当前正在运行的服务:");
	    for (String string : applications) {
			
			List<Provider> ps = providerService.findByApplication(string);
			System.out.println(string+":"+formatDateTime(ps.get(0).getAlived()));
			for (Provider p : ps) {
				System.out.println(p.getService());
				
			}
		}
	    System.out.println();
		c.close();
	}
	 public static String formatDateTime(long mss) {
		  StringBuffer dateTimes = new StringBuffer();
		  long days = mss / ( 60 * 60 * 24);
		  long hours = (mss % ( 60 * 60 * 24)) / (60 * 60);
		  long minutes = (mss % ( 60 * 60)) /60;
		  long seconds = mss % 60;
		  if(days>0){
			  dateTimes.append(days).append("天").append(hours).append("小时").append(minutes).append("分钟").append(seconds).append("秒");
		     
		  }else if(hours>0){
			  dateTimes.append(hours).append("小时").append(minutes).append("分钟").append(seconds).append("秒");		     
		  }else if(minutes>0){
			  dateTimes.append(minutes).append("分钟").append(seconds).append("秒");
		  }else{
			  dateTimes.append(seconds).append("秒");
		  }
		  
		  return dateTimes.toString();
	}
}
