package com.example.mapper;

import com.example.entity.Manager;
import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author {李永强}
 * @Title ManagerMapper
 * @Description
 * @date 2020/3/20
 */
@Mapper
public interface ManagerMapper {

    /**
     * 会员登录
     * @param munber
     * @param mpassword
     * @return
     */
    Manager managerlogin(String munber, String mpassword);

    /**
     * 修改密码
     */
    int updatePassword(Manager manager);
}
