package com.zengmin.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import com.zengmin.services.TestngRunService;
import com.zengmin.testng.listener.TaskReporter;
import com.zengmin.util.SpringPropertyResourceReader;
import com.zengmin.util.SystemConfig;

import org.uncommons.reportng.HTMLReporter;
import org.uncommons.reportng.JUnitXMLReporter;

@Service("TestngRunService")
public class TestngRunService{
	//组装xml构造器服务
	@Autowired
	TestngXmlService txs;
	//执行TestNG,传入测试套件名称
	public Set grid(String st) {
		//创建TestNG实例
		TestNG tng = null;
		tng = new TestNG();
		//获取testng.xml
		List<XmlSuite> suites = txs.createSuite(st, "1234567");
		//设置本机调试目录
		tng.setOutputDirectory("./demo");
		//设置配置目录
//		tng.setOutputDirectory(SystemConfig.BASEDIR + "/testreport/");
		//设置执行的套件列表
		tng.setXmlSuites(suites);
		//增加监听器
		tng.addListener(new TaskReporter());
		try {
			//执行
			tng.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Set reports = tng.getReporters();
		tng = null;
		return reports;
	}

	public Set grid(String st, String dirLocal) {

		TestNG tng = null;
		tng = new TestNG();
		List<XmlSuite> suites = txs.createSuite(st, "123456");
		// 本机调试
//		tng.setOutputDirectory("C:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\web\\testreport");
		tng.setOutputDirectory("./demo");

		//		tng.setOutputDirectory(dirLocal);
		tng.setXmlSuites(suites);
		tng.setUseDefaultListeners(false);
		tng.addListener(new TaskReporter());
		try {
			// Running the Suite file.
			tng.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Set reports = tng.getReporters();
		tng = null;
		return reports;

	}

}
