package zzc.beans;

/*
 *	User�࣬��װUser���ݡ�
 *	���ݰ������û�id���û����������룬ע�����ڡ�
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
