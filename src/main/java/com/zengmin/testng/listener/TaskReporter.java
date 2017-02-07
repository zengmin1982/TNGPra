package com.zengmin.testng.listener;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;

public class TaskReporter implements IReporter{
	public int getFailedNum() {
		return failedNum;
	}

	public int getPassedNum() {
		return passedNum;
	}

	public int getSkippedNum() {
		return skippedNum;
	}

	private int failedNum = 0;
	private int passedNum = 0;
	private int skippedNum = 0;
	private static final Logger logger = Logger.getLogger(TaskReporter.class);
	
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		for (ISuite suite : suites) {
			//获取测试套件的名称
			String suiteName = suite.getName();
			ITestContext tc = null;
			//获取测试的结果数据
			Map suiteResults = suite.getResults();
			for (Object sr : suiteResults.values()) {
				tc = ((ISuiteResult) sr).getTestContext();
				passedNum = passedNum + tc.getPassedTests().getAllResults().size();
				failedNum = failedNum + tc.getFailedTests().getAllResults().size();
				skippedNum = skippedNum + tc.getSkippedTests().getAllResults().size();
			}
			logger.info(
					"Passed tests for suite '" + suiteName + "' is:" + passedNum);
			logger.info(
					"Failed tests for suite '" + suiteName + "' is:" + failedNum);
			logger.info("Skipped tests for suite '" + suiteName + "' is:"
					+ skippedNum);

		}

	}

}
