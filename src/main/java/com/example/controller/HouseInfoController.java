package com.example.controller;

import com.example.entity.HouseInfo;
import com.example.entity.HousePhoto;
import com.example.service.HouseInfoService;
import com.example.service.HousePhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping("addHouse")
    public int addHouse(HouseInfo houseInfo, HousePhoto housePhoto){
        try{
            houseInfoService.addHouse(houseInfo);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

}
