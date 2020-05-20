package com.example.controller;

import com.example.entity.Manager;
import com.example.entity.User;
import com.example.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @author {李永强}
 * @Title ManagerController
 * @Description
 * @date 2020/3/20
 */
@RestController
public class ManagerController {

    @Autowired
    ManagerService managerService;

    /**
     * 管理员登录
     * method = RequestMethod.POST,headers = "Accept=application/json"
     */
    @RequestMapping(value = "/manager/login")
    public int userLogin(String number, String mpassword, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        Manager manager = managerService.login(number, mpassword);

        if (manager != null ){
            session.setAttribute("loginManager",manager);
            session.setAttribute("Managerid",manager.getMid());
            session.setAttribute("message",200);
            return 1;
        }else if (manager == null) {
            //用户名或密码错误
            return 3;
        } else {
            session.setAttribute("message",500);
            return 0;
        }
    }

    /**
     * 修改密码
     * @param manager
     * @return
     */
    @RequestMapping(value = "/manager/updatePassword")
    public int update(Manager manager){
        try {
            System.out.println("会员id是："+manager.getMid()+"  新密码是：："+manager.getMpassword());
            System.out.println();
            managerService.update(manager);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
}
