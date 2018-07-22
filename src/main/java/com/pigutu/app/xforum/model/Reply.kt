package com.pigutu.app.xforum.model

import com.google.gson.annotations.Expose
import com.pigutu.app.mapper.mybatis.BaseModel
import javax.persistence.Column
import javax.persistence.Transient

data class Reply(var status: Int=0,//状态，是否封禁等
                 var content: String="",
                 var image_list: ArrayList<String> = ArrayList(),
                 var user: User,//用户
                 var publish_time: String,
                 var count_like: Int,
                 var reply_user: User,//被回复者
                 var comment_id: String//被回复者comment_id
) : BaseModel()