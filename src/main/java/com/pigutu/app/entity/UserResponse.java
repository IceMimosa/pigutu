package com.pigutu.app.entity;

import lombok.Data;
//用户登陆，注册返回类
@Data
public class UserResponse {
    public String name;
    public String icon;
    public String token;
    public long userId;
}
