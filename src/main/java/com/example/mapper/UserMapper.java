package com.example.mapper;

import com.example.common.GetUids;
import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> queryByName(String name);
    List<User> queryBySex(String sex);
    int insertUser(User user);
    User userlogin(String phone,String password);
    int deleteById(Integer uid);
    int deleteUsers(GetUids uids);
    int updateUserByUid(User user);
}
