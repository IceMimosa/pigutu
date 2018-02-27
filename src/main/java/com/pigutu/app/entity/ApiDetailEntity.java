package com.pigutu.app.entity;

import lombok.Data;

import java.util.List;

/**
 * Desc:
 * Created by xuxinwei
 * Date: 2018/2/27
 * 详情接口类
 */
@Data
public class ApiDetailEntity {
    private List<ImageSetEntity> details;
    private List<ImageSetListEntity> recommends;
    private List<LikeRecordEntity> likes;
}
