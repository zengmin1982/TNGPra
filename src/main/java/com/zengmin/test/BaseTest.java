package com.zengmin.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;


@Test
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class BaseTest extends AbstractTestNGSpringContextTests{

	public BaseTest() {
		// TODO Auto-generated constructor stub
	}

}
