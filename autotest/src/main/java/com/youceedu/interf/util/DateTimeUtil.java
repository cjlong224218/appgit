package com.youceedu.interf.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @Title:  DateTimeUtil.java   
 * @Package com.youceedu.tools.date   
 * @Description:����ʱ�乤����  
 * @author: chenjianlong    
 * @date:   2021��5��4�� ����10:44:33   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * ע�⣺�����ݽ������Ų�����ڲ����ģ���ֹ��й�Լ�������������ҵĿ
 */

public class DateTimeUtil {	
	/**
	 * 
	 * @Title: getTimeImpl   
	 * @Description:��ȡʱ���
	 * @param: @return      
	 * @return: long      
	 * @throws
	 */
	public static long getTimeImpl(){
		return System.currentTimeMillis();
	}
	
	/**
	 * 
	 * @Title: getTime   
	 * @Description: ��ȡϵͳʱ��
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String getTime(){
		return DateFormat.getTimeInstance().format(new Date());
	}
	
	/**
	 * 
	 * @Title: getDate   
	 * @Description:��ȡϵͳ����
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String getDate(){
		return DateFormat.getDateInstance().format(new Date());
	}
	
	/**
	 * 
	 * @Title: getDateTime   
	 * @Description: ��ȡϵͳʱ�������
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String getDateTime(){
		return DateFormat.getDateTimeInstance().format(new Date());
	}
	
	/**
	 * 
	 * @Title: getPatternDateTime   
	 * @Description:���ƻ����ں�ʱ��
	 * @param: @param pattern
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String getPatternDateTime(String pattern){
		return new SimpleDateFormat(pattern).format(new Date()); 
	}
	
	public static void main(String[] args) {
		 System.out.println("��ǰʱ�����"+ getTimeImpl());
		 System.out.println("��ǰʱ�䣺"+ getTime());
		 System.out.println("��ǰ���ڣ�"+ getDate());
		 System.out.println("��ǰ���ں�ʱ�䣺"+ getDateTime());
		 System.out.println("���ƻ���ǰ���ں�ʱ�䣺"+ getPatternDateTime("yyyy-MM-dd HH-mm-ss"));
	}

}
