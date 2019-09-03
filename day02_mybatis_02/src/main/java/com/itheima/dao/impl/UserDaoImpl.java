package com.itheima.dao.impl;

import com.itheima.dao.IUserDao;
import com.itheima.domain.QueryVo;
import com.itheima.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements IUserDao {
    private SqlSession session;

    public UserDaoImpl(SqlSession session){
        this.session = session;
    }
    public List<User> findAll() {
        List<User> users = session.selectList("com.itheima.dao.IUserDao.findAll");
        return users;
    }

    public void save(User user) {
        session.insert("com.itheima.dao.IUserDao.save", user);
    }

    public void update(User user) {
        session.update("com.itheima.dao.IUserDao.update", user);
    }

    public void delete(Integer id) {
        session.delete("com.itheima.dao.IUserDao.delete", id);
    }

    public User findById(Integer id) {
        User user = session.selectOne("com.itheima.dao.IUserDao.findById", id);
        return user;
    }

    public List<User> findByName(String username) {
        List<User> users = session.selectList("com.itheima.dao.IUserDao.findByName", username);
        return users;
    }

    public int findTotal() {
        Integer count = session.selectOne("com.itheima.dao.IUserDao.findTotal");
        return count;
    }

    public List<User> findByCondition(User user) {
        List<User> users = session.selectList("com.itheima.dao.IUserDao.findByCondition", user);
        return users;
    }

    public List<User> findByList(QueryVo vo) {
        List<User> users = session.selectList("com.itheima.dao.IUserDao.findByList", vo);
        return users;
    }
}
