package com.xforum.library.model

data class User(val user_id: String, val user_nickname: String, val head_pic: String, val is_follow: Int, val count_follow: Int, val count_fans: Int, val count_post: Int, val level_point: Int, val level: String)