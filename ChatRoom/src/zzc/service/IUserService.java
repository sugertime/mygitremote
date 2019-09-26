package zzc.service;

import zzc.beans.User;

public interface IUserService {
	boolean addUser(User user);
	boolean isExist(User user);
	User showUserMes(User user);
}
