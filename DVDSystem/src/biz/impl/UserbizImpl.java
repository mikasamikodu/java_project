package biz.impl;

import biz.Userbiz;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import emptity.User;

public class UserbizImpl implements Userbiz {
	private UserDao userDao=null;
	
	public UserbizImpl() {
		userDao=new UserDaoImpl();
	}
	@Override
	public User login(User user) {
		return userDao.selectUser(user);
	}

	@Override
	public int register(User user) {
		if(userDao.selectUser(user)!=null) {
			return 1;
		}else {
			boolean u1=userDao.saveUser(user);
			if(u1) {
				return 2;
			}else {
				return 3;
			}
		}
	}

}
