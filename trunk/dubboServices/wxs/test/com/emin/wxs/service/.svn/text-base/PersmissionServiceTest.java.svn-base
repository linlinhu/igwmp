package com.emin.wxs.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emin.wxs.domain.Operation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring-servlet.xml"})
public class PersmissionServiceTest extends AbstractJUnit4SpringContextTests{

	@Autowired
	@Qualifier("permissionService")
	private PermissionService permissionService;
	@Test
	public void testloadOperations(){
		List<Operation> operations = permissionService.loadAllOperations();
		for (Operation operation : operations) {
			System.out.println(operation.getMenuName());
		}
	}
}
