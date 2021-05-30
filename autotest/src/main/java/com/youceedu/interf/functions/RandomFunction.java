package com.youceedu.interf.functions;

import com.youceedu.interf.util.RandomUtil;

/**
 * 
 * @Title:  RandomFunction.java   
 * @Package com.youceedu.interf.functions   
 * @Description:Random函数表达式   
 * @author: chenjianlong    
 * @date:   2021年5月11日 下午9:17:21   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * 注意：本内容仅限于优测教育内部传阅，禁止外泄以及用于其他的商业目
 */
public class RandomFunction implements Function {

	public String getFunc() {
		return "Random";
	}

	public String execParam(String[] args) {
		//${__Random(1,1,100)}
		//args=[1,1,100]
		//初始化
		String result=null;
		if(args[0].equals("1")){
			//随机整数
			int min=Integer.valueOf(args[1]);
			int max=Integer.valueOf(args[2]);
			result=String.valueOf(RandomUtil.getRandomInt(min, max));
		}else if(args[0].equals("2")){
			//随机boolean
			result=String.valueOf(RandomUtil.getRandomBoolean());
		}else if(args[0].equals("3")){
			//随机float
			float min=Float.valueOf(args[1]);
			float max=Float.valueOf(args[2]);
			result=String.valueOf(RandomUtil.getRandomFloat(min, max));
		}else if(args[0].equals("4")){
			//随机float
			double min=Double.valueOf(args[1]);
			double max=Double.valueOf(args[2]);
			result=String.valueOf(RandomUtil.getRandomDouble(min, max));
		}else if(args[0].equals("5")){
			//随机字符串
			result=RandomUtil.getRandomString(Integer.valueOf(args[1]));
		}

		return result;
	}

}
