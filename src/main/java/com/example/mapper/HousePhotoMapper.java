package com.example.mapper;

import com.example.entity.HousePhoto;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author {李永强}
 * @Title HousePhotoMapper
 * @Description
 * @date 2019/12/6
 */
@Mapper
public interface HousePhotoMapper {
   int insertPhoto(HousePhoto housePhoto);
}
