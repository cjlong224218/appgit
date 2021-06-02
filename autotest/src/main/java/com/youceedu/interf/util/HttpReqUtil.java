package com.youceedu.interf.util;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;



/**
 * 
 * @Title:  HttpReqUtil.java   
 * @Package com.youceedu.tools.http   
 * @Description: java发送http/https请求  
 * @author: chenjianlong    
 * @date:   2021年4月10日 下午6:55:29   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * 注意：本内容仅限于优测教育内部传阅，禁止外泄以及用于其他的商业目
 */

public class HttpReqUtil {
	
	/**
	 * 初始化
	 */
	private static CookieStore cookieStore=new BasicCookieStore();
	
	/**
	 * 
	 * @Title: httpRequeestCong   
	 * @Description: 配置请求header
	 * @param: @param httpRequestBase
	 * @param: @param param      
	 * @return: void      
	 * @throws
	 */
	public static void httpRequeestCong(HttpRequestBase httpRequestBase,String param){
		httpRequestBase.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:87.0) Gecko/20100101 Firefox/87.0");
		
		if(JsonUtil.isJsonArray(param)||JsonUtil.isJsonString(param)){
			httpRequestBase.setHeader("Content-Type", "application/json; charset=UTF-8");
		}else{
			httpRequestBase.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		}
		//{"name":"123352","password":"fasdfc"}
		
		//配置请求超时
		/*
		 * 
		Builder builder=RequestConfig.custom();
		builder.setConnectionRequestTimeout(2000);
		RequestConfig requestConfig=builder.build();
		httpRequestBase.setConfig(requestConfig);
		*/
		RequestConfig requestConfig=RequestConfig.custom()
								.setConnectionRequestTimeout(2000)
								.build();
		httpRequestBase.setConfig(requestConfig);
	}
	
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
	
	public static String sendGet(String url,String param) throws Exception{
		//初始化
		String result=null;
		String finalUrl=url+"?"+param;
		CloseableHttpClient httpclient=null;
		CloseableHttpResponse reponse=null;
		try{
			//得到httpclient对象
			
			//定制化开发
			/*			 
			HttpClientBuilder httpClientBuilder=HttpClients.custom();
			CookieStore  cookieStore=new BasicCookieStore();
			httpClientBuilder.setDefaultCookieStore(cookieStore);
			httpclient=httpClientBuilder.build();
			*/
			httpclient=HttpClients.custom()
					.setDefaultCookieStore(cookieStore)
					.build();
			
			
			
			
			//发送请求、配置header和配置请求超时
			HttpGet httpGet = new HttpGet(finalUrl);
			httpRequeestCong(httpGet,param);
			
			//配置请求超时
//			Builder builder=RequestConfig.custom();
//			builder.setConnectionRequestTimeout(2000);
//			RequestConfig requestConfig=builder.build();
//			httpGet.setConfig(requestConfig);
			
			reponse=httpclient.execute(httpGet);
			
			//据状态码得到返回值
			int statusCode=reponse.getStatusLine().getStatusCode();
			if(statusCode==HttpStatus.SC_OK){
				HttpEntity entity=reponse.getEntity();
				//返回结果类型转换
				//result=EntityUtils.toString(entity, "utf-8");
				result=JSON.parseObject(EntityUtils.toString(entity, "utf-8")).toJSONString();
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
	public static String sendPost(String url,String param) throws Exception{
	    //初始化
		String result=null;
		CloseableHttpClient httpclient=null;
		CloseableHttpResponse reponse=null;
		try{
			//得到httpclient对象
			
			//定制化开发
			httpclient=HttpClients.custom()
					.setDefaultCookieStore(cookieStore)
					.build();
			//得到请求对象
			HttpPost httpPost = new HttpPost(url);
			//phone=13459510007&passWord=123456&platform=Web&equipment=firefox+87.0
			
			
			//请求对象绑定请求数据
			httpRequeestCong(httpPost,param);
			//httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
			StringEntity stringEntity=new StringEntity(param,"utf-8");
			httpPost.setEntity(stringEntity);
			
			
			Builder builder=RequestConfig.custom();
			builder.setConnectionRequestTimeout(2000);
			RequestConfig requestConfig=builder.build();
			httpPost.setConfig(requestConfig);
			
			//发送请求
			reponse=httpclient.execute(httpPost);
			
			
			//据状态码得到返回值
			int statusCode=reponse.getStatusLine().getStatusCode();
			if(statusCode==HttpStatus.SC_OK){
				HttpEntity entity=reponse.getEntity();
				//返回值结果类型转换
				//result=EntityUtils.toString(entity, "utf-8");
				result=JSON.parseObject(EntityUtils.toString(entity, "utf-8")).toJSONString();
				
			}else{
				result="服务器状态码："+statusCode+"请检查接口地址和请求参数";
			} 
			List<Cookie> list=cookieStore.getCookies();
			for(Cookie cookie:list){
				//System.out.println(cookie.getName()+ " " + cookie.getValue());
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			reponse.close();
			httpclient.close();
		}
		return result;
	}
	
	public static void main(String[] args) throws Exception {
//		String url="http://8.131.245.231:8080/reader_main.html";
//		String param="id=10006&passwd=123456";
//		String result=sendPost(url,param);
//		System.out.println(result);
	
		
	}
}
