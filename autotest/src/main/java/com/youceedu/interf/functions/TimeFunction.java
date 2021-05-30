package com.youceedu.interf.functions;

import com.youceedu.interf.util.DateTimeUtil;

/**
 * 
 * @Title:  TimeFunction.java   
 * @Package com.youceedu.interf.functions   
 * @Description:Time�������ʽ  
 * @author: chenjianlong    
 * @date:   2021��5��11�� ����9:23:06   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * ע�⣺�����ݽ������Ų�����ڲ����ģ���ֹ��й�Լ�������������ҵĿ
 */
public class TimeFunction implements Function{

	public String getFunc() {
		return "Time";
	}

	public String execParam(String[] args) {
		//��ʼ��
		String result=null;
		if(args.length==0){
			//ʱ���
			result=String.valueOf(DateTimeUtil.getTimeImpl());
		}else if(args.length==1&&args[0].equals("YMDHMS")){
			//������ʱ����
			result=DateTimeUtil.getDateTime();
		}else if(args.length==1&&args[0].equals("YMD")){
			//������
			result=DateTimeUtil.getDate();
		}else if(args.length==1&&args[0].equals("HMS")){
			//ʱ����
			result=DateTimeUtil.getTime();
		}else
			//���ƻ����ں�ʱ��
			result=DateTimeUtil.getPatternDateTime(args[0]);
	
		return result;
	}

}
