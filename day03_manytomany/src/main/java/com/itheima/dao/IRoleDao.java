package com.itheima.dao;

import com.itheima.domain.Role;
import com.itheima.domain.User;

import java.util.List;

public interface IRoleDao {
    /**
     * 查询所有角色
     *
     */
    List<Role> findAll();

}
