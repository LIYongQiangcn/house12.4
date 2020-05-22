package com.example.service;

import com.example.entity.HousePhoto;
import com.example.mapper.HousePhotoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author {李永强}
 * @Title HousePhotoService
 * @Description
 * @date 2019/12/6
 */
@Service
public class HousePhotoService {

    @Resource
    HousePhotoMapper housePhotoMapper;

    public int addPhoto(HousePhoto housePhoto){
        return  housePhotoMapper.insertPhoto(housePhoto);
    }

    public List<HousePhoto> queryImg(Integer hid){
        return housePhotoMapper.selectImg(hid);
    }
}
