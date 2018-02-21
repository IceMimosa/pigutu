package com.pigutu.app.entity;

import com.pigutu.app.mapper.mybatis.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by me675 on 2018/2/17.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends BaseModel{
    private String userIcon;
    private String passWord;
    private String name;
}
