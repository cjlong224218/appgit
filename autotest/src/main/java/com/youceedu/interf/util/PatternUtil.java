package com.youceedu.interf.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.alibaba.fastjson.JSONPath;

public class PatternUtil {
	/*
	 *��ʼ����
	 */
	private static String compareResultregex="(\\$\\.[\\w-]+)=([\\u4e00-\\u9fa5\\w]+)";
	private static String depkeyregex="([\\w/]+):([\\$\\.\\w]+)";
	private static String reqDataRegex="([\\w/]+):([\\$\\.\\w]+)";
	private static String fuctionRegex="\\$\\{\\_\\_(\\w+)(\\([\\w,]+\\))\\}";
	private static Map<String,String> map=new HashMap<String,String>();
	private static Logger logger = Logger.getLogger(PatternUtil.class);
	/**
	 * 
	 * @Title: getMatcher   
	 * @Description: ������ִ��������ʽ���鿴ƥ����
	 * @param: @param regex
	 * @param: @param data
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: Matcher      
	 * @throws
	 */
	public static Matcher getMatcher(String regex,String data)throws Exception{
		 //����������ʽ
		  Pattern pattern =Pattern.compile(regex);
		  //ִ��������ʽ���鿴ƥ����
		  Matcher matcher=pattern.matcher(data);
		  return matcher;
		  
	}
	
	/**
	 * 
	 * @Title: handlerReqDataOfDep   
	 * @Description: reqData��������
	 * @param: @param reqData
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: String      
	 * @throws
	 */
	public static String handlerReqDataOfDep(String reqData) throws Exception{
		Matcher matcher=getMatcher(reqDataRegex,reqData);
		 while(matcher.find()){//true=reqData�б��ʽ�������滻
			 //ֵ�滻
			  String value=map.get(matcher.group());
			  reqData=StringUtil.replaceStr(reqData,matcher.group(),value);
		  }
		 return reqData;
	}
	
	/**
	 * 
	 * @Title: storeDepKeyValue   
	 * @Description: �洢depkeyvalue
	 * @param: @param depkey
	 * @param: @param actResult
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws
	 */
	public static void storeDepKeyValue(String depkey,String actResult) throws Exception{
		Matcher matcher= getMatcher(depkeyregex,depkey);

		  while(matcher.find()){
			  String value=JSONPath.read(actResult,matcher.group(2)).toString();
			  map.put(matcher.group(), value);
			  for(Entry<String,String> entry:map.entrySet()){
				  System.out.println(entry.getKey()+ " " + entry.getValue());
				  
			  } 
		  }  
	}
	
	/**
	 * 
	 * @Title: compareResult   
	 * @Description: Ԥ��ֵ��ʵ��ֵ�Ա�
	 * @param: @param exResult
	 * @param: @param actResult
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws
	 */
	public static void compareResult(String expResult,String actResult ) throws Exception{
		  Matcher matcher=getMatcher(compareResultregex,expResult);
		  //step3�鿴ƥ����
		  while(matcher.find()){
			String exJsonPath=matcher.group(1);//$.msg
			String exValue=matcher.group(2);////���ߵ�½�ɹ�
			String actValue=JSONPath.read(actResult, exJsonPath).toString();
			Assert.assertEquals(actValue,exValue);
			
		  }
	}
	/**
	 * 
	 * @Title: compareResultToDb   
	 * @Description: Ԥ��ֵ��ʵ��ֵ�Ա�
	 * @param: @param exResult
	 * @param: @param actResult
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: int      
	 * @throws
	 */
	public static int compareResultToDb(String expResult,String actResult ) throws Exception{
		  //��ʼ��
		  int flag=0;
		  List<Integer> list=new ArrayList<Integer>();
		 
		  Matcher matcher=getMatcher(compareResultregex,expResult);
		  while(matcher.find()){
			String exJsonPath=matcher.group(1);//$.msg
			String exValue=matcher.group(2);////���ߵ�½�ɹ�
			String actValue=JSONPath.read(actResult, exJsonPath).toString();
			int status=actValue.equals(exValue)?1:0;
			list.add(status);//[1 1]
		  }
		  
		  if(!list.equals(0)){
			  flag=1;
		  }
		  return flag;
		
	}
	
	/**
	 * 
	 * @Title: handleReqdataOffunc   
	 * @Description: reqData�������ʽ����
	 * @param: @param reqData
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws
	 */
	public static String handleReqdataOfFunc(String reqData) throws Exception{
		 Matcher matcher=getMatcher(fuctionRegex,reqData);
		   while(matcher.find()){
			   String group=matcher.group();
			   String funName=matcher.group(1);
			   String[] funParam=matcher.group(2).replace("(", "").replace(")", "").split(",");
			   
			   String value=null;
			   if(FunMapingClassUtil.isFunc(funName)){
				  value=FunMapingClassUtil.getValue(funName, funParam);
			   }
			   reqData=StringUtil.replaceStr(reqData, group, value);
		   }
		   return reqData;
	}

	public static void main(String[] args) {
		
		
	}

}
