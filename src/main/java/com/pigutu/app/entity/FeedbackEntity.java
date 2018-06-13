package com.pigutu.app.entity;

import com.pigutu.app.mapper.mybatis.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 还有个自增的id参数，展示没什么用
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FeedbackEntity extends BaseModel {
    private static final long serialVersionUID = -3582883097958858604L;
    public String userId;
    public String contact;
    public String content;
}
