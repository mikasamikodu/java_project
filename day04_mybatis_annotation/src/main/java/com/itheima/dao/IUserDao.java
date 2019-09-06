package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {
    /**
     * 查询所有用户
     * @Select @Insert @Update @Delete
     */
    @Select("select * from user")
    @Results(id="userMap",value={
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "username",property = "userName"),
            @Result(id=true,column = "sex",property = "sex"),
            @Result(id=true,column = "address",property = "address"),
            @Result(id=true,column = "birthday",property = "birthday"),
    })
    List<User> findAll();
    @Select("select * from user where id=#{id}")
    @ResultMap(value={"userMap"})
    User findById(Integer id);
    @Select("select * from user where username like #{username}")
    List<User> findByName(String username);
    @Select("select count(*) from user")
    Integer findCount();
    @Delete("delete  from user where id=#{id}")
    void delete(Integer id);
    @Insert("insert into user (username,address,sex,birthday) values(#{username},#{address},#{sex},#{birthday})")
    void save(User user);
    @Update("update user set username=#{username},address=#{address},sex=#{sex},birthday=#{birthday} where id=#{id}")
    void update(User user);


}
