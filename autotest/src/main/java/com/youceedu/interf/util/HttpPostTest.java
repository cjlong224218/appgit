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
	 * @Description: ����post����
	 * @param: @param url
	 * @param: @param param
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: String      
	 * @throws
	 */
	public String sendPost(String url,String param) throws Exception{
	    //��ʼ��
		String result=null;
		CloseableHttpClient httpclient=null;
		CloseableHttpResponse reponse=null;
		try{
			//�õ�httpclient����
			httpclient = HttpClients.createDefault();
			//�õ��������
			HttpPost httpPost = new HttpPost(url);
			//phone=13459510007&passWord=123456&platform=Web&equipment=firefox+87.0
			
			//����������������
			httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
			StringEntity stringEntity=new StringEntity(param,"utf-8");
			httpPost.setEntity(stringEntity);
			
			//��������
			reponse=httpclient.execute(httpPost);
			
			//��״̬��õ�����ֵ
			int statusCode=reponse.getStatusLine().getStatusCode();
			if(statusCode==HttpStatus.SC_OK){
				HttpEntity entity=reponse.getEntity();
				//����ֵ�������ת��
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
