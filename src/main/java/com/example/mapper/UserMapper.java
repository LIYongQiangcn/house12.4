package com.example.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> queryAllByName(String name);
    int insertUser(User user);
    User userlogin(String phone,String password);
    int deleteById(Integer uid);
    int updateUserByUid(User user);
}
