package com.youceedu.interf.functions;

import java.util.UUID;

/**
 * 
 * @Title:  UUIDFunction.java   
 * @Package com.youceedu.interf.functions   
 * @Description: UUID�������ʽ  
 * @author: chenjianlong    
 * @date:   2021��5��11�� ����9:19:19   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * ע�⣺�����ݽ������Ų�����ڲ����ģ���ֹ��й�Լ�������������ҵĿ
 */
public class UUIDFunction implements Function {

	public String getFunc() {
		return "UUID";
	}

	public String execParam(String[] args) {
		//��ʼ��
		String result=null;
		if(args.length==0){
			result=UUID.randomUUID().toString().replace("-", "");
		}
		return result;
	}

}
