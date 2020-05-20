package com.example.mapper;

import com.example.entity.HouseInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author {李永强}
 * @Title HouseInfoMapper
 * @Description
 * @date 2019/12/6
 */
@Mapper
public interface HouseInfoMapper {

    /**
     * 添加房源信息
     * @param houseInfo
     * @return
     */
    int insertHouse(HouseInfo houseInfo);

    /**
     * 根据不同条件搜索房源信息
     * @param city
     * @return
     */
    List<HouseInfo> selectHouseByCity(String city);
    List<HouseInfo> selectHouseByTitle(String title);
    List<HouseInfo> selectHouseByPrice(String price);


    /**
     * 未审核房源的查询
     */
    List<HouseInfo> selectHouse();

    /**
     * 房源审核
     */
    int updateStatusTrue(Integer id);

    /**
     * 房源的删除
     */
    int deleteHouseById(Integer id);
    /**
     * 房源的更新
     */
    int updateHouseByHid(HouseInfo houseInfo);

    List<HouseInfo> selectAllHouse();

    /**
     * 查询所有给前端展示
     * @return
     */
    List<HouseInfo> selectHouseToWeb();

    /**
     * 查询自己的房源信息
     * @param phone
     * @return
     */
    List<HouseInfo> selectMyself(String phone);

    /**
     * 根据id查询房源
     * @param id
     * @return
     */
    HouseInfo selectHouseByHid(Integer id);
}
