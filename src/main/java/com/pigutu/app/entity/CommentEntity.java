package com.pigutu.app.entity;

import com.pigutu.app.mapper.mybatis.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = true)
public class CommentEntity extends BaseModel {
    private int imageId;
   // private int orderId; 需要增加排名，每次返回十条
    private String fromUser;
    private String toUser;
    private Timestamp time;
    private String content;
}
