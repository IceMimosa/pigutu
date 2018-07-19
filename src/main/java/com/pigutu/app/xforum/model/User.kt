package com.pigutu.app.xforum.model

import com.pigutu.app.mapper.mybatis.BaseModel

data class User(var user_nickname: String = "",
                var head_pic: String = "",
                var is_follow: Int = 0,
                var count_follow: Int = 0,
                var count_fans: Int = 0,
                var count_post: Int = 0,
                var vip_status: Int = 0, //0普通用户 1 vip 2svip 10管理员
                var level_point: Int = 0,
                var level: String = "") : BaseModel()