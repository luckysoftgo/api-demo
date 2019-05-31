package com.application.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @NAME: DBHelper
 * @DESC: 数据处理类.
 * @USER: 孤狼.
 **/
public class DBHelper {
	
	/**	纯 java 式的连接
	 * 定义常量来存储配置
	 */
	public static final String DRIVER="com.mysql.jdbc.Driver";  					//使用的数据库连接
	
	public static final String URL="jdbc:mysql://192.168.10.70:3306/sme_integrated_finance_ronghe?useUnicode=true&characterEncoding=utf8"; 	//定义使用的数据库
	
	public static final String USER="root";      															//使用者的名字
	
	public static final String PASS="x5";        															//使用者的密码
	
	
	Connection conn=null;						//得到与数据库的连接
	PreparedStatement pstmt=null;				//执行 SQL 语句的方法
	ResultSet rs=null;							//存储的结果集 rs
	
	/**
	 * 得到数据库连接
	 */
	public Connection getConn()throws ClassNotFoundException,SQLException{
		
		Class.forName(DRIVER);                           //得到数据驱动
		conn= DriverManager.getConnection(URL,USER,PASS); //获得连接
		return conn;
	}
	
	
	/**
	 * 要执行的增 ，删 ，改 的操作，不执行查询 (注意参数的使用)
	 */
	public int executeSQL(String preparedSql,String [] param){
		
		int count =0;
		/**
		 * 执行的操作
		 */
		try {
			conn=this.getConn();					//获得连接
			pstmt=conn.prepareStatement(preparedSql);//要执行的 sql 语句
			if(param!=null){
				for (int i = 0; i < param.length; i++) {
					pstmt.setString(1+i, param [i]);//为预编译sql设置参数
				}
			}
			
			count=pstmt.executeUpdate();			//执行 sql 语句
			
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();					 // 处理SQLException异常
		}finally{
			this.closeAll(conn, pstmt, null);			 //释放资源
		}
		return count;								  //返回结果
	}
	
	
	/**
	 * 使用PreparedStatement查询数据
	 * @param sql
	 * @param param 参数列表
	 * @return 结果集   不要关闭连接
	 */
	public ResultSet selectSQL(String sql,String [] param){
		
		try {
			conn=this.getConn();
			pstmt=conn.prepareStatement(sql);           //执行sql语句
			for (int i = 0; i < param.length; i++) {
				pstmt.setString(i+1, param[i]);
			}
			rs=pstmt.executeQuery();                    //执行的结果
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return rs;
	}
	
	
	/**
	 * 使用statement执行查询
	 * @param sql 执行的SQL语句
	 * @return    不可关闭连接
	 */
	public ResultSet stQuery(String sql){
		try {
			conn=this.getConn();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * 关闭所有的接口 (注意括号中的参数)
	 */
	public void close(){
		//判断是否关闭，要时没有关闭，就让它关闭，并给它附一空值（null）,下同
		if(pstmt!=null){
			try {
				pstmt.close();
				pstmt=null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();   //异常处理
			}
		}
		if(rs!=null){
			try {
				rs.close();
				rs=null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();   //异常处理
			}
		}
		if(conn!=null){
			try {
				conn.close();
				conn=null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();  //异常处理
			}
		}
		
	}
	
	/**
	 * 关闭所有的接口 (注意括号中的参数)
	 */
	public void closeAll(Connection conn,PreparedStatement pstmt,ResultSet rs){
		//判断是否关闭，要时没有关闭，就让它关闭，并给它附一空值（null）,下同
		
		if(pstmt!=null){
			try {
				pstmt.close();
				pstmt=null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();   //异常处理
			}
		}
		if(rs!=null){
			try {
				rs.close();
				rs=null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();   //异常处理
			}
		}
		if(conn!=null){
			try {
				conn.close();
				conn=null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();  //异常处理
			}
		}
		
	}
	
	/**
	 * 编写测试类来进行对数据库的检验
	 */
	public static void main(String[] args) {
		DBHelper manager=new DBHelper();
		try {
			System.out.println(manager.getConn().isClosed());
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();                     //抛出异常
		}
	}
	
}
