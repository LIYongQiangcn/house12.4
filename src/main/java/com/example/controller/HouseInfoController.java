package com.example.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.example.common.MailService;
import com.example.entity.HouseInfo;
import com.example.entity.HousePhoto;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.HouseInfoService;
import com.example.service.HousePhotoService;
import com.example.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.common.SmsDemo.sendSms;
import static com.example.common.SmsDemo.sendSms2;

/**
 * @author {李永强}
 * @Title HouseInfoController
 * @Description
 * @date 2019/12/6
 */
@RestController
public class HouseInfoController {

    @Autowired
    HouseInfoService houseInfoService;
    @Autowired
    HousePhotoService housePhotoService;
    @Autowired
    UserService userService;
    @Autowired
    private MailService mailService;

    /**
     * 添加房源信息
     * @param houseInfo
     * @return
     */
    @RequestMapping("/house/add")
    public int addHouse(HouseInfo houseInfo){
        try{
            houseInfoService.addHouse(houseInfo);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    /**
     * 删除房源信息
     */
    @RequestMapping("/house/del")
    public int delHouse(Integer hid){
        try{
            houseInfoService.deleteByHid(hid);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    /**
     * 更新房源信息
     */
    @RequestMapping("/house/update")
    public int updateHouse(HouseInfo houseInfo){
        try{
            houseInfoService.updateByHid(houseInfo);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    @RequestMapping("/house/queryAll")
    public Map<String, Object> queryHouse(@RequestParam Integer page, @RequestParam Integer limit){
        //在查询之前pagehelper调用
        PageHelper.startPage(page, limit);
        List<HouseInfo> list = houseInfoService.queryAllhouse();
        Map<String, Object> map = new HashMap<>();
        PageInfo pageInfo = new PageInfo(list);
        System.out.println(list);
        map.put("code", 0);
        map.put("msg", "操作成功");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }
    /**
     * 查看所有房源+模糊查询
     */
    @RequestMapping("/house/query")
    public Map<String, Object> query(@RequestParam(required = false, defaultValue = "") String content,
                                     @RequestParam(required = false, defaultValue = "0") String type,
                                     @RequestParam Integer page, @RequestParam Integer limit){
        //在查询之前pagehelper调用
        PageHelper.startPage(page, limit);

        List<HouseInfo> list = new ArrayList<>();
        if (type.equals("0")) {
            list = houseInfoService.queryByCity(content);
        }else if (type.equals("1")){
            list = houseInfoService.queryByTitle(content);
        }else {
            list = houseInfoService.queryByPrice(content);
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
     * 查看用户自己的房源
     */
    @RequestMapping("/house/queryMyself")
    public Map<String, Object> queryMyself(@RequestParam Integer page, @RequestParam Integer limit, HttpSession session){
        //在查询之前pagehelper调用
        PageHelper.startPage(page, limit);
        List<HouseInfo> list = new ArrayList<>();
        list = houseInfoService.queryMyself((Integer) session.getAttribute("userid"));
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

    @RequestMapping("/house/query2")
    public List<HouseInfo> query2(){
        List<HouseInfo> list = houseInfoService.query2();
        return list;
    }


    /**
     * 查询未审核的房源信息
     */
    @RequestMapping("/house/queryStatus")
    public Map<String, Object> queryStatus(@RequestParam Integer page, @RequestParam Integer limit){
        //在查询之前pagehelper调用
        PageHelper.startPage(page, limit);
        List<HouseInfo> list = new ArrayList<>();
        list = houseInfoService.queryHouse();
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
     * 审核房源:通过
     */
    @RequestMapping("/house/verify")
    public int updateStatus(Integer hid){
        try{
            houseInfoService.updateStatus(hid);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }


    /**
     * 审核房源:未通过，并发短信通知房主
     */
    @RequestMapping("/house/noVerify")
    public int houseNotVerify(Integer uid,Integer hid)throws ClientException {
        try{
            User user = userService.queryByUid(uid);
            System.out.println(hid);
//            houseInfoService.updateStatus();
            String email = user.getEmail();
            //发送短信通知房主房源未通过
            String message = "你好，你的房源没有通过审核，请查看";
            mailService.sendSimpleMail(email,"YOUTI租房",message);
             return 1;
        }catch(Exception e){
            return 0;
        }
    }


}
