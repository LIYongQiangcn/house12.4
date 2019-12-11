package com.example.mapper;

import com.example.common.GetUids;
import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> queryByName(String name);
    List<User> queryBySex(String sex);
    List<User> queryByPhone(String phone);
    int insertUser(User user);
    User userlogin(String phone,String password);
    int deleteById(Integer uid);
    int deleteUsers(List<Integer> uids);
    int updateUserByUid(User user);
}
