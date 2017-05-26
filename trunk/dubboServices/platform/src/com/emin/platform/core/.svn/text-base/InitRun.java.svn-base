package com.emin.platform.core;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.emin.base.dao.PreFilters;
import com.emin.platform.domain.Company;
import com.emin.platform.service.CompanyService;

public class InitRun {
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext("conf/spring-servlet.xml");
		
		c.start();
		//初始化超级管理员，若已存在则会自动跳过
		CompanyService cs = (CompanyService) c.getBean("companyService");
		cs.initRootPerson();
		int count = cs.getCountByPreFilter(PreFilters.eq(Company.PROP_CODE, "laiebei"));
		if(count==0){
			Company company = new Company();
			company.setCode("laiebei");
			company.setName("来E杯");
			company.setPersonLimit(0);
			company.setEffectiveTime(0l);
			company.setTotalPerson(0);
			cs.saveOrUpdateCompany(company);
		}
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
}
