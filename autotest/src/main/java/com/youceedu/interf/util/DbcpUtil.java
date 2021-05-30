package com.youceedu.interf.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.dbcp.BasicDataSource;

import com.youceedu.interf.model.AutoLog;
/**
 * 
 * @Title:  DbcpUtil1.java   
 * @Package com.youceedu.tools.db.util   
 * @Description:java�������ݿ����ӳز������ݿ�  
 * @author: chenjianlong    
 * @date:   2021��5��23�� ����2:04:55   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * ע�⣺�����ݽ������Ų�����ڲ����ģ���ֹ��й�Լ�������������ҵĿ
 */

public class DbcpUtil {
	
	private static String sql="insert into autolog(testCase,reqType,reqUrl,reqData,expResult,actResult,result,execTime) values(?,?,?,?,?,?,?,?)";
	private static BasicDataSource pool=null;
	static{
		if(pool==null){
			pool=new BasicDataSource();
			
			//A ���ӳ�����db�Ļ�����Ϣ
			pool.setDriverClassName("com.mysql.jdbc.Driver");
			pool.setUrl("jdbc:mysql://8.131.245.231:3306/interface?characterEncoding=utf-8");
			pool.setUsername("root");
			pool.setPassword("123456");
			
			//B ��������������
			pool.setInitialSize(10);//����Ĭ��������
			pool.setMinIdle(10);//������С����
			pool.setMaxIdle(10);//����������
			pool.setMaxActive(10);//�������������
			
			//C �����ӣ��黹�����������
			pool.setMaxWait(2000);//�������ȴ�ʱ��
			pool.setTestOnBorrow(false);//���ӽ������ǰǰ���������Ч�ԣ�Ĭ��ֵ��true��ĳ�false
			pool.setTestOnReturn(false);//�黹����ǰ���������Ч��Ĭ��ֵfalse
			
			//D ��������
			pool.setPoolPreparedStatements(true);//����Ԥ����Ĭ��ֵfalse
		}
	}
	
	/**
	 * 
	 * @Title: getConnection   
	 * @Description: �ӳ��ӽ�����
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: Connection      
	 * @throws
	 */
	public static synchronized Connection getConnection() throws Exception{
		return pool.getConnection();
	}
	/**
	 * 
	 * @Title: paramSqlUpdate   
	 * @Description: ��������
	 * @param: @param list
	 * @param: @param sql
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: int[]      
	 * @throws
	 */
	public static int[] paramSqlUpdate(List<AutoLog> list) throws Exception{
		//�����ӳؽ�����
		Connection con=getConnection();
		
		//sqlԤ����
		PreparedStatement ps=con.prepareStatement(sql);
		for(AutoLog autolog:list){
			ps.setString(1, autolog.getTestCase());
			ps.setString(2, autolog.getReqType());
			ps.setString(3, autolog.getReqUrl());
			ps.setString(4, autolog.getReqData());
			ps.setString(5, autolog.getExpResult());
			ps.setString(6, autolog.getActResult());
			ps.setInt(7, autolog.getResult());
			ps.setString(8,autolog.getExecTime());
			ps.addBatch();//��ӵ�ps��set���ݼ�
		}//ps��������ݼ���=>sql
		
		//�õ����
		int[] count=ps.executeBatch();//������
		
		close(con,ps,null);
		return count;
	}
	/**
	 * 
	 * @Title: close   
	 * @Description: �ͷ���Դ�͹黹����
	 * @param: @param con
	 * @param: @param ps
	 * @param: @param rs
	 * @param: @throws Exception      
	 * @return: void      
	 * @throws
	 */
	public static void close(Connection con,PreparedStatement ps,ResultSet rs) throws Exception{
		if(rs!=null){
			rs.close();
		}
		
		if(ps!=null){
			ps.close();
		}
		
		if(con!=null){
			con.close();
		}
	}
	
	public static void main(String[] args) throws Exception {

		
	}

}
