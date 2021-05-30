package com.youceedu.interf.util;

import java.util.Random;

public class RandomUtil {
	
	private static Random r=new Random();
	private static String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; 
	
	/**
	 * 
	 * @Title: getRandomInt   
	 * @Description: �õ�[min,max)��Χ�ڵ��������
	 * @param: @param min
	 * @param: @param max
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	 
	public static int getRandomInt(int min,int max){
		//ʵ����
		return r.nextInt(max-min+1)+min;
	}
	
	/**
	 * 
	 * @Title: getRandomlong   
	 * @Description:�õ�long��Χ�������
	 * @param: @return      
	 * @return: long      
	 * @throws
	 */
	public static long getRandomLong(){
		return r.nextLong();
	}
	
	/**
	 * 
	 * @Title: getRandomBoolean   
	 * @Description: �õ�α�����true��false
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public static boolean getRandomBoolean(){
		return r.nextBoolean();
	}
	
	/**
	 * 
	 * @Title: getRandomFloat   
	 * @Description: �õ�[min,max)��Χ�ڵ������
	 * @param: @param min
	 * @param: @param max
	 * @param: @return      
	 * @return: float      
	 * @throws
	 */
	public static float getRandomFloat(float min,float max){
		return r.nextFloat()*(max-min)+min;
	}
	
	/**
	 * 
	 * @Title: getRandomDouble   
	 * @Description: �õ�[min,max)��Χ�ڵ�α�����
	 * @param: @param min
	 * @param: @param max
	 * @param: @return      
	 * @return: double      
	 * @throws
	 */
	public static double getRandomDouble(double min,double max){
		return r.nextDouble()*(max-min)+min;
	}
	
	/**
	 * 
	 * @Title: getRandomString   
	 * @Description: �õ�ָ�������ַ���
	 * @param: @param length
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String getRandomString(int length){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<length;i++){
			int number=r.nextInt(str.length());
			sb.append(str.charAt(number)); //append ��һ�����ڴ����
			//System.out.println(str.charAt(number));
		}
		return sb.toString();
	}  
	
	public static void main(String[] args) {
		//System.out.println(getRandomInt(3,5));
		//System.out.println(getRandomLong());
		//System.out.println(getRandomBoolean());
		//System.out.println(getRandomFloat(3.6f,5.7f));
		//System.out.println(getRandomDouble(3.6d,6.6d));
		//System.out.println(getRandomString(7));

	}

}
