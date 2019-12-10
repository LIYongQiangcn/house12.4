package com.example.service;

import com.example.common.GetUids;
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
     */
    public int delete(Integer uid){
        return userMapper.deleteById(uid);
    }

    /**
     * 用户批量删除
     */
    public int deleteNum(List<Integer> uids){
//        int sum=0;
//        for(Integer uid : uids){
//            int result = delete(uid);
//            sum += result;
//        }
//        if(sum==uids.length){
//            return 1;
//        }else {
//            return 0;
//        }
          return userMapper.deleteUsers(uids);

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