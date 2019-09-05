package com.itheima.dao;

import com.itheima.domain.User;

import java.util.List;

public interface IUserDao {
    /**
     * 查询所有用户
     *
     */
    List<User> findAll();

    /**
     * 通过id查询用户信息
     * @param id 用户id
     * @return 用户信息
     */
    User findById(Integer id);
}
