package com.zengmin.test.suite.demo;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test
public class TestSeo2 {

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
	public void seo2 (String result, String testcase, String testname) throws Exception{
		System.out.println("Run Test, Testcase is " + testcase + " Result is " + result);
	}

}
