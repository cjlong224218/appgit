package com.youceedu.inter.listener;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.youceedu.interf.test.TestRun;
/**
 * 
 * @Title:  IRetryAnalyzerImpl.java   
 * @Package com.youceedu.inter.listener   
 * @Description:    失败用例重试   
 * @author: chenjianlong    
 * @date:   2021年4月22日 下午10:08:37   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * 注意：本内容仅限于优测教育内部传阅，禁止外泄以及用于其他的商业目
 */
public class IRetryAnalyzerImpl implements IRetryAnalyzer {//失败用例重试
	private static Logger logger = Logger.getLogger(IRetryAnalyzerImpl.class);
	private int retryCount=1;
	private int retryMaxCount=3;
	
	@Override
	public boolean retry(ITestResult result) {
		//retry方法 true 就对失败用例重试  false 不重试
		//控制同一条用例失败重试的次数
		if(retryCount<=retryMaxCount){
			retryCount++;
			logger.info("retry方法为 true，失败用例重试");
			return true;
		}
		retryCount=1;
		logger.info("retry方法为false，失败用例不重试");
		return false;
	}
	
}
