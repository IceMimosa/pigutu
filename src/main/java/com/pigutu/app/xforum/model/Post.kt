package com.pigutu.app.xforum.model

import com.pigutu.app.mapper.mybatis.BaseModel
import java.sql.Date
import javax.persistence.Column
import javax.persistence.Transient

/**
 *
 */
data class Post(
        @Column(name = "user_id")
        var user: User = User(),
        var bar_id: Int = 0, // 1美图 2美图吧 3斗图 4斗图吧
        var publish_time: Date = Date(System.currentTimeMillis()), //发布时间
        var status: Int = 0, //帖子状态 0正常 1 封禁
        var count_read: Int = 0,
        var count_like: Int = 0,
        var count_reply: Int = 0,
        var count_forward: Int = 0, //转发数量
        var content: String = "", //内容zhe
        var content_summary: String = "", //内容摘要
        var last_update_time: Date = Date(System.currentTimeMillis()), //最后更新时间
        var image_list: ArrayList<String> = ArrayList()//显示的图片，最多9张，暂定
) : BaseModel()
