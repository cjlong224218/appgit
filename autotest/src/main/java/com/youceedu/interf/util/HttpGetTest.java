package com.youceedu.interf.util;



import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
/**
 * 
 * @Title:  HttpGetTest.java   
 * @Package com.youceedu.tools.http   
 * @Description:  发送get请求  
 * @author: chenjianlong    
 * @date:   2021年4月7日 下午9:16:53   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * 注意：本内容仅限于优测教育内部传阅，禁止外泄以及用于其他的商业目
 */
public class HttpGetTest {
	/**
	 * 
	 * @Title: sendGet   
	 * @Description: 发送get请求
	 * @param: @param url
	 * @param: @param param
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: String      
	 * @throws
	 */
	
	public String sendGet(String url,String param) throws Exception{
		//初始化
		String result=null;
		String finalUrl=url+"?"+param;
		CloseableHttpClient httpclient=null;
		CloseableHttpResponse reponse=null;
		try{
			//得到httpclient对象
			httpclient = HttpClients.createDefault();
			//发送请求
			HttpGet httpGet = new HttpGet(finalUrl);
			reponse=httpclient.execute(httpGet);
			
			//据状态码得到返回值
			int statusCode=reponse.getStatusLine().getStatusCode();
			if(statusCode==HttpStatus.SC_OK){
				HttpEntity entity=reponse.getEntity();
				//返回结果类型转换
				result=EntityUtils.toString(entity, "utf-8");
			}else{
				result="服务器状态码："+statusCode+"请检查接口地址和请求参数";
			} 
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			reponse.close();
			httpclient.close();
		}
		return result;
	}

	public static void main(String[] args) throws Exception, IOException {
		

	}

}
