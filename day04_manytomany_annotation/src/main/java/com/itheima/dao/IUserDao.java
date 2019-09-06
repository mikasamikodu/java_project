package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
@CacheNamespace(blocking = true)
public interface IUserDao {
    /**
     * 查询所有用户
     *
     */
    @Select("select * from user")
    @Results(id="userMap",value={
            @Result(id=true,column="id",property = "id"),
            @Result(column="username",property = "username"),
            @Result(column="address",property = "address"),
            @Result(column="sex",property = "sex"),
            @Result(column="birthday",property = "birthday"),
            @Result(property = "accounts",column = "id", many = @Many(select = "com.itheima.dao.IAccountDao.findByUid",fetchType = FetchType.LAZY))
    })
    List<User> findAll();

    /**
     * 通过id查询用户信息
     * @param id 用户id
     * @return 用户信息
     */
    @Select("select * from user where id = #{id}")
    User findById(Integer id);
}
