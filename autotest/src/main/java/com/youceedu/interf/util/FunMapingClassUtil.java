package com.youceedu.interf.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.youceedu.interf.functions.Function;

public class FunMapingClassUtil {
	/*
	 * 初始化
	 * funMap数据格式：{"Random"=>class com.youceedu.interf.functions.RandomFunction}
	 * 
	 * 方案：
	 * 1.得到funMap的value
	 * 2.得到funMap的key
	 */
	
	private static Map<String,Class<? extends Function>> funcMap=new HashMap<String,Class<? extends Function>>();
	
	static{
		try{
			//1.得到funMap的value--Function类的子类
			
			//1-1据包名路径得到Function类的子类
			Class<?> function=Class.forName("com.youceedu.interf.functions.Function");
			String pk=function.getPackage().getName();
			String path=pk.replace(".", "/");
			String classPath=function.getClassLoader().getResource(path).getPath();
			File file=new File(classPath);//创建file对象
			File[] files=file.listFiles();//展示一个数组，这个数组包含文件名字和目录名字
			for(File f:files){
				String fileName=f.getName();//得到文件名字
				if(fileName.endsWith(".class")){//为了更严谨做判断，只要后缀是.class的文件
					String className=pk+"."+fileName.substring(0,fileName.length()-6);
					Class<?> c= Class.forName(className);//字符串类转换为真正的类
					//c类是Function类的子类
					if(!function.equals(c)&&function.isAssignableFrom(c)){
						//2.得到funMap的key
						Function funcObject=(Function)c.newInstance();
						String funName=funcObject.getFunc();
						funcMap.put(funName,funcObject.getClass());
					}
				}
			}
			System.out.println(funcMap.get("Random"));//测试
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Title: isFunc   
	 * @Description: 判断funMap是否包含funName
	 * @param: @param funcName
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public static boolean isFunc(String funcName){
		return funcMap.containsKey(funcName);
	}
	
	/**
	 * 
	 * @Title: getValue   
	 * @Description: 据funName得到Autotest工程对应的类，
	 * 并基于对象调用execParam方法执行用户参数
	 * @param: @param funcName
	 * @param: @param args
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: String      
	 * @throws
	 */
	public static String getValue(String funcName,String[] args) throws Exception {
		return funcMap.get(funcName).newInstance().execParam(args);
	}
	
	public static void main(String[] args) throws Exception {
		
	}

}
