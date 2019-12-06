package com.example.mapper;

import com.example.entity.HouseInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author {李永强}
 * @Title HouseInfoMapper
 * @Description
 * @date 2019/12/6
 */
@Mapper
public interface HouseInfoMapper {
    int insertHouse(HouseInfo houseInfo);
}
