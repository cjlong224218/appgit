package com.youceedu.interf.util;

import java.util.Random;

public class RandomUtil {
	
	private static Random r=new Random();
	private static String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; 
	
	/**
	 * 
	 * @Title: getRandomInt   
	 * @Description: 得到[min,max)范围内的随机整数
	 * @param: @param min
	 * @param: @param max
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	 
	public static int getRandomInt(int min,int max){
		//实例化
		return r.nextInt(max-min+1)+min;
	}
	
	/**
	 * 
	 * @Title: getRandomlong   
	 * @Description:得到long范围的随机数
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
	 * @Description: 得到伪随机数true和false
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
	 * @Description: 得到[min,max)范围内的随机数
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
	 * @Description: 得到[min,max)范围内的伪随机数
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
	 * @Description: 得到指定长度字符串
	 * @param: @param length
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String getRandomString(int length){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<length;i++){
			int number=r.nextInt(str.length());
			sb.append(str.charAt(number)); //append 在一个堆内存叠加
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
