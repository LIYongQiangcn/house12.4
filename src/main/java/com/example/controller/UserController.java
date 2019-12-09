package com.example.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.example.common.GetUids;
import com.example.entity.User;
import com.example.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpSession;
import java.util.*;

import static com.example.common.SmsDemo.sendSms;

@RestController
public class UserController {


    @Autowired
    UserService userService;

    /**
     * 用户查询：模糊查询+分页操作
     * @return
     */
    @RequestMapping("/user/query")
    public Map<String, Object> query(@RequestParam(required = false, defaultValue = "") String content,
                                     @RequestParam(required = false, defaultValue = "0") String type,
                                     @RequestParam Integer page, @RequestParam Integer limit) {
        //在查询之前pagehelper调用
        PageHelper.startPage(page, limit);
        List<User> list = new ArrayList<>();
        //判断，如果是0则是通过名字进行模糊查询
        if (type.equals("0")) {
            list = userService.query(content);
        }else{
            list = userService.queryBySex(content);
        }
        //对查询后的数据进行包装
        PageInfo pageInfo = new PageInfo(list);


        //数据赋值
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "操作成功");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;

    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @RequestMapping("/user/register")
    public int addUser(User user) {
        try {
            userService.addUser(user);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 用户删除
     */
    @RequestMapping("/user/delete")
    public int deleteUser(Integer uid) {
        try {
            userService.delete(uid);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 用户批量删除
     */
    @RequestMapping("/user/deleteUsers")
    public int deleteUsers(GetUids uids) {
        System.out.println(uids);
//        try {
//            for (int i=0;i<a.length;i++){
//                Integer uid = Integer.valueOf(a[i]);
//                userService.delete(uid);
//            }
//            return 1;
//        } catch (Exception e) {
//            return 0;
//        }
        try{
            userService.deleteNum(uids);
            return 1;
        }catch (Exception e){
            return 0;
        }


    }

    /**
     * 用户信息修改保存
     */
    @RequestMapping("/user/update")
    public int updateUser(User user) {
        try {
            userService.update(user);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 用户登录
     * , method = RequestMethod.POST,headers = "Accept=application/json"
     */
    @RequestMapping(value = "/user/login")
    public int userlogin(String phone, String password, String verify, HttpSession session) {
        System.out.println(phone);
        System.out.println(password);
        System.out.println(verify);
        ModelAndView mv = new ModelAndView();
        User user = userService.login(phone, password);
        //校验验证码
        String inputStr = verify;
        String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
        //当查询的用户不为空，验证码校验正确才能成功登录，否则返回登录页面
        if (user != null && random.equals(inputStr)) {
            return 1;
        } else if (random.equals(inputStr) == false) {
            //验证码输入错误
            System.out.println("验证码输入错误");
            return 2;
        } else if (user == null) {
            //用户名或密码错误
            return 3;
        } else {
            return 0;
        }
    }

    /**
     * 发送短信
     */
    @RequestMapping(value = "/SendMessage")
    public String send(String phone) throws ClientException {
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        System.out.println("手机验证码是" + checkCode);
        try {
            //短信发送
            SendSmsResponse response = sendSms(phone, checkCode);
        } catch (Exception e) {
            return "";
        }
        return checkCode;
    }

}
