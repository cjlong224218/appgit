package com.youceedu.interf.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @Title:  DateTimeUtil.java   
 * @Package com.youceedu.tools.date   
 * @Description:日期时间工具类  
 * @author: chenjianlong    
 * @date:   2021年5月4日 上午10:44:33   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * 注意：本内容仅限于优测教育内部传阅，禁止外泄以及用于其他的商业目
 */

public class DateTimeUtil {	
	/**
	 * 
	 * @Title: getTimeImpl   
	 * @Description:获取时间戳
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
	 * @Description: 获取系统时间
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
	 * @Description:获取系统日期
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
	 * @Description: 获取系统时间和日期
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
	 * @Description:定制化日期和时间
	 * @param: @param pattern
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String getPatternDateTime(String pattern){
		return new SimpleDateFormat(pattern).format(new Date()); 
	}
	
	public static void main(String[] args) {
		 System.out.println("当前时间戳："+ getTimeImpl());
		 System.out.println("当前时间："+ getTime());
		 System.out.println("当前日期："+ getDate());
		 System.out.println("当前日期和时间："+ getDateTime());
		 System.out.println("定制化当前日期和时间："+ getPatternDateTime("yyyy-MM-dd HH-mm-ss"));
	}

}
