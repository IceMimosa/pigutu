package com.xforum.library.model

import com.pigutu.app.mapper.mybatis.BaseModel

data class User(var user_nickname: String,
                var head_pic: String,
                var is_follow: Int,
                var count_follow: Int,
                var count_fans: Int,
                var count_post: Int,
                var vip_status:Int,//0普通用户 1 vip 2svip 10管理员
                var level_point: Int,
                var level: String) : BaseModel()