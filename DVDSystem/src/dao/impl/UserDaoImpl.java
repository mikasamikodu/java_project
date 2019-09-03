package dao.impl;

import java.util.ArrayList;
import java.util.List;

import dao.UserDao;
import emptity.User;

public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public boolean saveUser(User user) {
		String  sql="insert into users (uname,upass,utype)values(?,?,?)";
		List<Object> list=new ArrayList<Object>();
		list.add(user.getUname());
		list.add(user.getUpass());
		list.add(user.getUtype());
		return this.operUpdate(sql, list);
	}

	@Override
	public boolean delUser(int id) {
		String  sql="delete from users where id=?";
		List<Object> list=new ArrayList<Object>();
		list.add(id);
		return this.operUpdate(sql, list);
	}

	@Override
	public boolean updateUser(User user) {
		String  sql="update users set uname=?,upass=?,utype=? where id=?";
		List<Object> list=new ArrayList<Object>();
		list.add(user.getUname());
		list.add(user.getUpass());
		list.add(user.getUtype());
		list.add(user.getId());
		return this.operUpdate(sql, list); 
	}

	@Override
	public User selectUser(User user) {
		List<User> list1=null;
		String  sql="select id,uname,upass,utype from users where uname=? and upass=? and utype=?";
		List<Object> list=new ArrayList<Object>();
		list.add(user.getUname());
		list.add(user.getUpass());
		list.add(user.getUtype());
		try {
			list1=this.operQuery(sql, list, User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(list1.size()>0) {
			return list1.get(0);
		}
		return null;
	}

}
