package com.zengmin.test.suite.demo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zengmin.dao.DemoDao;
/*
 * 继承AbstractTestNGSpringContextTests并且添加注解@ContextConfiguration使得该测试类在Spring框架中集成，
 * 可以使用Spring的事务管理，数据源管理，aop等功能
 */
@Test
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestSeo1 extends AbstractTestNGSpringContextTests{
	//通过Spring组装mybatis数据类
	@Autowired
	DemoDao dd;
	
	@BeforeTest
	public void setUp() throws Exception {
		System.out.println("SetUp test");
	}

	@AfterTest
	public void tearDown() throws Exception {
		System.out.println("TearDown test");

	}

	@Test
	@Parameters({ "result", "testcase", "testname"})
	public void seo1 (String result, String testcase, String testname) throws Exception{
		System.out.println("Run Test, Testcase is " + testcase + " Result is " + result);
		//获取数据库数据
		dd.getType("Demo");
	}

}
