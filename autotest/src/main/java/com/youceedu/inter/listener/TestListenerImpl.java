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
		
		//step1�ɹ������������
		Set<ITestResult> AllPassedTests=context.getPassedTests().getAllResults();
		for(ITestResult passedTestsResult:AllPassedTests){
			//System.out.println("�ɹ�����������£�\n"+ passedTestsResult.toString());
		}
		logger.info("�õ��ɹ������������="+ AllPassedTests);
		
		//step1ʧ�������������+step2 ��set���ϴ��1��id
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
		logger.info("�õ�ʧ�������������="+allFailedTests);
		logger.info("��set���ϴ��1��id"+faliledTestIds);
		logger.info("��list���ϴ���ظ���ʧ����������="+testRepwatToBeRemovdd);
		
		for(ITestResult repeatTestResult:testRepwatToBeRemovdd){
			allFailedTests.remove(repeatTestResult);
		}
		logger.info("ɾ���ظ���ʧ����������");
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
