package com.youceedu.interf.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
/**
 * 
 * @Title:  JsonUtil.java   
 * @Package com.youceedu.tools.json   
 * @Description:JsonUtil������  
 * @author: ChenJianlong    
 * @date:   2021��4��21�� ����7:58:31   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * ע�⣺�����ݽ������Ų�����ڲ����ģ���ֹ��й�Լ�������������ҵĿ
 */

public class JsonUtil {
	/**
	 * 
	 * @Title: isJsonString   
	 * @Description:�ж��Ƿ�ΪJSON�ַ���
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
	 * @Description:�ж��Ƿ�ΪJSON����
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
