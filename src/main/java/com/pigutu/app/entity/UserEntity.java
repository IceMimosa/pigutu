package com.pigutu.app.entity;

import com.pigutu.app.mapper.mybatis.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by me675 on 2018/2/17.
 * 用户类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends BaseModel{
    private String icon;
    private String pwd;
    private String name;
    private int point;//积分
    private int vip;//积分
}
