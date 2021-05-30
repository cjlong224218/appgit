package com.youceedu.interf.test;

import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONPath;
import com.youceedu.interf.model.AutoLog;
import com.youceedu.interf.util.DateTimeUtil;
import com.youceedu.interf.util.DbcpUtil;
import com.youceedu.interf.util.ExcelUtil;
import com.youceedu.interf.util.HttpReqUtil;
import com.youceedu.interf.util.PatternUtil;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestRun {
  /*
   * 初始化
   */
  
  private String filePath=null;
  private static List<AutoLog> list=new ArrayList<AutoLog>();
  private static Logger logger = Logger.getLogger(TestRun.class);
	
  @Parameters({"filePathParam"})//filePathParam="D:\\data\\001.xlsx"
  @BeforeTest
  public void beforeTest(String filePath){
	  this.filePath = filePath;
  }
	
  //测试方法接收数据驱动过来的数据，唯一的方式是要属性dataPPovider="dp" or dataProvider="数据驱动自定义的name值"
  @Test(dataProvider = "testCaseData")
  public void httpReq(String id, String isExec,String testCase,String reqType,String reqHost,String reqInterface,String reqData,String expResult,String isDep,String depkey) throws Exception {
	 
	  //初始化
	  String reqUrl=reqHost + reqInterface;
	  String actResult=null;
	  
	  //reqData函数表达式处理
	  reqData= PatternUtil.handleReqdataOfFunc(reqData);
	  //System.out.println("计算后reqData="+reqData);
	   
	  /*
	  //依赖值处理
	  reqData=PatternUtil.handlerReqDataOfDep(reqData);
	  Reporter.log("用例编号：" + id);
	  Reporter.log("用例是否执行：" + isExec);
	  Reporter.log("用例描述：" + testCase);
	  Reporter.log("用例接口：" + reqUrl);
	  Reporter.log("用例请求数据：" + reqData);
	 
	  //发送请求
	  if("YES".equals(isExec)){
		  if("Get".equals(reqType)){
			  actResult=HttpReqUtil.sendGet(reqUrl,reqData);
			  logger.info("发送get请求");
		  }else{
			  actResult=HttpReqUtil.sendPost(reqUrl,reqData);
			  logger.info("发送post请求");
		  }

	  }else{
		  Reporter.log("此用例不执行，因为excel设置Test_is_exec为No") ;
	  }
	  
	  //判断接口是否被依赖
	  if("YES".equals(isDep)){
		  PatternUtil.storeDepKeyValue(depkey, actResult);
		  logger.info("存储depkeyvalue");
	  }
	  
	  //收集数据存储到db
	  int result=PatternUtil.compareResultToDb(expResult, actResult);
	  list.add(new AutoLog(testCase,reqType,reqUrl,reqData,expResult,actResult,result,DateTimeUtil.getDateTime()));
	 
	  //预期值与实际值对比
	  Reporter.log("用例期望值：" + expResult);
	  Reporter.log("用例实际值：" + actResult);
	  PatternUtil.compareResult(expResult,actResult);
	  logger.info("预期值与实际值对比");
	 */
  }
  
  //数据驱动专门给-@test方法提供数据的
  @DataProvider(name="testCaseData")
  public Object[][] dp() throws Exception {
//    return new Object[][] {
//      new Object[] { 1, "a" },
//      new Object[] { 2, "b" },
      ExcelUtil excelUtil = new ExcelUtil(this.filePath);
      Object[][] value = excelUtil.getArrayCellValue(0);
      logger.info("成功得到@test方法驱动的用例数据");
      return value;
    }
  
  @AfterTest
  public void afterTest() throws Exception{
	  //接口数据沉淀到数据库
	  DbcpUtil.paramSqlUpdate(list);
  }

}
