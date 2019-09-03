package dao;

import emptity.User;

public interface UserDao {
	public boolean saveUser(User user);
	public boolean delUser(int id);
	public boolean updateUser(User user);
	public User selectUser(User user);
}
