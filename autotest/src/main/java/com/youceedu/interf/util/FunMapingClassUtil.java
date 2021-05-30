package com.youceedu.interf.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.youceedu.interf.functions.Function;

public class FunMapingClassUtil {
	/*
	 * ��ʼ��
	 * funMap���ݸ�ʽ��{"Random"=>class com.youceedu.interf.functions.RandomFunction}
	 * 
	 * ������
	 * 1.�õ�funMap��value
	 * 2.�õ�funMap��key
	 */
	
	private static Map<String,Class<? extends Function>> funcMap=new HashMap<String,Class<? extends Function>>();
	
	static{
		try{
			//1.�õ�funMap��value--Function�������
			
			//1-1�ݰ���·���õ�Function�������
			Class<?> function=Class.forName("com.youceedu.interf.functions.Function");
			String pk=function.getPackage().getName();
			String path=pk.replace(".", "/");
			String classPath=function.getClassLoader().getResource(path).getPath();
			File file=new File(classPath);//����file����
			File[] files=file.listFiles();//չʾһ�����飬�����������ļ����ֺ�Ŀ¼����
			for(File f:files){
				String fileName=f.getName();//�õ��ļ�����
				if(fileName.endsWith(".class")){//Ϊ�˸��Ͻ����жϣ�ֻҪ��׺��.class���ļ�
					String className=pk+"."+fileName.substring(0,fileName.length()-6);
					Class<?> c= Class.forName(className);//�ַ�����ת��Ϊ��������
					//c����Function�������
					if(!function.equals(c)&&function.isAssignableFrom(c)){
						//2.�õ�funMap��key
						Function funcObject=(Function)c.newInstance();
						String funName=funcObject.getFunc();
						funcMap.put(funName,funcObject.getClass());
					}
				}
			}
			System.out.println(funcMap.get("Random"));//����
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Title: isFunc   
	 * @Description: �ж�funMap�Ƿ����funName
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
	 * @Description: ��funName�õ�Autotest���̶�Ӧ���࣬
	 * �����ڶ������execParam����ִ���û�����
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
