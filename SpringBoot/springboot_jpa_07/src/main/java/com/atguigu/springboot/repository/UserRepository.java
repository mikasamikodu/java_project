package com.atguigu.springboot.repository;

import com.atguigu.springboot.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
//通过继承JpaRepository使得这个repository有了基本的crud和分页功能
//它的泛型用来指定这个repository是为哪个实体类建的，并指定实体类主键类型
public interface UserRepository extends JpaRepository<User, Integer> {
}
