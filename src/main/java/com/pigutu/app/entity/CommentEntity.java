package com.pigutu.app.entity;

import com.pigutu.app.mapper.mybatis.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CommentEntity extends BaseModel {
    private int imageId;
    private String fromUser;
    private String toUser;
    private String time;
    private String content;
}
