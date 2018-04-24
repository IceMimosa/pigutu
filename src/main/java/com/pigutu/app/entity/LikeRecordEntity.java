package com.pigutu.app.entity;

import com.pigutu.app.mapper.mybatis.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Desc:
 * Created by xuxinwei
 * Date: 2017/9/14
 * 喜欢类（可用作收藏）
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LikeRecordEntity extends BaseModel {
    private static final long serialVersionUID = 3796715409271014606L;

    private int allImagesId;
    private String ip;
    private String time;
    private String title;
    private String coverUrl;
}
