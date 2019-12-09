package com.example.common;

import java.io.Serializable;
import java.util.List;

/**
 * @author {李永强}
 * @Title GetIds
 * @Description
 * @date 2019/12/9
 */
public class GetUids<T> implements Serializable {

    private List<T> uids;

    public List<T> getUids() {
        return uids;
    }

    public void setUids(List<T> uids) {
        this.uids = uids;
    }
}
