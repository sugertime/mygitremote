package zzc.beans;

/*
 *	User类，封装User数据。
 *	数据包括：用户id，用户姓名，密码，注册日期。
 */

public class User {
	private String uid;
	private String uname;
	private String upassword;
	private String registerday;
	
	
	public User() {
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}

	public String getRegisterday() {
		return registerday;
	}

	public void setRegisterday(String registerday) {
		this.registerday = registerday;
	}
	
}
