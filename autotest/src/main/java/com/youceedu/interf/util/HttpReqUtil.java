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
 * @Description: java����http/https����  
 * @author: chenjianlong    
 * @date:   2021��4��10�� ����6:55:29   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * ע�⣺�����ݽ������Ų�����ڲ����ģ���ֹ��й�Լ�������������ҵĿ
 */

public class HttpReqUtil {
	
	/**
	 * ��ʼ��
	 */
	private static CookieStore cookieStore=new BasicCookieStore();
	
	/**
	 * 
	 * @Title: httpRequeestCong   
	 * @Description: ��������header
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
		
		//��������ʱ
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
	 * @Description: ����get����
	 * @param: @param url
	 * @param: @param param
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: String      
	 * @throws
	 */
	
	public static String sendGet(String url,String param) throws Exception{
		//��ʼ��
		String result=null;
		String finalUrl=url+"?"+param;
		CloseableHttpClient httpclient=null;
		CloseableHttpResponse reponse=null;
		try{
			//�õ�httpclient����
			
			//���ƻ�����
			/*			 
			HttpClientBuilder httpClientBuilder=HttpClients.custom();
			CookieStore  cookieStore=new BasicCookieStore();
			httpClientBuilder.setDefaultCookieStore(cookieStore);
			httpclient=httpClientBuilder.build();
			*/
			httpclient=HttpClients.custom()
					.setDefaultCookieStore(cookieStore)
					.build();
			
			
			
			
			//������������header����������ʱ
			HttpGet httpGet = new HttpGet(finalUrl);
			httpRequeestCong(httpGet,param);
			
			//��������ʱ
//			Builder builder=RequestConfig.custom();
//			builder.setConnectionRequestTimeout(2000);
//			RequestConfig requestConfig=builder.build();
//			httpGet.setConfig(requestConfig);
			
			reponse=httpclient.execute(httpGet);
			
			//��״̬��õ�����ֵ
			int statusCode=reponse.getStatusLine().getStatusCode();
			if(statusCode==HttpStatus.SC_OK){
				HttpEntity entity=reponse.getEntity();
				//���ؽ������ת��
				//result=EntityUtils.toString(entity, "utf-8");
				result=JSON.parseObject(EntityUtils.toString(entity, "utf-8")).toJSONString();
			}else{
				result="������״̬�룺"+statusCode+"����ӿڵ�ַ���������";
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
	 * @Description: ����post����
	 * @param: @param url
	 * @param: @param param
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: String      
	 * @throws
	 */
	public static String sendPost(String url,String param) throws Exception{
	    //��ʼ��
		String result=null;
		CloseableHttpClient httpclient=null;
		CloseableHttpResponse reponse=null;
		try{
			//�õ�httpclient����
			
			//���ƻ�����
			httpclient=HttpClients.custom()
					.setDefaultCookieStore(cookieStore)
					.build();
			//�õ��������
			HttpPost httpPost = new HttpPost(url);
			//phone=13459510007&passWord=123456&platform=Web&equipment=firefox+87.0
			
			
			//����������������
			httpRequeestCong(httpPost,param);
			//httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
			StringEntity stringEntity=new StringEntity(param,"utf-8");
			httpPost.setEntity(stringEntity);
			
			
			Builder builder=RequestConfig.custom();
			builder.setConnectionRequestTimeout(2000);
			RequestConfig requestConfig=builder.build();
			httpPost.setConfig(requestConfig);
			
			//��������
			reponse=httpclient.execute(httpPost);
			
			
			//��״̬��õ�����ֵ
			int statusCode=reponse.getStatusLine().getStatusCode();
			if(statusCode==HttpStatus.SC_OK){
				HttpEntity entity=reponse.getEntity();
				//����ֵ�������ת��
				//result=EntityUtils.toString(entity, "utf-8");
				result=JSON.parseObject(EntityUtils.toString(entity, "utf-8")).toJSONString();
				
			}else{
				result="������״̬�룺"+statusCode+"����ӿڵ�ַ���������";
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
