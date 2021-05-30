package com.youceedu.interf.functions;

import java.util.UUID;

/**
 * 
 * @Title:  UUIDFunction.java   
 * @Package com.youceedu.interf.functions   
 * @Description: UUID函数表达式  
 * @author: chenjianlong    
 * @date:   2021年5月11日 下午9:19:19   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * 注意：本内容仅限于优测教育内部传阅，禁止外泄以及用于其他的商业目
 */
public class UUIDFunction implements Function {

	public String getFunc() {
		return "UUID";
	}

	public String execParam(String[] args) {
		//初始化
		String result=null;
		if(args.length==0){
			result=UUID.randomUUID().toString().replace("-", "");
		}
		return result;
	}

}
