package com.pigutu.app.entity;


import com.pigutu.app.mapper.mybatis.BaseModel;

/**
 * Created by me on 2016/6/24.
 * 分类
 */
public class CategoryEntity extends BaseModel {
    private String path;
    private String title;
    private String parameter;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "path='" + path + '\'' +
                ", title='" + title + '\'' +
                ", parameter='" + parameter + '\'' +
                '}';
    }
}
