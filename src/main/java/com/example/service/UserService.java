package com.example.service;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    /**
     * 全查询
     * @return
     */
    public List<User> query() {
        return userMapper.queryAll();
    }

    /**
     * 添加注册用户
     * @param user
     * @return
     */
    public int addUser(User user){
        return userMapper.insertUser(user);
    }

    /**
     * 用户登录
     */
    public User login(String phone,String password){
        User user = userMapper.userlogin(phone,password);
        return user;
    }
}