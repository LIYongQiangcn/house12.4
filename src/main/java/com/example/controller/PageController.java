package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    /**
     * 用户相关页面
     * @return
     */
    @RequestMapping("/login")
    public String userlogin(){
        return "userlogin";
    }
    @RequestMapping("/loginsuccess")
    public String loginsuccess(){
        return "loginsuccess";
    }
    @RequestMapping("/loginfail")
    public String loginfail(){
        return "loginfail";
    }


    /**
     * 主页面
     */
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    /**
     * 注册页面
     */
    @RequestMapping("/register")
    public String register(){
        return "register";
    }
}
