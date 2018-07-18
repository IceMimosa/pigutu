package com.xforum.library.model

import com.google.gson.annotations.Expose
import com.pigutu.app.mapper.mybatis.BaseModel

/**
 * 首先遍历is_start是1就抽取出来, 100万以下，给图片，以上给
 */
data class Comment(val status: Int,//状态，是否封禁等
                   val bar_id:Int,// 1美图 2美图吧 3斗图 4斗图吧
                   val content: String,
                   val image_list: ArrayList<String>,
                   @Expose(serialize = false)
                   val user: User,//用户
                   val user_id: Int,//用户
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
): BaseModel()