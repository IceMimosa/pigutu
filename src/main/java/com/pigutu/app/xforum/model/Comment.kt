package com.xforum.library.model

import com.google.gson.annotations.Expose
import com.pigutu.app.mapper.mybatis.BaseModel
import com.pigutu.app.xforum.model.Reply
import javax.persistence.Transient

/**
 * 首先遍历is_start是1就抽取出来, 100万以下，给图片，以上给
 */
data class Comment(var status: Int,//状态，是否封禁等
                   var content: String,
                   var image_list: ArrayList<String>,
                   @Transient
                   var comment_user: User,//用户
                   var comment_user_id: Int,//用户
                   var publish_time: String,
                   var count_like: Int,
                   var count_reply: Int,
                   var reply_list:ArrayList<Reply>,
                   var floor_id: Int,
                   var post_id: String,//被回复者帖子id
                   var is_like: Int
): BaseModel()