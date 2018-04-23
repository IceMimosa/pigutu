package com.pigutu.app.entity;

import lombok.Data;
//用户登陆，注册返回类
@Data
public class UserResponse {
    private String name;
    private String icon;
    private String token;
    private String userId;
}
