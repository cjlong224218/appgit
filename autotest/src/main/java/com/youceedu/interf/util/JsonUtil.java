package com.youceedu.interf.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
/**
 * 
 * @Title:  JsonUtil.java   
 * @Package com.youceedu.tools.json   
 * @Description:JsonUtil工具类  
 * @author: ChenJianlong    
 * @date:   2021年4月21日 下午7:58:31   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * 注意：本内容仅限于优测教育内部传阅，禁止外泄以及用于其他的商业目
 */

public class JsonUtil {
	/**
	 * 
	 * @Title: isJsonString   
	 * @Description:判断是否为JSON字符串
	 * @param: @param jsonStr
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	
	public static boolean isJsonString(String jsonStr){
		
		try{
			JSONObject jsonObject=JSON.parseObject(jsonStr);
			return true;
		}catch(Exception e){
			return false;
		}

	}
	
	/**
	 * 
	 * @Title: isJsonArray   
	 * @Description:判断是否为JSON数组
	 * @param: @param jsonArray
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public static boolean isJsonArray(String jsonArray){
		
		try{
			JSONArray JsonArray=JSONArray.parseArray(jsonArray);
			return true;
		}catch(Exception e){
			return false;
		}

	}

	public static void main(String[] args) {
		
	}	

}
