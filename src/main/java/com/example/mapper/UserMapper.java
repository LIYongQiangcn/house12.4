package com.example.mapper;

import com.example.entity.Manager;
import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 根据条件查询
     * @param name
     * @return
     */
    List<User> queryByName(String name);
    List<User> queryBySex(String sex);
    List<User> queryByPhone(String phone);

    /**
     * 根据uid查询电话号码
     * @param uid
     * @return
     */
    User queryEmailByUid(Integer uid);

    /**
     * 添加用户
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 用户登录
     * @param phone
     * @param password
     * @return
     */
    User userlogin(String phone,String password);

    /**
     * 用户删除
     * @param uid
     * @return
     */
    int deleteById(Integer uid);

    /**
     * 根据id更新信息
     * @param user
     * @return
     */
    int updateUserByUid(User user);

    /**
     * 修改头像
     * @param user
     * @return
     */
    int updateUserPorByUid(User user);

    /**
     * 判断手机号是否已经被注册
     * @param phone
     * @return
     */
    User selectUserByPhone(String phone);

    /**
     * 修改密码
     * @param user
     * @return
     */
    int updatePassword(User user);

    /**
     * 根据id查询user
     * @param uid
     * @return
     */
    User selectUserById(Integer uid);


}
