package zzc.util;

/*
 * 	������Ҫ����ͨ��tomcat DBCP��ȡ���ݿ�����ӣ��������ݿ���ز�����
 * 	��ز��������ݵĸ��ºͽ���Ĳ�ѯ������
 *	ע��ʹ�øù�������Ҫ�������ݿ����ӳء�
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
	 * ��ȡ���Ӻ�����
	 * ��ȡ�����Ķ���context��
	 * ���������Ķ����ȡ����Դ����DataSource ds��
	 * ��������Դ�����ȡ���ݿ�����ӡ�
	 * ����ֵΪboolean�������ж��Ƿ��ȡ�����ӣ�ֻ��Ϊ��̬����connetion��ֵ���������ӡ�
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
	 *	�ر����ӵķ�����
	 *	һ���Թر��������ӣ�ResultSet��PreparedStatement��Connection����
	 *	����ֵΪboolean���ͣ����ڲ鿴�Ƿ������ر����ӡ�
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
	 *	���ݲ�ѯ�������������Ϊsql����Ҫ���õĲ�����
	 *	����ֵΪboolean�����жϲ�ѯ�����Ƿ�ɹ���
	 *	��Ϊ����ResultSet����Ϊ���еľ�̬���������Բ���Ҫ����ResultSet���л�ȡ��
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
	 * 	����ֵΪsql����Object���������
	 *	�������ݷ������������ݿ�Ĳ��룬���£�ɾ��������
	 *	����ֵΪint�����жϼ��������ܵ���Ӱ�졣
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
