package com.youceedu.interf.util;
/**
 * 
 * @Title:  StringUtil.java   
 * @Package com.youceedu.interf.util   
 * @Description: String������ 
 * @author: chenjianlong    
 * @date:   2021��4��28�� ����9:57:43   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * ע�⣺�����ݽ������Ų�����ڲ����ģ���ֹ��й�Լ�������������ҵĿ
 */

public class StringUtil {
	/**
	 * 
	 * @Title: replaceStr   
	 * @Description: �滻�ַ�
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
		
		//����ַ���
		int index=sourceStr.indexOf(matchStr);
		String leftStr=sourceStr.substring(0, index);
		
		//�ұ��ַ���
		int matchStrLen=matchStr.length();
		String rightStr=sourceStr.substring(index+matchStrLen);
		
		
		
		//ƴ��
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
