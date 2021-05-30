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
 * @Description:java基于数据库连接池操作数据库  
 * @author: chenjianlong    
 * @date:   2021年5月23日 下午2:04:55   
 * @version V1.0 
 * @Copyright: 2021 www.youceedu.com All rights reserved. 
 * 注意：本内容仅限于优测教育内部传阅，禁止外泄以及用于其他的商业目
 */

public class DbcpUtil {
	
	private static String sql="insert into autolog(testCase,reqType,reqUrl,reqData,expResult,actResult,result,execTime) values(?,?,?,?,?,?,?,?)";
	private static BasicDataSource pool=null;
	static{
		if(pool==null){
			pool=new BasicDataSource();
			
			//A 连接池连接db的基本信息
			pool.setDriverClassName("com.mysql.jdbc.Driver");
			pool.setUrl("jdbc:mysql://8.131.245.231:3306/interface?characterEncoding=utf-8");
			pool.setUsername("root");
			pool.setPassword("123456");
			
			//B 池子连接数配置
			pool.setInitialSize(10);//设置默认连接数
			pool.setMinIdle(10);//设置最小空闲
			pool.setMaxIdle(10);//设置最大空闲
			pool.setMaxActive(10);//设置最大连接数
			
			//C 借连接，归还连接相关配置
			pool.setMaxWait(2000);//设置最大等待时间
			pool.setTestOnBorrow(false);//池子借出连接前前检查连接有效性，默认值江true需改成false
			pool.setTestOnReturn(false);//归还连接前检查连接有效性默认值false
			
			//D 其他方法
			pool.setPoolPreparedStatements(true);//设置预编译默认值false
		}
	}
	
	/**
	 * 
	 * @Title: getConnection   
	 * @Description: 从池子借连接
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
	 * @Description: 批量插入
	 * @param: @param list
	 * @param: @param sql
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: int[]      
	 * @throws
	 */
	public static int[] paramSqlUpdate(List<AutoLog> list) throws Exception{
		//从连接池借连接
		Connection con=getConnection();
		
		//sql预编译
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
			ps.addBatch();//添加到ps的set数据集
		}//ps对象的数据集合=>sql
		
		//得到结果
		int[] count=ps.executeBatch();//批处理
		
		close(con,ps,null);
		return count;
	}
	/**
	 * 
	 * @Title: close   
	 * @Description: 释放资源和归还连接
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
