package com.youceedu.interf.functions;

import com.youceedu.interf.util.RandomUtil;

/**
 * 
 * @Title:  RandomFunction.java   
 * @Package com.youceedu.interf.functions   
 * @Description:Random�������ʽ   
 * @author: chenjianlong    
 * @date:   2021��5��11�� ����9:17:21   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * ע�⣺�����ݽ������Ų�����ڲ����ģ���ֹ��й�Լ�������������ҵĿ
 */
public class RandomFunction implements Function {

	public String getFunc() {
		return "Random";
	}

	public String execParam(String[] args) {
		//${__Random(1,1,100)}
		//args=[1,1,100]
		//��ʼ��
		String result=null;
		if(args[0].equals("1")){
			//�������
			int min=Integer.valueOf(args[1]);
			int max=Integer.valueOf(args[2]);
			result=String.valueOf(RandomUtil.getRandomInt(min, max));
		}else if(args[0].equals("2")){
			//���boolean
			result=String.valueOf(RandomUtil.getRandomBoolean());
		}else if(args[0].equals("3")){
			//���float
			float min=Float.valueOf(args[1]);
			float max=Float.valueOf(args[2]);
			result=String.valueOf(RandomUtil.getRandomFloat(min, max));
		}else if(args[0].equals("4")){
			//���float
			double min=Double.valueOf(args[1]);
			double max=Double.valueOf(args[2]);
			result=String.valueOf(RandomUtil.getRandomDouble(min, max));
		}else if(args[0].equals("5")){
			//����ַ���
			result=RandomUtil.getRandomString(Integer.valueOf(args[1]));
		}

		return result;
	}

}
