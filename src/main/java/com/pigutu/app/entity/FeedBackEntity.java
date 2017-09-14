package com.pigutu.app.entity;

import com.pigutu.app.mapper.mybatis.BaseModel;

/**
 * 还有个自增的id参数，展示没什么用
 */
public class FeedBackEntity  extends BaseModel {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
