package com.pigutu.app.entity;


/**
 * Created by me on 2016/6/24.
 * 分类
 */
public class CategoryEntity {
    private int id;
    private String path;
    private String title;
    private String parameter;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
