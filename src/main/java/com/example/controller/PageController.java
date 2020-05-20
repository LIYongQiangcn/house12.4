package com.example.controller;

import com.example.entity.HouseInfo;
import com.example.entity.Manager;
import com.example.entity.User;
import com.example.service.HouseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    private HttpSession session;
    @Autowired
    HouseInfoService houseInfoService;

    /**
     * 前台主页面
     */
    @RequestMapping("/index")
    public String index(Model model){
        List<HouseInfo> list =  houseInfoService.queryToWeb();
        model.addAttribute("houseList",list);
        return "index";
    }



    /**
     * 注册页面
     */
    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    /**
     * 用户登录
     * @return
     */
    @RequestMapping("userlogin")
    public String userlogin(){
        return "userlogin";
    }

    /**
     * 用户后台中心
     * @param request
     * @return
     */
    @RequestMapping("usermain")
    public String usermain(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");
        if (user!=null){
            return "usermain";
        }
        return "error";
    }

    /**
     * 用户后台中心：用户信息详情列表
     * @param model
     * @return
     */
    @RequestMapping("/userdetail")
    public String userdetail(Model model, HttpSession session){
        User user =  (User)session.getAttribute("loginUser");
        if (user.getHeadportrait().equals("0")) {
            user.setHeadportrait("96929a32-72ad-476a-92e3-42baf867531d.jpg");
        }
        model.addAttribute("user",user);
        session.setAttribute("name",user.getName());
        return "userdetail";
    }

    /**
     * 用户后台中心：用户编辑
     * @return
     */
    @RequestMapping("/user-edit")
    public String lookuser(){
        return "user-edit";
    }

    /**
     * 用户后台中心: 修改头像
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/headportrait2")
    public String lookphoto2(Model model, HttpSession session){
        User user =  (User)session.getAttribute("loginUser");
        model.addAttribute("user",user);
        return "headportrait2";
    }

    /**
     * 用户后台中心: 用户修改密码
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/userpassword")
    public String userPassword(Model model, HttpSession session) {
        User user =  (User)session.getAttribute("loginUser");
        model.addAttribute("user",user);
        return "userpassword";
    }

    /**
     * 用户后台中心: 所有房源信息
     * @return
     */
    @RequestMapping("/houseinfo2")
    public String house2(){
        return  "houseinfo2";
    }

    /**
     * 用户后台中心：查看自己的房源信息
     * @return
     */
    @RequestMapping("/houseForMyself")
    public String houseForMyself(){
        return  "houseForMyself";
    }





    /**
     * 管理员后台主页面
     * @param m
     * @return
     */
    @RequestMapping("/main")
    public String main(Model m){
        return "main";
    }

    /**
     * 管理员登录
     * @return
     */
    @RequestMapping("/managerLogin")
    public String managerLogin(){
        return "managerlogin";
    }

    /**
     * 管理员信息:修改密码
     * @param model
     * @return
     */
    @RequestMapping("/managerdetail")
    public String manager(Model model){
        Manager manager =  (Manager) session.getAttribute("loginManager");
        model.addAttribute("manager",manager);
        return "managerdetail";
    }

    /**
     * 用户信息列表
     * @return
     */
    @RequestMapping("/user")
    public String user(){
        return "user";
    }

    /**
     * 用户头像
     * @return
     */
    @RequestMapping("/headportrait")
    public String lookphoto(){
        return "headportrait";
    }

    /**
     * 房屋列表页面
     * @return
     */
    @RequestMapping("/houseinfo")
    public String house(){
        return  "houseinfo";
    }

    /**
     * 待审核房源
     * @return
     */
    @RequestMapping("/houseinfoVerify")
    public String houseVerify(){
        return "houseinfoVerify";
    }

    /**
     * 查看房源详情
     * @return
     */
    @RequestMapping("/houseinfo-look")
    public String houselook(){
        return  "houseinfo-look";
    }

    /**
     * 添加房源
     * @return
     */
    @RequestMapping("/houseadd")
    public String addhouse(){
        return  "houseadd";
    }


}
