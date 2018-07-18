package com.xforum.library.model

import com.pigutu.app.mapper.mybatis.BaseModel

data class User(val user_nickname: String,
                val head_pic: String,
                val is_follow: Int,
                val count_follow: Int,
                val count_fans: Int,
                val count_post: Int,
                val level_point: Int,
                val level: String) : BaseModel()