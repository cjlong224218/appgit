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
   * ��ʼ��
   */
  
  private String filePath=null;
  private static List<AutoLog> list=new ArrayList<AutoLog>();
  private static Logger logger = Logger.getLogger(TestRun.class);
	
  @Parameters({"filePathParam"})//filePathParam="D:\\data\\001.xlsx"
  @BeforeTest
  public void beforeTest(String filePath){
	  this.filePath = filePath;
  }
	
  //���Է������������������������ݣ�Ψһ�ķ�ʽ��Ҫ����dataPPovider="dp" or dataProvider="���������Զ����nameֵ"
  @Test(dataProvider = "testCaseData")
  public void httpReq(String id, String isExec,String testCase,String reqType,String reqHost,String reqInterface,String reqData,String expResult,String isDep,String depkey) throws Exception {
	 
	  //��ʼ��
	  String reqUrl=reqHost + reqInterface;
	  String actResult=null;
	  
	  //reqData�������ʽ����
	  reqData= PatternUtil.handleReqdataOfFunc(reqData);
	  //System.out.println("�����reqData="+reqData);
	   
	  /*
	  //����ֵ����
	  reqData=PatternUtil.handlerReqDataOfDep(reqData);
	  Reporter.log("������ţ�" + id);
	  Reporter.log("�����Ƿ�ִ�У�" + isExec);
	  Reporter.log("����������" + testCase);
	  Reporter.log("�����ӿڣ�" + reqUrl);
	  Reporter.log("�����������ݣ�" + reqData);
	 
	  //��������
	  if("YES".equals(isExec)){
		  if("Get".equals(reqType)){
			  actResult=HttpReqUtil.sendGet(reqUrl,reqData);
			  logger.info("����get����");
		  }else{
			  actResult=HttpReqUtil.sendPost(reqUrl,reqData);
			  logger.info("����post����");
		  }

	  }else{
		  Reporter.log("��������ִ�У���Ϊexcel����Test_is_execΪNo") ;
	  }
	  
	  //�жϽӿ��Ƿ�����
	  if("YES".equals(isDep)){
		  PatternUtil.storeDepKeyValue(depkey, actResult);
		  logger.info("�洢depkeyvalue");
	  }
	  
	  //�ռ����ݴ洢��db
	  int result=PatternUtil.compareResultToDb(expResult, actResult);
	  list.add(new AutoLog(testCase,reqType,reqUrl,reqData,expResult,actResult,result,DateTimeUtil.getDateTime()));
	 
	  //Ԥ��ֵ��ʵ��ֵ�Ա�
	  Reporter.log("��������ֵ��" + expResult);
	  Reporter.log("����ʵ��ֵ��" + actResult);
	  PatternUtil.compareResult(expResult,actResult);
	  logger.info("Ԥ��ֵ��ʵ��ֵ�Ա�");
	 */
  }
  
  //��������ר�Ÿ�-@test�����ṩ���ݵ�
  @DataProvider(name="testCaseData")
  public Object[][] dp() throws Exception {
//    return new Object[][] {
//      new Object[] { 1, "a" },
//      new Object[] { 2, "b" },
      ExcelUtil excelUtil = new ExcelUtil(this.filePath);
      Object[][] value = excelUtil.getArrayCellValue(0);
      logger.info("�ɹ��õ�@test������������������");
      return value;
    }
  
  @AfterTest
  public void afterTest() throws Exception{
	  //�ӿ����ݳ������ݿ�
	  DbcpUtil.paramSqlUpdate(list);
  }

}
