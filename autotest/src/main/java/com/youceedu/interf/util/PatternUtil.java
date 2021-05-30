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
	 *初始化化
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
	 * @Description: 建立并执行正则表达式，查看匹配结果
	 * @param: @param regex
	 * @param: @param data
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: Matcher      
	 * @throws
	 */
	public static Matcher getMatcher(String regex,String data)throws Exception{
		 //建立正则表达式
		  Pattern pattern =Pattern.compile(regex);
		  //执行正则表达式，查看匹配结果
		  Matcher matcher=pattern.matcher(data);
		  return matcher;
		  
	}
	
	/**
	 * 
	 * @Title: handlerReqDataOfDep   
	 * @Description: reqData依赖处理
	 * @param: @param reqData
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: String      
	 * @throws
	 */
	public static String handlerReqDataOfDep(String reqData) throws Exception{
		Matcher matcher=getMatcher(reqDataRegex,reqData);
		 while(matcher.find()){//true=reqData有表达式，则需替换
			 //值替换
			  String value=map.get(matcher.group());
			  reqData=StringUtil.replaceStr(reqData,matcher.group(),value);
		  }
		 return reqData;
	}
	
	/**
	 * 
	 * @Title: storeDepKeyValue   
	 * @Description: 存储depkeyvalue
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
	 * @Description: 预期值与实际值对比
	 * @param: @param exResult
	 * @param: @param actResult
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws
	 */
	public static void compareResult(String expResult,String actResult ) throws Exception{
		  Matcher matcher=getMatcher(compareResultregex,expResult);
		  //step3查看匹配结果
		  while(matcher.find()){
			String exJsonPath=matcher.group(1);//$.msg
			String exValue=matcher.group(2);////读者登陆成功
			String actValue=JSONPath.read(actResult, exJsonPath).toString();
			Assert.assertEquals(actValue,exValue);
			
		  }
	}
	/**
	 * 
	 * @Title: compareResultToDb   
	 * @Description: 预期值与实际值对比
	 * @param: @param exResult
	 * @param: @param actResult
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: int      
	 * @throws
	 */
	public static int compareResultToDb(String expResult,String actResult ) throws Exception{
		  //初始化
		  int flag=0;
		  List<Integer> list=new ArrayList<Integer>();
		 
		  Matcher matcher=getMatcher(compareResultregex,expResult);
		  while(matcher.find()){
			String exJsonPath=matcher.group(1);//$.msg
			String exValue=matcher.group(2);////读者登陆成功
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
	 * @Description: reqData函数表达式处理
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
