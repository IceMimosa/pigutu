package com.xforum.library.model

/**
 *
 */
data class PostDetail (val post_id: String,
                  val title: String,
                  val user:User,
                  val publish_time: String,//发布时间
                  val status: Int, //帖子状态 0正常 1 封禁
                  val count_read: Int,
                  val count_like: Int,
                  val count_reply: Int,
                  val count_forward: Int,//转发数量
                  val vip_status: Int,//0普通用户 1 vip 2可能svip
                  val content: String,//内容
                  val last_update_time: String, //最后更新时间
                  val image_list: ArrayList<String>//显示的图片，最多9张，暂定
)