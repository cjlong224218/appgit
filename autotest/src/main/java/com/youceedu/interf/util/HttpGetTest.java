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
 * @Description:  ����get����  
 * @author: chenjianlong    
 * @date:   2021��4��7�� ����9:16:53   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * ע�⣺�����ݽ������Ų�����ڲ����ģ���ֹ��й�Լ�������������ҵĿ
 */
public class HttpGetTest {
	/**
	 * 
	 * @Title: sendGet   
	 * @Description:����get����
	 * @param: @param url
	 * @param: @param param
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: String      
	 * @throws
	 */
	
	public String sendGet(String url,String param) throws Exception{
		//��ʼ��
		String result=null;
		String finalUrl=url+"?"+param;
		CloseableHttpClient httpclient=null;
		CloseableHttpResponse reponse=null;
		try{
			//�õ�httpclient����
			httpclient = HttpClients.createDefault();
			//��������
			HttpGet httpGet = new HttpGet(finalUrl);
			reponse=httpclient.execute(httpGet);
			
			//��״̬��õ�����ֵ
			int statusCode=reponse.getStatusLine().getStatusCode();
			if(statusCode==HttpStatus.SC_OK){
				HttpEntity entity=reponse.getEntity();
				//���ؽ������ת��
				result=EntityUtils.toString(entity, "utf-8");
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

	public static void main(String[] args) throws Exception, IOException {
		

	}

}
