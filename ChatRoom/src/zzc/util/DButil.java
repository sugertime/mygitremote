package zzc.util;

/*
 * 	该类主要用于通过tomcat DBCP获取数据库的连接，进行数据库相关操作。
 * 	相关操作：数据的更新和结果的查询操作。
 *	注：使用该工具类需要配置数据库连接池。
 */

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DButil {
	public static Connection connection;
	public static PreparedStatement pstm;
	public static ResultSet rs;
	
	/*
	 * 获取连接函数。
	 * 获取上下文对象context。
	 * 利用上下文对象获取数据源对象DataSource ds。
	 * 利用数据源对象获取数据库的连接。
	 * 返回值为boolean，用于判断是否获取了连接，只是为静态对象connetion赋值，创建连接。
	 */
	public static boolean getConnection() {
		boolean target = false;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:comp/env/DBCP");
			connection = ds.getConnection();
			target = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return target;
	}
	
	/*
	 *	关闭连接的方法。
	 *	一次性关闭所有连接（ResultSet，PreparedStatement，Connection）。
	 *	返回值为boolean类型，用于查看是否正常关闭连接。
	 */
	public static boolean closeAll() {
		boolean target = false;
		try {
			if(rs!=null) rs.close();
			if(pstm!=null)pstm.close();
			if(connection!=null)connection.close();
			target = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return target;
	}
	
	/*
	 *	数据查询方法，传入参数为sql语句和要设置的参数。
	 *	返回值为boolean用于判断查询操作是否成功。
	 *	因为类中ResultSet变量为公有的静态变量，所以不需要返回ResultSet进行获取。
	 */
	public static boolean query(String sql,Object[] objs) {
		boolean target = false;
		try {
			pstm = connection.prepareStatement(sql);
			if(objs!=null) {
				int i = 0;
				while(i < objs.length) {
					pstm.setObject(i+1,objs[i]);
					i++;
				}
			}
			rs = pstm.executeQuery();
			target = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return target;
	}
	
	/*
	 * 	传入值为sql语句和Object数组参数。
	 *	更新数据方法，用于数据库的插入，更新，删除操作。
	 *	返回值为int用于判断几条数据受到了影响。
	 */
	public static int update(String sql,Object[] objs) {
		int num = 0;
		try {
			pstm = connection.prepareStatement(sql);
			int i = 0;
			while(i < objs.length) {
				pstm.setObject(i+1,objs[i]);
				i++;
			}
			num = pstm.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return num;
	}
}
