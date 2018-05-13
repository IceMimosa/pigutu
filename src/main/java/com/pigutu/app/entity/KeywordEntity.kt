package com.pigutu.app.entity

import com.pigutu.app.mapper.mybatis.BaseModel
import lombok.Data
import lombok.EqualsAndHashCode

/**
 * Desc:
 * Created by xuxinwei
 * Date: 2018/5/13
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class KeywordEntity : BaseModel() {
    var name:String?=null
    var count:Int?=null
}