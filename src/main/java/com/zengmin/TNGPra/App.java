package com.zengmin.TNGPra;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import com.zengmin.services.*;

/**
 * Hello world!
 *
 */
public class App {

    public static void main ( String[] args ) throws Exception
    {
    	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    	context.getBean(com.zengmin.services.TestngRunService.class).grid("demo");
    	}
}
