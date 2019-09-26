package zzc.dao;

import zzc.beans.User;

public interface IUserDao {
	boolean addUser(User user);
	boolean isExist(User user);
	User queryUserMessage(User user);
	boolean deleteUser(User user);
}















