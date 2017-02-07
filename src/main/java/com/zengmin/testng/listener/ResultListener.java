package com.zengmin.testng.listener;


import org.testng.ISuite;
import org.testng.ISuiteListener;

public class ResultListener implements ISuiteListener{

	public ResultListener() {
		// TODO Auto-generated constructor stub
	}

	public void onStart(ISuite suite) {
		String time  =  suite.getParameter("time");
		System.out.println(time);
	}

	public void onFinish(ISuite suite) {
		String time  =  suite.getParameter("time");
		System.out.println(time + "stopped");
	}

}
