package zzc.service.impl;

import zzc.beans.User;
import zzc.dao.IUserDao;
import zzc.dao.impl.UserDaoImpl;
import zzc.service.IUserService;

public class UserServiceImpl implements IUserService {
	private IUserDao userdaoimpl = new UserDaoImpl();
	
	@Override
	public boolean addUser(User user) {
		boolean target = false;
		if(!userdaoimpl.isExist(user)) {
			target = userdaoimpl.addUser(user);
		}
		return target;
	}

	@Override
	public boolean isExist(User user) {
		boolean target = false;
		target = userdaoimpl.isExist(user);
		return target;
	}

	@Override
	public User showUserMes(User user) {
		User temp;
		if(userdaoimpl.isExist(user)) {
			temp = userdaoimpl.queryUserMessage(user);
		}else {
			temp = new User();
		}
		return temp;
	}
	
}
