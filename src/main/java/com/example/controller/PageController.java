package com.example.controller;

import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {

    @Autowired
    private HttpSession session;

    /**
     * 前台主页面
     */
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    /**
     * 后台主页面
     */
    @RequestMapping("/main")
    public String main(Model m){
        User user =  (User)session.getAttribute("loginUser");
        //给登录后的右上角的基本信息赋值
        session.setAttribute("name",user.getName());
        return "main";
    }



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
     * 注册页面
     */
    @RequestMapping("/register")
    public String register(){
        return "register";
    }


    /**
     * 用户列表页面
     */
    @RequestMapping("/user")
    public String user(){
        return "user";
    }
    @RequestMapping("/userdetail")
    public String userdetail(Model model){
        User user =  (User)session.getAttribute("loginUser");
        model.addAttribute("user",user);
        return "userdetail";
    }
    @RequestMapping("/user-edit")
    public String lookuser(){
        return "user-edit";
    }
    @RequestMapping("/headportrait")
    public String lookphoto(){
        return "headportrait";
    }


    /**
     * 房屋列表页面
     */
    @RequestMapping("/houseinfo")
    public String house(){
        return  "houseinfo";
    }
    @RequestMapping("/houseinfo-look")
    public String houselook(){
        return  "houseinfo-look";
    }
    @RequestMapping("/housephotos")
    public String housePhotos(){
        return  "housephotos";
    }
    @RequestMapping("/houseadd")
    public String addhouse(){
        return  "houseadd";
    }
    @RequestMapping("/houseforme")
    public String houseForMe(){
        return "houseforme";
    }

    /**
     * 未审核房源信息
     */
    @RequestMapping("/houseinfoVerify")
    public String houseVerify(){
        return "houseinfoVerify";
    }



}
