package com.xforum.library.model

import com.pigutu.app.mapper.mybatis.BaseModel
import javax.persistence.Transient

/**
 *
 */
data class Post(
        @Transient
        var user: User,
        var user_id: Int,
        var bar_id: Int,// 1美图 2美图吧 3斗图 4斗图吧
        var publish_time: String,//发布时间
        var status: Int, //帖子状态 0正常 1 封禁
        var count_read: Int,
        var count_like: Int,
        var count_reply: Int,
        var count_forward: Int,//转发数量
        var content: String,//内容
        @Transient
        var content_summary: String,//内容摘要
        var last_update_time: String, //最后更新时间
        var image_list: ArrayList<String>//显示的图片，最多9张，暂定
) : BaseModel()
