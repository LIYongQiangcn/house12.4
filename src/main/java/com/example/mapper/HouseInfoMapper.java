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
    int insertHouse(HouseInfo houseInfo);

    /**
     * 根据不同条件搜索房源信息
     * @param city
     * @return
     */
    List<HouseInfo> selectHouseByCity(String city);
    List<HouseInfo> selectHouseByTitle(String title);
    List<HouseInfo> selectHouseByPrice(String price);

    List<HouseInfo> queryHouseByUid(Integer uid);
    List<HouseInfo> queryById();

    /**
     * 未审核房源的查询
     */
    List<HouseInfo> selectHouse();
    /**
     * 房源审核
     */
    int updateStatus(Integer hid);

    /**
     * 房源的删除
     */
    int deleteHouseByHid(Integer hid);
    /**
     * 房源的更新
     */
    int updateHouseByHid(HouseInfo houseInfo);

    List<HouseInfo> selectAllHouse();
}
