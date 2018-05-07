package com.pigutu.app.entity;

import com.pigutu.app.mapper.mybatis.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 2.0.1
 * 最后一位不提示
 * 倒数第二位提示
 * 第三位强制更新
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UpgradeEntity extends BaseModel {
    private String versionName;//2.0.1
    private String info;
    private String apkUrl;
}
