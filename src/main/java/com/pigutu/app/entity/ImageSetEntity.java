package com.pigutu.app.entity;


import com.pigutu.app.mapper.mybatis.BaseModel;

/**
 * Created by me on 2016/6/24.
 * 图集
 */
public class ImageSetEntity extends BaseModel {
    private int allImagesId;
    private String url;

    public int getAllImagesId() {
        return allImagesId;
    }

    public void setAllImagesId(int allImagesId) {
        this.allImagesId = allImagesId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
