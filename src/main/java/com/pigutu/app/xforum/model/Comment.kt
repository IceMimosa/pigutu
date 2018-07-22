package com.pigutu.app.xforum.model

import com.pigutu.app.mapper.mybatis.BaseModel
import com.pigutu.app.xforum.model.Reply
import com.pigutu.app.xforum.model.User
import javax.persistence.Column
import javax.persistence.Transient

/**
 *
 */
data class Comment(var status: Int,//状态，是否封禁等
                   var content: String,
                   var image_list: ArrayList<String>,
                   var user: User,//用户
                   var publish_time: String,
                   var count_like: Int,
                   var count_reply: Int,
                   @Transient
                   var reply_list:ArrayList<Reply>,
                   var post_id: String//被回复者帖子id
): BaseModel()