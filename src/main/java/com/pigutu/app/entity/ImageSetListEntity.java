package com.pigutu.app.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.pigutu.app.mapper.mybatis.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Transient;
import java.sql.Date;

/**
 * Created by me on 2016/6/24.
 * 所有图集信息
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ImageSetListEntity extends BaseModel {
    private static final long serialVersionUID = -5015945832815071384L;

    public String title;
    public String coverUrl;
    public int likeCount;
    public int imgCount;
    public int commentCount;
    public String category;
    public String label;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    public Date createTime;
    public int viewCount;
    public int recommendCount;
    public int hide;
    @Transient
    public int isLike=0;
}
