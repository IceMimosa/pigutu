package com.pigutu.app.entity;


import com.pigutu.app.mapper.mybatis.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by me on 2016/6/24.
 * 图集
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ImageSetEntity extends BaseModel {
    private static final long serialVersionUID = -7642258145019383569L;

    private int allImagesId;
    private String url;
}
