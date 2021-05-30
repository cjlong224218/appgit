package com.youceedu.interf.util;
/**
 * 
 * @Title:  StringUtil.java   
 * @Package com.youceedu.interf.util   
 * @Description: String工具类 
 * @author: chenjianlong    
 * @date:   2021年4月28日 下午9:57:43   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * 注意：本内容仅限于优测教育内部传阅，禁止外泄以及用于其他的商业目
 */

public class StringUtil {
	/**
	 * 
	 * @Title: replaceStr   
	 * @Description: 替换字符
	 * @param: @param sourceStr
	 * @param: @param matchStr
	 * @param: @param replaceValue
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	
	public static String replaceStr(String sourceStr,String matchStr,String replaceValue){
		  // sourceStr   reddata=  oldPasswd=/api/loginCheck:$.stateCode&newPasswd=123456&reNewPasswd=123456
		  // matchStr    /api/loginCheck:$.stateCode
		  // replaceValue    2
		
		//左边字符串
		int index=sourceStr.indexOf(matchStr);
		String leftStr=sourceStr.substring(0, index);
		
		//右边字符串
		int matchStrLen=matchStr.length();
		String rightStr=sourceStr.substring(index+matchStrLen);
		
		
		
		//拼接
		// oldPasswd=2&newPasswd=123456&reNewPasswd=123456
		return leftStr + replaceValue + rightStr;
	}

	public static void main(String[] args) {
		String sourceStr="oldPasswd=/api/loginCheck:$.stateCode&newPasswd=123456&reNewPasswd=123456";
		String matchStr="/api/loginCheck:$.stateCode";
		int index=sourceStr.indexOf(matchStr);
		String leftStr=sourceStr.substring(0, index);
		System.out.println(leftStr);
		
		int matchStrLen=matchStr.length();
		String rightStr=sourceStr.substring(index+matchStrLen);
		System.out.println(rightStr);
	}

}
