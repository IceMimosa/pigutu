package com.xforum.library.model

/**
 * 首先遍历is_start是1就抽取出来, 100万以下，给图片，以上给
 */
data class Comment(val comment_id: String,
                   val status: Int,//状态，是否封禁等
                   val content: String,
                   val image_list: ArrayList<String>,
                   val user: User,//用户
                   val publish_time: String,
                   val count_like: Int,
                   val count_reply: Int,
                   val floor_id: Int,
                   val is_starter: Int,//是否主帖，核心
                   val parent: User,//被回复者
                   val parent_id: String,//被回复者帖子id
                   val is_like: Int,
                   val is_official: Int,//是否官方
                   val is_top: Int//是否置顶
)