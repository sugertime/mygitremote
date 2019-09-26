package zzc.dao.impl;

import java.sql.SQLException;

import zzc.beans.User;
import zzc.dao.IUserDao;
import zzc.util.DButil;

public class UserDaoImpl implements IUserDao{

	@Override
	public boolean addUser(User user) {
		boolean target = false;
		String sql = "insert into user(uid,uname,upwd,uday) values(?,?,?,?)";
		
		String id = user.getUid();
		String name = user.getUname();
		String pwd = user.getUpassword();
		String day = user.getRegisterday();
		
		Object[] objs = {id,name,pwd,day};
		
		DButil.getConnection();
		int i = DButil.update(sql, objs);
		if(i>0) {
			target = true;
		}
		DButil.closeAll();
		return target;
	}

	@Override
	public boolean isExist(User user) {
		boolean target = false;
		String sql = "select count(1) from user where uid=? and uname=? and upwd=?";
		int i = 0;
		
		String id = user.getUid();
		String name = user.getUname();
		String pwd = user.getUpassword();
		
		Object[] objs = {id,name,pwd};
		
		DButil.getConnection();
		DButil.query(sql, objs);
		try {
			while(DButil.rs.next()) {
				i = DButil.rs.getInt("count(1)");
			}
			if(i>0) {
				target = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DButil.closeAll();
		}

		return target;
	}

	@Override
	public User queryUserMessage(User user) {
		String sql = "select uid,uname,uday from user where id=? and password=?";
		User u = new User();
		
		String id = user.getUid();
		String password = user.getUname();
		
		Object[] objs = {id,password};
		
		DButil.getConnection();
		DButil.query(sql, objs);
		try {
			while(DButil.rs.next()) {
				u.setUid(DButil.rs.getString("uid"));
				u.setUname(DButil.rs.getString("uname"));
				u.setRegisterday(DButil.rs.getString("uday"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DButil.closeAll();
		}
		return u;
	}

	@Override
	public boolean deleteUser(User user) {
		return false;
	}
	
}
