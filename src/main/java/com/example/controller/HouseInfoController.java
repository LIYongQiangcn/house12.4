package com.example.controller;

import com.aliyuncs.exceptions.ClientException;
import com.example.common.MailService;
import com.example.entity.*;
import com.example.service.HouseInfoService;
import com.example.service.HousePhotoService;
import com.example.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



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
     * 首页：查询房源信息
     */
    @RequestMapping("/house/queryToWeb")
    public List<HouseInfo> queryToWeb(){
        List<HouseInfo> list =  houseInfoService.queryToWeb();
        return list;
    }

    /**
     * 查询自己的房源
     * @param page
     * @param limit
     * @param request
     * @return
     */
    @RequestMapping("/house/queryMyself")
    public Map<String, Object> queryMyself(@RequestParam Integer page, @RequestParam Integer limit,HttpServletRequest request) {
        //在查询之前pagehelper调用
        PageHelper.startPage(page, limit);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");
        List<HouseInfo> list = houseInfoService.queryMyself(user.getPhone());
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
     * 添加房源信息
     * @param
     * @return
     */
    @RequestMapping("/house/add")
    public int addHouse(HouseRequest houseRequest){
        String[] strings = houseRequest.getPictures().split(",");
        HouseInfo houseInfo = new HouseInfo();
        BeanUtils.copyProperties(houseRequest,houseInfo);
        houseInfo.setPicture(strings[0]);
        try {
            int result = houseInfoService.addHouse(houseInfo);
            if (result == 1) {
                //
                for (String img : strings) {
                    HousePhoto housePhoto = new HousePhoto();
                    housePhoto.setHid(houseInfo.getId());
                    housePhoto.setPhoto(img);
                    housePhotoService.addPhoto(housePhoto);
                }
            }
            return 1;
        }catch (Exception e) {
            return 0;
        }
    }

    /**
     * 删除房源信息
     */
    @RequestMapping("/house/del")
    public int delHouse(Integer id){
        try{
            houseInfoService.deleteByHid(id);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/user/delHouseInfos")
    public int deleteUsers(String[] ids) {
        int[] idList = new int[ids.length];
        //循环获取用户ids
        for(int i=0;i<ids.length;i++){
            idList[i] = Integer.parseInt(ids[i]);
        }
        try{
            //循环遍历一个一个的删除
            for(int j=0;j<ids.length;j++)
            {
               houseInfoService.deleteByHid(idList[j]);
            }
            return 1;
        }catch (Exception e){
            return  0;
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

    /**
     * 查看所有房源
     * @param page
     * @param limit
     * @return
     */
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
    public int updateStatus(String phone,Integer id){
        try{
            houseInfoService.updateStatus(id);
            User user = userService.queryPhone(phone);
            String email = user.getEmail();
            HouseInfo houseInfo = houseInfoService.queryById(id);
            //发送短信通知房主房源未通过
            String message = "你好，你的房源名为（"+houseInfo.getTitle()+"）的信息已经通过审核";
            mailService.sendSimpleMail(email,"YOUTI租房",message);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    /**
     * 审核房源:未通过，并发短信通知房主
     */
    @RequestMapping("/house/noVerify")
    public int houseNotVerify(String phone,Integer id)throws ClientException {
        try{
            User user = userService.queryPhone(phone);
            String email = user.getEmail();
            HouseInfo houseInfo = houseInfoService.queryById(id);
            //发送短信通知房主房源未通过
            String message = "你好，你的房源标题为（"+houseInfo.getTitle()+"）的信息没有通过审核，请查看并修改申请再次审核";
            mailService.sendSimpleMail(email,"YOUTI租房",message);
             return 1;
        }catch(Exception e){
            return 0;
        }
    }

}
