package com.atguigu.manager.service;

import java.util.List;
import java.util.Map;

import com.atguigu.bean.Member;
import com.atguigu.bean.Permission;
import com.atguigu.bean.Role;
import com.atguigu.bean.User;
import com.atguigu.utils.Page;
import com.atguigu.vo.Data;

public interface UserService {

	User queryUserLogin(Map<String, Object> map);

	void doRegister(Member member);

	Page queryPage(Map<String, Object> map);

	int saveUser(User user);

	User findById(Integer id);

	int updateUser(User user);

	int deleteUserById(Integer id);

	int deleteUser(Integer[] ids);

	List<Role> queryAllRole();

	List<Integer> queryRoleByUserId(Integer id);

	void saveUserRole(Integer userid, Data data);

	int deleteBatchUser(Data data); 

	void removeUserRole(Integer userid, Data data);

	List<Permission> quertPermissionsByUserId(Integer id); 
}
