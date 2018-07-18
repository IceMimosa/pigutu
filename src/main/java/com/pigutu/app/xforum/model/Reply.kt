package com.pigutu.app.xforum.model

import com.google.gson.annotations.Expose
import com.pigutu.app.mapper.mybatis.BaseModel
import com.xforum.library.model.User
import javax.persistence.Transient

data class Reply(var status: Int,//状态，是否封禁等
                 var content: String,
                 var image_list: ArrayList<String>,
                 @Expose(serialize = true)
                 @Transient
                 var user: User,//用户
                 var user_id: Int,//用户
                 var publish_time: String,
                 var count_like: Int,
                 var count_reply: Int,
                 var floor_id: Int,
                 var is_starter: Int,//是否主帖，核心
                 var reply_user: User,//被回复者
                 var reply_user_id:Int,
                 var parent_id: String,//被回复者帖子id
                 var is_like: Int
) : BaseModel()