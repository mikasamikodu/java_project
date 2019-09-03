package com.itheima.dao;

import com.itheima.domain.QueryVo;
import com.itheima.domain.User;

import java.util.List;

public interface IUserDao {
    /**
     * 查询所有用户
     *
     */
    List<User> findAll();

    /**
     * 保存用户信息
     * @param user 用户信息
     */
    void save(User user);

    /**
     * 更新用户信息
     * @param user  用户信息
     */
    void update(User user);

    /**
     * 根据id删除用户
     * @param id  用户id
     */
    void delete(Integer id);

    /**
     * 通过id查询用户信息
     * @param id 用户id
     * @return 用户信息
     */
    User findById(Integer id);

    /**
     * 通过name查询用户
     * @param username 用户姓名
     * @return 用户列表
     */
    List<User> findByName(String username);

    /**
     * 查询所有用户数量
     */
    int findTotal();

    List<User> findByCondition(User user);

    List<User> findByList(QueryVo vo);
}
