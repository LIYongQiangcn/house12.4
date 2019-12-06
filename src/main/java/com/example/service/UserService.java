package com.example.service;

import com.example.common.Md5Util;
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
    public List<User> query(String name) {
        return userMapper.queryAllByName(name);
    }

    /**
     * 添加注册用户
     * @param user
     * @return
     */
    public int addUser(User user){
        //密码加密
        String pwd = Md5Util.md5(user.getPassword());
        user.setPassword(pwd);
        return userMapper.insertUser(user);
    }

    /**
     * 用户的删除
     */
    public int delete(Integer uid){
        return userMapper.deleteById(uid);
    }

    /**
     * 用户信息修改
     */
    public int update(User user){
        return userMapper.updateUserByUid(user);
    }

    /**
     * 用户登录
     */
    public User login(String phone,String password){
        User user = userMapper.userlogin(phone,password);
        return user;
    }
}