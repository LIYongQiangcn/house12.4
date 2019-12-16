package com.example.controller;

import com.example.entity.HouseInfo;
import com.example.entity.HousePhoto;
import com.example.service.HouseInfoService;
import com.example.service.HousePhotoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 添加房源
     * @param houseInfo
     * @return
     */
    @RequestMapping("/house/add")
    public int addHouse(HouseInfo houseInfo, HousePhoto housePhoto){
        try{
            houseInfoService.addHouse(houseInfo);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    /**
     * 查看所有房源
     */
    @RequestMapping("/house/query")
    public Map<String, Object> query(@RequestParam Integer page, @RequestParam Integer limit){
        //在查询之前pagehelper调用
        PageHelper.startPage(page, limit);

        List<HouseInfo> list = new ArrayList<>();
        list = houseInfoService.query();


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


}
