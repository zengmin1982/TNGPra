package com.zengmin.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.testng.IReporter;
import org.testng.TestNG;
import org.testng.reporters.FailedReporter;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.zengmin.services.TestngXmlService;
import com.zengmin.util.ClassUtil;
import com.zengmin.util.SpringPropertyResourceReader;
import com.zengmin.util.SystemConfig;
//在Spring框架中注册为Service
@Service("TestngXmlService")
public class TestngXmlService{
	
	//封装xml生成的方法
	public List<XmlSuite> createSuite(String testsuite, String time) {
		//建立测试套件列表
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		Set<String> packagenames = new HashSet<String>();

		// suite.setVerbose(1);
		// suite.setPreserveOrder("true");
		// 并行线程数
		// suite.setThreadCount(4);
		// 并行处理 http://testng.org/doc/documentation-main.html#parallel-running
		// suite.setParallel("tests");
		// suite.setTimeOut("5000");

		// List<String> listeners = new ArrayList<String>();
		// listeners.add("report.Report");
		// suite.setListeners(listeners);
		//扫描suite下面的包
		if (testsuite.equals("")) {
			Set<Class<?>> testClasses = ClassUtil.getClasses("com.zengmin.test.suite");
			for (Class<?> testClass : testClasses) {
				String pcname = testClass.getPackage().getName();
				if (!packagenames.contains(pcname)) {
					packagenames.add(pcname);
				}
			}
		} else {
			packagenames.add("com.zengmin.test.suite." + testsuite);
		}
		for (String packagename : packagenames) {
			//构建testng.xml的测试用例类标签<test>的列表
			List<XmlTest> tests = new ArrayList<XmlTest>();
			//构建testng.xml的测试套件标签<suite>
			XmlSuite suite = new XmlSuite();
			HashMap timemap = new HashMap<String, String> ();
			timemap.put("time", time);
			suite.setParameters(timemap);
			//设置测试套件suite的名字
			suite.setName("Demo");
			//构建testng.xml的测试驱动类标签<class>的列表
			List<XmlClass> classes = new ArrayList<XmlClass>();
			String testclass = "TestSeo1";
			//构建testng.xml的测试用例类标签<test>，并且置于上文中构建的suite "Demo"之下
			XmlTest test = new XmlTest(suite);
			//增加测试参数
			test.addParameter("testcase", "This is testcase");
			//设置用例名称
			test.setName("TestDemo");
			test.addParameter("testname", "TestDemo");
			test.addParameter("result", "This is testresult");
			XmlClass clazz = null;
			//见封装方法，构建测试驱动类
			clazz = greatTest(packagename + "." + testclass);
			//在驱动类列表中增加测试驱动类
			classes.add(clazz);
			//在测试用例中增加驱动类列表
			test.setXmlClasses(classes);
			//在测试用例列表中增加该用例
			tests.add(test);
			
			//并行线程数，采用配置文件中的线程数
			suite.setThreadCount(Integer.parseInt(SystemConfig.TESTTHREADNUM));
			//并行处理 http://testng.org/doc/documentation-main.html#parallel-running
			suite.setParallel(XmlSuite.ParallelMode.TESTS);
			//设置超时时间
			suite.setTimeOut("100000");
			//设置测试用例列表
			suite.setTests(tests);
			//增加reportng的监听类
			suite.addListener("org.uncommons.reportng.HTMLReporter");
			suite.addListener("org.uncommons.reportng.JUnitXMLReporter");
			suite.addListener("com.zengmin.testng.listener.ResultListener");
			//打印xml列表
			System.out.println(suite.toXml());

			suites.add(suite);

		}

		return suites;

	}

	/**
	 * 添加测试方法,方法名为多个的情况下使用"/"进行分割
	 * 
	 * @param classname
	 *            类名字
	 * @param methodListname
	 *            方法名
	 * @return
	 */
	private XmlClass greatTest(String className) {
		//构建测试驱动类标签<class>
		XmlClass clazz = new XmlClass(className);
		//添加测试方法列表<methods>
		List<XmlInclude> methodList = new ArrayList<XmlInclude>();
		Set<String> methodListnames = null;
		try {
			//通过反射，从驱动类中获取方法列表
			methodListnames = ClassUtil.getTestMethod(Class.forName(className));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//添加方法至包含方法列表<include>
		for (String methodListname : methodListnames) {
			methodList.add(new XmlInclude(methodListname));
		}
		//设置方法标签至驱动列表
		clazz.setIncludedMethods(methodList);
		return clazz;

	}

}