package com.atguigu.springboot.controller;

import com.atguigu.springboot.bean.User;
import com.atguigu.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") Integer id){
        return userRepository.getOne(id);
    }

    @GetMapping("/user")
    public User saveUser(User user){
        return userRepository.save(user);
    }
}
