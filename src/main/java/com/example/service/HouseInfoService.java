package com.example.service;

import com.example.entity.HouseInfo;
import com.example.mapper.HouseInfoMapper;
import org.springframework.stereotype.Service;

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
     * 首页，查询所有展示
     * @return
     */
    public   List<HouseInfo>  queryToWeb(){
        return houseInfoMapper.selectHouseToWeb();
    }


    /**
     * 新增
     * @param houseInfo
     * @return
     */
    public int addHouse(HouseInfo houseInfo){
        return houseInfoMapper.insertHouse(houseInfo);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int deleteByHid(Integer id){
        return houseInfoMapper.deleteHouseById(id);
    }

    /**
     * 房源更新
     * @param houseInfo
     * @return
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
     * 审核房源
     * @param id
     * @return
     */
    public int updateStatus(Integer id){
        return houseInfoMapper.updateStatusTrue(id);
    }

    /**
     * 查询未审核的房源信息
     * @return
     */
    public List<HouseInfo> queryHouse(){
        return houseInfoMapper.selectHouse();
    }

    /**
     * 主后台页面展示
     * @return
     */
    public List<HouseInfo> queryAllhouse(){
        return houseInfoMapper.selectAllHouse();
    }

    /**
     * 查询自己房源
     * @param phone
     * @return
     */
    public List<HouseInfo> queryMyself(String phone){
        return houseInfoMapper.selectMyself(phone);
    }

    /**
     * 根据id查询房源
     * @param id
     * @return
     */
    public HouseInfo queryById(Integer id){
        return houseInfoMapper.selectHouseByHid(id);
    }

}
