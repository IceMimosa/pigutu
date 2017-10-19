package com.pigutu.app.entity;


import com.pigutu.app.mapper.mybatis.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by me on 2016/6/24.
 * 分类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CategoryEntity extends BaseModel {
    private static final long serialVersionUID = -2295402283392233693L;

    private String path;
    private String title;
    private String parameter;

}
