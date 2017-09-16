package com.pigutu.app.entity;

import com.pigutu.app.mapper.mybatis.BaseModel;

import java.io.Serializable;

/**
 * Desc:
 * Created by xuxinwei
 * Date: 2017/9/14
 */
public class LikeRecordEntity extends BaseModel implements Serializable {
    private int id;
    private int allImagesId;
    private String ip;
    private String time;
    private String title;
    private String coverUrl;

    public int getAllImagesId() {
        return allImagesId;
    }

    public void setAllImagesId(int allImagesId) {
        this.allImagesId = allImagesId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}
