package com.example.service;

import com.example.common.Md5Util;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    /**
     * 根据名字查询
     * @return
     */
    public List<User> query(String name) {
        return userMapper.queryByName(name);
    }

    /**
     * 根据性别查询
     * @return
     */
    public List<User> queryBySex(String sex) {
        return userMapper.queryBySex(sex);
    }

    /**
     * 根据电话查询
     * @return
     */
    public List<User> queryByPhone(String phone) {
        return userMapper.queryByPhone(phone);
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
     * 用户单个删除
     * @param uid
     * @return
     */
    public int delete(Integer uid){
        return userMapper.deleteById(uid);
    }

    /**
     * 用户信息修改
     * @param user
     * @return
     */
    public int update(User user){
        return userMapper.updateUserByUid(user);
    }

    /**
     * 修改头像
     * @param user
     * @return
     */
    public int updateHead(User user){
        return userMapper.updateUserPorByUid(user);
    }

    /**
     * 修改密码
     * @param user
     * @return
     */
    public int updatePassword(User user) {
        String pwd = Md5Util.md5(user.getPassword());
        user.setPassword(pwd);
        return userMapper.updatePassword(user);
    }

    /**
     * 用户登录
     * @param phone
     * @param password
     * @return
     */
    public User login(String phone,String password){
        User user = userMapper.userlogin(phone,password);
        return user;
    }

//    /**
//     * 根据用户id查询email
//     * @param uid
//     * @return
//     */
//    public User queryByUid(String Phone){
//        return  userMapper.queryEmailByUid(uid);
//    }

    /**
     * 校验手机号是否被注册
     * @param phone
     * @return
     */
    public User queryPhone(String phone){
        return userMapper.selectUserByPhone(phone);
    }

    /**
     * 根据id查询user
     * @param uid
     * @return
     */
    public User selectByUid(Integer uid){
        return userMapper.selectUserById(uid);
    }
}