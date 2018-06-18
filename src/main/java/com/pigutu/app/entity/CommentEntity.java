package com.pigutu.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pigutu.app.mapper.mybatis.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Transient;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = true)
public class CommentEntity extends BaseModel {
    private int imageId;
   // private int orderId; 需要增加排名，每次返回十条
    private int fromUser;
    private int toUser;
    @Transient
    private String fromUserString;
    @Transient
    private String toUserString;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;
    private String content;
    private int likeCount;
    @Transient
    private String icon;
}
