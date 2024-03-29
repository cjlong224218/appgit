package com.youceedu.interf.functions;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 
 * @Title:  Md5Function.java   
 * @Package com.youceedu.interf.functions   
 * @Description:Md5函数表达式
 * @author: chenjianlong    
 * @date:   2021年5月11日 下午9:21:37   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * 注意：本内容仅限于优测教育内部传阅，禁止外泄以及用于其他的商业目
 */
public class Md5Function implements Function {

	public String getFunc() {
		return "Md5";
	}

	public String execParam(String[] args) {
		//初始化
		String result=null;
		if(args.length==1){
			result=DigestUtils.md5Hex(args[0]);
		}
		return null;
	}

}
