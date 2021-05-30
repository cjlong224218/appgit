package com.youceedu.inter.listener;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.youceedu.interf.test.TestRun;
/**
 * 
 * @Title:  IRetryAnalyzerImpl.java   
 * @Package com.youceedu.inter.listener   
 * @Description:    ʧ����������   
 * @author: chenjianlong    
 * @date:   2021��4��22�� ����10:08:37   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * ע�⣺�����ݽ������Ų�����ڲ����ģ���ֹ��й�Լ�������������ҵĿ
 */
public class IRetryAnalyzerImpl implements IRetryAnalyzer {//ʧ����������
	private static Logger logger = Logger.getLogger(IRetryAnalyzerImpl.class);
	private int retryCount=1;
	private int retryMaxCount=3;
	
	@Override
	public boolean retry(ITestResult result) {
		//retry���� true �Ͷ�ʧ����������  false ������
		//����ͬһ������ʧ�����ԵĴ���
		if(retryCount<=retryMaxCount){
			retryCount++;
			logger.info("retry����Ϊ true��ʧ����������");
			return true;
		}
		retryCount=1;
		logger.info("retry����Ϊfalse��ʧ������������");
		return false;
	}
	
}
