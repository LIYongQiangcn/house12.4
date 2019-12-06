package com.example.entity;

/**
 * @author {李永强}
 * @Title HouseInfo
 * @Description
 * @date 2019/12/5
 */
public class HouseInfo {
    Integer hid;
    Integer uid;
    String area;
    String roomnum;
    String floor;
    String orientation;
    Integer price;
    Integer status;
    Integer likecoount;

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(String roomnum) {
        this.roomnum = roomnum;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLikecoount() {
        return likecoount;
    }

    public void setLikecoount(Integer likecoount) {
        this.likecoount = likecoount;
    }
}
