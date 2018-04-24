package com.pigutu.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pigutu.app.mapper.mybatis.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * Desc:
 * Created by xuxinwei
 * Date: 2018/4/24
 * 收藏类
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class CollectEntity extends BaseModel{
    private String userId;
    private String imageId;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp time;
}
