package com.example.entity;

/**
 * @author {李永强}
 * @Title HousePhoto
 * @Description
 * @date 2019/12/5
 */
public class HousePhoto {
    Integer pid;
    Integer phid;
    String photo;

    public Integer getPhid() {
        return phid;
    }

    public void setPhid(Integer phid) {
        this.phid = phid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
