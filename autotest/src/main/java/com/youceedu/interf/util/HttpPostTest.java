package com.youceedu.interf.util;

import java.io.IOException;


import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpPostTest {
	/**
	 * 
	 * @Title: sendPost   
	 * @Description: 发送post请求
	 * @param: @param url
	 * @param: @param param
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: String      
	 * @throws
	 */
	public String sendPost(String url,String param) throws Exception{
	    //初始化
		String result=null;
		CloseableHttpClient httpclient=null;
		CloseableHttpResponse reponse=null;
		try{
			//得到httpclient对象
			httpclient = HttpClients.createDefault();
			//得到请求对象
			HttpPost httpPost = new HttpPost(url);
			//phone=13459510007&passWord=123456&platform=Web&equipment=firefox+87.0
			
			//请求对象绑定请求数据
			httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
			StringEntity stringEntity=new StringEntity(param,"utf-8");
			httpPost.setEntity(stringEntity);
			
			//发送请求
			reponse=httpclient.execute(httpPost);
			
			//据状态码得到返回值
			int statusCode=reponse.getStatusLine().getStatusCode();
			if(statusCode==HttpStatus.SC_OK){
				HttpEntity entity=reponse.getEntity();
				//返回值结果类型转换
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
