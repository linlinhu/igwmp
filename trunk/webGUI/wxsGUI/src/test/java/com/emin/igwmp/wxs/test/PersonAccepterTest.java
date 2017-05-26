package com.emin.igwmp.wxs.test;

import java.util.List;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.alibaba.dubbo.config.annotation.Reference;
import com.emin.base.dao.PageRequest;
import com.emin.base.dao.PagedResult;
import com.emin.igwmp.prds.domain.Product;
import com.emin.igwmp.prds.facade.accepters.ProductAccepter;
import com.emin.igwmp.prds.facade.accepters.TasteAccepter;
import com.emin.platform.domain.Person;
import com.emin.platform.facade.accepters.PersonAccepter;
import com.emin.wxs.domain.Operation;
import com.emin.wxs.service.PermissionService;
@ContextConfiguration(locations={"/spring-servlet.xml"})
public class PersonAccepterTest extends AbstractJUnit4SpringContextTests{

	@Reference(version="0.0.1")
	PersonAccepter personAccepter;
	@Reference(version="0.0.1")
	PermissionService permissionService;
	@Reference(version="0.0.1")
	ProductAccepter productAccepter;
	@Reference(version="0.0.1")
	TasteAccepter tasteAccepter;
	@Test
	public void test1(){
		/*Person person = personAccepter.findById(1l);
		System.out.println(person);
		List<Operation> operations = permissionService.getMenuOperationsForPerson(1l, "PERSON");
		System.out.println(operations.size());
		for (Operation operation : operations) {
			System.out.println(operation.getCode());
		}*/
		System.out.println(productAccepter.findProducts(null));
		//System.out.println(tasteAccepter.findTastes(null));
	}
}
