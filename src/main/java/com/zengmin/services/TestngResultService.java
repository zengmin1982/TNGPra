package com.zengmin.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.testng.IReporter;

import com.zengmin.services.TestngResultService;

@Service("TestngResultService")
public class TestngResultService{
	//处理结果
	public IReporter resultReporter(Set reports, Class reporterType) {
		Iterator<IReporter> itr = reports.iterator();
		IReporter tr = null;
		while (itr.hasNext()) {
			IReporter str = itr.next();
			if (str.getClass() == reporterType) {
				tr = str;
			}
		}
		return tr;
	}

}
