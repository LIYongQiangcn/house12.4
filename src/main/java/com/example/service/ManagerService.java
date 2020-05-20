package com.example.service;

import com.example.entity.Manager;
import com.example.mapper.ManagerMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author {李永强}
 * @Title ManagerMapper
 * @Description
 * @date 2020/3/20
 */
@Service
public class ManagerService {

    @Resource
    ManagerMapper managerMapper;

    /**
     * 管理员登录
     */
    public Manager login(String number , String mpassword ){
        Manager manager = managerMapper.managerlogin(number,mpassword);
        return manager;
    }

    /**
     * 会员密码修改
     */
    public int  update(Manager manager){
        return managerMapper.updatePassword(manager);
    }

}
