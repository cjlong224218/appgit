package com.youceedu.interf.functions;

import com.youceedu.interf.util.DateTimeUtil;

/**
 * 
 * @Title:  TimeFunction.java   
 * @Package com.youceedu.interf.functions   
 * @Description:Time函数表达式  
 * @author: chenjianlong    
 * @date:   2021年5月11日 下午9:23:06   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * 注意：本内容仅限于优测教育内部传阅，禁止外泄以及用于其他的商业目
 */
public class TimeFunction implements Function{

	public String getFunc() {
		return "Time";
	}

	public String execParam(String[] args) {
		//初始化
		String result=null;
		if(args.length==0){
			//时间戳
			result=String.valueOf(DateTimeUtil.getTimeImpl());
		}else if(args.length==1&&args[0].equals("YMDHMS")){
			//年月日时分秒
			result=DateTimeUtil.getDateTime();
		}else if(args.length==1&&args[0].equals("YMD")){
			//年月日
			result=DateTimeUtil.getDate();
		}else if(args.length==1&&args[0].equals("HMS")){
			//时分秒
			result=DateTimeUtil.getTime();
		}else
			//定制化日期和时间
			result=DateTimeUtil.getPatternDateTime(args[0]);
	
		return result;
	}

}
