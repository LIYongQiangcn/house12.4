package com.example.service;

import com.example.entity.HouseInfo;
import com.example.mapper.HouseInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

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
    /**
     * 删除房源
     */
    public int deleteByHid(Integer hid){
        return houseInfoMapper.deleteHouseByHid(hid);
    }
    /**
     * 房源的更新
     */
    public int updateByHid(HouseInfo houseInfo){
        return houseInfoMapper.updateHouseByHid(houseInfo);
    }

    /**
     * 根据不同条件查询房源信息
     */
    public List<HouseInfo> queryByCity(String city){
        return houseInfoMapper.selectHouseByCity(city);
    }
    public List<HouseInfo> queryByTitle(String title){
        return houseInfoMapper.selectHouseByTitle(title);
    }
    public List<HouseInfo> queryByPrice(String price){
        return houseInfoMapper.selectHouseByPrice(price);
    }

    /**
     * 带有关联的查询
     * @return
     */
    public List<HouseInfo> query2(){
        return houseInfoMapper.queryById();
    }

    /**
     * 根据用户id查询房源
     */
    public List<HouseInfo> queryMyself(Integer uid){
        return  houseInfoMapper.queryHouseByUid(uid);
    }
    /**
     * 审核房源
     */
    public int updateStatus(Integer hid){
        return houseInfoMapper.updateStatus(hid);
    }


    /**
     * 查询未审核的房源信息
     */
    public List<HouseInfo> queryHouse(){
        return houseInfoMapper.selectHouse();
    }

    public List<HouseInfo> queryAllhouse(){
        return houseInfoMapper.selectAllHouse();
    }

}
