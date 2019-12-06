package com.example.service;

import com.example.entity.HouseInfo;
import com.example.mapper.HouseInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author {李永强}
 * @Title HouseInfoService
 * @Description
 * @date 2019/12/6
 */

@Service
public class HouseInfoService {

    @Resource
    HouseInfoMapper houseInfoMapper;

    /**
     * 添加房源
     */
    public int addHouse(HouseInfo houseInfo){
        return houseInfoMapper.insertHouse(houseInfo);
    }
}
