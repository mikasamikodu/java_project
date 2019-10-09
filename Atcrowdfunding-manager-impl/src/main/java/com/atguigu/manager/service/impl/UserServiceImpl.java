package com.atguigu.manager.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.Member;
import com.atguigu.bean.Permission;
import com.atguigu.bean.Role;
import com.atguigu.bean.User;
import com.atguigu.exception.LoginFailException;
import com.atguigu.manager.dao.RoleMapper;
import com.atguigu.manager.dao.UserMapper;
import com.atguigu.manager.dao.UserRoleMapper;
import com.atguigu.manager.service.UserService;
import com.atguigu.utils.Const;
import com.atguigu.utils.MD5Util;
import com.atguigu.utils.Page;
import com.atguigu.vo.Data;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public User queryUserLogin(Map<String, Object> map) {
		User user = userMapper.queryUserlogin(map);
		if(user == null) {
			throw new LoginFailException("用户名或密码不正确");
		}
		return user;
	}

	@Override
	public void doRegister(Member member) {
		User user = new User();
		user.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString());
		user.setEmail(member.getEmail());
		user.setLoginacct(member.getLoginacct());
		user.setUsername(member.getUsername());
		user.setUserpswd(member.getUserpswd());
		userMapper.doRegister(member);
		userMapper.insert(user);
	}

	@Override
	public Page queryPage(Map<String,Object> map) {
		Page page = new Page((Integer) map.get( "pageNo" ), (Integer) map.get( "pageSize" ));
		Integer startIndex = page.getStartIndex();
		map.put("startIndex", startIndex);
		List<User> users = userMapper.queryList(map);
		page.setDatas(users);
		page.setTotalSize(userMapper.queryCount(map));
		return page;
	}

	@Override
	public int saveUser(User user) {
		user.setUserpswd(MD5Util.digest(Const.PASSWORD));
		user.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		int result = userMapper.insert(user);		
		return result;
	}
	
	@Override
	public int updateUser(User user) {
		int result = userMapper.updateByPrimaryKey(user);		
		return result;
	}

	@Override
	public User findById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int deleteUserById(Integer id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteUser(Integer[] ids) {
		int count = 0;
		for(Integer id: ids) {
			count += userMapper.deleteByPrimaryKey(id);
		}
		return count;
	}

	@Override
	public List<Role> queryAllRole() {
		return roleMapper.selectAll();
	}

	@Override
	public List<Integer> queryRoleByUserId(Integer id){
		return userRoleMapper.queryRoleByUserId(id);
	}

	@Override
	public void saveUserRole(Integer userid, Data data) {
		userRoleMapper.insert(userid, data);
	}

	
	  @Override 
	  public int deleteBatchUser(Data data) { 
		  return  userMapper.deleteBatchUser(data);
	  }
	 

	
	  @Override 
	  public void removeUserRole(Integer userid, Data data) { 
		  userRoleMapper.deleteByUR(userid, data); 
	  }

	@Override
	public List<Permission> quertPermissionsByUserId(Integer id) {
		return userMapper.queryPermissionsByUserId(id);
	}
	 
}
