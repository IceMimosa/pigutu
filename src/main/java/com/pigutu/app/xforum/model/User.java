package com.pigutu.app.xforum.model;

import com.pigutu.app.mapper.mybatis.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Transient;

@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseModel {
    public String user_nickname = "";
    public String password = "";
    public String head_pic = "";
    @Transient
    public int is_follow = 0;
    public int count_follow = 0;
    public int count_fans = 0;
    public int count_post = 0;
    public int vip_status = 0;//0普通户 1 vip 2svip 10管理员
    public int level_point = 0;
    @Transient
    public String level = "";
}