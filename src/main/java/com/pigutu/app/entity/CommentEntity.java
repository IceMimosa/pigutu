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
    public int imageId;
    // private int orderId; 需要增加排名，每次返回十条
    public Long fromUser;
    public Long toUser;
    @Transient
    public String fromUserString;
    @Transient
    public String toUserString;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    public Date time;
    public String content;
    public int likeCount;
    @Transient
    public String icon;
}
