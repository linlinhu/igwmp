package com.emin.igwmp.pcs.base;


import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration({"/spring-servlet.xml"}) //加载配置文件
public class BaseJunit4Test extends AbstractJUnit4SpringContextTests {

}