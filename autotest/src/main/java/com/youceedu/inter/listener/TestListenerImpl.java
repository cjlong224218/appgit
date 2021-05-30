package com.youceedu.inter.listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.testng.IResultMap;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.youceedu.interf.util.ExcelUtil;

public class TestListenerImpl implements ITestListener{
	//step3
	private List<ITestResult> testRepwatToBeRemovdd=new ArrayList<ITestResult>();
	private static Logger logger = Logger.getLogger(TestListenerImpl.class);
	@Override
	public void onFinish(ITestContext context) {
		
		//step1成功用例结果集合
		Set<ITestResult> AllPassedTests=context.getPassedTests().getAllResults();
		for(ITestResult passedTestsResult:AllPassedTests){
			//System.out.println("成功用例结果如下：\n"+ passedTestsResult.toString());
		}
		logger.info("得到成功用例结果集合="+ AllPassedTests);
		
		//step1失败用例结果集合+step2 在set集合存放1个id
		Set<Integer> faliledTestIds=new HashSet<Integer>();
		Set<ITestResult> allFailedTests=context.getFailedTests().getAllResults();
		for(ITestResult failedTestsResult:allFailedTests){
			int failHashCodeId =failedTestsResult.toString().hashCode();
			if(faliledTestIds.contains(failHashCodeId )){
				testRepwatToBeRemovdd.add(failedTestsResult);
			}else{
				faliledTestIds.add(failHashCodeId);
			}
		}
		logger.info("得到失败用例结果集合="+allFailedTests);
		logger.info("在set集合存放1个id"+faliledTestIds);
		logger.info("在list集合存放重复的失败用例集合="+testRepwatToBeRemovdd);
		
		for(ITestResult repeatTestResult:testRepwatToBeRemovdd){
			allFailedTests.remove(repeatTestResult);
		}
		logger.info("删除重复的失败用例集合");
	}
	
	@Override
	public void onTestStart(ITestResult result) {}

	@Override
	public void onTestSuccess(ITestResult result) {}

	@Override
	public void onTestFailure(ITestResult result) {}

	@Override
	public void onTestSkipped(ITestResult result){}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

	@Override
	public void onStart(ITestContext context) {}
	

}
