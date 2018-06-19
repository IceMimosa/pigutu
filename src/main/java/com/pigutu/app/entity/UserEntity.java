package com.pigutu.app.entity;

import com.pigutu.app.mapper.mybatis.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Created by me675 on 2018/2/17.
 * 用户类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends BaseModel{
    public String icon;
    public String pwd;
    public String name;
    public int point;//积分
    public int vip;//积分
    public String permission;
    public String role;
}
