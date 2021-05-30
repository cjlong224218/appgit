package com.youceedu.interf.functions;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 
 * @Title:  Md5Function.java   
 * @Package com.youceedu.interf.functions   
 * @Description:Md5�������ʽ
 * @author: chenjianlong    
 * @date:   2021��5��11�� ����9:21:37   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * ע�⣺�����ݽ������Ų�����ڲ����ģ���ֹ��й�Լ�������������ҵĿ
 */
public class Md5Function implements Function {

	public String getFunc() {
		return "Md5";
	}

	public String execParam(String[] args) {
		//��ʼ��
		String result=null;
		if(args.length==1){
			result=DigestUtils.md5Hex(args[0]);
		}
		return null;
	}

}
