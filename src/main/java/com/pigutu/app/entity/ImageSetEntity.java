package com.pigutu.app.entity;


/**
 * Created by me on 2016/6/24.
 * 图集
 */
public class ImageSetEntity{
    private int id;
    private int all_images_id;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAll_images_id() {
        return all_images_id;
    }

    public void setAll_images_id(int all_images_id) {
        this.all_images_id = all_images_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
